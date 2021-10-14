import axios from 'axios';

const baseUrl = `http://localhost:8979/api/materials`;

const materialAPI = {
    getAllMaterialsInventory: () => axios.get(`${baseUrl}`)
}

export default materialAPI;