package com.tsystems.jschool.mobile.actions.admin.option;

import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tsystems.jschool.mobile.actions.Action;

public class RemoveOptionAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String option_id = request.getParameter("option_id");

        try {
            app.optionService.removeOptionById(option_id);
            request.setAttribute("massage", "Опция успешно удалена.");
            return "/admin/info.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Невозможно удалить опцию! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
