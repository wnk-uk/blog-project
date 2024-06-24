<template>
    <div class="container">
        <div class="mb-3">
            <input class="post_subject" id="title" placeholder="제목을 입력하세요.">
        </div>
        <div>
            <select class="form-select mb-2" id="tags" aria-label="Default select example">
                <option v-for="tag in tags" :key="tag.id" :value="tag.id">{{ tag.tagName }}</option>
            </select>
        </div>
        <div ref="groundElRef"></div>
        <div class="mt-3" style="display: flex; justify-content:space-between">
            <div style="cursor:pointer;" @click="back">
                <font-awesome-icon class="btn" style="padding-right:0px;" :icon="['fas', 'arrow-left']" />
                <button class="btn" style="padding-left:0px;" >뒤로가기</button>
            </div>
            <div>
                <button class="btn" @click="tempsave">임시저장</button>
                <button class="btn" @click="save">포스팅</button>
            </div>
        </div>
    </div>
</template>

<script>
    import { Editor } from '@toast-ui/editor';
    import { useStore } from 'vuex';
    import { onMounted, ref, watch } from 'vue';
    import router from '../router';
    import PostService from '../services/PostService';

    export default {
        setup() {
            const store = useStore();
            const groundElRef = ref(null);
            let editorInstance = null; // 에디터 인스턴스 저장 변수
            
            const tags = ref(null);

            onMounted(() => {
                tags.value = store.state.tags;

                editorInstance = new Editor({
                    el: groundElRef.value,
                    previewStyle: 'vertical',
                    height: '700px',
                    hooks : {
                        async addImageBlobHook(blob, callback) {
                            const fomrData = new FormData();
                            fomrData.append('image', blob);    
                            
                            const response = await PostService.uploadInline(fomrData);
                            const imageUrl = 'http://localhost:8081' + '/posts/inlines/' + response.id;
                            
                            callback(imageUrl);
                            
                        }
                    }
                });
            });

            const save = async () => {
                let post = {
                    title : document.querySelector('#title').value,
                    tag : document.querySelector('#tags').value,
                    content : editorInstance.getMarkdown(),
                    postStatus : 'P'
                }
                await PostService.addNewPost(post);
                router.push({path:'/tags/' + post.tag})
            }

            watch(() => store.state.tags, (newValue) => {
                tags.value = newValue;
            });
            
            const back = () => {
                history.back();
            }
            
            const tempsave = async () => {
                console.log(editorInstance);
                console.log(editorInstance.getMarkdown());
            }

            return { groundElRef, editorInstance, save, back, tags, tempsave }
        }
    }
</script>

<style scoped>
    @import '@toast-ui/editor/dist/toastui-editor.css';
    @import '@toast-ui/editor/dist/toastui-editor-viewer.css';
    /* @import 'codemirror/lib/codemirror.css'; */
    .post_subject {
        width: 100%; 
        border:none; 
        height: 60px; 
        font-size: 30px;
        font-weight: bold;
    }
    .post_subject:focus {
        outline:none;
    }
    
</style>