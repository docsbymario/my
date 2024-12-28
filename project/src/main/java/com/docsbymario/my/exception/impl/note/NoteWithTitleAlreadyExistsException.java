package com.docsbymario.my.exception.impl.note;

import com.docsbymario.my.exception.GenericException;

public class NoteWithTitleAlreadyExistsException extends GenericException {
    @Override
    public String getAdditionalMessage() {
        return "A note with the given title already exists.";
    }
}