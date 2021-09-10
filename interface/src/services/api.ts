import axios from 'axios';


const axiosConfig = {
    baseURL: 'http://localhost:8080/',
};


export const api = axios.create(axiosConfig);
