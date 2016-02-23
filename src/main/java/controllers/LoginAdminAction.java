package controllers;

import entities.User;
import services.Impl.UserServiceImpl;
import services.API.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Alexandra on 18.02.2016.
 */
public class LoginAdminAction extends Action{

    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("pass");

        User user = userService.adminExists(email, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", "admin");
            return "/admin/index.jsp";
        } else {
            return "/adminLogin.jsp";
        }

    }
}
