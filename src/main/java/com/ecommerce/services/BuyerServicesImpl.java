package com.ecommerce.services;

import com.ecommerce.data.models.*;
import com.ecommerce.data.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BuyerServicesImpl implements BuyerServices {

    @Autowired
    BuyerRepository buyerRepository;

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
    public CustomerUpdateDto updateAccount(CustomerCreationDto customerCreationDto) {
        return null;
    }


}
