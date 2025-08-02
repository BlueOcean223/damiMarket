import axios from "axios";

export function getProducts(product_id) {
    return axios.get("http://localhost:8080/api/product",{
        params:{product_id},
    });
}