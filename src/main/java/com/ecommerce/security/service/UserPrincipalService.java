package com.ecommerce.security.service;

import com.ecommerce.data.models.Buyer;
import com.ecommerce.data.models.Seller;
import com.ecommerce.dtos.UserLoginDto;
import com.ecommerce.data.repository.BuyerRepository;
import com.ecommerce.data.repository.SellerRepository;
import com.ecommerce.security.exceptions.IncorrectPasswordException;
import com.ecommerce.security.security.AppAuthenticationProvider;
import com.ecommerce.security.security.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserPrincipalService implements UserDetailsService {
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AppAuthenticationProvider authenticationManager;

    @Autowired
    TokenProviderServiceImpl tokenProviderService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Check this email " + username);
        Optional<Seller>optionalSeller = Optional.empty();
        Optional<Buyer> optionalBuyer;

         optionalBuyer= buyerRepository.findBuyerByBuyerEmailAddress(username);
        if(optionalBuyer.isEmpty()){
            optionalSeller=sellerRepository.findAllSellerBySellerName(username);
            if(optionalSeller.isEmpty()){
                throw new UsernameNotFoundException("User with this email is not found");}
        }
        if(optionalBuyer.isPresent()){
            return optionalBuyer.get();
        }
        return optionalSeller.get();
    }

    public JWTToken loginUser(UserLoginDto userLoginDto) throws UsernameNotFoundException, IncorrectPasswordException, AccountException {
       UserDetails userEntity = loadUserByUsername(userLoginDto.getEmailAddress());
       String role=userEntity.getAuthorities().toString();
       log.info("The role is -->{}",role);
       switch (role){
           case "Seller":
               Seller seller=sellerRepository.findSellerBySellerEmailAddress(userLoginDto.getEmailAddress())
                       .orElseThrow(()-> new UsernameNotFoundException("User with this email is not found"));
                break;
           case "Buyer":
               Buyer buyer=buyerRepository.findBuyerByBuyerEmailAddress(userLoginDto.getEmailAddress())
                       .orElseThrow(()-> new UsernameNotFoundException("User with this email is not found"));
               break;
           default:
               throw new AccountException("Invalid account type");
       }

        log.info(userEntity.getPassword() + " user password ==================================");
        log.info(passwordEncoder.encode(userLoginDto.getPassword()) + " encoded dto password ++++++++++++++++++++++++++++++++++");
        boolean matchingResult=passwordEncoder.matches(userLoginDto.getPassword(),passwordEncoder.encode( userEntity.getPassword()));
        log.info(String.valueOf(matchingResult));
        if(!matchingResult){
        throw new IncorrectPasswordException("The password is Incorrect");
    }
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                      userLoginDto.getEmailAddress(), userLoginDto.getPassword()
                )
        );
        log.info("after authentication");
        log.info("security context authentication");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userEntity = userRepository.findUserByEmail(userLoginDTO.getEmail());

        JWTToken jwtToken = new JWTToken(tokenProviderService.generateLoginToken(authentication, userEntity.get()));

        log.info("JWT object -> {}", jwtToken.toString());
        return jwtToken;

    }


    public String signUpUser(UserEntity userEntity) {
        StringBuilder stringBuilder= new StringBuilder("Validates ");
        boolean userExists=userRepository.findUserByEmail(userEntity.getEmail()).isPresent();
        if(userExists){
            throw new IllegalStateException("user with this email already exists");
        }
        userRepository.save(userEntity);
        String encodedPassword=passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);
        String token= UUID.randomUUID().toString();

        stringBuilder.append(token);
        return stringBuilder.toString();
    }
}
