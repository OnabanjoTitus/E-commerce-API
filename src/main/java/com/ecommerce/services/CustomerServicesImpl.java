package com.ecommerce.services;

import com.ecommerce.data.models.Customer;
import com.ecommerce.data.models.CustomerCreationDto;
import com.ecommerce.data.models.CustomerUpdateDto;
import com.ecommerce.data.models.Product;
import com.ecommerce.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServicesImpl implements CustomerServices{

    @Autowired
    CustomerRepository customerRepository;

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
