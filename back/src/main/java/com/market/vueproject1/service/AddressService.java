package com.market.vueproject1.service;

import com.market.vueproject1.entity.Address;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.List;

public interface AddressService {
    List<String> searchAddressByUsername(String username);

    List<Address> searchAllAddress(String username);

    boolean addAddress(String username,String shouhuoName, String phoneNumber, String address);

    boolean deleteAddress(Address address);

    boolean updateAddress(Address address);
}
