package com.tsystems.jschool.mobile.validators;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.API.TariffService;
import com.tsystems.jschool.mobile.services.API.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewContractValidator implements Validator {

    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;


    private String phoneRegexp = "^\\+7\\s{1}\\(\\d{3}\\)\\s{1}\\d{3}-\\d{2}-\\d{2}$";

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

        if (contract.getUser().getName().isEmpty()) {
            errors.rejectValue("user.name", "NotEmpty.user.name");
        }
        if (contract.getUser().getSurname().isEmpty()) {
            errors.rejectValue("user.surname", "NotEmpty.user.surname");
        }
        if (contract.getUser().getBirthday() == null) {
            errors.rejectValue("user.birthday", "NotEmpty.user.birthday");
        }
        if (contract.getUser().getPassport().isEmpty()) {
            errors.rejectValue("user.passport", "NotEmpty.user.passport");
        }
        if (contract.getUser().getPassport().isEmpty()) {
            errors.rejectValue("user.address", "NotEmpty.user.address");
        }
        if (contract.getUser().getEmail().isEmpty()) {
            errors.rejectValue("user.email", "NotEmpty.user.email");
        } else if (!EmailValidator.getInstance().isValid(contract.getUser().getEmail())) {
            errors.rejectValue("user.email", "NotSuch.user.email");
        } else if (contract.getUser().getId() == 0 && userService.existsUserWithEmail(contract.getUser().getEmail())){
            errors.rejectValue("user.email", "Exists.user.email");
        }
        if (contract.getUser().getPassword().isEmpty()) {
            errors.rejectValue("user.password", "NotEmpty.user.password");
        }
        if (contract.getNumber().isEmpty() || contract.getNumber().length() < 5) {
            errors.rejectValue("number", "NotEmpty.contract.number");
        } else if (!contract.getNumber().matches(phoneRegexp)) {
            errors.rejectValue("number", "NotSuch.contract.number");
        } else if (!contractService.findContractByNumber(contract.getNumber()).isEmpty()) {
            errors.rejectValue("number", "Exists.contract.number");
        }
        if (contract.getUser().getRole() != null && contract.getUser().getRole().getRoleName() == RoleName.ROLE_ADMIN){
            errors.rejectValue("options", "NotSuch.contract.user.role");
        }
        if (contract.getTariff() == null){
            errors.rejectValue("tariff", "NotEmpty.contract.tariff");
        }
        if (optionService.isIncompatibleOptions(contract.getOptions())){
            errors.rejectValue("options", "Error.contract.options.incompatible");
        }
        if (optionService.NotAllRequiredOptionAvailable(contract.getOptions())){
            errors.rejectValue("options", "Error.contract.options.incompatible");
        }

    }
}
