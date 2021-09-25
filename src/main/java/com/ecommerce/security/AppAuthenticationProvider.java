package com.SimpleBanking.application.data.security.config;

import com.SimpleBanking.application.data.model.Account;
import com.SimpleBanking.application.data.model.Role;
import com.SimpleBanking.application.services.BankServices;
import com.SimpleBanking.application.web.Exceptions.AccountException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class AppAuthenticationProvider implements AuthenticationManager {
    @Autowired
    BankServices bankServices;

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        log.info("The token name na-->{}",token.getName());
        String username = token.getName();
        String password = (String) token.getCredentials();

        Account account=bankServices.findUserByAccountNumber(username);
        List<Role> authorities = Collections.singletonList(account.getRole());
        log.info("Authorities content " + account.getRole());
        if(account.getRole() == null) throw new AccountException("User has no authority");

        return new UsernamePasswordAuthenticationToken(username, password, authorities.stream().map(authority
                -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList()));
    }
}
