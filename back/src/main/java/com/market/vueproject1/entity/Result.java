package com.market.vueproject1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//统一响应结果
public class Result {
    private Integer success;
    private String message;
    private String token;
    private Object data;

    public static Result success() {
        return new Result(1,"success","some-jwt-token",null);
    }
    public static Result success(String message) {
        return new Result(1,message,"some-jwt-token",null);
    }
    public static Result success(Object data) {
        return new Result(1,"success","some-jwt-token",data);
    }
    public static Result failure() {
        return new Result(0,"failure","",null);
    }
    public static Result failure(String message) {
        return new Result(0,message,"",null);
    }
}
