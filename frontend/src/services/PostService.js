import axios from './axios';

class PostService {
    async addNewPost(data) {
        try {
            const response = await axios.post("/api/posts/add", JSON.stringify(data), {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            throw new Error('Failed to add posts');
        }
    }

    async fetchPosts(id) {
        try {
            const response = await axios.get("/api/tags/" + id + '/posts', {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            throw new Error('Failed to fetch posts');
        } 
    }

    async fetchPost(id) {
        try {
            const response = await axios.get("/api/posts/" + id, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            throw new Error('Failed to fetch posts');
        } 
    }
}

export default new PostService();