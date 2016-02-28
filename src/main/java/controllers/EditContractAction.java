package controllers;

import entities.Contract;
import entities.Option;
import entities.Tariff;
import services.API.ContractService;
import services.API.OptionService;
import services.API.TariffService;
import services.Impl.ContractServiceImpl;
import services.Impl.OptionServiceImpl;
import services.Impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class EditContractAction extends Action {

    ContractService contractService = new ContractServiceImpl();
    TariffService tariffService = new TariffServiceImpl();
    OptionService optionService = new OptionServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        String contractId = request.getParameter("entity_id");
        Contract contract = contractService.getContractById(contractId);
        List<Tariff> tariffs = tariffService.getAllTariffs();
        List<Option> options = optionService.getAllOptions();
        request.setAttribute("options", options);
        request.setAttribute("tariffs", tariffs);
        request.setAttribute("contract", contract);
        System.out.println(contract.isBlockedByAdmin());
        return "/admin/editContract.jsp";
    }
}
