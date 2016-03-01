package com.tsystems.jschool.mobile.actions.client;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.services.Impl.UserServiceImpl;
import com.tsystems.jschool.mobile.services.API.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tsystems.jschool.mobile.controllers.Action;
import org.apache.log4j.Logger;

/**
 * Created by Alexandra on 16.02.2016.
 */
public class LoginClientAction extends Action {

    private static Logger logger = Logger.getLogger(LoginClientAction.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.clientExists(email, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            logger.error(user.getName());
            return "/client/index.jsp";
        } else {
            request.setAttribute("errorMassage", "Неверные логин или пароль");
            return "/clientLogin.jsp";
        }

    }
}
