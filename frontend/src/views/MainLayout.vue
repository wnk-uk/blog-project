<template>
    <MainHeader></MainHeader>
    <div class="container">
            <div class="row">
                <!-- Categories widget-->
                <TagSection @fetchTags="$emit('fetchTags')" @addNewTag="handlerNewTag" @setTagsIsSuccess="setTagsIsSuccess"></TagSection>    

                <!-- Blog entries-->
                <div class="col-lg-9">
                    <div class="card mb-4" v-for="post in posts.data" :key="post.id" @click="goView(post.id)">
                        <a href="#!"><img class="card-img-top" style="height:400px;" :src="post.thumbnail" alt="..." /></a>
                    <div class="card-body">
                        <div class="small text-muted">{{ formatDateTime(post.postAt) }}</div>
                        <h2 class="card-title">{{ post.title }}</h2>
                        <p class="card-text">{{ post.description }}</p>
                    </div>
                    </div>
                </div>
                <!-- Side widgets-->
            </div>
        </div>    

        

</template>

<script>
import MainHeader from '../components/MainHeader.vue';
import TagSection from '../components/TagSection.vue';
import { onMounted, reactive } from 'vue';
import PostService from '../services/PostService';
import router from '../router';
import dayjs from 'dayjs';

    export default {
        props: ['fetchTags', 'addNewTag'],
        components: {
            TagSection,
            MainHeader
        },
        setup(props, { emit }) {

            const handlerNewTag = (value) => {
                emit('addNewTag', value);
            }

            const setTagsIsSuccess = (value) => {
                emit('setTagsIsSuccess', value);
            }

            const posts = reactive({
                data : [],
            });

            const goView = (pid) => {
            router.push({path: '/posts/view/' + pid});
            }

            onMounted(async () => {
                const response = await PostService.fetchAll();
                posts.data = response;
            });

            const formatDateTime = (datetime) => {
                return dayjs(datetime).format('YYYY년 MM월 DD일 HH:mm');
            };

            return {handlerNewTag, setTagsIsSuccess, posts, goView, formatDateTime};
        }
        
    }
</script>

<style scoped>
    * {
        font-family: 'KCC-Hanbit', sans-serif;
    }
</style>