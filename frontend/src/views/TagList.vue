<template>
  <div class="container">
    <div class="row">
      <TagSection @fetchTags="$emit('fetchTags')" @addNewTag="handlerNewTag" @setTagsIsSuccess="setTagsIsSuccess"></TagSection>    

      <div class="col-lg-9">
        <div class="card mb-4">
          <a href="#!"><img class="card-img-top" src="https://dummyimage.com/850x350/dee2e6/6c757d.jpg" alt="..." /></a>
          <div class="card-body">
            <div class="small text-muted">January 1, 2023</div>
            <h2 class="card-title">Featured Post Title</h2>
            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis aliquid atque,
              nulla? Quos cum ex quis soluta, a laboriosam. Dicta expedita corporis animi vero voluptate voluptatibus
              possimus, veniam magni quis!</p>
          </div>
        </div>
        <div class="card mb-4">
          <a href="#!"><img class="card-img-top" src="https://dummyimage.com/850x350/dee2e6/6c757d.jpg" alt="..." /></a>
          <div class="card-body">
            <div class="small text-muted">January 1, 2023</div>
            <h2 class="card-title">Featured Post Title</h2>
            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis aliquid atque,
              nulla? Quos cum ex quis soluta, a laboriosam. Dicta expedita corporis animi vero voluptate voluptatibus
              possimus, veniam magni quis!</p>
          </div>
        </div>
        <div class="card mb-4">
          <a href="#!"><img class="card-img-top" src="https://dummyimage.com/850x350/dee2e6/6c757d.jpg" alt="..." /></a>
          <div class="card-body">
            <div class="small text-muted">January 1, 2023</div>
            <h2 class="card-title">Featured Post Title</h2>
            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis aliquid atque,
              nulla? Quos cum ex quis soluta, a laboriosam. Dicta expedita corporis animi vero voluptate voluptatibus
              possimus, veniam magni quis!</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useRoute } from 'vue-router';
import { onMounted } from 'vue';
import TagSection from '../components/TagSection.vue';
import PostService from '../services/PostService';

export default {
  setup() {
    const route = useRoute();
    const id = route.params.id;

    const posts = null;

    onMounted(async () => {
      const response = await PostService.fetchPosts(id);
      this.posts = response;
    });
      return { posts }
  },
  components: {
    TagSection
  }
}
</script>

<style scoped></style>