package com.tsystems.jschool.mobile.controllers;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.API.UserService;
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


    @RequestMapping(value = "/checkOptionUsed")
    public @ResponseBody
    boolean checkOptionUsed(@RequestParam String optionId) {
        return contractService.isExistContractWithOption(optionId);
    }

    @RequestMapping(value = "/checkTariffUsed")
    public @ResponseBody
    boolean checkTariffUsed(@RequestParam String tariffId) {
        return contractService.isExistContractWithTariff(tariffId);
    }

    @RequestMapping(value = "/checkEmailExists")
    public @ResponseBody
    boolean checkEmailExists(@RequestParam String email) {
        return userService.existsUserWithEmail(email);
    }


    @RequestMapping(value = "/getUser")
    public @ResponseBody
    int getUser(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return user.getId();
    }

    @RequestMapping(value = "/getOptionsForTariff")
    public @ResponseBody
    List<Option> getOptionsForTariff(@RequestParam String tariffId) {
        List<Option> optionList = new ArrayList<>();
        Tariff tariff = tariffService.getTariffById(tariffId);
        optionList = tariff.getOptions();
        return optionList;
    }

    @RequestMapping(value = "/getTariff")
    public @ResponseBody
    Tariff getTariff(@RequestParam String tariffId) {
        return tariffService.getTariffById(tariffId);
    }

    @RequestMapping(value = "/getContractOptions")
    public @ResponseBody
    List<Option> getContractOptions(@RequestParam String contractId) {
        return contractService.getContractById(contractId).getOptions();
    }



}
