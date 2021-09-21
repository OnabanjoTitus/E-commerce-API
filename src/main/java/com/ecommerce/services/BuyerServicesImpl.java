package com.ecommerce.services;

import com.ecommerce.data.models.*;
import com.ecommerce.data.repository.BuyerRepository;
import com.ecommerce.web.exceptions.AccountCreationException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class BuyerServicesImpl implements BuyerServices {

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CustomerCreationDto addAccount(CustomerCreationDto customerCreationDto) throws AccountCreationException {
        log.info("The Customer Information received is -->{}",customerCreationDto);
        if(customerCreationDto.getEmailAddress().isBlank()){
            throw new AccountCreationException("Account Name cannot be blank, please enter a valid name");
        }
        if(customerCreationDto.getPassword().isBlank()){
            throw new AccountCreationException("Account Name cannot be blank, please enter a valid name");
        }

        Buyer buyer= new Buyer();
        modelMapper.map(customerCreationDto,buyer);
        buyerRepository.save(buyer);

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


}
