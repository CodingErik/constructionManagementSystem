import axios from 'axios';
// const baseUrl = `http://localhost:8080/api/employees`;
const baseUrl = `http://localhost:8979/api/employees`;

const employeeAPI = {
  getAllEmployees: () => {
    return axios.get(`${baseUrl}`);
  },
  // getEmployeeByName: (name) => {
  //     return axios.get(`${baseUrl}/findByName/${name}`)
  // },
  getEmployeeByUsername: (username) => {
    return axios.get(`${baseUrl}/findByUsername/${username}`);
  },
  getEmployeeById: (id) => {
    return axios.get(`${baseUrl}/${id} `);
  },
  getEmployeeByProjectId: (projectId) => {
    return axios.get(`${baseUrl}/findByProjectId/${projectId}`);
  },
  putEmployee: (updatedEmployee) => {
    return axios.put(`${baseUrl}`, updatedEmployee);
  },
  getEmployeeByTitle: (title) => {
    return axios.get(`${baseUrl}/findByTitle/${title}`);
  },
};

export default employeeAPI;
