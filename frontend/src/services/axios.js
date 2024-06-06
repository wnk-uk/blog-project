import axios from 'axios';
import AuthService from './AuthService';

// HTTP 요청을 보낼 때마다 헤더에 토큰을 추가하는 인터셉터
axios.interceptors.request.use(
  config => {
    const token = AuthService.getToken();
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

export default axios;