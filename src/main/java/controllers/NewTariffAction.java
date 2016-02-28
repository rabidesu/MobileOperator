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
public class NewTariffAction extends Action {

    OptionService optionService = new OptionServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Option> options = optionService.getAllOptions();
        request.setAttribute("options", options);
        for (Option op : options){
            System.out.println(op.getName());
        }
        return "/admin/newTariff.jsp";
    }
}
