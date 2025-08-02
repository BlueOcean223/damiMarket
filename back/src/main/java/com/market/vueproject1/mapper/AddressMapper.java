package com.market.vueproject1.mapper;

import com.market.vueproject1.entity.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper {
    @Select("SELECT address FROM useraddress WHERE username = #{username}")
    List<String> getAddressByUsername(String username);

    @Select("SELECT * FROM useraddress WHERE username = #{username}")
    List<Address> getAllAddress(String username);

    @Insert("INSERT INTO useraddress (username,shouhuoName,phoneNumber,address) VALUES (#{username},#{shouhuoName},#{phoneNumber},#{address})")
    int insertAddress(String username, String shouhuoName,String phoneNumber, String address);

    @Delete("DELETE FROM useraddress WHERE username = #{username} and shouhuoName=#{shouhuoName} and phoneNumber = #{phoneNumber} and address = #{address}")
    int deleteAddress(Address address);

    @Update("UPDATE useraddress SET username = #{username},shouhuoName = #{shouhuoName},phoneNumber = #{phoneNumber},address = #{address} WHERE id = #{id}")
    int updateAddress(Address address);
}
