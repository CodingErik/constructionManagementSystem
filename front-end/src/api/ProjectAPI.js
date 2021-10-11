import axios from "axios";
const baseUrl = `http://localhost:8080/api/projects`;
const API = {
    getAllProjects: (roomType, name, isPlumbing, isElectric) => {
        return axios.get(`${baseUrl}`, {params:{roomType:roomType,name:name}});
    }
};

export default API;