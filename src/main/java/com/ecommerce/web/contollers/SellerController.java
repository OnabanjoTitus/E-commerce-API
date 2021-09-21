package com.ecommerce.web.contollers;

import com.ecommerce.data.models.BuyerRequestDto;
import com.ecommerce.data.models.Role;
import com.ecommerce.data.models.SellerRequestDto;
import com.ecommerce.services.SellerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class SellerController {
    @Autowired
    SellerServices sellerServices;

    @PostMapping("/sellerRegistration")
    public BuyerRequestDto createAccount(SellerRequestDto sellerRequestDto){
        sellerServices.addAccount(sellerRequestDto);
        return sellerRequestDto;
    }
}
