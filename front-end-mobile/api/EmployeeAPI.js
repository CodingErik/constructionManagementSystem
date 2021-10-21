import axios from 'axios';
import {LOCAL_IP} from "@env";
// const baseUrl = `http://${LOCAL_IP}:8979/api/employees`;

const employeeAPI = {
  getAllEmployees: () => {
    return axios.get(`${baseUrl}`);
  },
  getEmployeeByUsername: (username) => {
    return axios.get(`${baseUrl}/findByUsername/${username}`);
  },
  getEmployeeById: (id) => {
    return axios.get(`${baseUrl}/${id} `);
  },
  getAllEmployeeByProjectId: (projectId) => {
    return axios.get(`${baseUrl}/findByProjectId/${projectId}`);
  },
  putEmployee: (updatedEmployee) => {
    return axios.put(`${baseUrl}`, updatedEmployee);
  },
  getEmployeeByTitle: (title) => {
    return axios.get(`${baseUrl}/findByTitle/${title}`);
  },
  updatePassword: async (id, newPassword) => {
    const res = await axios.put(`http://localhost:8979/api/resetPassword`, {
      id: id,
      password: newPassword,
    });

    return res;
  },
  deleteEmployeeById: (employeeId) => {
    return axios.delete(`${baseUrl}/${employeeId}`);
  },
};

export default employeeAPI;
