import axios from 'axios';

const baseUrl = `http://localhost:8979/api/machine`;

const machineryAPI = {
    getAllMachineryInventory: () => axios.get(`${baseUrl}/inventory`)
}

export default machineryAPI;