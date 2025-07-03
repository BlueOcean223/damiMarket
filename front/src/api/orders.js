import axios from "axios";

export function get(username){
    return axios.get("http://localhost:8080/api/orders",{
        params:{
            username:username
        }
    });
}

export function getStatus(username){
    return axios.get("http://localhost:8080/api/orders/status",{
        params:{
            username:username
        }
    });
}

export function deleteOrder(data){
    return axios.delete("http://localhost:8080/api/orders",{
        data:data
    });
}

export function putOrder(data){
    return axios.put("http://localhost:8080/api/orders",data);
}