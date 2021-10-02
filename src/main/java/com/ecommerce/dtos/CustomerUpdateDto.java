package com.ecommerce.dtos;

import com.ecommerce.data.models.Role;
import lombok.Data;

@Data
public class CustomerUpdateDto {
    private String emailAddress;
    private String previousPassword;
    private String newPassword;
    private Role role;
}
