package com.tsystems.jschool.mobile.actions.admin.tariff;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.Impl.OptionServiceImpl;
import com.tsystems.jschool.mobile.services.Impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.tsystems.jschool.mobile.actions.Action;
/**
 * Created by Alexandra on 25.02.2016.
 */
public class EditTariffAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String tariffId = request.getParameter("entity_id");

        try {
            Tariff tariff = app.tariffService.getTariffById(tariffId);
            List<Option> options = app.optionService.getAllOptions();
            request.setAttribute("tariff", tariff);
            request.setAttribute("options", options);
            return "/admin/tariff/editTariff.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Редактирование тарифа невозможно! (" + e.getCause().getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
