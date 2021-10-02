package com.ecommerce.dtos;

import lombok.Data;

@Data
public class UserLoginDto {
    private String emailAddress;
    private String password;
}
