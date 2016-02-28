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
 * Created by Alexandra on 25.02.2016.
 */
public class EditTariffAction extends Action {

    OptionService optionService = new OptionServiceImpl();
    TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String tariffId = request.getParameter("entity_id");
        Tariff tariff = tariffService.getTariffById(tariffId);
        List<Option> options = optionService.getAllOptions();
        request.setAttribute("tariff", tariff);
        request.setAttribute("options", options);
        return "/admin/editTariff.jsp";
    }
}
