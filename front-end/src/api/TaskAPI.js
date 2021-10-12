import axios from "axios";
const baseUrl = `http://localhost:8080/api/tasks`;
const taskAPI = {
    getAllTasks: (projectId, employeeId, name) => {
        return axios.get(`${baseUrl}`,{params:{projectId:projectId, employeeId:employeeId, name:name}});
    }
};

export default taskAPI;