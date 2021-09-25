package com.ecommerce.web.contollers;

import com.ecommerce.data.models.CustomerRequestDto;
import com.ecommerce.data.models.SellerRequestDto;
import com.ecommerce.services.SellerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class SellerController {
    @Autowired
    SellerServices sellerServices;

    @PostMapping("/sellerRegistration")
    public CustomerRequestDto createAccount(@RequestBody SellerRequestDto sellerRequestDto){
        sellerServices.addAccount(sellerRequestDto);
        return sellerRequestDto;
    }
    @PostMapping("/productUpload")
    public SellerRequestDto uploadProduct(@RequestParam String authentication, SellerRequestDto sellerRequestDto){
        sellerServices.addAccount(sellerRequestDto);
        return sellerRequestDto;
    }
}
