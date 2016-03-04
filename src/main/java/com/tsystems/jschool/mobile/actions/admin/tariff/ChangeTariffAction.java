package com.tsystems.jschool.mobile.actions.admin.tariff;

import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tsystems.jschool.mobile.actions.Action;

public class ChangeTariffAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("tariff_id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String[] options = request.getParameterValues("options");

        try {
            app.tariffService.changeTariff(id, name, price, options);
            request.setAttribute("massage", "Тариф успешно изменен!");
            return "/admin/info.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Не удалось изменить тариф! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
