package com.market.vueproject1.controller;

import com.market.vueproject1.entity.Product;
import com.market.vueproject1.entity.Result;
import com.market.vueproject1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private ProductService productService;

    // 搜索接口，查询参数通过 ?keyword=xxx 传入
    @GetMapping("/search")
    public Result search(String keyword) {
        List<Product> products = productService.searchByKeyword(keyword);
        if(products.isEmpty()){
            //System.out.println(keyword+"没找到");
            return  Result.failure("未找到相关结果");
        }
        return Result.success(products);
    }
}
