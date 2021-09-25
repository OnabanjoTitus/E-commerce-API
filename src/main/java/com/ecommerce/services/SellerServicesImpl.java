package com.ecommerce.services;

import com.ecommerce.data.models.*;
import com.ecommerce.data.repository.SellerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServicesImpl  implements SellerServices{

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CustomerRequestDto addAccount(SellerRequestDto sellerRequestDto) {
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
    public CustomerUpdateDto updateAccount(SellerRequestDto sellerRequestDto) {
        return null;
    }

    @Override
    public Product sellerUploadsProduct(String loginToken, ProductDto productDto) {
        return null;
    }
}
