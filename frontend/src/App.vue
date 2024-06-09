<template>
  <MainNav :name="accountName" :email="accountEmail" :image="accountImage"></MainNav>
  <MainHeader></MainHeader>
  <RouterView @setToken="setToken" @loadAccount="loadAccount" @findTags="findTags" :tags="tags" @addTag="addTag" :message="message"></RouterView>
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
        message : null
    }
  },
  components: {
    MainNav,
    MainFooter,
    MainHeader
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
          this.findTags();
        }).catch(error => {
            if (error.response) {
              this.message = error.response.data.message;
            } else {
              console.error('Network Error:', error.message);
            }
            
        });
    }
  },
  created() {
    this.loadAccount();
  }
}
</script>

<style scoped>
@import '../src/assets/css/styles.css';
</style>
