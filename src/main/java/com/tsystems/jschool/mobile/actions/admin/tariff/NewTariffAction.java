package com.tsystems.jschool.mobile.actions.admin.tariff;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.tsystems.jschool.mobile.actions.Action;

public class NewTariffAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Option> options = app.optionService.getAllOptions();
            request.setAttribute("options", options);
            return "/admin/tariff/newTariff.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Создание нового тарифа невозможно! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
