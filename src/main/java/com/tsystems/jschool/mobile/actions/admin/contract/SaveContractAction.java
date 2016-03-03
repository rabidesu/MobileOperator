package com.tsystems.jschool.mobile.actions.admin.contract;

import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.Impl.ContractServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tsystems.jschool.mobile.actions.Action;
/**
 * Created by Alexandra on 22.02.2016.
 */
public class SaveContractAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String date = request.getParameter("date");
        System.out.println(date);
        String passport = request.getParameter("passport");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone_number = request.getParameter("phone_number");
        String tariffId = request.getParameter("tariff_id");
        String[] options = request.getParameterValues("options");
        try {
            app.contractService.addContract(name, surname, date, passport, address, email, password, phone_number, tariffId, options);
            request.setAttribute("massage", "Контракт успешно добавлен!");
            return "/admin/info.jsp";
        } catch (MobileServiceException e) {
            request.setAttribute("massage", "Не удалось добавить контракт! (" + e.getCause().getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
