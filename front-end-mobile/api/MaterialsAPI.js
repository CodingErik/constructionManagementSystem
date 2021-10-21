import axios from 'axios';
import {LOCAL_IP} from '@env';
const baseUrl = `http://${LOCAL_IP}:8979/api/materials`;

const materialAPI = {
  getWarehouseMaterialsInventory: () => {
    return axios.get(`${baseUrl}/warehouse`)
  },
  refillWarehouseMaterialsInventory: () => {
    return axios.put(`http://localhost:8979/api/material/warehouse/refill`)
  },
  getMaterialsByProjectId: (projectId) => {
    return axios.get(`${baseUrl}/project/${projectId}`)
  },
  requestMaterialsForProject: (requestAmount) => {
    return axios.post(`${baseUrl}/project/request`, requestAmount)
  },
  getAllMaterialsInProjects: () => {
    return axios.get(`${baseUrl}`)
  }
};

export default materialAPI;
