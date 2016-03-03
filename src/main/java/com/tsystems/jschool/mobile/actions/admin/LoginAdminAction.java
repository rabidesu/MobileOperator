package com.tsystems.jschool.mobile.actions.admin;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.exceptions.LoginUserException;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.Impl.UserServiceImpl;
import com.tsystems.jschool.mobile.services.API.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tsystems.jschool.mobile.actions.Action;
import org.apache.log4j.Logger;

/**
 * Created by Alexandra on 18.02.2016.
 */
public class LoginAdminAction extends Action{

    private static Logger logger = Logger.getLogger(LoginAdminAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            User user = app.userService.adminExists(email, password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            logger.info("admin registered as " + user.getEmail());
            return "/admin/index.jsp";
        } catch (LoginUserException e){
            request.setAttribute("errorMassage", "Неверные логин или пароль");
            return "/adminLogin.jsp";
        } catch (MobileServiceException e){
            return "error.jsp";
        }
    }
}
