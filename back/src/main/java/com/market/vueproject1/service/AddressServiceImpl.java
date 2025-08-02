package com.market.vueproject1.service;

import com.market.vueproject1.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.market.vueproject1.mapper.AddressMapper;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    public List<String> searchAddressByUsername(String username) {
        return addressMapper.getAddressByUsername(username);
    }

    public List<Address> searchAllAddress(String username){
        return addressMapper.getAllAddress(username);
    }

    public boolean addAddress(String username,String shouhuoName,String phoneNumber,String address){
        return addressMapper.insertAddress(username,shouhuoName,phoneNumber,address) > 0;
    }

    public boolean deleteAddress(Address address){
        return addressMapper.deleteAddress(address) > 0;
    }

    public boolean updateAddress(Address address){
        return addressMapper.updateAddress(address) > 0;
    }
}
