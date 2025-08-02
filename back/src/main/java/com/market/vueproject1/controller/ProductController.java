package com.market.vueproject1.controller;


import com.market.vueproject1.entity.Product;
import com.market.vueproject1.entity.Result;
import com.market.vueproject1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 搜索接口，查询参数通过 ?product_id=xxx 传入
    @GetMapping("/product")
    public Result getProductById(Integer product_id) {
        Product product = productService.getProductById(product_id);
        System.out.println(product_id);
        if (product != null) {
            return  Result.success(product);
        }else{
            return Result.failure("没有该商品");
        }
    }
}
