// request.js
import axios from "axios";

// 创建一个 axios 实例，统一配置 baseURL
const instance = axios.create({
    baseURL: "http://localhost:8080/api"
});

export default instance;
