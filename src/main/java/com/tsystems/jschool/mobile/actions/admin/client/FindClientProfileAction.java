package com.tsystems.jschool.mobile.actions.admin.client;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.services.API.UserService;
import com.tsystems.jschool.mobile.services.Impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tsystems.jschool.mobile.controllers.Action;
/**
 * Created by Alexandra on 22.02.2016.
 */
public class FindClientProfileAction extends Action {

    UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String user_id = request.getParameter("user_id");
        User user = userService.getUserById(user_id);
        for (Contract contract : user.getContracts()){
            System.out.println(contract.getId());
        }
        request.setAttribute("user", user);
        return "/admin/client/clientProfile.jsp";
    }
}
