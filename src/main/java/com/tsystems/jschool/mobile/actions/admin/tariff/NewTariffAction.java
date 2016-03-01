package com.tsystems.jschool.mobile.actions.admin.tariff;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.Impl.OptionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.tsystems.jschool.mobile.controllers.Action;
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
        return "/admin/tariff/newTariff.jsp";
    }
}
