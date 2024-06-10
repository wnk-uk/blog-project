import axios from './axios';

class AccountService {

async loadAccount() {
    try {
        const response = await axios.get("/api/users/me");
        return response.data;
    } catch (error) {
        throw new Error("No Session", error.message);
    }
  }

}

export default new AccountService();
