import axios from "axios";
const baseUrl = `http://localhost:8080/api/projects`;
const API = {
    getAllProjects: () => {
        return axios.get(`${baseUrl}`)
    },
    getProjectsByRoomType: (roomType) => {
        return axios.get(`${baseUrl}/roomType/${roomType}`)
    },
    getProjectsByProjectName: (projectName) => {
        return axios.get(`${baseUrl}/name/${projectName}`)
    },
    getProjectsByRoomTypeAndName: (roomType,name) => {
        return axios.get(`${baseUrl}/roomType/${roomType}/name/${name}`)
    }
};

export default API;