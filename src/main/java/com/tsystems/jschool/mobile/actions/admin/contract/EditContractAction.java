package com.tsystems.jschool.mobile.actions.admin.contract;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.Impl.ContractServiceImpl;
import com.tsystems.jschool.mobile.services.Impl.OptionServiceImpl;
import com.tsystems.jschool.mobile.services.Impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.tsystems.jschool.mobile.controllers.Action;
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

        return "/admin/contract/editContract.jsp";
    }
}
