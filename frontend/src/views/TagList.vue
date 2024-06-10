<template>
  <div>

  </div>
  </template>

<script>
  import { useRoute } from 'vue-router';
  import router from '../router';
  import { onMounted } from 'vue';

  export default {
    setup(props, { emit }) {
        const route = useRoute();
        const id = route.params.id;

        
        console.log(router);
        console.log(emit);

        onMounted(() => {
          console.log(id);
        });

    }
  }
</script>

<style scoped>

</style>