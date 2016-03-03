package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public interface OptionService {

    List<Option> getAllOptions() throws MobileServiceException;

    List<Option> getAllAnotherOptions(String id) throws MobileServiceException;

    void addOption(String name, String price, String connectPrice, String[] requiredOption,
                   String[] incompatibleOption) throws MobileServiceException;

    void changeOption(String id, String name, String price, String connectPrice, String[] requiredOption,
                      String[] incompatibleOption) throws MobileServiceException;

    Option getOptionById(String id) throws MobileServiceException;

    void removeOptionById(String id) throws MobileServiceException;

    List<Option> getOptionsByName(String name) throws MobileServiceException;
}
