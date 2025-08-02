package com.market.vueproject1.service;

import com.market.vueproject1.entity.UserBuy;
import com.market.vueproject1.mapper.UserBuyMapper;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBuyServiceImpl implements UserBuyService {

    @Autowired
    private UserBuyMapper userBuyMapper;

    public boolean addToCart(UserBuy userBuy) {
        return userBuyMapper.insertUserBuy(userBuy) > 0;
    }

    public List<Integer> getProductIdsByUsername(String username) {
        return userBuyMapper.findProductIdsByUsername(username);
    }

    public boolean deleteFromCart(UserBuy userBuy) {
        return userBuyMapper.deleteUserBuy(userBuy) > 0;
    }

    public boolean addTobuyed(UserBuy userBuy) {
        return userBuyMapper.insertBuyed(userBuy) > 0;
    }

    public List<Integer> getProductIdFromBuyed(String username){
        return userBuyMapper.findProductIdFromBuyed(username);
    }

    public  boolean deleteFrombuyed(UserBuy userBuy){
        return userBuyMapper.deleteFromBuyed(userBuy) > 0;
    }

    public List<String> getStatusFrombuyed(String username){
        return userBuyMapper.findStatusFromBuyed(username);
    }

    public boolean updateStatus(UserBuy userBuy){
        return userBuyMapper.updateStatus(userBuy) > 0;
    }

    public String findOneAddress(UserBuy userBuy){
        return userBuyMapper.findOneAddress(userBuy);
    }

    public String findAddressFromBuyed(UserBuy userBuy){
        return userBuyMapper.findOneAddressFromBuyed(userBuy);
    }
}
