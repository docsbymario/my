package com.docsbymario.my.exception.impl.user;

import com.docsbymario.my.exception.GenericException;

public class UsernameAlreadyExistsException extends GenericException {
    @Override
    public String getAdditionalMessage() {
        return "The specified username already exists.";
    }
}