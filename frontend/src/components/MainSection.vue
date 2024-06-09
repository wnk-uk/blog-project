<template>
    <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <!-- Categories widget-->
                        <div class="card-header">
                            <div style="display:flex; align-items: center; justify-content:space-between; border-bottom: 1px solid lightgray;">
                                <div style="display:flex; align-items:center; height: 36px;;">태그 목록</div>
                                <button v-if="state.role === 'ADMIN'" type="button" style="background-color: white; border:0px;" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#tagModal">+</button>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-12">
                                    <ul class="list-unstyled mb-0 mt-2">
                                        <li v-for="tag in tags" :key="tag.id" class="link-wrapper">
                                            <a :href="'/tags/' + tag.id" class="link-btn">{{ tag.tagName }}</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    <!-- Side widget-->
                    <!-- <div class="card mb-4">
                        <div class="card-header">link</div>
                        <div class="card-body">
                            <div>gmail</div>
                            <div>github</div>
                            <div>간단소개</div>
                        </div>
                    </div> -->
                </div>

                <!-- Blog entries-->
                <div class="col-lg-9">
                    <!-- Featured blog post-->
                    <div class="card mb-4">
                        <a href="#!"><img class="card-img-top" src="https://dummyimage.com/850x350/dee2e6/6c757d.jpg" alt="..." /></a>
                        <div class="card-body">
                            <div class="small text-muted">January 1, 2023</div>
                            <h2 class="card-title">Featured Post Title</h2>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis aliquid atque, nulla? Quos cum ex quis soluta, a laboriosam. Dicta expedita corporis animi vero voluptate voluptatibus possimus, veniam magni quis!</p>
                            <a class="btn btn-primary" href="#!">Read more →</a>
                        </div>
                    </div>
                    <!-- Nested row for non-featured blog posts-->
                    <div class="row">
                        <div class="col-lg-6">
                            <!-- Blog post-->
                            <div class="card mb-4">
                                <a href="#!"><img class="card-img-top" src="https://dummyimage.com/700x350/dee2e6/6c757d.jpg" alt="..." /></a>
                                <div class="card-body">
                                    <div class="small text-muted">January 1, 2023</div>
                                    <h2 class="card-title h4">Post Title</h2>
                                    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis aliquid atque, nulla.</p>
                                    <a class="btn btn-primary" href="#!">Read more →</a>
                                </div>
                            </div>
                            <!-- Blog post-->
                            <div class="card mb-4">
                                <a href="#!"><img class="card-img-top" src="https://dummyimage.com/700x350/dee2e6/6c757d.jpg" alt="..." /></a>
                                <div class="card-body">
                                    <div class="small text-muted">January 1, 2023</div>
                                    <h2 class="card-title h4">Post Title</h2>
                                    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis aliquid atque, nulla.</p>
                                    <a class="btn btn-primary" href="#!">Read more →</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <!-- Blog post-->
                            <div class="card mb-4">
                                <a href="#!"><img class="card-img-top" src="https://dummyimage.com/700x350/dee2e6/6c757d.jpg" alt="..." /></a>
                                <div class="card-body">
                                    <div class="small text-muted">January 1, 2023</div>
                                    <h2 class="card-title h4">Post Title</h2>
                                    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis aliquid atque, nulla.</p>
                                    <a class="btn btn-primary" href="#!">Read more →</a>
                                </div>
                            </div>
                            <!-- Blog post-->
                            <div class="card mb-4">
                                <a href="#!"><img class="card-img-top" src="https://dummyimage.com/700x350/dee2e6/6c757d.jpg" alt="..." /></a>
                                <div class="card-body">
                                    <div class="small text-muted">January 1, 2023</div>
                                    <h2 class="card-title h4">Post Title</h2>
                                    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis aliquid atque, nulla? Quos cum ex quis soluta, a laboriosam.</p>
                                    <a class="btn btn-primary" href="#!">Read more →</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Pagination-->
                    <!-- <nav aria-label="Pagination">
                        <hr class="my-0" />
                        <ul class="pagination justify-content-center my-4">
                            <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1" aria-disabled="true">Newer</a></li>
                            <li class="page-item active" aria-current="page"><a class="page-link" href="#!">1</a></li>
                            <li class="page-item"><a class="page-link" href="#!">2</a></li>
                            <li class="page-item"><a class="page-link" href="#!">3</a></li>
                            <li class="page-item disabled"><a class="page-link" href="#!">...</a></li>
                            <li class="page-item"><a class="page-link" href="#!">15</a></li>
                            <li class="page-item"><a class="page-link" href="#!">Older</a></li>
                        </ul>
                    </nav> -->
                </div>
                <!-- Side widgets-->
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
                                    <small class="form-text text-danger" v-if="message">{{ message }}</small>
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
    import axios from 'axios';
import { onMounted, reactive } from 'vue';
    export default {
        emits: ['setToken', 'loadAccount', 'findTags', 'addTag', 'resetFlag'],
        props:['tags', 'message', 'success'],
        setup(props, { emit }) {

            const submitForm = () => {
                emit('addTag', document.querySelector('#tagName').value);
            }

            const state = reactive({
                role: null,
                error: null
            });

            onMounted(async () => {
              emit('findTags');
              
              try {
                const response = await axios.get('/api/users/me');
                state.role = response.data.role;
              } catch (error) {
                state.error = error.message;
              }
              
            });

            function handleClick() {
                document.querySelector('#close').click();
                document.querySelector('#tagName').value = "";
                emit('resetFlag');
            }

            return { submitForm, state, handleClick };
        },
        watch: {
            success(newVlaue) {
                if (newVlaue) {
                    this.handleClick();
                }
            }
        }
    }
</script>

<style scoped>
    .link-btn {
        color: inherit;
        text-decoration: none;
    }

    .link-wrapper {
        margin-bottom: 10px;
        color: rgb(134, 142, 150);
        cursor: pointer;
        transition: color 0.3s ease 0s;
    }

    * {
        font-family: 'KCC-Hanbit', sans-serif;
    }
</style>