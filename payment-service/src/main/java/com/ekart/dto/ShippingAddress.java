package com.ekart.dto;

import lombok.Data;

@Data
public class ShippingAddress {

    private String name;
    private String city;
    private int zipCode;
    private String state;
}
