package com.ecommerce.dtos;

import com.ecommerce.data.models.Role;
import lombok.Data;

@Data
public class UserLoginDto {
    private String emailAddress;
    private String password;
    private Role role;
}
