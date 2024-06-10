<template>
  <MainNav :account="account"></MainNav>
  <RouterView @setToken="setToken" @fetchTags="fetchTags" @addNewTag="addNewTag" ></RouterView>
  <MainFooter></MainFooter>
</template>

<script>
import MainNav from './components/MainNav.vue';
import MainFooter from './components/MainFooter.vue';
import { mapActions } from 'vuex';
import TagService from './services/TagService';
import AccountService from './services/AccountService';

export default {
  data() {
    return {
        account : null,
    }
  },
  components: {
    MainNav,
    MainFooter,
  },
  methods : {
    ...mapActions(['setToken', 'clearToken', 'setTags', 'setTagsMessage', 'setTagsIsSuccess']),
    async loadAccount() {
      try {
        this.account = await AccountService.loadAccount();
      } catch(error) {
        this.account = null;
      }
    },
    async fetchTags() {
      try {
        this.setTags(await TagService.fetchTags());
      } catch (error) {
        console.log(error);
      }
    },
    async addNewTag(tagName) {
        try {
            this.setTagsIsSuccess(await TagService.addNewTag(tagName));
            this.setTagsMessage(null);
            this.fetchTags();
          } catch (error) {
            this.setTagsMessage(error.message);
            this.setTagsIsSuccess(false);
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
