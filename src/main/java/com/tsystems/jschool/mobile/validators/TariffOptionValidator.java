package com.tsystems.jschool.mobile.validators;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.services.API.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Component
public class TariffOptionValidator implements Validator{

    @Autowired
    private OptionService optionService;

    public boolean supports(Class<?> clazz) {
        return Tariff.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {

        Tariff tariff = (Tariff) target;

        if (tariff.getOptions() == null){
            tariff.setOptions(new ArrayList<>());
        }

        List<Option> options = tariff.getOptions();

        if (optionService.NotAllRequiredOptionAvailable(options)){
            errors.rejectValue("options", "Error.tariff.option.not.available");
        }

    }

}
