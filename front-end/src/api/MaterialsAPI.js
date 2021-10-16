import axios from 'axios';

const baseUrl = `http://localhost:8979/api/materials`;

const materialAPI = {
  getWarehouseMaterialsInventory: () => {
    return axios.get(`${baseUrl}/warehouse`)
  },
  refillWarehouseMaterialsInventory: () => {
    return axios.put(`${baseUrl}/warehouse/refill`)
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
