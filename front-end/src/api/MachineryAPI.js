import axios from 'axios';

const baseUrl = `http://localhost:8979/api/machines`;

const machineryAPI = {
    getWarehouseMachineryInventory: () => {
        return axios.get(`${baseUrl}/warehouse`)
    },
    getMachineryByProjectId: (projectId) => {
        return axios.get(`${baseUrl}/project/${projectId}`)
    },
    getAllMachinesInProjects: () => {
        return axios.get(`${baseUrl}`)
    },
    requestMachineryForProject: (machineryRequest) => {
        return axios.post(`${baseUrl}/project/request`, machineryRequest);
    },
    returnMachineryForProject: (machineryReturn) => {
        return axios.post(`${baseUrl}/project/return`, machineryReturn)
    }

}

export default machineryAPI;