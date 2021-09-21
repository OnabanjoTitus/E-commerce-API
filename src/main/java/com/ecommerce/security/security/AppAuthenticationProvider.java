package com.ecommerce.security.security;

import com.autox.exceptions.AuthorizationException;
import com.autox.models.Role;
import com.autox.models.UserEntity;
import com.autox.repositories.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Primary
public class AppAuthenticationProvider implements AuthenticationManager {


    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        String password = (String) token.getCredentials();



        Optional<UserEntity> user = userRepository.findUserByEmail(username);

        if (!user.isPresent()) {
            throw new BadCredentialsException("There is not account with given credentials");
        }
//        if (!passwordEncoder.matches(passwordEncoder.encode(password),passwordEncoder.encode( user.get().getPassword()))) {
//            throw new BadCredentialsException("Invalid username or password");
//        }


        List<Role> authorities = Collections.singletonList(user.get().getRole());
        log.info("Authorities content " + user.get().getRole());
        if(user.get().getRole() == null) throw new AuthorizationException("User has no authority");
        return new UsernamePasswordAuthenticationToken(username, password, authorities.stream().map(authority
                -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList()));
    }
}
