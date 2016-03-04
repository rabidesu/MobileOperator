package com.tsystems.jschool.mobile.actions.client.tariff;

import com.tsystems.jschool.mobile.actions.Action;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTariffToContractAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String tariffId = request.getParameter("tariff_id");
        try {
            Tariff tariff = app.tariffService.getTariffById(tariffId);
            request.setAttribute("tariff", tariff);
            return "/client/tariff/addTariffToContract.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Переход на тариф невозможен! (" + e.getMessage() + ")");
            return "/client/info.jsp";
        }
    }
}
