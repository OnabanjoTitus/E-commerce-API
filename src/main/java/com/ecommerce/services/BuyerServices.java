package com.ecommerce.services;

import com.ecommerce.data.models.*;
import com.ecommerce.web.exceptions.AccountCreationException;
import com.ecommerce.web.exceptions.AccountException;
import com.ecommerce.web.exceptions.ProductException;

import java.util.List;


public interface BuyerServices {
    BuyerRequestDto addAccount(BuyerRequestDto buyerRequestDto) throws AccountCreationException;
    List<SellerDto> findSellerByName(String sellerName) throws AccountException;
    List<ProductRequest> findProductsByName(String productName) throws ProductException;
    List<ProductRequest> findProductsBySellerName(String sellerName) throws ProductException, AccountException;
    CustomerUpdateDto updateAccount(CustomerUpdateDto customerUpdateDto) throws AccountException;
}
