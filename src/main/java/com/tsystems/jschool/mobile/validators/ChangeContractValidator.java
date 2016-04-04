package com.tsystems.jschool.mobile.validators;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.API.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeContractValidator implements Validator {

    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;


    @Override
    public boolean supports(Class clazz) {
        return Contract.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Contract contract = (Contract) target;

        if (contract.getOptions() == null){
            contract.setOptions(new ArrayList<>());
        }
        if (contract.getTariff() == null){
            errors.rejectValue("tariff", "NotEmpty.contract.tariff");
        }
        if (optionService.isIncompatibleOptions(contract.getOptions())){
            errors.rejectValue("options", "Error.contract.options.incompatible");
        }
        if (optionService.NotAllRequiredOptionAvailable(contract.getOptions())){
            errors.rejectValue("options", "Error.tariff.option.not.available");
        }

    }

//    private boolean isIncompatibleOptions(List<Option> options){
//        for (Option option : options){
//            for (Option incOption : option.getOptionsIncompatible()){
//                if (options.contains(incOption)) return true;
//            }
//        }
//        return false;
//    }
}
