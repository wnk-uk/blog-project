<template>
    <div ref="groundElRef"></div>
</template>

<script>

    import { Editor } from '@toast-ui/editor';
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
                editorInstance = new Editor({
                    el: groundElRef.value,
                    previewStyle: 'vertical',
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