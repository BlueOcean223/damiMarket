package com.market.vueproject1.service;

import com.market.vueproject1.entity.UserBuy;

import java.util.List;

public interface UserBuyService {
    boolean addToCart(UserBuy userBuy);
    List<Integer> getProductIdsByUsername(String username);
    boolean deleteFromCart(UserBuy userBuy);
    boolean addTobuyed(UserBuy userBuy);
    List<Integer> getProductIdFromBuyed(String username);
    boolean deleteFrombuyed(UserBuy userBuy);
    List<String> getStatusFrombuyed(String username);
    boolean updateStatus(UserBuy userBuy);
    String findOneAddress(UserBuy userBuy);
    String findAddressFromBuyed(UserBuy userBuy);
}
