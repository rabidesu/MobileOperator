package com.tsystems.jschool.mobile.controllers;

import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;

import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public String handleException(HttpServletRequest request, Exception ex, Model model) {
        Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String resultPage = "500";
        if (ex instanceof MobileServiceException) {
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(RoleName.ROLE_ADMIN.name())) {
                    resultPage = "admin/info";
                } else if (authority.getAuthority().equals(RoleName.ROLE_CLIENT.name())) {
                    resultPage = "client/info";
                }
            }
        }
        model.addAttribute("message", ex.getMessage());
        return resultPage;
    }


}
