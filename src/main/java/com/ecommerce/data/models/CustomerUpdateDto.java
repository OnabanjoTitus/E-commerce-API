package com.ecommerce.data.models;

import lombok.Data;

@Data
public class CustomerUpdateDto {
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
}
