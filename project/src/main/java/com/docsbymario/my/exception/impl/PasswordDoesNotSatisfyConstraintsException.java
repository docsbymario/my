package com.docsbymario.my.exception.impl;

import com.docsbymario.my.exception.GenericException;

public class PasswordDoesNotSatisfyConstraintsException extends GenericException {
    @Override
    public String getAdditionalMessage() {
        return "The password must contain at least 8 characters, an uppercase letter, a lowercase letter, a digit and a special symbol.";
    }
}