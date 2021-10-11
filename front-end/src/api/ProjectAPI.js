import axios from "axios";
const baseUrl = `http://localhost:8080/api/projects`;
const API = {
    getAllProjects: () => {
        return axios.get(`${baseUrl}`)
    }
};

export default API;