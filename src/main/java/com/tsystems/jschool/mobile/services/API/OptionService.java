package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import java.util.List;


public interface OptionService {

    List<Option> getAllOptions();

    List<Option> getAvailableOptions();

    List<Option> getAllAnotherOptions(String id);

    void changeOption(String optionId, Option option);

    Option getOptionById(String id);

    void removeOptionById(String id);

    List<Option> getOptionsByName(String name);

    void saveOption(Option option);

    List<Option> getAllRequiredOption(Option option);

    List<Option> getAllIncompatibleOption (Option option);

    List<Option> getNotSelectedOption(String contractId);

    boolean isInterdependentOptions(Option option);

    boolean isOptionDependOnIncompatibleParent(Option option);

    boolean isRequiredAndIncompatibleIntersection(Option option);

    boolean isOptionIncompatibleWithParentRequired(Option option);

    boolean NotAllRequiredOptionAvailable(List<Option> options);

    boolean isIncompatibleOptions(List<Option> options);

}
