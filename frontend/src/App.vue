<template>
  <MainNav :name="accountName" :email="accountEmail" :image="accountImage"></MainNav>
  <MainHeader></MainHeader>
  <RouterView @setToken="setToken" @loadAccount="loadAccount" @findTags="findTags" :tags="tags" @addTag="addTag" :message="message" :success="successFlag" @resetFlag="resetFlag"></RouterView>
  <MainFooter></MainFooter>
</template>

<script>
import MainNav from './components/MainNav.vue';
import MainFooter from './components/MainFooter.vue';
import MainHeader from './components/MainHeader.vue';
import { mapActions } from 'vuex';
import axios from './services/axios';


export default {
  data() {
    return {
        accountName : null,
        accountEmail : null,
        accountImage : null,
        tags : null,
        message : null,
        successFlag : false
    }
  },
  components: {
    MainNav,
    MainFooter,
    MainHeader,
  },
  methods : {
    ...mapActions(['setToken', 'clearToken']),
    loadAccount() {
      axios.get("/api/users/me")
        .then(response => {
          console.log(response);
          this.accountName = response.data.name;
          this.accountEmail = response.data.email;
          this.accountImage = response.data.picture;
        }).catch(error => {
          this.accountName = null;
          this.accountEmail = null;
          this.accountImage = null;
          console.log(error);
        });
    },
    findTags() {
      axios.get("/api/tags")
        .then(response => {
          this.tags = response.data;
        }).catch(error => {
            console.error(error);
        });
    },
    addTag(tagName) {
      axios.post("/api/tags/add", {
        tagName : tagName
      })
        .then(response => {
          console.log(response);
          this.successFlag = true;
          this.findTags();
        }).catch(error => {
            if (error.response) {
              this.message = error.response.data.message;
            } else {
              console.error('Network Error:', error.message);
            }
            
        });
    },
    resetFlag() {
      this.successFlag = false;
      this.message = null;
    }
  },
  created() {
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
