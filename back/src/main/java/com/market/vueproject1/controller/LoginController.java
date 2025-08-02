package com.market.vueproject1.controller;


import com.market.vueproject1.entity.User;
import com.market.vueproject1.service.UserService;
import com.market.vueproject1.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User loginUser) {

        User user = userService.findByUsername(loginUser.getUsername());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            return Result.success("登陆成功！");
        }else{
            return Result.failure("用户名或密码错误！");
        }
    }

    @PutMapping("/login")
    public Result register(@RequestBody User registerUser){
        try{
            User user = userService.findByUsername(registerUser.getUsername());
            if(user != null){
                return Result.failure("该用户已存在，请重新输入");
            }else{
                boolean success = userService.insertUser(registerUser);
                if(success){
                    return Result.success("注册成功！");
                }else{
                    return Result.failure("注册失败！");
                }
            }
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }

    @PatchMapping("/login")
    public Result update(@RequestBody User updateUser){
        try{
            if(updateUser.getUsername() == null || updateUser.getPassword() == null){
                return Result.failure("传递的参数是空参");
            }
            boolean success = userService.updateUser(updateUser);
            if(success){
                return Result.success("更新成功！");
            }else{
                return Result.failure("更新失败！");
            }
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }
}
