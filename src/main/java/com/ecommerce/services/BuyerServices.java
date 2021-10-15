package com.ecommerce.services;

import com.ecommerce.data.models.*;
import com.ecommerce.dtos.*;
import com.ecommerce.security.exceptions.IncorrectPasswordException;
import com.ecommerce.security.security.JWTToken;
import com.ecommerce.web.exceptions.AccountCreationException;
import com.ecommerce.web.exceptions.AccountException;
import com.ecommerce.web.exceptions.AuthorizationException;
import com.ecommerce.web.exceptions.ProductException;

import java.util.List;


public interface BuyerServices {
    BuyerDto addAccount(BuyerRequestDto buyerRequestDto) throws AccountCreationException;
    List<SellerDto> findSellerByName(String sellerName) throws AccountException;
    List<ProductRequest> findProductsByName(String productName) throws ProductException;
    JWTToken buyerLogin(UserLoginDto userLoginDTO) throws AuthorizationException, IncorrectPasswordException, javax.security.auth.login.AccountException;
    List<ProductRequest> findProductsBySellerName(String sellerName) throws ProductException, AccountException;
    CustomerUpdateDto updateAccount(String authentication, CustomerUpdateDto customerUpdateDto) throws AccountException, AuthorizationException;
}
