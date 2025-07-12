// address.js
import axios from "./request"; 

export function get(username) {
    return axios.get("/address", {
        params: { username }
    });
}

export function getAll(username) {
    return axios.get("/allAddress", {
        params: { username }
    });
}

export function add(data) {
    return axios.post("/address", data);
}

export function remove(data) {
    return axios.delete("/address", {
        data: data
    });
}

export function update(data) {
    return axios.patch("/address", data);
}
