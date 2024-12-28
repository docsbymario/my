package com.docsbymario.my.controller;

import com.docsbymario.my.aop.annotation.RedirectIfAuthenticated;
import com.docsbymario.my.dto.RegisterDto;
import com.docsbymario.my.entity.Note;
import com.docsbymario.my.exception.GenericException;
import com.docsbymario.my.exception.impl.user.UsernameDoesNotExistException;
import com.docsbymario.my.service.NoteService;
import com.docsbymario.my.service.RegisterService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RestController
public class AuthController {
    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    @RedirectIfAuthenticated
    public ModelAndView getRegister() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register.html");
        return modelAndView;
    }

    @GetMapping("/login")
    @RedirectIfAuthenticated
    public ModelAndView getLogin(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }

        ModelAndView modelAndView = new ModelAndView("login.html");
        modelAndView.addObject("errorMessage", errorMessage);
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView postRegister(HttpServletRequest httpServletRequest, @ModelAttribute RegisterDto registerDto) {
        ModelAndView modelAndView;

        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            modelAndView = new ModelAndView("redirect:/");
        }
        else {
            try {
                registerService.register(registerDto);
                httpServletRequest.login(registerDto.getUsername(), registerDto.getPassword());
                modelAndView = new ModelAndView("redirect:/");

            } catch (GenericException e) {
                modelAndView = new ModelAndView("register.html");
                modelAndView.addObject("errorMessage", e.getAdditionalMessage());
            } catch (ServletException e) {
                modelAndView = new ModelAndView("register.html");
                modelAndView.addObject("errorMessage", "There was a servlet error while trying to create an account for you.");
            }
        }

        return modelAndView;
    }
}
