package com.ecommerce.web.exceptions;

public class AccountCreationException extends Exception{
    public AccountCreationException() {
    }

    public AccountCreationException(String message) {
        super(message);
    }

    public AccountCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountCreationException(Throwable cause) {
        super(cause);
    }
}
