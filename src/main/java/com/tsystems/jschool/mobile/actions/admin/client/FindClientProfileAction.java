package com.tsystems.jschool.mobile.actions.admin.client;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.UserService;
import com.tsystems.jschool.mobile.services.Impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tsystems.jschool.mobile.actions.Action;
/**
 * Created by Alexandra on 22.02.2016.
 */
public class FindClientProfileAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String user_id = request.getParameter("user_id");
        try {
            User user = app.userService.getUserById(user_id);
            request.setAttribute("user", user);
            return "/admin/client/clientProfile.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Не удалось найти профиль клиента! (" + e.getCause().getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
