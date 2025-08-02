package com.market.vueproject1.service;

import com.market.vueproject1.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> searchByKeyword(String keyword);
    Product getProductById(Integer id);
}
