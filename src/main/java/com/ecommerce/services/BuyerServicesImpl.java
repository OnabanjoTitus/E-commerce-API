package com.ecommerce.services;

import com.ecommerce.data.models.*;
import com.ecommerce.data.repository.BuyerRepository;
import com.ecommerce.data.repository.ProductRepository;
import com.ecommerce.data.repository.SellerRepository;
import com.ecommerce.dtos.*;
import com.ecommerce.security.exceptions.IncorrectPasswordException;
import com.ecommerce.security.security.JWTToken;
import com.ecommerce.security.service.UserPrincipalService;
import com.ecommerce.services.EmailServices.EmailService;
import com.ecommerce.web.exceptions.AccountCreationException;
import com.ecommerce.web.exceptions.AccountException;
import com.ecommerce.web.exceptions.AuthorizationException;
import com.ecommerce.web.exceptions.ProductException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BuyerServicesImpl implements BuyerServices {
    @Autowired
    EmailService emailService;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserPrincipalService userPrincipalService;

    @Override
    public BuyerDto addAccount(BuyerRequestDto buyerRequestDto) throws AccountCreationException {
        log.info("The Customer Information received is -->{}", buyerRequestDto);
        if(buyerRequestDto.getBuyerEmailAddress().isBlank()){
            throw new AccountCreationException("Customer email address cannot be blank, please enter a valid email address ");
        }
        if(buyerRequestDto.getBuyerPassword().isBlank()){
            throw new AccountCreationException("Customer password cannot be blank, please enter a valid password");
        }
        if(sellerRepository.findSellerBySellerEmailAddress(buyerRequestDto.getBuyerEmailAddress()).isPresent()){
            throw new AccountCreationException("Customer is already a seller, you cannot have multiple accounts with the same email");
        }
        if(!buyerRequestDto.getBuyerPassword().equals(buyerRequestDto.getConfirmPassword())){
            throw new AccountCreationException("The Passwords do not match please enter matching passwords");
        }

        Buyer buyer= new Buyer();
        buyer.setRole(Role.BUYER);
        modelMapper.map(buyerRequestDto,buyer);
        log.info("The new buyer ---->{}",buyer);
        String token=userPrincipalService.signUpUser(buyer);
        if(token.isBlank()||token.isEmpty()){
            throw new AccountCreationException("Error creating buyer's account");
        }
        BuyerDto buyerDto= new BuyerDto(buyerRequestDto.getBuyerEmailAddress());
        emailService.sendMail(buyerDto);
        return buyerDto ;
    }

    @Override
    public List<SellerDto> findSellerByName(String sellerName) throws AccountException {

        if(sellerName.isBlank()){
            throw new AccountException("Seller Name cannot be blank");
        }
        List<SellerDto> sellerDtoList = sellerRepository.findAllSellerBySellerName(sellerName)
                .stream().map(seller ->modelMapper.map(seller, SellerDto.class)).collect(Collectors.toList());
        if(sellerDtoList.isEmpty()){
           sellerDtoList=sellerRepository.findAllSellerBySellerNameContaining(sellerName)
                   .stream().map(seller -> modelMapper.map(seller,SellerDto.class))
                   .collect(Collectors.toList());
            if(sellerDtoList.isEmpty()){
                throw new AccountException("We do not have seller with the name "+sellerName+" in our records");
            }
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
    public JWTToken buyerLogin(UserLoginDto userLoginDTO) throws IncorrectPasswordException, javax.security.auth.login.AccountException {

        return userPrincipalService.loginUser(userLoginDTO);
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
    public CustomerUpdateDto updateAccount(String token, CustomerUpdateDto customerUpdateDto) throws AccountException, AuthorizationException {
        if(token.isBlank()){
            throw new AuthorizationException("User token cannot be empty");
        }
        if(customerUpdateDto.getEmailAddress().isBlank()){
            throw new AccountException("Customer email address cannot be blank, please enter a valid email address ");
        }
        if(customerUpdateDto.getPreviousPassword().isBlank()){
            throw new AccountException("Customer password cannot be blank, please enter a valid password");
        }
        if(customerUpdateDto.getNewPassword().isBlank()){
            throw new AccountException("Customer password cannot be blank, please enter a valid password");
        }
        String email=userPrincipalService.getUserEmailAddressFromToken(token);
        log.info("email from token-->{}",email);
        Buyer buyer= buyerRepository.findBuyerByBuyerEmailAddress(email).orElseThrow(
                () -> new AccountException("Customer With this email does not exist"));
        if(!passwordEncoder.matches(customerUpdateDto.getPreviousPassword(), buyer.getBuyerPassword())){
            throw new AccountException("Password is incorrect account cannot be updated");
        }
        buyer.setBuyerPassword(passwordEncoder.encode(customerUpdateDto.getNewPassword()));
        buyer.setBuyerEmailAddress(customerUpdateDto.getEmailAddress());
        log.info("buyer information after update -->{}",buyer);
        buyerRepository.save(buyer);
        return customerUpdateDto;
    }


}
