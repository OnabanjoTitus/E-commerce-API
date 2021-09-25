package com.ecommerce.web.contollers;

import com.ecommerce.data.models.ProductDto;
import com.ecommerce.data.models.SellerRequestDto;
import com.ecommerce.services.SellerServices;
import com.ecommerce.web.exceptions.AccountCreationException;
import com.ecommerce.web.exceptions.AccountException;
import com.ecommerce.web.exceptions.ProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class SellerController {
    @Autowired
    SellerServices sellerServices;

    @PostMapping("/seller/sellerRegistration")
    public ResponseEntity<?> createAccount(@RequestBody SellerRequestDto sellerRequestDto){
        try{
            return new ResponseEntity<>( sellerServices.addAccount(sellerRequestDto), HttpStatus.OK);
        }
        catch (AccountCreationException accountCreationException){
            return new ResponseEntity<>(accountCreationException.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/productUpload")
    public ResponseEntity<?> uploadProduct(@RequestHeader("Authorization")String authentication, ProductDto productDto) {
        try {
            return new ResponseEntity<>(sellerServices.sellerUploadsProduct(authentication, productDto), HttpStatus.OK);
        } catch (ProductException | AccountException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

}
