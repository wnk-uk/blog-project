import axios from './axios';

class TagService {
    async fetchTags() {
        try {
            const response = await axios.get("/api/tags",{
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            //localStorage.setItem('tags', JSON.stringify(response.data));
            return response.data;
        } catch (error) {
            throw new Error('Failed to fetch tags');
        }
    }

    async addNewTag(tagName) {
        try {
            await axios.post("/api/tags/add", { tagName : tagName });
            return true;
        } catch (error) {
            if (error.response) {
                throw new Error(error.response.data.message);
              } else {
                throw new Error('Network Error:', error.message);
              }
        }
    }
}

export default new TagService();