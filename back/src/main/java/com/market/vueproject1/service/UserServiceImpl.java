package com.market.vueproject1.service;

import com.market.vueproject1.entity.User;
import com.market.vueproject1.mapper.UserMapper;
import com.market.vueproject1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public boolean insertUser(User user){
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public boolean updateUser(User user){
        return userMapper.updatePassword(user) > 0;
    }
}
