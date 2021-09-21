package com.ecommerce.services;

import com.ecommerce.data.models.*;
import com.ecommerce.data.repository.BuyerRepository;
import com.ecommerce.data.repository.ProductRepository;
import com.ecommerce.data.repository.SellerRepository;
import com.ecommerce.web.exceptions.AccountCreationException;
import com.ecommerce.web.exceptions.AccountException;
import com.ecommerce.web.exceptions.ProductException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BuyerServicesImpl implements BuyerServices {

    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public BuyerRequestDto addAccount(BuyerRequestDto buyerRequestDto) throws AccountCreationException {
        log.info("The Customer Information received is -->{}", buyerRequestDto);
        if(buyerRequestDto.getEmailAddress().isBlank()){
            throw new AccountCreationException("Customer email address cannot be blank, please enter a valid email address ");
        }
        if(buyerRequestDto.getPassword().isBlank()){
            throw new AccountCreationException("Customer password cannot be blank, please enter a valid password");
        }
        if(!buyerRequestDto.getPassword().equals(buyerRequestDto.getConfirmPassword())){
            throw new AccountCreationException("The Passwords do not match please enter matching passwords");
        }
        Buyer buyer= new Buyer();
        buyer.setCustomerRole(Role.BUYER);
        modelMapper.map(buyerRequestDto,buyer);
        buyerRepository.save(buyer);
        return null;
    }

    @Override
    public List<SellerDto> findSellerByName(String sellerName) throws AccountException {
        if(sellerName.isBlank()){
            throw new AccountException("Seller Name cannot be blank");
        }
        List<SellerDto> sellerDtoList =sellerRepository.findCustomerByEmailAddress(sellerName)
                .stream().map(seller ->modelMapper.map(seller, SellerDto.class)).collect(Collectors.toList());
        if(sellerDtoList.isEmpty()){
            throw new AccountException("We do not have seller with the name "+sellerName+" in our records");
        }
        return sellerDtoList;
    }

    @Override
    public List<ProductRequest> findProductsByName(String productName) throws ProductException {
        if(productName.isBlank()){
            throw new ProductException("product Name cannot be blank");
        }
        List<ProductRequest> productRequestList=productRepository.findAllByProductNameContains(productName)
                .stream().map(product -> modelMapper.map(product,ProductRequest.class)).collect(Collectors.toList());
        if(productRequestList.isEmpty()){
            throw new ProductException("We do not have product with the name "+productName+" in our records");
        }
        return productRequestList;
    }

    @Override
    public List<ProductRequest> findProductsBySellerName(String sellerName) throws AccountException, ProductException {
        if(sellerName.isBlank()){
            throw new AccountException("product Name cannot be blank");
        }
        List<ProductRequest> productRequestList=productRepository.findAllBySellerSellerNameContains(sellerName)
                .stream().map(product -> modelMapper.map(product,ProductRequest.class)).collect(Collectors.toList());
        if(productRequestList.isEmpty()){
            throw new ProductException("We do not have seller with the name "+sellerName+" in our records");
        }
        return productRequestList;
    }

    @Override
    public CustomerUpdateDto updateAccount(CustomerUpdateDto customerUpdateDto) throws AccountException {
        if(customerUpdateDto.getEmailAddress().isBlank()){
            throw new AccountException("Customer email address cannot be blank, please enter a valid email address ");
        }
        if(customerUpdateDto.getPreviousPassword().isBlank()){
            throw new AccountException("Customer password cannot be blank, please enter a valid password");
        }
        if(customerUpdateDto.getNewPassword().isBlank()){
            throw new AccountException("Customer password cannot be blank, please enter a valid password");
        }
        Buyer buyer=buyerRepository.findCustomerByEmailAddress(customerUpdateDto.getEmailAddress()).orElseThrow(
                () -> new AccountException("Customer With this email does not exist"));
        modelMapper.map(customerUpdateDto,buyer);
        log.info("buyer information after update -->{}",buyer);
        buyerRepository.save(buyer);
        return customerUpdateDto;
    }


}
