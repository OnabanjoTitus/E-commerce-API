package com.ecommerce.security.security;

import lombok.Data;


@Data

public class JWTToken {
    private String accessToken;
    private TokenType tokenType = TokenType.BEARER_TOKEN;
    public JWTToken(String loginToken) {
        accessToken = loginToken;
    }
}
