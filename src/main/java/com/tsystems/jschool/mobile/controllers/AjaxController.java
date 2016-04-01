package com.tsystems.jschool.mobile.controllers;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.API.UserService;
import com.tsystems.jschool.mobile.utils.constants.Actions;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class AjaxController {

    @Autowired
    private UserService userService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;


    @RequestMapping(value = Actions.CHECK_IF_OPTION_USED)
    public @ResponseBody
    boolean checkOptionUsed(@RequestParam String optionId) {
        return contractService.isExistContractWithOption(optionId);
    }

    @RequestMapping(value = Actions.CHECK_IF_TARIFF_USED)
    public @ResponseBody
    boolean checkTariffUsed(@RequestParam String tariffId) {
        return contractService.isExistContractWithTariff(tariffId);
    }

    @RequestMapping(value = Actions.CHECK_IF_EMAIL_EXISTS)
    public @ResponseBody
    boolean checkEmailExists(@RequestParam String email) {
        return userService.existsUserWithEmail(email);
    }


    @RequestMapping(value = Actions.GET_USER_DATA)
    public @ResponseBody
    int getUser(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return user.getId();
    }

    @RequestMapping(value = Actions.GET_OPTIONS_FOR_TARIFF)
    public @ResponseBody
    List<Option> getOptionsForTariff(@RequestParam String tariffId) {
        List<Option> optionList = new ArrayList<>();
        Tariff tariff = tariffService.getTariffById(tariffId);
        optionList = tariff.getOptions();
        return optionList;
    }

}
