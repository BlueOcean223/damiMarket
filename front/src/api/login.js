import axios from "axios";

export function login(data){
    return axios.post("http://localhost:8080/api/login",data);
}

export function register(data){
    return axios.put("http://localhost:8080/api/login",data);
}

export function forgot(data){
    return axios.patch("http://localhost:8080/api/login",data);
}