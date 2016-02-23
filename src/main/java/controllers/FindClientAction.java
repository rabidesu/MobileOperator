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
        String phone_number = request.getParameter("phone_number");
        List<User> users = userService.getUserByNumber(phone_number);
        if (!users.isEmpty()) {
            User user = userService.getUserByNumber(phone_number).get(0);
            request.setAttribute("user", user);
            System.out.println(user.getName());
        }
        return "/pages/admin/findClient.jsp";
    }
}
