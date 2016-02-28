package controllers;

import entities.User;
import services.API.UserService;
import services.Impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandra on 22.02.2016.
 */
public class FindClientAction extends Action {

    UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String searchText = request.getParameter("search_text");
        String searchField = request.getParameter("search_field");
        List<User> users = null;
        if (searchText != null){
            users = userService.getUserByField(searchText, searchField);
        } else {
            users = userService.getAllUsers();
        }
        request.setAttribute("listUsers", users);
        System.out.println(users.isEmpty());
        if (users.isEmpty()) {
            request.setAttribute("message", "Нет ни одного клиента, удовлетворяющего условиям поиска");
        } else {
            request.setAttribute("message", null);
        }
        return "/pages/admin/searchClient.jsp";
    }
}
