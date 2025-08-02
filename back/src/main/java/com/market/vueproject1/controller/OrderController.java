package com.market.vueproject1.controller;


import com.market.vueproject1.entity.Product;
import com.market.vueproject1.entity.Result;
import com.market.vueproject1.entity.UserBuy;
import com.market.vueproject1.service.ProductService;
import com.market.vueproject1.service.UserBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private UserBuyService userBuyService;

    @Autowired
    private ProductService productService;

    //获取用户已经购买的商品信息
    @GetMapping("/orders")
    public Result getOrdersItems(String username){
        if(username == null || username.isEmpty()){
            return Result.failure("用户名不能为空！");
        }
        try{
            //获取用户购买的商品ID列表
            List<Integer> productIds = userBuyService.getProductIdFromBuyed(username);

            // 根据商品ID查询商品详细信息
            List<Product> cartItems = new ArrayList<>();
            for(Integer productId : productIds){
                Product product = productService.getProductById(productId);
                cartItems.add(product);
            }
            return Result.success(cartItems);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }

    //获取用户购买商品订单状态
    @GetMapping("/orders/status")
    public Result getOrdersStatus(String username){
        if(username == null || username.isEmpty()){
            return Result.failure("参数异常");
        }
        List<String> status = userBuyService.getStatusFrombuyed(username);
        if(status == null){
            return Result.failure("查询失败，订单里没有该商品");
        }else{
            return Result.success(status);
        }
    }

    @DeleteMapping("/orders")
    public Result deleteOrders(@RequestBody UserBuy userBuy){
        if(userBuy.getUsername() != null && userBuy.getProductId() != null){
            boolean success = userBuyService.deleteFrombuyed(userBuy);
            if(success){
                return Result.success("成功从我的订单移除商品！");
            }else{
                return Result.failure("我的订单没有该商品");
            }
        }else{
            return Result.failure("请求参数错误！");
        }
    }
//    更新订单信息
    @PutMapping("/orders")
    public Result updateOrders(@RequestBody UserBuy userBuy){
        if(userBuy.getUsername() != null && userBuy.getProductId() != null){
            boolean success = userBuyService.updateStatus(userBuy);
            if(success){
                return Result.success("成功更新商品状态！");
            }else{
                return Result.failure("我的订单没有该商品");
            }
        }else{
            System.out.println(userBuy.getUsername());
            System.out.println(userBuy.getProductId());
            return Result.failure("请求参数错误！");
        }

    }
}
