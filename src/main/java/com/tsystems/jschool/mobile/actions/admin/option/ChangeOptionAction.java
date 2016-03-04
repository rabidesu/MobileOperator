package com.tsystems.jschool.mobile.actions.admin.option;

import com.tsystems.jschool.mobile.actions.Action;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeOptionAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("option_id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String connectPrice = request.getParameter("connect_price");
        String[] requiredOption = request.getParameterValues("required_option");
        String[] incompatibleOption = request.getParameterValues("incompatible_option");

        try {
            app.optionService.changeOption(id, name, price, connectPrice, requiredOption, incompatibleOption);
            request.setAttribute("massage", "Опция успешно изменена!");
            return "/admin/info.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Не удалось сохранить изменения! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
