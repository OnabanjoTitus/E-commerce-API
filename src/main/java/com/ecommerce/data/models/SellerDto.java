package com.ecommerce.data.models;

import lombok.Data;


import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
public class SellerDto {

    private String sellerEmailAddress;
    private String sellerName;
    private String sellerLocation;

    public SellerDto(){

    }

    public SellerDto(String sellerEmailAddress, String sellerName, String sellerLocation) {
        this.sellerEmailAddress=sellerEmailAddress;
        this.sellerName=sellerName;
        this.sellerLocation=sellerLocation;
    }
}
