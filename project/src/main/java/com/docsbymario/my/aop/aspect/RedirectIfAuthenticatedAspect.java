package com.docsbymario.my.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class RedirectIfAuthenticatedAspect {
    @Around("@annotation(com.docsbymario.my.aop.annotation.RedirectIfAuthenticated)")
    public Object checkIfAuthenticated(ProceedingJoinPoint joinPoint) throws Throwable {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return new ModelAndView("redirect:/");
        }
        else {
            return joinPoint.proceed();
        }
    }
}
