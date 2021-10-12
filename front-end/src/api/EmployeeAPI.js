import axios from "axios";
const baseUrl = `http://localhost:8080/api/employees`;
const employeeAPI = {
    getAllEmployees: () => {
        return axios.get(`${baseUrl}`)
    },
    getEmployeeByName: (name) => {
        return axios.get(`${baseUrl}/findByName/${name}`)
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

export default employeeAPI;