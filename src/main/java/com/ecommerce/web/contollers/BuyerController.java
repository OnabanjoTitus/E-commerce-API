package com.ecommerce.web.contollers;

import com.ecommerce.data.models.BuyerRequestDto;
import com.ecommerce.services.BuyerServices;
import com.ecommerce.web.exceptions.AccountCreationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createAccount(BuyerRequestDto buyerRequestDto){
           try{
               return new ResponseEntity<>( buyerServices.addAccount(buyerRequestDto), HttpStatus.OK);
           }
           catch (AccountCreationException accountCreationException){
               return new ResponseEntity<>(accountCreationException.getMessage(),HttpStatus.BAD_REQUEST);
           }
    }

}
