package com.tsystems.jschool.mobile.controllers;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.API.UserService;
import com.tsystems.jschool.mobile.validators.ChangeContractValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.List;

/**
 * Created by Alexandra on 29.03.2016.
 */
@Controller
@SessionAttributes({"loggedUser"})
public class ClientController {

    @Autowired
    private UserService userService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private ChangeContractValidator changeContractValidator;

    private static Logger logger = Logger.getLogger(ClientController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Option.class, "options", new PropertyEditorSupport(Option.class){
            @Override
            public void setAsText(String str) {
                if( str.equals("") || str == null )
                    setValue(null);
                else
                    setValue(optionService.getOptionById(str));
            }
            @Override
            public String getAsText() {
                return String.valueOf(((Option)getValue()).getId());
            }
        });
        binder.registerCustomEditor(Tariff.class, "tariff", new PropertyEditorSupport(Tariff.class){
            @Override
            public void setAsText(String str) {
                if( str.equals("") || str == null )
                    setValue(null);
                else
                    setValue(tariffService.getTariffById(str));
            }
        });
    }

    @RequestMapping(value = "clientProfile")
    public String clientProfile(Model model) {
        return "/client/profile/profile";
    }

    @RequestMapping(value = "clientContracts")
    public String clientContracts(@ModelAttribute(value = "loggedUser") User loggedUser, Model model) {
        User user = userService.getUserByEmail(loggedUser.getEmail());
        model.addAttribute("user", user);
        return "/client/contract/contracts";
    }

    @RequestMapping(value = "searchTariffs")
    public String searchTariffs(Model model) {
        List<Tariff> tariffs = tariffService.getAvailableTariffs();
        model.addAttribute("tariffs", tariffs);
        return "/client/tariff/searchTariffs";
    }

    @RequestMapping(value = "editContractByClient")
    public String editContract(@ModelAttribute(value = "loggedUser") User loggedUser,
                               @RequestParam(value = "contract_id") String contractId, Model model) {
        userService.checkIfUserHasContract(loggedUser, contractId);
        Contract contract = contractService.getContractById(contractId);
        List<Tariff> tariffs = tariffService.getAvailableTariffsForContract(contractId);
        List<Option> notSelectedOption = optionService.getNotSelectedOption(contractId);
        model.addAttribute("contract", contract);
        model.addAttribute("tariffs", tariffs);
        model.addAttribute("notSelectedOptions", notSelectedOption);
        return "/client/contract/editContract";
    }

    @RequestMapping(value = "addTariffToContract")
    public String addTariffToContract(@ModelAttribute(value = "loggedUser") User loggedUser,
                                      @RequestParam(value = "tariff_id") String tariffId, Model model) {
        model.addAttribute("tariff", tariffService.getTariffById(tariffId));
        model.addAttribute("contracts", loggedUser.getContracts());
        return "/client/tariff/addTariffToContract";
    }

    @RequestMapping(value = "saveTariffToContract")
    public String saveTariffToContract(@ModelAttribute(value = "loggedUser") User loggedUser,
                                       Contract contract,
                                       BindingResult errors, Model model) {
        userService.checkIfUserHasContract(loggedUser, String.valueOf(contract.getId()));
        changeContractValidator.validate(contract, errors);
        if (errors.hasErrors()){
            logger.debug(errors);
            model.addAttribute("tariff", contract.getTariff());
            model.addAttribute("contracts", loggedUser.getContracts());
            return "/client/tariff/addTariffToContract";
        }

        contractService.changeContract(String.valueOf(contract.getId()), contract);
        model.addAttribute("message", "Контракт успешно изменен!");
        return "/client/info";
    }

    @RequestMapping(value = "changeContractByClient")
    public String changeContractByClient(@ModelAttribute(value = "loggedUser") User loggedUser,
                                         @RequestParam(value = "contract_id") String contractId, Contract contract,
                                 BindingResult errors, Model model) {
        userService.checkIfUserHasContract(loggedUser, contractId);
        changeContractValidator.validate(contract, errors);
        if (errors.hasErrors()){
            logger.debug(errors);
            model.addAttribute("contract", contractService.getContractById(contractId));
            model.addAttribute("tariffs", tariffService.getAvailableTariffsForContract(contractId));
            model.addAttribute("notSelectedOptions", optionService.getNotSelectedOption(contractId));
            return "/client/contract/editContract";
        }

        contractService.changeContract(contractId, contract);
        model.addAttribute("message", "Контракт успешно изменен!");
        return "/client/info";
    }

    @RequestMapping(value = "blockContractByClient")
    public String blockContractByAdmin(@ModelAttribute(value = "loggedUser") User loggedUser,
                                       @RequestParam(value = "contract_id")String contractId, Model model) {
        userService.checkIfUserHasContract(loggedUser, contractId);
        contractService.blockContractByClient(contractId);
        model.addAttribute("message", "Контракт успешно заблокирован!");
        return "client/info";
    }

    @RequestMapping(value = "unblockContractByClient")
    public String unblockContractByAdmin(@ModelAttribute(value = "loggedUser") User loggedUser,
                                         @RequestParam(value = "contract_id")String contractId, Model model) {
        userService.checkIfUserHasContract(loggedUser, contractId);
        contractService.unblockContractByClient(contractId);
        model.addAttribute("message", "Контракт успешно разблокирован!");
        return "client/info";
    }

}
