package com.tsystems.jschool.mobile.controllers;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes({"loggedUser"})
public class TariffController {


    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private TariffOptionValidator tariffValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
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
    }

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
        model.addAttribute("allOptions", optionService.getAvailableOptions());
        model.addAttribute("tariff", tariff);
        model.addAttribute("used", contractService.isExistContractWithTariff(tariffId));
        return "/admin/tariff/editTariff";
    }

    @RequestMapping(value = "changeTariff")
    public String changeTariff(@RequestParam(value = "id")String tariffId, @Valid Tariff tariff, BindingResult errors, Model model) {
        tariffValidator.validate(tariff, errors);
        if (errors.hasErrors()){
            model.addAttribute("allOptions", optionService.getAvailableOptions());
            model.addAttribute("used", contractService.isExistContractWithTariff(tariffId));
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
}
