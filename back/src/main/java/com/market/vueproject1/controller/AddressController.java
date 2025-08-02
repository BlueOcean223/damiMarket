package com.market.vueproject1.controller;


import com.market.vueproject1.entity.Result;
import com.market.vueproject1.service.AddressService;
import com.market.vueproject1.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/address")
    public Result getAddress(String username) {
        List<String> address = addressService.searchAddressByUsername(username);
        if(address.isEmpty()){
            return Result.failure("用户地址不存在");
        }else{
            return Result.success(address);
        }
    }

    @GetMapping("/allAddress")
    public Result getAllAddress(String username) {
        List<Address> addresses = addressService.searchAllAddress(username);
        if(addresses.isEmpty()){
            return Result.failure("用户地址不存在");
        }else{
            return Result.success(addresses);
        }
    }
    @PostMapping("/address")
    public Result addAddress(@RequestBody Address address) {
        try {
            boolean success = addressService.addAddress(address.getUsername(),address.getShouhuoName(),address.getPhoneNumber(),address.getAddress());
            if(success){
                return Result.success("成功添加地址！");
            }else{
                return Result.failure("添加地址失败！");
            }
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }

    @DeleteMapping("/address")
    public Result deleteAddress(@RequestBody Address address) {
        try {
            boolean success = addressService.deleteAddress(address);
            if(success){
                return Result.success("删除地址成功！");
            }else{
                System.out.println(address);
                return Result.failure("删除地址失败！");
            }
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }

    @PatchMapping("/address")
    public Result updateAddress(@RequestBody Address address) {
        try{
            boolean success = addressService.updateAddress(address);
            if(success){
                return Result.success("修改地址信息成功！");
            }else{
                return Result.failure("修改地址信息失败！");
            }
        }catch (Exception e){
            System.out.println(address);
            return Result.failure(e.getMessage());
        }
    }
}
