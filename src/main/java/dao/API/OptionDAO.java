package dao.API;

import entities.Option;

import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public interface OptionDAO extends GenericDAO<Option, Integer> {

    public List<Option> getAnotherOptions(int id);

    public void removeOptionById(int id);

    public List<Option> getOptionsByName(String name);

}
