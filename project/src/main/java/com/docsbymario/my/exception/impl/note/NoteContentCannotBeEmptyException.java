package com.docsbymario.my.exception.impl.note;

import com.docsbymario.my.exception.GenericException;

public class NoteContentCannotBeEmptyException extends GenericException {
    @Override
    public String getAdditionalMessage() {
        return "Note title cannot be empty.";
    }
}