package com.ecommerce.data.models;

import lombok.Data;

@Data
public class CustomerRequestDto {
    private String emailAddress;
    private String password;
    private String confirmPassword;
    private Role role;
}
