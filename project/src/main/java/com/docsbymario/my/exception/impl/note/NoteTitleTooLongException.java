package com.docsbymario.my.exception.impl.note;

import com.docsbymario.my.exception.GenericException;

public class NoteTitleTooLongException extends GenericException {
    @Override
    public String getAdditionalMessage() {
        return "Note title should not exceed 40 characters.";
    }
}