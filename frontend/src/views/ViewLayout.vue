<template>
    <div class="container">
        <header class="py-5 mb-5">
            <div class="container">
                <div class="row">
                    <div class="profile-wrapper col-lg-12">
                        <div>
                            <div>{{ post.title }}</div>
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
    import { onMounted, ref, watch } from 'vue';
    import { useRoute } from 'vue-router';

    export default {
        props: ['post'],
        setup(props, { emit }) {
            const route = useRoute();
            const id = route.params.id;
            const groundElRef = ref(null);
            let editorInstance = null; // 에디터 인스턴스 저장 변수


            onMounted(() => {
                editorInstance = new Viewer({
                    el: groundElRef.value,
                    height: '700px'
                });

                fetchPost();
            });

            const fetchPost = () => {
                emit('fetchPost', id);
            }

            watch(() => props.post, (post) => {
                editorInstance.setMarkdown(post.content);
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