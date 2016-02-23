package controllers;

import entities.User;
import services.Impl.UserServiceImpl;
import services.API.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Alexandra on 16.02.2016.
 */
public class LoginClientAction extends Action {

    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("pass");

        User user = userService.clientExists(email, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", "client");
            return "/client/userAccount.jsp";
        } else {
            return "/clientLogin.jsp";
        }

    }
}
