package com.tsystems.jschool.mobile.actions.admin.client;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tsystems.jschool.mobile.actions.Action;

public class FindClientProfileAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String user_id = request.getParameter("user_id");

        try {
            User user = app.userService.getUserById(user_id);
            request.setAttribute("user", user);
            return "/admin/client/clientProfile.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Не удалось найти профиль клиента! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
