<template>
   <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">Uk's Blog</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" aria-current="page" href="#">search</a></li>
                <li class="nav-item">
                    <div class="dropdown"> <!-- v-if -->
                        <button class="btn btn-secondary dropdown-toggle" 
                                type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="/logout">Logout</a></li>
                        </ul>
                    </div>
                    <div>
                        <a href="http://localhost:8081/oauth2/authorization/google" class="btn btn-success active" role="button">Google Login</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
</template>

<script>
    export default {
        props: ['account'],
        methods: {

        }
    }
</script>

<style scoped>
    .profile {
        background-repeat:no-repeat; 
        background-size:cover; 
        border-radius: 50px;
    }
</style>