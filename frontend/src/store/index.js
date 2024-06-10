import { createStore } from 'vuex';

export default createStore({
  state: {
    token: null, // 토큰 상태
    tags: [],
    tagsMessage: null,
    tagsIsSuccess : false
  },

  //vuex의 상태를 변경하는 유일한 방법
  //동기적으로 동작
  //순수한 함수로 구현되어야 하며, 상태 변경 외에는 어떤 로직도 포함X
  mutations: { 
    setToken(state, token) {
      state.token = token;
    },
    clearToken(state) {
      state.token = null;
    },
    setTags(state, tags) {
      state.tags = tags;
    },
    setTagsMessage(state, message) {
      state.tagsMessage = message;
    },
    setTagsIsSuccess(state, flag) {
      state.tagsIsSuccess = flag;
    }
  },

  //액션은 비동기적인 작업이나 여러 뮤테이션을 연속적으로 호출하는 등의 복잡한 작업을 처리할 때 사용됩니다. 
  //주로 비동기 작업을 처리하고, 서버에서 데이터를 가져오거나 뮤테이션을 호출하여 상태를 변경하는 등의 작업을 수행합니다. 
  //액션은 비동기적으로 동작할 수 있으며, 외부 데이터를 가져오거나 다른 비동기 작업을 수행할 수 있습니다.
  actions: {
    setToken({ commit }, token) {
      commit('setToken', token);
    },
    clearToken({ commit }) {
      commit('clearToken');
    },
    setTags({ commit }, tags) {
      commit('setTags', tags);
    },
    setTagsMessage({ commit }, message) {
      commit('setTagsMessage', message);
    },
    setTagsIsSuccess({ commit }, flag) {
      commit('setTagsIsSuccess', flag);
    }
  },
  getters: {
    getToken: state => state.token,
    getTags: state => state.tags,
    getTagsMessage: state => state.tagsMessage,
    getTagsIsSuccess: state => state.tagsIsSuccess
  }
});