package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.Option;

import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public interface OptionDAO extends GenericDAO<Option> {

    List<Option> getAnotherOptions(int id);

    void removeOptionById(int id);

    List<Option> getOptionsByName(String name);

}
