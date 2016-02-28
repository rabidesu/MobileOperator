package controllers;

import entities.User;
import services.API.UserService;
import services.Impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandra on 23.02.2016.
 */
public class AllClientsAction extends Action {

    UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<User> users = userService.getAllUsers();
        request.setAttribute("listUsers", users);
        System.out.println(request.getSession(false).getAttribute("email"));
        return "/pages/admin/searchClient.jsp";
    }
}
