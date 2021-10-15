import axios from 'axios';
// const baseUrl = `http://localhost:8080/api/projects`;
const baseUrl = `http://localhost:8979/api/projects`;

const token = localStorage.getItem('token')
  ? JSON.parse(localStorage.getItem('token'))
  : null;

const config = {
  headers: {
    'content-Type': 'application/json',
    Authorization: `Bearer ${token}`,
  },
};

const projectAPI = {
  getAllProjects: (roomType, name) => {
    return axios.get(`${baseUrl}`, {
      params: { name: name, roomType: roomType },
    });
  },
  getProjectsByRoomType: (roomType) => {
    return axios.get(`${baseUrl}/roomType/${roomType}`);
  },
  getProjectsByProjectName: (projectName) => {
    return axios.get(`${baseUrl}/name/${projectName}`);
  },
  getProjectsByRoomTypeAndName: (roomType, name) => {
    return axios.get(`${baseUrl}/roomType/${roomType}/name/${name}`);
  },
  getProjectById: (projectId) => {
    return axios.get(`${baseUrl}/id/${projectId}`);
  },
  putProject: (updatedProject) => {
    return axios.put(`${baseUrl}`, updatedProject);
  },
  addProject: (newProject) => {
    return axios.post(`${baseUrl}`, newProject);
  },
  deleteProjectById: (projectId) => {
    return axios.delete(`${baseUrl}/${projectId}`);
  }
};

export default projectAPI;
