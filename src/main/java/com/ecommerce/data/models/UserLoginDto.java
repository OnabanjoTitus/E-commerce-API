package com.ecommerce.data.models;

import lombok.Data;

@Data
public class UserLoginDto {
    private String emailAddress;
    private String password;
}
