package com.ecommerce.web.contollers;

import com.ecommerce.data.models.Role;
import com.ecommerce.dtos.CustomerUpdateDto;
import com.ecommerce.dtos.ProductDto;
import com.ecommerce.dtos.SellerRequestDto;
import com.ecommerce.dtos.UserLoginDto;
import com.ecommerce.security.exceptions.IncorrectPasswordException;
import com.ecommerce.services.SellerServices;
import com.ecommerce.web.contollers.util.ApiRoutes;
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
@RequestMapping(ApiRoutes.CUSTOMERS)
@Slf4j
public class SellerController {
    @Autowired
    SellerServices sellerServices;

    @PostMapping(ApiRoutes.SELLER+"/seller/sellerRegistration")
    public ResponseEntity<?> createAccount(@RequestBody SellerRequestDto sellerRequestDto){
        try{
            return new ResponseEntity<>( sellerServices.addAccount(sellerRequestDto), HttpStatus.OK);
        }
        catch (AccountCreationException accountCreationException){
            return new ResponseEntity<>(accountCreationException.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping(ApiRoutes.SELLER+"/productUpload")
    public ResponseEntity<?> uploadProduct(@RequestHeader("Authorization")String authentication,@RequestBody ProductDto productDto) {
        try {
            return new ResponseEntity<>(sellerServices.sellerUploadsProduct(authentication, productDto), HttpStatus.OK);
        } catch (ProductException | AccountException | AuthorizationException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping(ApiRoutes.SELLER+"/findSellerByName")
    public ResponseEntity<?> findSellerByName(@RequestBody String sellerName){
        try{

            return new ResponseEntity<>( sellerServices.findSellerByName(sellerName), HttpStatus.OK);
        }
        catch (AccountException accountException){
            return new ResponseEntity<>(accountException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(ApiRoutes.SELLER+"/findProductByName")
    public ResponseEntity<?> findProductsByName(@RequestBody String productName){
        try{
            return new ResponseEntity<>( sellerServices.findProductsByName(productName), HttpStatus.OK);
        }
        catch (ProductException accountCreationException){
            return new ResponseEntity<>(accountCreationException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(ApiRoutes.SELLER+"/findProductsBySellerName")
    public ResponseEntity<?> findProductBySellerName(@RequestBody String sellerName){
        try{
            return new ResponseEntity<>( sellerServices.findProductsBySellerName(sellerName), HttpStatus.OK);
        }
        catch (ProductException | AccountException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ApiRoutes.SELLER+"/sellerLogin")
    public  ResponseEntity<?>buyerLogin(@RequestBody UserLoginDto userLoginDto){
        try{
            userLoginDto.setRole(Role.SELLER);
            return new ResponseEntity<>(sellerServices.sellerLogin(userLoginDto),HttpStatus.OK);
        }
        catch (AuthorizationException | IncorrectPasswordException | javax.security.auth.login.AccountException accountException){
            return new ResponseEntity<>(accountException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(ApiRoutes.SELLER+"/buyerAccountUpdate")
    public ResponseEntity<?> updateAccount(@RequestHeader("Authorization")String token,@RequestBody CustomerUpdateDto customerUpdateDto){
        try{
            return new ResponseEntity<>(sellerServices.updateAccount(token,customerUpdateDto), HttpStatus.OK);
        }
        catch (AccountException | AuthorizationException exception){
            return new ResponseEntity<>(exception.getMessage(),  HttpStatus.BAD_REQUEST);
        }
    }

}
