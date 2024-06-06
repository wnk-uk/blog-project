<template>
    <div>

    </div>
</template>

<script>
    import router from '../router/router';

    export default {
        created() {
            const token = this.$route.query.token;
            console.log(token);

            if (token) {
                sessionStorage.setItem("jwt-token", token);
                router.push({path: '/'})
            } else {
                router.push({path: '/'})
            }
        }
    }
</script>

<style scoped>

</style>