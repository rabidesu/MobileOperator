package com.tsystems.jschool.mobile.actions.client.tariff;

import com.tsystems.jschool.mobile.controllers.Action;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.Impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandra on 22.02.2016.
 */
public class ViewTariffAction extends Action {

    TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Tariff> tariffs = tariffService.getAllTariffs();
        request.setAttribute("listTariffs", tariffs);

        if (tariffs.isEmpty()) {
            request.setAttribute("message", "Нет ни одного тарифа, удовлетворяющего условиям поиска");
        } else {
            request.setAttribute("message", null);
        }
        return "/client/tariff/searchTariff.jsp";
    }
}
