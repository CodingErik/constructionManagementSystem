import axios from 'axios';

// const baseUrl = `http://localhost:8080`;
const baseUrl = `http://localhost:8979`;

const API = {
  // @login
  login: async (username, password) => {
    try {
      const config = {
        headers: {
          'content-Type': 'application/json',
        },
      };

      const response = await axios.post(
        `${baseUrl}/api/employees/login`,
        {
          username: username,
          password: password,
        },
        config
      );

      if (response.status === 200) {
        await localStorage.setItem(
          'token',
          JSON.stringify(response.data.jwt_token)
        );
      }

      return response;
    } catch (error) {
      return error.response.data;
    }
  },
  // @register
  register: async (name, title, username, email, password) => {
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
          title: title,
          username: username,
          email: email,
          password: password,
        },
        config
      );

      const response = {
        status: request.status,
      };

      return response;
    } catch (error) {
      return error.response.data;
    }
  },
};

export default API;
