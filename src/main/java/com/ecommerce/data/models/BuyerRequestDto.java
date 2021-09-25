package com.ecommerce.data.models;

import lombok.Data;

@Data
public class BuyerRequestDto {
    private String buyerEmailAddress;
    private String buyerPassword;
    private String confirmPassword;
}
