package com.docsbymario.my.controller;

import com.docsbymario.my.dto.NoteDto;
import com.docsbymario.my.entity.Note;
import com.docsbymario.my.exception.GenericException;
import com.docsbymario.my.exception.impl.user.UsernameDoesNotExistException;
import com.docsbymario.my.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    public ModelAndView getNotes(Principal principal, @RequestParam(value="errorMessage", required = false) String errorMessage) {
        ModelAndView modelAndView;

        try {
            List<Note> notes = noteService.findByUserId(principal);
            modelAndView = new ModelAndView("notes.html");
            modelAndView.addObject("notes", notes);
        } catch (UsernameDoesNotExistException e) {
            modelAndView = new ModelAndView("redirect:/");
        }

        if (errorMessage != null) {
            modelAndView.addObject("errorMessage", errorMessage);
        }

        return modelAndView;
    }

    @GetMapping("/notes/add")
    public ModelAndView getNotesAdd(@RequestParam(value="errorMessage", required = false) String errorMessage) {
        ModelAndView modelAndView = new ModelAndView("notes-add.html");

        if (errorMessage != null) {
            modelAndView.addObject("errorMessage", errorMessage);
        }

        return modelAndView;
    }

    @PostMapping("/notes/add")
    public RedirectView postNotesAdd(Principal principal, @ModelAttribute NoteDto noteDto, RedirectAttributes redirectAttributes) {
        RedirectView redirectView;
        try {
            noteService.createNote(principal, noteDto);
            redirectView = new RedirectView("/notes", true);;
        } catch (GenericException e) {
            redirectAttributes.addAttribute("errorMessage", e.getAdditionalMessage());
            redirectView = new RedirectView("/notes/add", true);
        }

        return redirectView;
    }

    @PostMapping("/notes/delete")
    public RedirectView deleteNote(Principal principal, @ModelAttribute NoteDto noteDto, RedirectAttributes redirectAttributes) {
        RedirectView modelAndView = new RedirectView("/notes", true);

        try {
            noteService.deleteNote(principal, noteDto);
            redirectAttributes.addAttribute("errorMessage", "The note has been deleted successfully.");
        } catch (GenericException e) {
            redirectAttributes.addAttribute("errorMessage", e.getAdditionalMessage());
        }

        return modelAndView;
    }

    @PostMapping("/notes/update")
    public RedirectView updateNote(Principal principal, @ModelAttribute NoteDto noteDto, RedirectAttributes redirectAttributes) {
        RedirectView modelAndView = new RedirectView("/notes", true);

        try {
            noteService.updateNote(principal, noteDto);
            redirectAttributes.addAttribute("errorMessage", "The note has been updated successfully.");
        } catch (GenericException e) {
            redirectAttributes.addAttribute("errorMessage", e.getAdditionalMessage());
        }

        return modelAndView;
    }}
