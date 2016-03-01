package com.tsystems.jschool.mobile.actions.admin.option;

import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.Impl.OptionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tsystems.jschool.mobile.controllers.Action;
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
