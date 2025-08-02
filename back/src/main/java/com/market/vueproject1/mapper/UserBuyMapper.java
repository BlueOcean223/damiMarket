package com.market.vueproject1.mapper;

import com.market.vueproject1.entity.UserBuy;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserBuyMapper {
    @Insert("INSERT INTO userbuy (username,product_id,address) VALUES (#{username},#{productId},#{address})")
    int insertUserBuy(UserBuy userBuy);
    //程序会根据类的属性名进行同名自动映射

    @Select("SELECT product_id FROM userbuy WHERE username = #{username}")
    List<Integer> findProductIdsByUsername(String username);

    @Delete("DELETE FROM userbuy WHERE username=#{username} AND product_id=#{productId}")
    int deleteUserBuy(UserBuy userBuy);

    @Insert("INSERT INTO buyed (username, product_id,status,address) VALUE (#{username},#{productId},'未完成',#{address})")
    int insertBuyed(UserBuy userBuy);

    @Select("SELECT product_id FROM buyed WHERE username = #{username}")
    List<Integer> findProductIdFromBuyed(String username);

    @Delete("DELETE FROM buyed WHERE username=#{username} AND product_id=#{productId}")
    int deleteFromBuyed(UserBuy userBuy);

    @Select("SELECT status FROM buyed WHERE username = #{username}")
    List<String> findStatusFromBuyed(String username);

    @Update("UPDATE buyed SET status = '完成' WHERE username = #{username} and product_id = #{productId}")
    int updateStatus(UserBuy userBuy);

    @Select("SELECT address FROM userbuy WHERE username = #{username} AND product_id = #{productId}")
    String findOneAddress(UserBuy userBuy);

    @Select("SELECT address FROM buyed WHERE username = #{username} AND product_id = #{productId}")
    String findOneAddressFromBuyed(UserBuy userBuy);
}
