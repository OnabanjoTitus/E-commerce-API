package com.ecommerce.web.contollers;

import com.ecommerce.data.models.CustomerRequestDto;
import com.ecommerce.data.models.CustomerUpdateDto;
import com.ecommerce.services.BuyerServices;
import com.ecommerce.web.exceptions.AccountCreationException;
import com.ecommerce.web.exceptions.AccountException;
import com.ecommerce.web.exceptions.AuthorizationException;
import com.ecommerce.web.exceptions.ProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class BuyerController {

    @Autowired
    BuyerServices buyerServices;

    @PostMapping("/buyerRegistration")
    public ResponseEntity<?> createAccount(CustomerRequestDto customerRequestDto){
           try{
               return new ResponseEntity<>( buyerServices.addAccount(customerRequestDto), HttpStatus.OK);
           }
           catch (AccountCreationException accountCreationException){
               return new ResponseEntity<>(accountCreationException.getMessage(),HttpStatus.BAD_REQUEST);
           }
    }
    @GetMapping("/findSellerByName")
    public ResponseEntity<?> findSellerByName(String sellerName){
        try{

            return new ResponseEntity<>( buyerServices.findSellerByName(sellerName), HttpStatus.OK);
        }
        catch (AccountException accountException){
            return new ResponseEntity<>(accountException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findProductByName")
    public ResponseEntity<?> findProductsByName(String productName){
        try{
            return new ResponseEntity<>( buyerServices.findProductsByName(productName), HttpStatus.OK);
        }
        catch (ProductException accountCreationException){
            return new ResponseEntity<>(accountCreationException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findProductsBySellerName")
    public ResponseEntity<?> findProductBySellerName(String sellerName){
        try{
            return new ResponseEntity<>( buyerServices.findProductsBySellerName(sellerName), HttpStatus.OK);
        }
        catch (ProductException | AccountException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/buyerAccountUpdate")
    public ResponseEntity<?> updateAccount(@RequestHeader("Authorization")String token,CustomerUpdateDto customerUpdateDto){
        try{

            return new ResponseEntity<>( buyerServices.updateAccount(token,customerUpdateDto), HttpStatus.OK);
        }
        catch (AccountException | AuthorizationException exception){
            return new ResponseEntity<>(exception.getMessage(),  HttpStatus.BAD_REQUEST);
        }
    }

}
