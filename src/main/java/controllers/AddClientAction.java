package controllers;

import services.API.UserService;
import services.Impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * Created by Alexandra on 22.02.2016.
 */
public class AddClientAction extends Action {

    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String date = request.getParameter("date");
        System.out.println(date);
        String passport = request.getParameter("passport");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        userService.addUser(name, surname, date, passport, address, email, password);
        return "/admin/addClient.jsp";
    }
}
