<template>
    <div class="container">
      <div class="row">
  
        <div class="col-lg-9">
          <div class="card mb-4" v-for="post in posts.data" :key="post.id" @click="goWrite(post.id)">
            <a href="#!"><img v-if="post.thumbnail != ''" class="card-img-top" style="height:400px;" :src="post.thumbnail" alt="..." /></a>
            <div class="card-body">
              <div class="small text-muted">{{ formatDateTime(post.postAt) }}</div>
              <h2 class="card-title">{{ post.title }}</h2>
              <p class="card-text">{{ post.description }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { onMounted, reactive } from 'vue';
  import PostService from '../services/PostService';
  import router from '../router';
  import dayjs from 'dayjs';
  
  export default {
    setup() {
  
      const posts = reactive({
          data : [],
      });
  
      const goWrite = (pid) => {
        router.push({path: '/posts/write/' + pid});
      }
  
      onMounted(async () => {
        const response = await PostService.fetchPostsTemp();
        posts.data = response;
      });
  
      const formatDateTime = (datetime) => {
        return dayjs(datetime).format('YYYY년 MM월 DD일 HH:mm');
      };
      
      return { posts, goWrite, formatDateTime }
  
    }
  }
  </script>
  
  <style scoped></style>