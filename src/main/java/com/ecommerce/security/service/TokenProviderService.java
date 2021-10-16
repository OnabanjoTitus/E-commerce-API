package com.ecommerce.security.service;


import com.ecommerce.data.models.Buyer;
import com.ecommerce.data.models.Seller;
import com.ecommerce.web.exceptions.AuthorizationException;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;


public interface TokenProviderService {
    String generateLoginToken( Buyer buyer);
    String generateLoginToken(Seller seller);
    String getUserEmailFromToken(String token) throws AuthorizationException;
}
