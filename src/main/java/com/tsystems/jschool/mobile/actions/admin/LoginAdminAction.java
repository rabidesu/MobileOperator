package com.tsystems.jschool.mobile.actions.admin;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.services.Impl.UserServiceImpl;
import com.tsystems.jschool.mobile.services.API.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tsystems.jschool.mobile.controllers.Action;
import org.apache.log4j.Logger;

/**
 * Created by Alexandra on 18.02.2016.
 */
public class LoginAdminAction extends Action{

    private UserService userService = new UserServiceImpl();
    private static Logger logger = Logger.getLogger(LoginAdminAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.adminExists(email, password);
        System.out.println(user.getName());
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            logger.debug("admin registered!");
            return "/admin/index.jsp";
        } else {
            request.setAttribute("errorMassage", "Неверные логин или пароль");
            return "/adminLogin.jsp";
        }
    }
}
