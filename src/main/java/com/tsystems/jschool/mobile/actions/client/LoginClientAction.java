package com.tsystems.jschool.mobile.actions.client;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.exceptions.LoginUserException;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tsystems.jschool.mobile.actions.Action;
import org.apache.log4j.Logger;

public class LoginClientAction extends Action {

    private static Logger logger = Logger.getLogger(LoginClientAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = app.userService.clientExists(email, password);

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            logger.info("client registered as " + user.getEmail());

            return "/client/index.jsp";
        } catch (LoginUserException e){
            request.setAttribute("errorMassage", "Неверные логин или пароль");
            return "/clientLogin.jsp";
        } catch (MobileServiceException e){
            return "404.jsp";
        }
    }
}
