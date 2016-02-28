package services.API;

import entities.Option;
import entities.User;

import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public interface OptionService {

    public List<Option> getAllOptions();

    public List<Option> getAllAnotherOptions(String id);

    public boolean addOption(String name, String price, String connectPrice, String[] requiredOption, String[] incompatibleOption);

    public boolean changeOption(String id, String name, String price, String connectPrice, String[] requiredOption, String[] incompatibleOption);

    public Option getOptionById(String id);

    public void removeOptionById(String id);

    public List<Option> getOptionsByName(String name);
}
