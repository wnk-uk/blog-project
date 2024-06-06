// 토큰을 저장하고 가져오는 로직을 포함한 서비스
class AuthService {
    getToken() {
      return sessionStorage.getItem('jwt-token');
    }
  
    saveToken(token) {
        sessionStorage.setItem('token', token);
    }
  
    removeToken() {
        sessionStorage.removeItem('token');
    }
  }
  
  export default new AuthService();