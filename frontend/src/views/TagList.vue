<template>
  <div class="container">
    <div class="row">
      <TagSection @fetchTags="$emit('fetchTags')" @addNewTag="handlerNewTag" @setTagsIsSuccess="setTagsIsSuccess" :select="tags.id"></TagSection>    

      <div class="col-lg-9">
        <div class="card mb-4" v-for="post in posts.data" :key="post.id" @click="postView">
          <a href="#!"><img class="card-img-top" src="https://dummyimage.com/850x350/dee2e6/6c757d.jpg" alt="..." /></a>
          <div class="card-body">
            <div class="small text-muted">January 1, 2023  {{ post.postAt }}</div>
            <h2 class="card-title">{{ post.title }}</h2>
            <p class="card-text">{{ post.content }}</p>
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

    const setTagsIsSuccess = (value) => {
      emit('setTagsIsSuccess', value);
    }

    onMounted(async () => {
      const response = await PostService.fetchPosts(id);
      tags.id = response.id;
      posts.data = response.posts;
    });
      return { posts, tags, handlerNewTag, setTagsIsSuccess }
  },
  components: {
    TagSection
  }
}
</script>

<style scoped></style>