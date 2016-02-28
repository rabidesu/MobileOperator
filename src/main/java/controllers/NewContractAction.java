package controllers;

import entities.Option;
import entities.Tariff;
import services.API.OptionService;
import services.API.TariffService;
import services.API.UserService;
import services.Impl.OptionServiceImpl;
import services.Impl.TariffServiceImpl;
import services.Impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class NewContractAction extends Action {

    TariffService tariffService = new TariffServiceImpl();
    OptionService optionService = new OptionServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Option> options = optionService.getAllOptions();
        List<Tariff> tariffs = tariffService.getAllTariffs();
        request.setAttribute("options", options);
        request.setAttribute("tariffs", tariffs);
        return "/admin/newContract.jsp";
    }
}
