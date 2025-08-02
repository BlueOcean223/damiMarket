package com.market.vueproject1.service;

import com.market.vueproject1.entity.User;

public interface UserService {
    User findByUsername(String username);
    boolean insertUser(User user);
    boolean updateUser(User user);
}
