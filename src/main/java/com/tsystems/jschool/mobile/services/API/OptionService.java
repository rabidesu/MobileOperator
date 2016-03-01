package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.User;

import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public interface OptionService {

    List<Option> getAllOptions();

    List<Option> getAllAnotherOptions(String id);

    void addOption(String name, String price, String connectPrice, String[] requiredOption, String[] incompatibleOption);

    void changeOption(String id, String name, String price, String connectPrice, String[] requiredOption, String[] incompatibleOption);

    Option getOptionById(String id);

    void removeOptionById(String id);

    List<Option> getOptionsByName(String name);
}
