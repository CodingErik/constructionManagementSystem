import axios from "axios";
const baseUrl = `http://localhost:8080/api/employees`;
const API = {
    getAllEmployees: () => {
        return axios.get(`${baseUrl}`)
    },
    getEmployeeById: (id) => {
        return axios.get(`${baseUrl}/${id} `)
    }
};

export default API;