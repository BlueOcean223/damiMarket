import axios from "axios";

export function get(username){
    return axios.get("http://localhost:8080/api/address",{
        params:{username}
    });
}

export function getAll(username){
    return axios.get("http://localhost:8080/api/allAddress",{
        params:{username}
    });
}

export function add(data){
    return axios.post("http://localhost:8080/api/address",data);
}

export function remove(data){
    return axios.delete("http://localhost:8080/api/address",{
        data: data
    });
}

export function update(data){
    return axios.patch("http://localhost:8080/api/address",data);
}