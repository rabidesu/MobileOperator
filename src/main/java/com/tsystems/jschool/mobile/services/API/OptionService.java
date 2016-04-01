package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public interface OptionService {

    List<Option> getAllOptions() throws MobileServiceException;

    List<Option> getAvailableOptions();

    List<Option> getAllAnotherOptions(String id) throws MobileServiceException;

    void addOption(Option option, List<Integer> requiredOption,
                   List<Integer> incompatibleOption) throws MobileServiceException;

    void changeOption(String id, String name, String price, String connectPrice, List<Integer> requiredOption,
                      List<Integer> incompatibleOption) throws MobileServiceException;

    void changeOption(String optionId, Option option);

    Option getOptionById(String id) throws MobileServiceException;

    void removeOptionById(String id) throws MobileServiceException;

    List<Option> getOptionsByName(String name) throws MobileServiceException;

    void saveOption(Option option);

    List<Option> getAllRequiredOption(Option option);

    List<Option> getAllIncompatibleOption (Option option);

    List<Option> getNotSelectedOption(String contractId);

    List<Option> getAllIncompatibleOption (Option option, List<Option> requiredOptions);

}
