package com.tsystems.jschool.mobile.controllers;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.services.API.UserService;
import com.tsystems.jschool.mobile.utils.constants.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"loggedUser"})
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = Actions.SHOW_ADMIN_START_PAGE)
    public String adminDashboard(Model model){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = userService.getUserByEmail(user.getUsername());
        model.addAttribute("loggedUser", loggedUser);
        return "admin/index";
    }

    @RequestMapping(value = Actions.SHOW_CLIENT_START_PAGE)
    public String clientAccount(Model model){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedUser = userService.getUserByEmail(user.getUsername());
        model.addAttribute("loggedUser", loggedUser);
        return "client/index";
    }

}
