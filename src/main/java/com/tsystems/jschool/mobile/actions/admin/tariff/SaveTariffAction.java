package com.tsystems.jschool.mobile.actions.admin.tariff;

import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tsystems.jschool.mobile.actions.Action;

public class SaveTariffAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String[] options = request.getParameterValues("options");

        try {
            app.tariffService.addTariff(name, price, options);
            request.setAttribute("massage", "Тариф успешно добавлен!");
            return "/admin/info.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Не удалось добавить тариф! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
