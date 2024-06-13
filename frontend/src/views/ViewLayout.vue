<template>
    <div class="container">
        <div ref="groundElRef"></div>
    </div>
    
</template>

<script>

    //import { Editor } from '@toast-ui/editor';
    import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
    import { onMounted, ref, watch } from 'vue';
    import { useRoute } from 'vue-router';

    export default {
        props: ['post'],
        setup(props) {
            const route = useRoute();
            const id = route.params.id;
            const groundElRef = ref(null);
            let editorInstance = null; // 에디터 인스턴스 저장 변수

            onMounted(() => {
                editorInstance = new Viewer({
                    el: groundElRef.value,
                    height: '700px'
                });

                editorInstance.setMarkdown('# test');
            });

            watch(() => props.post, (post) => {
                editorInstance.setMarkdown(post.content);
            });

            return { groundElRef, editorInstance, id }
        }   
    }
</script>

<style scoped>

</style>