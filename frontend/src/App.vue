<template>
  <MainNav></MainNav>
  <MainHeader></MainHeader>
  <RouterView></RouterView>
  <MainFooter></MainFooter>
</template>

<script>
import MainNav from './components/MainNav.vue';
import MainFooter from './components/MainFooter.vue';
import MainHeader from './components/MainHeader.vue';
import axios from 'axios';


export default {
  data() {
    return {
      account : {}
    }
  },
  components: {
    MainNav,
    MainFooter,
    MainHeader
  },
  methods : {
    test() {
      axios.get("/").then(response => {console.log(response)});
    }
    
  }
}
</script>

<style scoped>

</style>
