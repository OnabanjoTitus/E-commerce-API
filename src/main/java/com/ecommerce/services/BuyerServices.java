package com.ecommerce.services;

import com.ecommerce.data.models.*;
import com.ecommerce.web.exceptions.AccountCreationException;

import java.util.List;


public interface BuyerServices {
    CustomerCreationDto addAccount(CustomerCreationDto customerCreationDto) throws AccountCreationException;
    List<Customer> findSellerByName(String sellerName);
    List<Product> findProductsByName(String productName);
    List<Product> findProductsBySellerName(String sellerName);
    CustomerUpdateDto updateAccount(CustomerCreationDto customerCreationDto);
}
