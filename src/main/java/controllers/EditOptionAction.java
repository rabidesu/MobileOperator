package controllers;

import entities.Option;
import services.API.OptionService;
import services.Impl.OptionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class EditOptionAction extends Action {

    OptionService optionService = new OptionServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String optionId = request.getParameter("entity_id");
        Option option = optionService.getOptionById(optionId);
        List<Option> options = optionService.getAllAnotherOptions(optionId);
        request.setAttribute("option", option);
        request.setAttribute("options", options);
        return "/admin/editOption.jsp";
    }
}
