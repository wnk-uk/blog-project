<template>
   <nav class="navbar navbar-expand-lg navbar-dark bg-dar mb-4" style="background-color: white !important; border-bottom: 1px solid lightgray;">
    <div class="container">
        <a class="navbar-brand" href="/" style="color:black; font-weight:bold;">Uk's Blog</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" aria-current="page" href="#">search</a></li>
                <li class="nav-item">
                    <div v-if="name" class="dropdown"> <!-- v-if -->
                        <button class="btn btn-secondary dropdown-toggle profile" 
                                type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false"
                                :style="{ backgroundImage : 'url(' + image + ')' }" >
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="http://localhost:8081/logout">Logout</a></li>
                        </ul>
                    </div>
                    <div v-if="!name">
                        <a href="http://localhost:8081/oauth2/authorization/google" class="btn" role="button">로그인</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
</template>

<script>
    export default {
        props: ['name', 'email', 'image'],
        methods: {

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
</style>