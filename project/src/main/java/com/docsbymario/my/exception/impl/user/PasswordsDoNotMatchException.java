package com.docsbymario.my.exception.impl.user;

import com.docsbymario.my.exception.GenericException;

public class PasswordsDoNotMatchException extends GenericException {
    @Override
    public String getAdditionalMessage() {
        return "The two passwords do not match.";
    }
}