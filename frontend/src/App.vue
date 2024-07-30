<template>
    <MainNav :account="account"></MainNav>
    <RouterView @setToken="setToken"></RouterView>
    <MainFooter></MainFooter>
</template>

<script>
import MainNav from './components/MainNav.vue';
import MainFooter from './components/MainFooter.vue';
import { mapActions } from 'vuex';
import AccountService from './services/AccountService';


export default {
  components: {
    MainNav,
    MainFooter,
  },
  methods : {
    ...mapActions(['setToken', 'clearToken', 'setAccount']),
    async loadAccount() {
      try {
        this.setAccount(await AccountService.loadAccount());
      } catch(error) {
        this.setAccount(null);
      }
    }
  },
   
  created() {
    this.setToken(sessionStorage.getItem("jwt-token"));
    this.loadAccount();
  }
}
</script>

<style scoped>
@import '../src/assets/css/styles.css';

@font-face {
    font-family: 'KCC-Hanbit';
    src: url('./assets/fonts/KCC-Hanbit.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

* {
  font-family: 'KCC-Hanbit', sans-serif;
}

</style>
