package controllers;

import services.API.ContractService;
import services.API.OptionService;
import services.API.TariffService;
import services.API.UserService;
import services.Impl.ContractServiceImpl;
import services.Impl.OptionServiceImpl;
import services.Impl.TariffServiceImpl;
import services.Impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * Created by Alexandra on 22.02.2016.
 */
public class SaveContractAction extends Action {

    private ContractService contractService = new ContractServiceImpl();

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
        contractService.addContract(name, surname, date, passport, address, email, password, phone_number, tariffId, options);
        request.setAttribute("massage", "Контракт успешно добавлен!");
        return "/admin/info.jsp";
    }
}
