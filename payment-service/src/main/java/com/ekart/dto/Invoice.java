/**
 * Author: BIKASH
 */
package com.ekart.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Invoice {

    private String CustomerName;
    private String invoiceNo;
    private String recipientName;
    private String city;
    private int zipCode;
    private String state;
    private double Amount;
    private Date purchaseDate;


}
