package com.ecommerce.web.contollers;

import com.ecommerce.data.models.CustomerCreationDto;
import com.ecommerce.data.models.Role;
import com.ecommerce.services.BuyerServices;
import com.ecommerce.services.SellerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

public class SellerController {
    @Autowired
    SellerServices sellerServices;

    @PostMapping("/buyerRegistration")
    public CustomerCreationDto createAccount(CustomerCreationDto customerCreationDto){
        customerCreationDto.setRole(Role.BUYER);
        sellerServices.addAccount(customerCreationDto);
        return customerCreationDto;
    }
}
