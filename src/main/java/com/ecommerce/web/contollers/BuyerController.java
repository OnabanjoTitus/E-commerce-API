package com.ecommerce.web.contollers;

import com.ecommerce.data.models.CustomerCreationDto;
import com.ecommerce.data.models.Role;
import com.ecommerce.services.BuyerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class BuyerController {
    @Autowired
    BuyerServices buyerServices;

    @PostMapping("/registration")
    public CustomerCreationDto createAccount(CustomerCreationDto customerCreationDto){
        customerCreationDto.setRole(Role.BUYER);
        buyerServices.addAccount(customerCreationDto);
        return customerCreationDto;
    }

}
