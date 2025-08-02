package com.market.vueproject1.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//允许前端跨端口发送请求以及传输数据
@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 允许跨域的路径
                        .allowedOrigins("http://localhost","http://localhost:7070","http://localhost:80") // 允许的前端地址
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH") // 允许的 HTTP 方法
                        .allowCredentials(true); // 允许携带 Cookie
            }
        };
    }
}

