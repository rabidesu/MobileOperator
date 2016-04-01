package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
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

    List<Option> getAllIncompatibleOption (Option option, List<Option> requiredOptions);

}
