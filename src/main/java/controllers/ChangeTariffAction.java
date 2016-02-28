package controllers;

import services.API.OptionService;
import services.API.TariffService;
import services.Impl.OptionServiceImpl;
import services.Impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class ChangeTariffAction extends Action {

    TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("tariff_id");
        String name = request.getParameter("name");
        byte[] bytes = name.getBytes(StandardCharsets.ISO_8859_1);
        name = new String(bytes, StandardCharsets.UTF_8);
        String price = request.getParameter("price");
        String[] options = request.getParameterValues("options");
        tariffService.changeTariff(id, name, price, options);
        request.setAttribute("massage", "Тариф успешно изменен!");
        return "/admin/info.jsp";
    }
}
