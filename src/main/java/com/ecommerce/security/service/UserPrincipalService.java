package com.ecommerce.security.service;

import com.autox.dtos.UserLoginDto;
import com.autox.exceptions.IncorrectPasswordException;
import com.autox.models.UserEntity;
import com.autox.repositories.UserRepository;
import com.autox.security.AppAuthenticationProvider;
import com.autox.security.ApplicationUser;
import com.autox.security.JWTToken;
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
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AppAuthenticationProvider authenticationManager;

    @Autowired
    TokenProviderServiceImpl tokenProviderService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Check this email " + username);
        Optional<UserEntity> optionalUser = userRepository.findUserByEmail(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User with given email not found");
        }
        else{
            UserEntity user =  optionalUser.get();
            return ApplicationUser.create(user);
        }
    }

    public JWTToken loginUser(UserLoginDto userLoginDTO) throws UsernameNotFoundException, IncorrectPasswordException {
        Optional<UserEntity> userEntity = userRepository.findUserByEmail(userLoginDTO.getEmail());

        if(userEntity.isPresent()){
        log.info(userEntity.get().getPassword() + " user password ==================================");
        log.info(passwordEncoder.encode(userLoginDTO.getPassword()) + " encoded dto password ++++++++++++++++++++++++++++++++++");
        boolean matchingResult=passwordEncoder.matches(userLoginDTO.getPassword(),passwordEncoder.encode( userEntity.get().getPassword()));
        log.info(String.valueOf(matchingResult));
            if(!matchingResult){
            throw new IncorrectPasswordException("The password is Incorrect");
        }
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                      userLoginDTO.getEmail(), userLoginDTO.getPassword()
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
            throw new UsernameNotFoundException("User Not Found");

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
