package com.docsbymario.my.exception.impl.note;

import com.docsbymario.my.exception.GenericException;

public class NoteWithIdDoesNotExistException extends GenericException {
    @Override
    public String getAdditionalMessage() {
        return "A note with the given id does not exist.";
    }
}