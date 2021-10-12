import axios from "axios";
const baseUrl = `http://localhost:8080/api/tasks`;
const taskAPI = {
    getAllTasks: (projectId, employeeId, name) => {
        return axios.get(`${baseUrl}`,{params:{projectId:projectId, employeeId:employeeId, name:name}});
    },
    getTaskById: (taskId) => {
        return axios.get(`${baseUrl}/id?taskId=${taskId}`)
    },
    putTask: (updatedTask) => {
        return axios.put(`${baseUrl}`, updatedTask)
    }
};

export default taskAPI;