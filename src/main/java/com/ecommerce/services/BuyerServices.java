package com.ecommerce.services;

import com.ecommerce.data.models.*;

import java.util.List;


public interface BuyerServices {
    CustomerCreationDto addAccount(CustomerCreationDto customerCreationDto);
    List<Customer> findSellerByName(String sellerName);
    List<Product> findProductsByName(String productName);
    List<Product> findProductsBySellerName(String sellerName);
    CustomerUpdateDto updateAccount(CustomerCreationDto customerCreationDto);
}
