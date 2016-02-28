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
public class SaveTariffAction extends Action {

    TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        byte[] bytes = name.getBytes(StandardCharsets.ISO_8859_1);
        name = new String(bytes, StandardCharsets.UTF_8);
        String price = request.getParameter("price");
        String[] options = request.getParameterValues("options");

        tariffService.addTariff(name, price, options);
        request.setAttribute("massage", "Тариф успешно добавлен!");
        return "/admin/info.jsp";
    }
}
