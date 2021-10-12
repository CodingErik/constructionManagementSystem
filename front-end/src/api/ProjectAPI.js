import axios from "axios";
const baseUrl = `http://localhost:8080/api/projects`;
const projectAPI = {
    getAllProjects: (roomType, name) => {
        return axios.get(`${baseUrl}`, {params:{roomType:roomType, name:name}});
    }
};

export default projectAPI;