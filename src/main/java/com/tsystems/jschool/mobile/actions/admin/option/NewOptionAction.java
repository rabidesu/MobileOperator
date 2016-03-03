package com.tsystems.jschool.mobile.actions.admin.option;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.Impl.OptionServiceImpl;
import com.tsystems.jschool.mobile.actions.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class NewOptionAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Option> options = app.optionService.getAllOptions();
            request.setAttribute("options", options);
            return "/admin/option/newOption.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Невозможно создать новую опцию! (" + e.getCause().getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
