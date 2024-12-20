package com.avila.proposals.exception.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
      super("User already exists");
    }

    public UserAlreadyExistsException(String message) {
      super(message);
    }
}
