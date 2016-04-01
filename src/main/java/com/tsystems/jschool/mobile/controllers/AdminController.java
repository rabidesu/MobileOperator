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
import com.tsystems.jschool.mobile.validators.CompatibilityOptionValidator;
import com.tsystems.jschool.mobile.validators.NewContractValidator;
import com.tsystems.jschool.mobile.validators.TariffOptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes({"loggedUser", "changingContract"})
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private NewContractValidator newContractValidator;
    @Autowired
    private CompatibilityOptionValidator optionValidator;
    @Autowired
    private TariffOptionValidator tariffValidator;
    @Autowired
    private ChangeContractValidator changeContractValidator;


    /*
    * Clients control
     */

//    @RequestMapping(value = "users/page/{pageNumber}", method = RequestMethod.GET)
//    public String getUsersPage(@PathVariable Integer pageNumber,
//                               @RequestParam(value = "search_text", required = false) String text,
//                               @RequestParam(value = "search_field", required = false) String searchField,
//                               Model model) {
//
//        Page<User> page;
//        if (text == null || text.isEmpty()) {
//            page = userService.getUserList(pageNumber);
//        } else {
//            System.out.println(searchField);
//            switch (searchField){
//                case "phone": page = userService.getUserListByContract(pageNumber, text); break;
//                case "surname": page = userService.getUserListBySurname(pageNumber, text); break;
//                case "email": page = userService.getUserListByEmail(pageNumber, text); break;
//                default: page = userService.getUserList(pageNumber); //todo log
//            }
//        }
//
//        int current = page.getNumber() + 1;
//        int begin = Math.max(1, current - 5);
//        int end = Math.min(begin + 10, page.getTotalPages());
//
//        model.addAttribute("listUsers", page);
//        model.addAttribute("beginIndex", begin);
//        model.addAttribute("endIndex", end);
//        model.addAttribute("currentIndex", current);
//
//        return "/admin/client/searchClient";
//    }

    @RequestMapping(value = "findClientProfile")
    public String findClientProfile(@RequestParam(value = "user_id")String userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "/admin/client/clientProfile";
    }

    /*
    * Contracts control
     */

    @RequestMapping(value = {"contract/page/{pageNumber}/{searchText}", "contract/page/{pageNumber}"}, method = RequestMethod.GET)
    public String getContractPage(@PathVariable Integer pageNumber, @PathVariable Optional<String> searchText,
                                  @RequestParam(value = "search_text", required = false) String text, Model model) {

        int pageSize = 3;
        int totalCount;
        List<Contract> pageContracts;

        if (text != null && !text.isEmpty()){
            pageContracts = contractService.getContractsPageByNumber(pageNumber, pageSize, text);
            totalCount = contractService.getCountContractsByNumber(text);
        } else if (searchText.isPresent()){
            pageContracts = contractService.getContractsPageByNumber(pageNumber, pageSize, searchText.get());
            totalCount = contractService.getCountContractsByNumber(searchText.get());
        } else {
            pageContracts = contractService.getContractsPage(pageNumber, pageSize);
            totalCount = contractService.getCountContracts();
        }

        int current = pageNumber;
        int begin = Math.max(1, current - 5);
        long totalPage = totalCount % 3 == 0 ? totalCount/3 : totalCount/3 + 1;
        long end = Math.min(begin + 10, totalPage);


        model.addAttribute("listContracts", pageContracts);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("searchText", text != null ? text : searchText.isPresent() ? searchText.get() : "");

        return "/admin/contract/searchContract";
    }

    @RequestMapping(value = "newContract")
    public String newContract(Model model) {
        List<Tariff> tariffs = tariffService.getAvailableTariffs();
        model.addAttribute("tariffs", tariffs);
        model.addAttribute("contract", new Contract());
        return "/admin/contract/newContract";
    }

    @RequestMapping(value = "newContractForUser")
    public String newContractForUser(@RequestParam(value = "user_id") String userId, Model model) {
        List<Tariff> tariffs = tariffService.getAvailableTariffs();
        model.addAttribute("tariffs", tariffs);
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("contract", new Contract());
        return "/admin/contract/newContractForUser";
    }

    @RequestMapping(value = "saveContractForUser")
    public String saveContractForUser(@RequestParam(value = "user_id")String userId, Contract contract, BindingResult errors, Model model, HttpServletRequest request) {
        contract.setUser(userService.getUserById(userId));
        newContractValidator.validate(contract, errors);
        if (errors.hasErrors()) {
            User user = userService.getUserById(userId);
            model.addAttribute("user", user);
            List<Tariff> tariffs = tariffService.getAvailableTariffs();
            model.addAttribute("tariffs", tariffs);
            return "/admin/contract/newContractForUser";
        }

        contractService.addContractForUser(contract);
        request.getSession().removeAttribute("user");
        model.addAttribute("message", "Контракт успешно добавлен!");
        return "/admin/info";
    }

    @RequestMapping(value = "saveContract")
    public String saveContract(Contract contract, BindingResult errors, Model model) {
        newContractValidator.validate(contract, errors);
        if (errors.hasErrors()){
            List<Tariff> tariffs = tariffService.getAvailableTariffs();
            model.addAttribute("tariffs", tariffs);
            return "/admin/contract/newContract";
        }

        //todo validation
        contractService.addContract(contract);
        model.addAttribute("message", "Контракт успешно добавлен!");
        return "/admin/info";
    }

    @RequestMapping(value = "editContractByAdmin")
    public String editContractByAdmin(@RequestParam(value = "entity_id")String contractId, Model model) {
        model.addAttribute("contract", contractService.getContractById(contractId));
        model.addAttribute("tariffs",  tariffService.getAvailableTariffsForContract(contractId));
        model.addAttribute("notSelectedOptions", optionService.getNotSelectedOption(contractId));
        return "admin/contract/editContract";
    }

    @RequestMapping(value = "changeContract")
    public String changeContract(@RequestParam(value = "contract_id") String contractId, Contract contract,
                                 BindingResult errors, Model model) {
        changeContractValidator.validate(contract, errors);
        if (errors.hasErrors()){
            System.out.println(errors);
            List<Tariff> tariffs = tariffService.getAllTariffs();
            model.addAttribute("tariffs", tariffs);
            return "/admin/contract/editContract";
        }

        //todo validation
        contractService.changeContract(contractId, contract);
        model.addAttribute("message", "Контракт успешно изменен!");
        return "/admin/info";
    }

    @RequestMapping(value = "blockContractByAdmin")
    public String blockContractByAdmin(@RequestParam(value = "contract_id")String contractId, Model model) {
        contractService.blockContractByAdmin(contractId);
        model.addAttribute("message", "Контракт успешно заблокирован!");
        return "admin/info";
    }

    @RequestMapping(value = "unblockContractByAdmin")
    public String unblockContractByAdmin(@RequestParam(value = "contract_id")String contractId, Model model) {
        contractService.unblockContractByAdmin(contractId);
        model.addAttribute("message", "Контракт успешно разблокирован!");
        return "admin/info";
    }

    @RequestMapping(value = "searchContract")
    public String searchContract(Model model) {

        List<Contract> contracts = contractService.getAllContracts();
        model.addAttribute("listContracts", contracts);
        return "/admin/contract/searchContract";
    }

    /*
    * Tariff control
     */

    @RequestMapping(value = "newTariff", method = RequestMethod.GET)
    public String newTariff(Model model) {
        model.addAttribute("options", optionService.getAvailableOptions());
        model.addAttribute("tariff", new Tariff());
        return "/admin/tariff/newTariff";
    }

    @RequestMapping(value = "searchTariff")
    public String searchTariff(@RequestParam(value = "search_text", required = false) String searchText, Model model) {

        List<Tariff> tariffs;
        if (searchText != null) {
            tariffs = tariffService.findTariffByName(searchText);
        } else {
            tariffs = tariffService.getAllTariffs();
        }
        model.addAttribute("listTariffs", tariffs);
        return "/admin/tariff/searchTariff";
    }

    @RequestMapping(value = "saveTariff")
    public String saveTariff(@Valid Tariff tariff, BindingResult errors, Model model) {
        tariffValidator.validate(tariff, errors);
        if (errors.hasErrors()){
            model.addAttribute("options", optionService.getAvailableOptions());
            return "/admin/tariff/newTariff";
        }

        //todo validation
        tariffService.saveTariff(tariff);
        model.addAttribute("message", "Тариф успешно добавлен!");
        return "/admin/info";
    }

    @RequestMapping(value = "editTariff")
    public String editTariff(@RequestParam(value = "entity_id")String tariffId, Model model) {
        Tariff tariff = tariffService.getTariffById(tariffId);
        model.addAttribute("options", optionService.getAvailableOptions());
        model.addAttribute("tariff", tariff);
        return "/admin/tariff/editTariff";
    }

    @RequestMapping(value = "changeTariff")
    public String changeTariff(@RequestParam(value = "id")String tariffId, @Valid Tariff tariff, BindingResult errors, Model model) {
        tariffValidator.validate(tariff, errors);
        if (errors.hasErrors()){
            model.addAttribute("options", optionService.getAvailableOptions());
            System.out.println(errors);
            return "/admin/tariff/editTariff";
        }
        tariffService.changeTariff(tariffId, tariff);
        model.addAttribute("message", "Тариф успешно изменен!");
        return "/admin/info";
    }

    @RequestMapping(value = "removeTariff")
    public String removeTariff(@RequestParam(value = "tariff_id")String tariffId, Model model) {

        // todo validation
        tariffService.removeTariffById(tariffId);
        model.addAttribute("message", "Тариф удален");
        return "/admin/info";
    }






    /*
    *  Options control
     */

//    @ModelAttribute("options")
//    public List getAllOptions(){
//        return optionService.getAllOptions();
//    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Option.class, "optionsRequired", new PropertyEditorSupport(Option.class){
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
        binder.registerCustomEditor(Option.class, "optionsIncompatible", new PropertyEditorSupport(Option.class){
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
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-d"), true));
    }

    @RequestMapping(value = "newOption", method = RequestMethod.GET)
    public String newOption(Model model) {
        model.addAttribute("options", optionService.getAvailableOptions());
        model.addAttribute("option", new Option());
        return "/admin/option/newOption";
    }

    @RequestMapping(value = "saveOption")
    public String saveOption(@Valid Option option, BindingResult errors, Model model) {
        optionValidator.validate(option, errors);
        if (errors.hasErrors()){
            model.addAttribute("options", optionService.getAvailableOptions());
            return "/admin/option/newOption";
        }
        optionService.saveOption(option);
        model.addAttribute("message", "Опция успешно добавлена!");
        return "/admin/info";
    }

    @RequestMapping(value = "editOption")
    public String editOption(@RequestParam(value = "entity_id")String optionId, Model model) {
        Option option = optionService.getOptionById(optionId);
        optionService.getAllRequiredOption(option);
        model.addAttribute("option", option);
        model.addAttribute("anotherOptions", optionService.getAllAnotherOptions(optionId));
        model.addAttribute("option_name", option.getName());

        return "/admin/option/editOption";
    }

    @RequestMapping(value = "changeOption")
    public String changeOption(@RequestParam(value = "id")String optionId, @Valid Option option, BindingResult errors, Model model) {
        optionValidator.validate(option, errors);
        if (errors.hasErrors()){
            model.addAttribute("anotherOptions", optionService.getAllAnotherOptions(String.valueOf(option.getId())));
            return "/admin/option/editOption";
        }

        //todo validation
        optionService.changeOption(optionId, option);
        model.addAttribute("message", "Опция успешно изменена!");
        return "/admin/info";
    }


    @RequestMapping(value = "searchOption")
    public String searchOption(@RequestParam(value = "search_text", required = false) String searchText, Model model) {

        List<Option> options;
        if (searchText != null) {
            options = optionService.getOptionsByName(searchText);
        } else {
            options = optionService.getAllOptions();
        }
        model.addAttribute("listOptions", options);
        return "/admin/option/searchOption";
    }

    @RequestMapping(value = "removeOption")
    public String removeOption(@RequestParam(value = "option_id")String optionId, Model model) {

        // todo validation
        optionService.removeOptionById(optionId);
        model.addAttribute("message", "Опция удалена");
        return "/admin/info";
    }

}
