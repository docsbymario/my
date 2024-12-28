package com.docsbymario.my.service;

import com.docsbymario.my.entity.Note;
import com.docsbymario.my.entity.User;
import com.docsbymario.my.exception.impl.user.UsernameDoesNotExistException;
import com.docsbymario.my.repository.NoteRepository;
import com.docsbymario.my.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

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
}
