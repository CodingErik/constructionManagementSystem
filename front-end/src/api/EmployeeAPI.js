import axios from "axios";
const baseUrl = `http://localhost:8080/api/employees`;
const employeeAPI = {
    getAllEmployees: () => {
        return axios.get(`${baseUrl}`)
    },
    getEmployeeByName: (name) => {
        return axios.get(`${baseUrl}/findByName/${name}`)
    }
};

export default employeeAPI;