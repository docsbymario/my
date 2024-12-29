package com.docsbymario.my.service;

import com.docsbymario.my.dto.NoteDto;
import com.docsbymario.my.entity.Note;
import com.docsbymario.my.entity.User;
import com.docsbymario.my.exception.impl.note.*;
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

    public void createNote(Principal principal, NoteDto noteDto) throws UsernameDoesNotExistException, NoteTitleTooLongException, NoteWithTitleAlreadyExistsException, NoteTitleCannotBeEmptyException, NoteContentCannotBeEmptyException {
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

        if (noteDto.getTitle().trim().isEmpty()) {
            throw new NoteTitleCannotBeEmptyException();
        }

        if (noteDto.getContent().trim().isEmpty()) {
            throw new NoteContentCannotBeEmptyException();
        }

        noteRepository.save(new Note(users.get(0).getId(), noteDto.getTitle().trim(), noteDto.getContent().trim()));
    }

    public void deleteNote(Principal principal, NoteDto noteDto) throws UsernameDoesNotExistException, NoteWithIdDoesNotExistException, NoteDoesNotBelongToPrincipalException {
        List<User> users = userRepository.findByUsername(principal.getName());
        if (users.size() == 0) {
            throw new UsernameDoesNotExistException();
        }

        Optional<Note> note = noteRepository.findById(noteDto.getId());
        if (note.isEmpty()) {
            throw new NoteWithIdDoesNotExistException();
        }

        if (!note.get().getUserId().equals(users.get(0).getId())) {
            throw new NoteDoesNotBelongToPrincipalException();
        }

        noteRepository.delete(note.get());
    }

    public void updateNote(Principal principal, NoteDto noteDto) throws UsernameDoesNotExistException, NoteWithIdDoesNotExistException, NoteDoesNotBelongToPrincipalException, NoteTitleCannotBeEmptyException, NoteContentCannotBeEmptyException {
        List<User> users = userRepository.findByUsername(principal.getName());
        if (users.size() == 0) {
            throw new UsernameDoesNotExistException();
        }

        Optional<Note> note = noteRepository.findById(noteDto.getId());
        if (note.isEmpty()) {
            throw new NoteWithIdDoesNotExistException();
        }

        if (!note.get().getUserId().equals(users.get(0).getId())) {
            throw new NoteDoesNotBelongToPrincipalException();
        }

        if (noteDto.getTitle().trim().isEmpty()) {
            throw new NoteTitleCannotBeEmptyException();
        }

        if (noteDto.getContent().trim().isEmpty()) {
            throw new NoteContentCannotBeEmptyException();
        }

        Note newNote = new Note(noteDto.getId(), note.get().getUserId(), noteDto.getTitle().trim(), noteDto.getContent().trim());
        noteRepository.save(newNote);
    }
}
