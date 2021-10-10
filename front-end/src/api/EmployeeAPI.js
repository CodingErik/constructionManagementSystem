import axios from "axios";
const baseUrl = `http://localhost:8080/api/employees`;
const API = {
    getAllEmployees: () => {
        return axios.get(`${baseUrl}`)
    }
};

export default API;