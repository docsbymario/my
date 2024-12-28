package com.docsbymario.my.exception.impl.user;

import com.docsbymario.my.exception.GenericException;

public class UsernameDoesNotExistException extends GenericException {
    @Override
    public String getAdditionalMessage() {
        return "A user with the specified username does not exist.";
    }
}