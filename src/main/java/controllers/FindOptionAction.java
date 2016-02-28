package controllers;

import entities.Option;
import entities.User;
import services.API.OptionService;
import services.API.UserService;
import services.Impl.OptionServiceImpl;
import services.Impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandra on 22.02.2016.
 */
public class FindOptionAction extends Action {

    OptionService optionService = new OptionServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String searchText = request.getParameter("search_text");
        List<Option> options;
        if (searchText != null){
            options = optionService.getOptionsByName(searchText);
        } else {
            options = optionService.getAllOptions();
        }
        request.setAttribute("listOptions", options);

        if (options.isEmpty()) {
            request.setAttribute("message", "Нет ни одной опции, удовлетворяющей условиям поиска");
        } else {
            request.setAttribute("message", null);
        }
        return "/admin/searchOption.jsp";
    }
}
