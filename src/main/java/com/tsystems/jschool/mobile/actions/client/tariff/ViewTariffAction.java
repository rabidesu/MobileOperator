package com.tsystems.jschool.mobile.actions.client.tariff;

import com.tsystems.jschool.mobile.actions.Action;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.Impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandra on 22.02.2016.
 */
public class ViewTariffAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Tariff> tariffs = app.tariffService.getAllTariffs();
            request.setAttribute("listTariffs", tariffs);
            return "/client/tariff/searchTariff.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Просмотр тарифов невозможен! (" + e.getCause().getMessage() + ")");
            return "/client/info.jsp";
        }
    }
}
