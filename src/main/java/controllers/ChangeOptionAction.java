package controllers;

import services.API.OptionService;
import services.Impl.OptionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class ChangeOptionAction extends Action {

    OptionService optionService = new OptionServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("option_id");
        String name = request.getParameter("name");
        byte[] bytes = name.getBytes(StandardCharsets.ISO_8859_1);
        name = new String(bytes, StandardCharsets.UTF_8);
        String price = request.getParameter("price");
        String connectPrice = request.getParameter("connect_price");
        String[] requiredOption = request.getParameterValues("required_option");
        String[] incompatibleOption = request.getParameterValues("incompatible_option");
        optionService.changeOption(id, name, price, connectPrice, requiredOption, incompatibleOption);
        request.setAttribute("massage", "Опция успешно изменена!");
        return "/admin/info.jsp";
    }
}
