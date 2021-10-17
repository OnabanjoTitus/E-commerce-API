package com.ecommerce.security.service;


import com.ecommerce.data.models.Buyer;
import com.ecommerce.data.models.Seller;
import com.ecommerce.web.exceptions.AuthorizationException;


public interface TokenProviderService {
    String generateLoginToken( Buyer buyer);
    String generateLoginToken(Seller seller);
    String getUserEmailFromToken(String token) throws AuthorizationException;
}
