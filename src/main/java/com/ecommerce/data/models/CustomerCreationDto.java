package com.ecommerce.data.models;

import lombok.Data;

@Data
public class CustomerCreationDto {
    private String emailAddress;
    private String password;
    private String confirmPassword;
}
