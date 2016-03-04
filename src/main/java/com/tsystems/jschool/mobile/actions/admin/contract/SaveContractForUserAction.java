package com.tsystems.jschool.mobile.actions.admin.contract;

import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tsystems.jschool.mobile.actions.Action;

public class SaveContractForUserAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String user_id = request.getParameter("user_id");
        String phone_number = request.getParameter("phone_number");
        String tariffId = request.getParameter("tariff_id");
        String[] options = request.getParameterValues("options");

        try {
            app.contractService.addContractForUser(user_id, phone_number, tariffId, options);
            request.setAttribute("massage", "Контракт успешно добавлен!");
            return "/admin/info.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Не удалось добавить контракт! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
