package com.market.vueproject1.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    Integer id=0;
    String username;
    String shouhuoName;
    @JsonProperty("phone_number")
    String phoneNumber;

    String address;


}
