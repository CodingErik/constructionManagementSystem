import axios from 'axios';

const baseUrl = `http://localhost:8080`;

const API = {
  login: async (username, password) => {
    try {
      const config = {
        headers: {
          'content-Type': 'application/json',
        },
      };

      const request = await axios.post(
        `${baseUrl}/api/employees/login`,
        {
          username: username,
          password: password,
        },
        config
      );

      const response = {
        data: request.data,
        status: request.status,
      };

      return response;
    } catch (error) {
      return error.response.data;
    }
  },
  register: async (name, username, email, password) => {
    try {
      const config = {
        headers: {
          'content-Type': 'application/json',
        },
      };

      const request = await axios.post(
        `${baseUrl}/api/employees/register`,
        {
          name: name,
          username: username,
          email: email,
          password: password,
        },
        config
      );

      const response = {
        data: request.data,
        status: request.status,
      };

      return response;
    } catch (error) {
      console.log(error.response.data);

      return error.response.data;
    }
  },
};

export default API;
// /api/employees/register
