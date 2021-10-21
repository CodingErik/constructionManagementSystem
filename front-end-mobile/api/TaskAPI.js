import axios from "axios";
const baseUrl = `http://localhost:8979/api/tasks`;
const taskAPI = {
    getAllTasks: (projectId, employeeId, name) => {
        return axios.get(`${baseUrl}`,{params:{projectId:projectId, employeeId:employeeId, name:name}});
    },
    getTaskById: (taskId) => {
        return axios.get(`${baseUrl}/id?taskId=${taskId}`)
    },
    putTask: (updatedTask) => {
        return axios.put(`${baseUrl}`, updatedTask)
    },
    getTaskByProjectId: (projectId) => {
        return axios.get(`${baseUrl}?projectId=${projectId}`)
    },
    addTask: (updatedTask) => {
        return axios.post(`${baseUrl}`, updatedTask);
    },
    deleteTask:(taskId) => {
        return axios.delete(`${baseUrl}/${taskId}`)
    }

};

export default taskAPI;