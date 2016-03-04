package com.tsystems.jschool.mobile.actions.admin.contract;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.tsystems.jschool.mobile.actions.Action;

public class NewContractAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Option> options = app.optionService.getAllOptions();
            List<Tariff> tariffs = app.tariffService.getAllTariffs();

            request.setAttribute("options", options);
            request.setAttribute("tariffs", tariffs);

            String user_id = request.getParameter("user_id");
            if (user_id == null) {
                return "/admin/contract/newContract.jsp";
            } else {
                User user = app.userService.getUserById(user_id);
                request.setAttribute("user", user);
                return "/admin/contract/newContractForUser.jsp";
            }
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Невозможно создать контракт! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
