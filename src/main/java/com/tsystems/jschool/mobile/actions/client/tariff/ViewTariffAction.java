package com.tsystems.jschool.mobile.actions.client.tariff;

import com.tsystems.jschool.mobile.actions.Action;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewTariffAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Tariff> tariffs = app.tariffService.getAllTariffs();
            request.setAttribute("listTariffs", tariffs);
            return "/client/tariff/searchTariff.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Просмотр тарифов невозможен! (" + e.getMessage() + ")");
            return "/client/info.jsp";
        }
    }
}
