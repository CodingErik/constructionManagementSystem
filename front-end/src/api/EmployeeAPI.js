import axios from "axios";
const baseUrl = `http://localhost:8080/api/employees`;

//Get Local storage and store in token
const token = "";

const config = {
    headers: {
        'content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
    },
};

const employeeAPI = {
    getAllEmployees: () => {
        return axios.get(`${baseUrl}`, config)
    },
    // getEmployeeByName: (name) => {
    //     return axios.get(`${baseUrl}/findByName/${name}`)
    // },
    getEmployeeByUsername: (username) => {
        return axios.get(`${baseUrl}/findByUsername/${username}`)
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