package com.tsystems.jschool.mobile.actions.admin.tariff;

import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tsystems.jschool.mobile.actions.Action;

public class RemoveTariffAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String tariff_id = request.getParameter("tariff_id");

        try {
            app.tariffService.removeTariffById(tariff_id);
            request.setAttribute("massage", "Тариф успешно удален.");
            return "/admin/info.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Не удалось удалить тариф! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
