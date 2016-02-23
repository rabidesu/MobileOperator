package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexandra on 23.02.2016.
 */
public class LogoutAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(false).invalidate();
        return "pages/adminLogin.jsp";
    }
}
