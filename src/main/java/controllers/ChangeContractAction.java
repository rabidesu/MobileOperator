package controllers;

import services.API.ContractService;
import services.API.OptionService;
import services.Impl.ContractServiceImpl;
import services.Impl.OptionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class ChangeContractAction extends Action {

    ContractService contractService = new ContractServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("contract_id");
        String tariff = request.getParameter("tariff_id");
        String block = request.getParameter("block");
        String[] options = request.getParameterValues("options");

        contractService.changeContract(id, tariff, options, block);
        request.setAttribute("massage", "Контракт успешно изменен!");
        return "/admin/info.jsp";
    }
}
