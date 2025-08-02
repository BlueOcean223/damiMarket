package com.market.vueproject1.service;


import com.market.vueproject1.entity.Product;
import com.market.vueproject1.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> searchByKeyword(String keyword){
        //System.out.println("Searching for keyword: " + keyword); // 输出查询的关键词
        return productMapper.findByKeyword(keyword);
    }

    @Override
    public Product getProductById(Integer id){
        return productMapper.getProductById(id);
    }
}
