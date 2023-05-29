package com.ecommerce.ECommerce.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

public class EmailFailed extends Exception {

    public EmailFailed(String message) {
        super(message);
    }

}
