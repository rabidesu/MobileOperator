package com.tsystems.jschool.mobile.actions.client.contract;

import com.tsystems.jschool.mobile.actions.Action;
import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeContractByClientAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("contract_id");
        String tariff = request.getParameter("tariff_id");
        String block = request.getParameter("block");
        String[] options = request.getParameterValues("options");

        try {
            app.contractService.changeContract(id, tariff, options, block);
            request.setAttribute("massage", "Вы успешно изменили условия контракта!");

            HttpSession session = request.getSession(false);
            int userId = ((User) session.getAttribute("user")).getId();
            User newUser = app.userService.getUserById(String.valueOf(userId));
            session.setAttribute("user", newUser);

            return "/client/info.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Не удалось изменить условия контракта! (" + e.getMessage() + ")");
            return "/client/info.jsp";
        }
    }
}
