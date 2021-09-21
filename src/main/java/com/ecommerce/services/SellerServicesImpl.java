package com.ecommerce.services;

import com.ecommerce.data.models.*;
import com.ecommerce.data.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SellerServicesImpl  implements SellerServices{
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public CustomerCreationDto addAccount(CustomerCreationDto customerCreationDto) {
        return null;
    }

    @Override
    public List<Customer> findSellerByName(String sellerName) {
        return null;
    }

    @Override
    public List<Product> findProductsByName(String productName) {
        return null;
    }

    @Override
    public List<Product> findProductsBySellerName(String sellerName) {
        return null;
    }

    @Override
    public CustomerUpdateDto updateAccount(CustomerCreationDto customerCreationDto) {
        return null;
    }

    @Override
    public Product sellerUploadsProduct(String loginToken, ProductDto productDto) {
        return null;
    }
}
