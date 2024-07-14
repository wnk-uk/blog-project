<template>
  <div class="container">
    <div class="row">
      <TagSection @fetchTags="$emit('fetchTags')" @addNewTag="handlerNewTag" @setTagsIsSuccess="setTagsIsSuccess" :select="tags.id"></TagSection>    

      <div class="col-lg-9">
        <div class="card mb-4" v-for="post in posts.data" :key="post.id" @click="goView(post.id)">
          <a href="#!"><img class="card-img-top" style="height:400px;" :src="post.thumbnail" alt="..." /></a>
          <div class="card-body">
            <div class="small text-muted">January 1, 2023  {{ post.postAt }}</div>
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


export default {
  setup(props, { emit }) {
    const route = useRoute();
    const id = route.params.id;

    const posts = reactive({
        data : [],
    });

    const tags = reactive({
        id : null
    });

    const handlerNewTag = (value) => {
      emit('addNewTag', value);
    }

    const setTagsIsSuccess = (flag) => {
      emit('setTagsIsSuccess', flag);
    }

    const goView = (pid) => {
      router.push({path: '/posts/view/' + pid});
    }

    onMounted(async () => {
      const response = await PostService.fetchPosts(id);
      tags.id = response.id;
      posts.data = response.posts;
    });
    
      return { posts, tags, handlerNewTag, setTagsIsSuccess, goView }
  },
  components: {
    TagSection
  }
}
</script>

<style scoped></style>