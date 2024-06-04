<template>
  <MainNav></MainNav>
  <MainHeader></MainHeader>
  <MainSection></MainSection>
  <MainFooter></MainFooter>
</template>

<script>
import MainNav from './components/MainNav.vue';
import MainSection from './components/MainSection.vue';
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
    MainSection,
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
