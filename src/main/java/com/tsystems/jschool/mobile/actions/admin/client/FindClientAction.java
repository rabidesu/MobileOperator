package com.tsystems.jschool.mobile.actions.admin.client;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.tsystems.jschool.mobile.actions.Action;

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
            request.setAttribute("massage", "Поиск клиентов невозможен! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
