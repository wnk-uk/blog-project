<template>
  <MainNav v-bind:name="accountName" v-bind:email="accountEmail" v-bind:image="accountImage"></MainNav>
  <MainHeader></MainHeader>
  <RouterView @setToken="setToken" @loadAccount="loadAccount" @findTags="findTags" :tags="tags" @addTag="addTag"></RouterView>
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
        tags : null        
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
          this.accountName = response.data.name;
          this.accountEmail = response.data.email;
          this.accountImage = response.data.picture;
        }).catch(error => {
            console.error(error);
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
      console.log(tagName);
      axios.post("/api/tags/add", {
        tagName : tagName
      })
        .then(response => {
          console.log(response);
          this.findTags();
        }).catch(error => {
            console.error(error);
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
