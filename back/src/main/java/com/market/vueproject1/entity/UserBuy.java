package com.market.vueproject1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBuy {
    private String username;

    //将前端数据映射
    //前端数据同名时，可以自动映射，但是不同名时需要手动添加映射，添加后不会再匹配原来的名字
    @JsonProperty("product_id")
    private Integer productId;

    private String status="未完成";

    private String address="无";
}
