import axios from "axios";
const baseUrl = `http://localhost:8080/api/employees`;
const API = {
    getAllEmployees: () => {
        return axios.get(`${baseUrl}`)
    },
    getEmployeeById: (id) => {
        return axios.get(`${baseUrl}/${id} `)
    },
    getEmployeeByProjectId: (projectId) => {
        return axios.get(`${baseUrl}/findByProjectId/${projectId}`)
    },
    putEmployee: (updatedEmployee) => {
        return axios.put(`${baseUrl}`, updatedEmployee);
    }
};

export default API;