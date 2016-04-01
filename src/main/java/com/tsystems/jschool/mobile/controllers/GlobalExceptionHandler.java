package com.tsystems.jschool.mobile.controllers;

import com.tsystems.jschool.mobile.entities.CurrentUser;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.exceptions.CompatibilityOptionException;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MobileDAOException.class)
    public ModelAndView handleMobileDAOException(HttpServletRequest request, Exception ex) {
        Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        for (GrantedAuthority authority : authorities){
            if (authority.getAuthority().equals(RoleName.ADMIN.name())) {
                modelAndView.setViewName("admin/info");
            } else if (authority.getAuthority().equals(RoleName.CLIENT.name())){
                modelAndView.setViewName("client/info");
            }
        }
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

//    @ExceptionHandler(CompatibilityOptionException.class)
//    public ModelAndView handleCompabilityOptionException(HttpServletRequest request, Exception ex) {
//        Collection<? extends GrantedAuthority> authorities =
//                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("exception", ex);
//        for (GrantedAuthority authority : authorities){
//            System.out.println(authority.getAuthority());
//            if (authority.getAuthority().equals(RoleName.ADMIN.name())) {
//                modelAndView.setViewName("admin/info");
//            } else if (authority.getAuthority().equals(RoleName.CLIENT.name())){
//                modelAndView.setViewName("client/info");
//            }
//        }
//        modelAndView.addObject("message", "Действие не удалось. Нарушена совместимость опций.");
//        return modelAndView;
//    }
}
