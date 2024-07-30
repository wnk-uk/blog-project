<template>
   <nav class="navbar navbar-expand-lg navbar-dark bg-dar mb-4" style="background-color: white !important; border-bottom: 1px solid lightgray;">
    <div class="container">
        <a class="navbar-brand" href="/" style="color:black; font-weight:bold;">Uk's Blog</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item fs-25"><font-awesome-icon :icon="['fas', 'magnifying-glass']" /></li>
                <li class="nav-item fs-25" v-if="state.account && state.account.role === 'ADMIN'" @click="goWriteView"><font-awesome-icon :icon="['fas', 'pen']" /></li>
                <li class="nav-item">
                    <div v-if="state.account" class="dropdown"> <!-- v-if -->
                        <button class="btn btn-secondary dropdown-toggle profile" 
                                type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false"
                                :style="{ backgroundImage : 'url(' + state.account.picture + ')' }" >
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item" @click="goTemp">임시저장</a></li>
                            <li><a class="dropdown-item" @click="logout">Logout</a></li>
                        </ul>
                    </div>
                    <div v-if="!state.account">
                        <a href="http://localhost:8081/oauth2/authorization/google" class="btn" role="button">로그인</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
</template>

<script>
    import { onMounted, reactive, watch } from 'vue';
    import { useStore } from 'vuex';
    import AuthService from '../services/AuthService';
    import router from '../router';

    export default {
        setup() {
            
            const store = useStore();
            const state = reactive({
                account: null,
                error: null
            });

            onMounted(async () => {
                try {
                    state.account = store.state.account;
                } catch (error) {
                    state.error = error.message;
                }
            });

            watch(() => store.state.account, (newValue) => {
                state.account = newValue;
            });

            const logout = () => {
                AuthService.removeToken();
                window.location.href = "http://localhost:8081/logout";
            }

            const goTemp = () => {
                router.push({path: '/posts/temp'});
            }

            const goWriteView = () => {
                router.push({path: '/posts/write'});
            }

            return { state, logout, goWriteView, goTemp }
        }
        

    }
</script>

<style scoped>
    .profile {
        background-repeat:no-repeat; 
        background-size:cover; 
        border-radius: 50px;
        border:white !important;

    }
    .dropdown-toggle:empty::after {
        margin-left: .255em;
        visibility : hidden;
    }

    .nav-item {
        display: flex;
        align-items: center;
        margin-left:15px;
    }
    .fs-25 {
        font-size: 25px;
    }
</style>