package com.docsbymario.my.exception.impl.note;

import com.docsbymario.my.exception.GenericException;

public class NoteDoesNotBelongToPrincipalException extends GenericException {
    @Override
    public String getAdditionalMessage() {
        return "The selected note does not belong to you.";
    }
}