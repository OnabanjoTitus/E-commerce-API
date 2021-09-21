package com.ecommerce.web.contollers;

import com.ecommerce.data.models.BuyerRequestDto;
import com.ecommerce.services.BuyerServices;
import com.ecommerce.web.exceptions.AccountCreationException;
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

    @PostMapping("/buyerRegistration")
    public BuyerRequestDto createAccount(BuyerRequestDto buyerRequestDto) throws AccountCreationException {
        buyerServices.addAccount(buyerRequestDto);
        return buyerRequestDto;
    }

}
