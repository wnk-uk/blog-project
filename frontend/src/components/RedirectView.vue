<template>
    <div>

    </div>
</template>

<script>
    import { useRoute } from 'vue-router';
    import { onMounted } from 'vue';
    import router from '../router';

    export default {
        emits: ['setToken', 'loadAccount', 'fetchTag', 'addNewTag'],
        setup(props, { emit }) {
            const route = useRoute();
            const token = route.query.token;
            console.log(token);
            if (token != null) {
                sessionStorage.setItem("jwt-token", token);
                emit('setToken', token);
            } else {
                sessionStorage.removeItem("jwt-token");
                emit('clearToken');
            }
            emit('loadAccount');

            onMounted(() => {
                router.push({path: '/'})
            });
        },
    }
</script>

<style scoped>

</style>