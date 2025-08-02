package com.market.vueproject1.controller;


import com.market.vueproject1.entity.Product;
import com.market.vueproject1.entity.Result;
import com.market.vueproject1.entity.UserBuy;
import com.market.vueproject1.service.ProductService;
import com.market.vueproject1.service.UserBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private UserBuyService userBuyService;

    @Autowired
    private ProductService productService;

    @PostMapping("/cart")
    public Result addToCart(@RequestBody UserBuy userBuy){
        if(userBuy.getUsername() != null && userBuy.getProductId() != null){
            boolean success = userBuyService.addToCart(userBuy);
            if(success){
                return Result.success("加入购物车成功！");
            }else{
                return Result.failure("加入购物车失败！");
            }
        }else{
            return Result.failure("请求参数错误！");
        }
    }


    @GetMapping("/cart")
    public Result getCartItems(String username){
        if(username == null || username.isEmpty()){
            return Result.failure("用户名不能为空！");
        }
        try{
            //获取用户购买的商品ID列表
            List<Integer> productIds = userBuyService.getProductIdsByUsername(username);

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

    @DeleteMapping("/cart")
    public Result deleteFromCart(@RequestBody UserBuy userBuy){
        if(userBuy.getUsername() != null && userBuy.getProductId() != null){
            boolean success = userBuyService.deleteFromCart(userBuy);
            if(success){
                return Result.success("成功从购物车移除商品！");
            }else{
                return Result.failure("购物车没有该商品");
            }
        }else{
            return Result.failure("请求参数错误！");
        }
    }
    //结算购物车
    @PostMapping("/checkout")
    public Result addTobuyed(@RequestBody UserBuy userBuy){
        if(userBuy.getUsername() != null && userBuy.getProductId() != null){
            // 查找购物车结算商品的收货地址
            String address = userBuyService.findOneAddress(userBuy);
            userBuy.setAddress(address);

            boolean success = userBuyService.addTobuyed(userBuy);
            if(success){
                return Result.success("结算成功！");
            }else{
                return Result.failure("结算失败！");
            }
        }else{
            System.out.println(userBuy);
            return Result.failure("请求参数错误！");
        }
    }
    //获取已购商品的收货地址
    @GetMapping("/checkout")
    public Result getCheckoutAddress(@RequestParam String username,@RequestParam Integer product_id){
        UserBuy userBuy = new UserBuy();
        userBuy.setUsername(username);
        userBuy.setProductId(product_id);
        if(userBuy.getUsername() != null && userBuy.getProductId() != null){
            Object address = userBuyService.findAddressFromBuyed(userBuy);
            if(address != null){
                return Result.success(address);
            }else{
                return Result.failure("订单中未找到该商品!");
            }
        }else{
            System.out.println(userBuy);
            return Result.failure("请求参数错误!");
        }
    }
    //查看购物车是否已有该商品
    @GetMapping("/cart/ishave")
    public Result getIshave(@RequestParam String username,@RequestParam Integer product_id){
        UserBuy userBuy = new UserBuy();
        userBuy.setUsername(username);
        userBuy.setProductId(product_id);
        if(userBuy.getUsername() != null && userBuy.getProductId() != null){
            Object address = userBuyService.findOneAddress(userBuy);
            if(address != null){
                return Result.success(address);
            }else{
                return Result.failure("购物车尚未添加该商品");
            }
        }else{
            System.out.println(userBuy);
            return Result.failure("请求参数错误!");
        }
    }
}
