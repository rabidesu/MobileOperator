package com.tsystems.jschool.mobile.webservices.controllers;

import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.API.UserService;
import com.tsystems.jschool.mobile.webservices.entities.WebContract;
import com.tsystems.jschool.mobile.webservices.entities.WebTariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/report")
public class RestController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private TariffService tariffService;

    @RequestMapping(value= "/contracts/{tariffId}", method = RequestMethod.GET,  produces = "application/json")
    @ResponseBody
    public List<WebContract> getWebContracts(@PathVariable("tariffId") String tariffId) {
        return contractService.getAllContractsWithTariff(tariffId);
    }

    @RequestMapping(value= "/tariffs", method = RequestMethod.GET,  produces = "application/json")
    @ResponseBody
    public List<WebTariff> getWebTariffs() {
        return tariffService.getAllWebTariffs();
    }


}
