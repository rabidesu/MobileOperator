package com.tsystems.jschool.mobile.actions.client.tariff;

import com.tsystems.jschool.mobile.controllers.Action;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.Impl.TariffServiceImpl;
import org.hibernate.Hibernate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandra on 22.02.2016.
 */
public class AddTariffToContractAction extends Action {

    TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String tariffId = request.getParameter("tariff_id");
        Tariff tariff = tariffService.getTariffById(tariffId);
        request.setAttribute("tariff", tariff);
        for (Option option : tariff.getOptions()){
            Hibernate.initialize(option.getOptionsRequired());
            System.out.println(option.getName());
        }
        return "/client/tariff/addTariffToContract.jsp";
    }
}
