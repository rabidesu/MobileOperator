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
public class RemoveOptionAction extends Action {

    OptionService optionService = new OptionServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String option_id = request.getParameter("option_id");
        optionService.removeOptionById(option_id);
        request.setAttribute("massage", "Опция успешно удалена.");
        return "/admin/info.jsp";
    }
}
