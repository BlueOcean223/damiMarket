import axios from "axios";

export function getCartItem(username){
    return axios.get("http://localhost:8080/api/cart",{
        params: { username}
    });
} 

export function remove(data){
    return axios.delete("http://localhost:8080/api/cart",{
        data: data
    });
}

//将购物车结算的商品发送到我的订单中
export function checkout(data){
    return axios.post("http://localhost:8080/api/checkout",data);
}

//获取单个已购商品的收货地址
export function getCheckoutAddress(data){
    return axios.get("http://localhost:8080/api/checkout",{
       params: { 
        username: data.username,
        product_id: data.product_id
    }
    });
}

//将商品添加到购物车
export function add(data){
    return axios.post("http://localhost:8080/api/cart",data);
}

//检测购物车中是否已经有该商品
export function checkIsInCart(data){
    return axios.get("http://localhost:8080/api/cart/ishave",{
        params: { 
            username: data.username,
            product_id: data.product_id
        }
    });
}