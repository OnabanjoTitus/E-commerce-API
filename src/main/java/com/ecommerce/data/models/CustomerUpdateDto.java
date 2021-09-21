package com.ecommerce.data.models;

import lombok.Data;

@Data
public class CustomerUpdateDto {
    private String emailAddress;
    private String previousPassword;
    private String newPassword;
    private Role role;
}
