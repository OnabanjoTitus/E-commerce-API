package com.ecommerce.data.models;

import lombok.Data;

@Data
public class BuyerRequestDto {
    private String emailAddress;
    private String password;
    private String confirmPassword;
    private Role role;
}
