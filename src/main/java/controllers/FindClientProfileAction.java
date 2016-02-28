package controllers;

import entities.Contract;
import entities.Option;
import entities.User;
import services.API.OptionService;
import services.API.UserService;
import services.Impl.OptionServiceImpl;
import services.Impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandra on 22.02.2016.
 */
public class FindClientProfileAction extends Action {

    UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String user_id = request.getParameter("entity_id");
        User user = userService.getUserById(user_id);
        for (Contract contract : user.getContracts()){
            System.out.println(contract.getId());
        }
        request.setAttribute("user", user);
        return "/admin/clientProfile.jsp";
    }
}
