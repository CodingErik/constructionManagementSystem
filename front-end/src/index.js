import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
// import 'bootstrap/dist/css/bootstrap.min.css';
import './bootstrap.min.css';
import axios from 'axios';

axios.interceptors.request.use((request) => {
  // add authorization headers for requests to own api (request url starts with
  // "/api" exactly)
  const token = JSON.parse(localStorage.getItem('token'));
  if (token && request.url.includes(`/api`)) {
    request.headers['Authorization'] = `Bearer ${token}`;
  }
  return request;
});

// interceptAPIAndAddHeader();
ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);
