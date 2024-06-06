<template>
    <div>

    </div>
</template>

<script>
    import router from '../router';

    export default {
        created() {
            const token = this.$route.query.token;
            console.log(token);

            if (token) {
                sessionStorage.setItem("jwt-token", token);
                this.$emit('setToken', token);
                router.push({path: '/'})
            } else {
                router.push({path: '/'})
            }
        },
    }
</script>

<style scoped>

</style>