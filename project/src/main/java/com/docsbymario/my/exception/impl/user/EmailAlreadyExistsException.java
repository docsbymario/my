package com.docsbymario.my.exception.impl.user;

import com.docsbymario.my.exception.GenericException;

public class EmailAlreadyExistsException extends GenericException {
    @Override
    public String getAdditionalMessage() {
        return "The specified email address already exists.";
    }
}