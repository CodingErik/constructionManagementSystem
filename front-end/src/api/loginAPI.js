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
        // data: request.data,
        status: request.status,
      };

      return response;
    } catch (error) {
      return error.response.data;
    }
  },
  // loginWithJwt: (userInfo) => {
  //   return axios.post(`${baseUrl}/authenticate`, userInfo);
  // },
};

export default API;
// /api/employees/register
