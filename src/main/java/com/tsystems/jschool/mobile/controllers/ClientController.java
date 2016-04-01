package com.tsystems.jschool.mobile.controllers;

import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.API.UserService;
import com.tsystems.jschool.mobile.utils.constants.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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

    @RequestMapping(value = Actions.CLIENT_PROFILE)
    public String clientProfile(Model model) {
        return "/client/profile/profile";
    }

    @RequestMapping(value = Actions.SHOW_CLIENT_CONTRACTS)
    public String clientContracts(@ModelAttribute(value = "loggedUser") User loggedUser, Model model) {
        User user = userService.getUserByEmail(loggedUser.getEmail());
        model.addAttribute("user", user);
        return "/client/contract/contracts";
    }

    @RequestMapping(value = Actions.CLIENT_SHOW_TARIFFS)
    public String searchTariffs(Model model) {
        List<Tariff> tariffs = tariffService.getAvailableTariffs();
        model.addAttribute("tariffs", tariffs);
        return "/client/tariff/searchTariffs";
    }

}
