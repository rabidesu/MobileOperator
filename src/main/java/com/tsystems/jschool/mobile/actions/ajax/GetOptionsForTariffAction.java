package com.tsystems.jschool.mobile.actions.ajax;

import com.google.gson.Gson;
import com.tsystems.jschool.mobile.controllers.Action;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.Impl.OptionServiceImpl;
import com.tsystems.jschool.mobile.services.Impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexandra on 23.02.2016.
 */
public class GetOptionsForTariffAction extends Action {

    OptionService optionService = new OptionServiceImpl();
    TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String tariffId = request.getParameter("tariff_id");
        Tariff tariff = tariffService.getTariffById(tariffId);
        List<Option> options = tariff.getOptions();
        Gson gson = new Gson();
        String json = gson.toJson(options);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
