package com.tsystems.jschool.mobile.actions;

import com.tsystems.jschool.mobile.controllers.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexandra on 23.02.2016.
 */
public class LogoutAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(false).invalidate();
        return "index.jsp";
    }
}
