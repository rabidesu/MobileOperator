package controllers;

import entities.Option;
import entities.Tariff;
import services.API.OptionService;
import services.API.TariffService;
import services.Impl.OptionServiceImpl;
import services.Impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandra on 22.02.2016.
 */
public class FindTariffAction extends Action {

    TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String searchText = request.getParameter("search_text");
        List<Tariff> tariffs = null;
        if (searchText != null){
            tariffs = tariffService.findTariffByName(searchText);
        } else {
            tariffs = tariffService.getAllTariffs();
        }
        request.setAttribute("listTariffs", tariffs);

        if (tariffs.isEmpty()) {
            request.setAttribute("message", "Нет ни одного тарифа, удовлетворяющего условиям поиска");
        } else {
            request.setAttribute("message", null);
        }
        return "/admin/searchTariff.jsp";
    }
}
