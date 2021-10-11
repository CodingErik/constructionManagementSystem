import axios from "axios";
const baseUrl = `http://localhost:8080/api/tasks`;
const API = {
    getAllTasks: () => {
        return axios.get(`${baseUrl}/all`)
    }
};

export default API;