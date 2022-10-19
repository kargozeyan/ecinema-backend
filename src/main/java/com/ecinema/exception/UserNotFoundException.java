package com.ecinema.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "User not found");
    }

    public UserNotFoundException(String msg) {
        super(HttpStatus.NOT_FOUND, msg);
    }
}
