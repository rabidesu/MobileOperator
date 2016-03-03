package com.tsystems.jschool.mobile.actions.client.contract;

import com.tsystems.jschool.mobile.actions.Action;
import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.Impl.ContractServiceImpl;
import com.tsystems.jschool.mobile.services.Impl.OptionServiceImpl;
import com.tsystems.jschool.mobile.services.Impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class EditContractAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String contractId = request.getParameter("entity_id");
        try {
            Contract contract = app.contractService.getContractById(contractId);
            List<Tariff> tariffs = app.tariffService.getAllTariffs();
            List<Option> options = app.optionService.getAllOptions();
            request.setAttribute("options", options);
            request.setAttribute("tariffs", tariffs);
            request.setAttribute("contract", contract);
            return "/client/contract/editContract.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Редактирование контракта невозможно! (" + e.getCause().getMessage() + ")");
            return "/client/info.jsp";
        }
    }
}
