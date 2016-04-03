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

        List<Option> allRequiredOption = optionService.getAllRequiredOption(option);
        List<Option> allIncompatibleOption = optionService.getAllIncompatibleOption(option, allRequiredOption);

        if (isRequiredAndIncompatibleIntersection(option)){
            errors.rejectValue("optionsIncompatible", "Error.option.compatibility");
        }
        if (isOptionDependOnIncompatibleParent(option, allIncompatibleOption)){
            errors.rejectValue("optionsIncompatible", "Error.option.compatibility.depend.parent.inc");
        }
        if (isOptionIncompatibleWithParentRequired(option, allRequiredOption)){
            errors.rejectValue("optionsIncompatible", "Error.option.compatibility.inc.parent.required");
        }
        if (isInterdependentOptions(option)){
            errors.rejectValue("optionsIncompatible", "Error.option.compatibility.interdependent");
        }




    }

    private boolean isRequiredAndIncompatibleIntersection(Option option){
        for (Option reqOption : option.getOptionsRequired()){
            if (option.getOptionsIncompatible().contains(reqOption)) {
                return true;
            }
        }
        return false;
    }


    /*
    * Опция зависима от опции, которая несовместима с другими требуемыми опциями
     */
    private boolean isOptionDependOnIncompatibleParent(Option option, List<Option> allIncompatibleOptions){
        for (Option reqOption : option.getOptionsRequired()){
            if (allIncompatibleOptions.contains(reqOption)) return true;
        }

        return false;
    }

    /*
    * Опция несовместима с опцией, которая является требуемой для одной из родительских опций
     */
    private boolean isOptionIncompatibleWithParentRequired(Option option, List<Option> allRequiredOptions){
        for (Option incOption : option.getOptionsIncompatible()){
            if (allRequiredOptions.contains(incOption)) return true;
        }

        return false;
    }

    private boolean isInterdependentOptions(Option option){
        for (Option reqOption : option.getOptionsRequired()){
            List<Option> allRequiredOptions = optionService.getAllRequiredOption(reqOption);
            if (allRequiredOptions.contains(option)) return true;
        }

        return false;
    }
}
