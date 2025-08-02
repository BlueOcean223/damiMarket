package com.market.vueproject1.mapper;

import com.market.vueproject1.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM userdata WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO userdata (username,password) VALUES (#{username},#{password})")
    int insertUser(User user);

    @Update("UPDATE userdata SET password = #{password} WHERE username = #{username}")
    int updatePassword(User user);
}
