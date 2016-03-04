package com.tsystems.jschool.mobile.actions.admin.option;

import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsystems.jschool.mobile.actions.Action;

public class SaveOptionAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String connectPrice = request.getParameter("connect_price");
        String[] requiredOption = request.getParameterValues("required_option");
        String[] incompatibleOption = request.getParameterValues("incompatible_option");

        try {
            app.optionService.addOption(name, price, connectPrice, requiredOption, incompatibleOption);
            request.setAttribute("massage", "Опция успешно добавлена!");
            return "/admin/info.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Не удалось добавить опцию! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
