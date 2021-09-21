package com.ecommerce.security.security;

public enum TokenType {

    BEARER_TOKEN, REFRESH_TOKEN, PASSWORD_RESET_TOKEN, CREATE_PASSWORD_TOKEN;


    @Override
    public String toString() {
        switch (this){
            case BEARER_TOKEN:return "Bearer";
            case REFRESH_TOKEN:return "Refresh";
            case PASSWORD_RESET_TOKEN:return "Password Reset";
            case CREATE_PASSWORD_TOKEN:return "Create Password";
            default: return null;
        }

    }
}
