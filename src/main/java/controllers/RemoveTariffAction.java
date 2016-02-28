package controllers;

import services.API.OptionService;
import services.API.TariffService;
import services.Impl.OptionServiceImpl;
import services.Impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class RemoveTariffAction extends Action {

    TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String tariff_id = request.getParameter("tariff_id");
        tariffService.removeTariffById(tariff_id);
        request.setAttribute("massage", "Тариф успешно удален.");
        return "/admin/info.jsp";
    }
}
