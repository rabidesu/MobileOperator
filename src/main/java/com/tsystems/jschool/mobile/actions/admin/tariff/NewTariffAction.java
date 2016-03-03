package com.tsystems.jschool.mobile.actions.admin.tariff;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.Impl.OptionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.tsystems.jschool.mobile.actions.Action;
/**
 * Created by Alexandra on 25.02.2016.
 */
public class NewTariffAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Option> options = app.optionService.getAllOptions();
            request.setAttribute("options", options);
            return "/admin/tariff/newTariff.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Создание нового тарифа невозможно! (" + e.getCause().getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
