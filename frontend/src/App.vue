<template>
  <MainNav></MainNav>
  <MainHeader></MainHeader>
  <RouterView @setToken="setToken"></RouterView>
  <MainFooter></MainFooter>
</template>

<script>
import MainNav from './components/MainNav.vue';
import MainFooter from './components/MainFooter.vue';
import MainHeader from './components/MainHeader.vue';
import { mapActions } from 'vuex';


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
    ...mapActions(['setToken', 'clearToken'])
  }
}
</script>

<style scoped>

</style>
