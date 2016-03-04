package com.tsystems.jschool.mobile.actions.admin.option;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.tsystems.jschool.mobile.actions.Action;

public class EditOptionAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String optionId = request.getParameter("entity_id");

        try {
            Option option = app.optionService.getOptionById(optionId);
            List<Option> options = app.optionService.getAllAnotherOptions(optionId);

            request.setAttribute("option", option);
            request.setAttribute("options", options);

            return "/admin/option/editOption.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Редактирование опции невозможно! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
