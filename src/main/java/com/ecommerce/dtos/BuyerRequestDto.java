package com.ecommerce.dtos;

import lombok.Data;

@Data
public class BuyerRequestDto {
    private String buyerEmailAddress;
    private String buyerPassword;
    private String confirmPassword;
}
