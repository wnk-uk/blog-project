<template>
  <div class="container">
    <div class="row">
      <TagSection></TagSection>

      <div class="col-lg-9">
        <div class="card mb-4" v-for="post in posts.data" :key="post.id" @click="goView(post.id)">
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
import { useRoute } from 'vue-router';
import { onMounted, reactive } from 'vue';
import TagSection from '../components/TagSection.vue';
import PostService from '../services/PostService';
import router from '../router';
import dayjs from 'dayjs';

export default {
  setup() {
    const route = useRoute();
    const id = route.params.id;

    const posts = reactive({
        data : [],
    });

    const goView = (pid) => {
      router.push({path: '/posts/view/' + pid});
    }

    onMounted(async () => {
      const response = await PostService.fetchPosts(id);
      posts.data = response;
    });

    const formatDateTime = (datetime) => {
      return dayjs(datetime).format('YYYY년 MM월 DD일 HH:mm');
    };
    
    return { posts, goView, formatDateTime }

  },
  components: {
    TagSection
  }
}
</script>

<style scoped></style>