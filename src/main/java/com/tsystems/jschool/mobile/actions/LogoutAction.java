package com.tsystems.jschool.mobile.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(false).invalidate();
        return "index.jsp";
    }
}
