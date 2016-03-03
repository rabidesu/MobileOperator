package com.tsystems.jschool.mobile.actions.admin.tariff;

import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.Impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.tsystems.jschool.mobile.actions.Action;
/**
 * Created by Alexandra on 22.02.2016.
 */
public class FindTariffAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String searchText = request.getParameter("search_text");
        List<Tariff> tariffs;
        try {
            if (searchText != null) {
                tariffs = app.tariffService.findTariffByName(searchText);
            } else {
                tariffs = app.tariffService.getAllTariffs();
            }
            request.setAttribute("listTariffs", tariffs);
            return "/admin/tariff/searchTariff.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Просмотр тарифов невозможен! (" + e.getCause().getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
