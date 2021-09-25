package com.ecommerce.security.security;

import com.ecommerce.security.service.TokenProviderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityConstants {

    public static final String AUTHORITIES_KEY = "roles";

    Logger logger = LoggerFactory.getLogger(TokenProviderServiceImpl.class);

    public String BEARER_TOKEN_VALIDITY;

    public static final String SIGNING_KEY_STRING = "appsecret";

    public static final long EXPIRATION_DATE = 864_000_00;

    public static int ACCESS_TOKEN_VALIDITY;


}
