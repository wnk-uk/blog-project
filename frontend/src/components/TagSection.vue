<template>
    <div class="col-lg-3">
        <div class="card-header">
            <div
                style="display:flex; align-items: center; justify-content:space-between; border-bottom: 1px solid lightgray;">
                <div style="display:flex; align-items:center; height: 36px;;">태그 목록</div>
                <button v-if="state.account && state.account.role === 'ADMIN'" type="button" style="background-color: white; border:0px;"
                    class="btn btn-light" data-bs-toggle="modal" data-bs-target="#tagModal">+</button>
            </div>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-sm-12">
                    <ul class="list-unstyled mb-0 mt-2">
                        <li v-for="tag in state.tags" :key="tag.id" class="link-wrapper">
                            <a :href="'/tags/' + tag.id" class="link-btn" :id="'tag_' + tag.id">{{ tag.tagName }}</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="tagModal" tabindex="-1" aria-labelledby="tagModalCenterTitle" style="display: none;" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="tagModalCenterTitle">태그추가</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form class="needs-validation col-12" action="" @submit.prevent="submitForm" method="post" novalidate>
                            <div class="modal-body">
                                    <label for="tagName">태그명</label>
                                    <input id="tagName" class="form-control" type="text">
                                    <small class="form-text text-danger" v-if="state.message">{{ state.message }}</small>
                            </div>
                            <div class="modal-footer">
                                <button id="close" type="button" class="btn btn-secondary" data-bs-dismiss="modal">종료</button>
                                <button id="saveBtn" type="submit" class="btn btn-primary">저장</button>
                            </div>
                        </form>
                    </div>
                </div>
    </div>
</template>

<script>
import { onMounted, reactive, watch  } from 'vue';
import { useStore } from 'vuex';
import { useRoute } from 'vue-router';
import TagService from '../services/TagService';

    export default {
        setup() {
            const store = useStore();

            const route = useRoute();
            const id = route.params.id;

            onMounted(async () => {
              await fetchTags();
              if (id) document.querySelector('#tag_' + id).style.color = 'black';
              try {
                state.account = store.state.account;
              } catch (error) {
                state.error = error.message;
              }
            });

            const state = reactive({
                account: null,
                message: null,
                tags: [],
            }); 

            const submitForm = async () => {
                try {
                    await TagService.addNewTag(document.querySelector('#tagName').value);
                    state.message = null;
                    fetchTags();
                    } catch (error) {
                        state.message = error;
                    }
            }

            const fetchTags = async () => {
                try {
                    const tags = await TagService.fetchTags();
                    state.tags = tags;
                    store.state.tags = tags;
                } catch (error) {
                    console.log(error);
                }
            }

            watch(() => state.message, (newValue) => {
                if (newValue == null) {
                    document.querySelector("#close").click();
                    document.querySelector('#tagName').value = "";
                }
            });

            watch(() => store.state.account, (newValue) => {
                state.account = newValue;
            });

            return { submitForm, state, fetchTags };
        }
    }
</script>

<style scoped>
    .link-btn {
        color: inherit;
        text-decoration: none;
    }

    .link-wrapper {
        margin-bottom: 6px;
        color: rgb(134, 142, 150);
        cursor: pointer;
        transition: color 0.3s ease 0s;
    }
</style>