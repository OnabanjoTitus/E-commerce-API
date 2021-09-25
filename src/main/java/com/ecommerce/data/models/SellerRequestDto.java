package com.ecommerce.data.models;

import lombok.Data;

@Data
public class SellerRequestDto{
    private String sellerEmailAddress;
    private String sellerPassword;
    private String sellerName;
    private String sellerLocation;
    private String confirmPassword;
}
