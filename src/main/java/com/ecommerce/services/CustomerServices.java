package com.ecommerce.services;

import com.ecommerce.data.models.Customer;
import com.ecommerce.data.models.CustomerCreationDto;
import com.ecommerce.data.models.CustomerUpdateDto;
import com.ecommerce.data.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerServices {
    CustomerCreationDto addAccount(CustomerCreationDto customerCreationDto);
    List<Customer> findSellerByName(String sellerName);
    List<Product> findProductsByName(String productName);
    CustomerUpdateDto updateAccount(CustomerCreationDto customerCreationDto);
}
