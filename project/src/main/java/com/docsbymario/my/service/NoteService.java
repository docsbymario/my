package com.docsbymario.my.service;

import com.docsbymario.my.dto.NoteDto;
import com.docsbymario.my.entity.Note;
import com.docsbymario.my.entity.User;
import com.docsbymario.my.exception.impl.note.NoteDoesNotBelongToPrincipalException;
import com.docsbymario.my.exception.impl.note.NoteTitleTooLongException;
import com.docsbymario.my.exception.impl.note.NoteWithIdDoesNotExistException;
import com.docsbymario.my.exception.impl.note.NoteWithTitleAlreadyExistsException;
import com.docsbymario.my.exception.impl.user.UsernameDoesNotExistException;
import com.docsbymario.my.repository.NoteRepository;
import com.docsbymario.my.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Note> findByUserId(Principal principal) throws UsernameDoesNotExistException {
        List<User> users = userRepository.findByUsername(principal.getName());

        if (users.size() == 0) {
            throw new UsernameDoesNotExistException();
        }

        return noteRepository.findByUserId(users.get(0).getId());
    }

    public void createNote(Principal principal, NoteDto noteDto) throws UsernameDoesNotExistException, NoteTitleTooLongException, NoteWithTitleAlreadyExistsException {
        List<User> users = userRepository.findByUsername(principal.getName());
        if (users.size() == 0) {
            throw new UsernameDoesNotExistException();
        }

        if (noteDto.getTitle().length() > 40) {
            throw new NoteTitleTooLongException();
        }

        Note note = noteRepository.findByTitle(noteDto.getTitle());
        if (note != null) {
            throw new NoteWithTitleAlreadyExistsException();
        }

        noteRepository.save(new Note(users.get(0).getId(), noteDto.getTitle(), noteDto.getContent()));
    }

    public void deleteNote(Principal principal, String noteId) throws UsernameDoesNotExistException, NoteWithIdDoesNotExistException, NoteDoesNotBelongToPrincipalException {
        List<User> users = userRepository.findByUsername(principal.getName());
        if (users.size() == 0) {
            throw new UsernameDoesNotExistException();
        }

        Optional<Note> note = noteRepository.findById(noteId);
        if (note.isEmpty()) {
            throw new NoteWithIdDoesNotExistException();
        }

        if (!note.get().getUserId().equals(users.get(0).getId())) {
            throw new NoteDoesNotBelongToPrincipalException();
        }

        noteRepository.delete(note.get());
    }
}
