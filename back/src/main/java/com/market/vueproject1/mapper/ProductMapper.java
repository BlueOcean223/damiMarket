package com.market.vueproject1.mapper;


import com.market.vueproject1.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM products WHERE keyword = #{keyword}")
    List<Product> findByKeyword(String keyword);

    @Select("SELECT * FROM products WHERE id = #{id}")
    Product getProductById(Integer id);
}
