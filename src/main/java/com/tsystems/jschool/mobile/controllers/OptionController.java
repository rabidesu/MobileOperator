package com.tsystems.jschool.mobile.controllers;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.API.UserService;
import com.tsystems.jschool.mobile.validators.ChangeContractValidator;
import com.tsystems.jschool.mobile.validators.CompatibilityOptionValidator;
import com.tsystems.jschool.mobile.validators.NewContractValidator;
import com.tsystems.jschool.mobile.validators.TariffOptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.List;

@Controller
@SessionAttributes({"loggedUser"})
public class OptionController {

    @Autowired
    private OptionService optionService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private CompatibilityOptionValidator optionValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Option.class, "optionsRequired", new PropertyEditorSupport(Option.class) {
            @Override
            public void setAsText(String str) {
                if (str.equals("") || str == null)
                    setValue(null);
                else
                    setValue(optionService.getOptionById(str));
            }

            @Override
            public String getAsText() {
                return String.valueOf(((Option) getValue()).getId());
            }
        });
        binder.registerCustomEditor(Option.class, "optionsIncompatible", new PropertyEditorSupport(Option.class) {
            @Override
            public void setAsText(String str) {
                if (str.equals("") || str == null)
                    setValue(null);
                else
                    setValue(optionService.getOptionById(str));
            }

            @Override
            public String getAsText() {
                return String.valueOf(((Option) getValue()).getId());
            }
        });
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
        model.addAttribute("used", contractService.isExistContractWithOption(optionId));
        model.addAttribute("inTariff", tariffService.existsTariffWithOption(optionId));

        return "/admin/option/editOption";
    }

    @RequestMapping(value = "changeOption")
    public String changeOption(@RequestParam(value = "id")String optionId, @Valid Option option, BindingResult errors, Model model) {
        optionValidator.validate(option, errors);
        if (errors.hasErrors()){
            model.addAttribute("anotherOptions", optionService.getAllAnotherOptions(String.valueOf(option.getId())));
            model.addAttribute("used", contractService.isExistContractWithOption(optionId));
            model.addAttribute("inTariff", tariffService.existsTariffWithOption(optionId));
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
