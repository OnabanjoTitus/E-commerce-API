package com.ecommerce.security.service;

import com.ecommerce.data.models.Customer;
import com.ecommerce.data.repository.CustomerRepository;
import com.ecommerce.security.exceptions.IncorrectPasswordException;
import com.ecommerce.security.security.AppAuthenticationProvider;
import com.ecommerce.security.security.ApplicationUser;
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

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserPrincipalService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AppAuthenticationProvider authenticationManager;

    @Autowired
    TokenProviderServiceImpl tokenProviderService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Check this email " + username);
        Optional<Customer> optionalUser = customerRepository.findCustomerByEmailAddress(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User with given email not found");
        }
        else{
            Customer user =  optionalUser.get();
            return ApplicationUser.create(user);
        }
    }

    public JWTToken loginUser(Customer customer) throws UsernameNotFoundException, IncorrectPasswordException {
        Optional<Customer> customerFound = customerRepository.findCustomerByEmailAddress(customer.getEmailAddress());

        if(customerFound.isPresent()){
        log.info(customerFound.get().getPassword() + " user password ==================================");
        log.info(passwordEncoder.encode(customer.getPassword()) + " encoded dto password ++++++++++++++++++++++++++++++++++");
        boolean matchingResult=passwordEncoder.matches(customer.getPassword(),passwordEncoder.encode( customerFound.get().getPassword()));
        log.info(String.valueOf(matchingResult));
            if(!matchingResult){
            throw new IncorrectPasswordException("The password is Incorrect");
        }
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                      customer.getEmailAddress(), customer.getPassword()
                )
        );
        log.info("after authentication");
        log.info("security context authentication");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        customerFound = customerRepository.findCustomerByEmailAddress(customer.getEmailAddress());

        JWTToken jwtToken = new JWTToken(tokenProviderService.generateLoginToken(authentication, customerFound.get()));

        log.info("JWT object -> {}", jwtToken.toString());
        return jwtToken;
        }
            throw new UsernameNotFoundException("User Not Found");

    }

    public String signUpUser(Customer customer) {
        StringBuilder stringBuilder= new StringBuilder("Validates ");
        boolean userExists= customerRepository.findCustomerByEmailAddress(customer.getEmailAddress()).isPresent();
        if(userExists){
            throw new IllegalStateException("user with this email already exists");
        }
        customerRepository.save(customer);
        String encodedPassword=passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        String token= UUID.randomUUID().toString();

        stringBuilder.append(token);
        return stringBuilder.toString();
    }
}
