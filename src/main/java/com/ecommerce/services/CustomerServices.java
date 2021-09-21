package com.ecommerce.services;

import com.ecommerce.data.models.*;

import java.util.List;


public interface CustomerServices {
    CustomerCreationDto addAccount(CustomerCreationDto customerCreationDto);
    List<Customer> findSellerByName(String sellerName);
    List<Product> findProductsByName(String productName);
    CustomerUpdateDto updateAccount(CustomerCreationDto customerCreationDto);
    Product sellerUploadsProduct(String loginToken, ProductDto productDto);
}
