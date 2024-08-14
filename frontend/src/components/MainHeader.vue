<template>
    <header class="py-5">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3"></div>
                    <div class="profile-wrapper col-lg-9">
                        <div class="profile-img">
                        </div>
                        <div>
                            <div>
                                #UK's
                            </div>
                            <div>발전을 위한 공간으로 사용하겠습니다.</div>
                            <div>
                                <a href="https://github.com/wnk-uk" target="_black" class="header-link">
                                    <font-awesome-icon class="header-icon" :icon="['fab', 'github']" />
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </header>
</template>

<script>
    export default {
        
    }
</script>

<style scoped>
    .profile-img {
        flex: 0 0 auto;
        margin-right: 16px;
        width: 128px;
        height: 128px;
        border-radius: 999px;
        background-size: cover;
        background-position: center center;
        border: 1px solid black;
    }
    .profile-wrapper {
        display: flex;
        -webkit-box-align: center;
        align-items: center;
    }

    .header-icon {
        font-size: 35px;
    }

    .header-link {
        text-decoration: none;
        color:black;
    }
</style>