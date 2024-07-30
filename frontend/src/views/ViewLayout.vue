<template>
    <div class="container">
        <header class="py-5 mb-5">
            <div class="container">
                <div class="row">
                    <div class="profile-wrapper col-lg-12">
                        <div>
                            <div id="subject"></div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    <div ref="groundElRef"></div>
    </div>
    
</template>

<script>

    import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
    import { onMounted, ref, watch, reactive } from 'vue';
    import { useRoute } from 'vue-router';
    import PostService from '../services/PostService';

    export default {
        setup() {
            const route = useRoute();
            const id = route.params.id;
            const groundElRef = ref(null);
            let editorInstance = null; // 에디터 인스턴스 저장 변수
            
            const post = reactive({
                data : null,
            });

            onMounted(() => {
                editorInstance = new Viewer({
                    el: groundElRef.value,
                    height: '700px'
                });

                fetchPost();
            });

            const fetchPost = async () => {
                post.data = await PostService.fetchPost(id);
            }

            watch(() => post.data , (newValue) => {
                editorInstance.setMarkdown(newValue.content);
                document.querySelector("#subject").innerHTML = newValue.title;
            });

            return { groundElRef, editorInstance, id, fetchPost }
        }
    }
</script>

<style scoped>
    .profile-wrapper {
        display: flex;
        -webkit-box-align: center;
        align-items: center;
        justify-content: center;
        font-size: 3rem;
    }
</style>