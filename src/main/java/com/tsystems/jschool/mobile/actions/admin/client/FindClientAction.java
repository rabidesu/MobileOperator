package com.tsystems.jschool.mobile.actions.admin.client;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.UserService;
import com.tsystems.jschool.mobile.services.Impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.tsystems.jschool.mobile.actions.Action;
/**
 * Created by Alexandra on 22.02.2016.
 */
public class FindClientAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String searchText = request.getParameter("search_text");
        String searchField = request.getParameter("search_field");
        List<User> users;
        try {
            if (searchText != null) {
                users = app.userService.getUserByField(searchText, searchField);
            } else {
                users = app.userService.getAllUsers();
            }
            request.setAttribute("listUsers", users);
            return "/pages/admin/client/searchClient.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Поиск клиентов невозможен! (" + e.getCause().getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
