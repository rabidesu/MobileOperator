package com.tsystems.jschool.mobile.actions.admin.tariff;

import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.Impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import com.tsystems.jschool.mobile.actions.Action;
/**
 * Created by Alexandra on 25.02.2016.
 */
public class ChangeTariffAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("tariff_id");
        String name = request.getParameter("name");
        byte[] bytes = name.getBytes(StandardCharsets.ISO_8859_1);
        name = new String(bytes, StandardCharsets.UTF_8);
        String price = request.getParameter("price");
        String[] options = request.getParameterValues("options");
        try {
            app.tariffService.changeTariff(id, name, price, options);
            request.setAttribute("massage", "Тариф успешно изменен!");
            return "/admin/info.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Не удалось изменить тариф! (" + e.getCause().getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
