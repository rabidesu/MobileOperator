package com.tsystems.jschool.mobile.validators;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.services.API.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompatibilityOptionValidator implements Validator{

    @Autowired
    private OptionService optionService;

    public boolean supports(Class<?> clazz) {
        return Option.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {

        Option option = (Option) target;

        if (option.getOptionsRequired() == null){
            option.setOptionsRequired(new ArrayList<>());
        }
        if (option.getOptionsIncompatible() == null){
            option.setOptionsIncompatible(new ArrayList<>());
        }

        if (optionService.isRequiredAndIncompatibleIntersection(option)){
            errors.rejectValue("optionsIncompatible", "Error.option.compatibility");
        }
        if (optionService.isOptionDependOnIncompatibleParent(option)){
            errors.rejectValue("optionsIncompatible", "Error.option.compatibility.depend.parent.inc");
        }
        if (optionService.isOptionIncompatibleWithParentRequired(option)){
            errors.rejectValue("optionsIncompatible", "Error.option.compatibility.inc.parent.required");
        }
        if (optionService.isInterdependentOptions(option)){
            errors.rejectValue("optionsIncompatible", "Error.option.compatibility.interdependent");
        }
    }
}
