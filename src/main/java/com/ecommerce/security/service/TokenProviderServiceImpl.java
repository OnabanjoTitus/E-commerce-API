package com.ecommerce.security.service;


import com.ecommerce.data.models.Buyer;
import com.ecommerce.data.models.Seller;
import com.ecommerce.web.exceptions.AuthorizationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

import static com.ecommerce.security.security.SecurityConstants.*;


@Slf4j
@Data
@Service
@Configuration
public class TokenProviderServiceImpl implements Serializable, TokenProviderService {

    private String getEncryptedSigningKey(){

        String encryptedSigningKey =  Base64.getEncoder().encodeToString(SIGNING_KEY_STRING.getBytes());
        return encryptedSigningKey;
    }
    Logger logger = LoggerFactory.getLogger(TokenProviderServiceImpl.class);



    @Override
    public String generateLoginToken(Buyer buyer) {
        final String authorities = buyer.getRole().toString();
        log.info(authorities);
        String jwts = Jwts.builder()
                .setSubject(buyer.getBuyerEmailAddress())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuer("E-COMMERCEBYTEE")
                .signWith(SignatureAlgorithm.HS512, getEncryptedSigningKey())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()
                        + EXPIRATION_DATE))
                .compact();
        log.info("JWT token with claims -> {}", jwts);
        return jwts;
    }

    @Override
    public String generateLoginToken(Seller seller) {
        final String authorities = seller.getRole().toString();
        log.info(authorities);
        String jwts = Jwts.builder()
                .setSubject(seller.getSellerEmailAddress())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuer("E-COMMERCEBYTEE")
                .signWith(SignatureAlgorithm.HS512, getEncryptedSigningKey())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()
                        + EXPIRATION_DATE))
                .compact();
        log.info("JWT token with claims -> {}", jwts);
        return jwts;
    }

    @Override
    public String getUserEmailFromToken(String token) throws AuthorizationException {
            log.info("The token => "+ token);
            var split = token.split(" ");
            String t = split[split.length-1];
            log.info("T is now => " + t);
            Claims claims;
            try{
                claims = Jwts.parser()
                        .setSigningKey(getEncryptedSigningKey())
                        .parseClaimsJws(t)
                        .getBody();
                return claims.getSubject();
            }
            catch (SignatureException ex){
                logger.error("untrusted token detected and invalidated");
                throw new AuthorizationException("unable to resolve token");

        }
    }


}
