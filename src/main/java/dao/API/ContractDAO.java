package dao.API;

import entities.Contract;
import entities.Option;

import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public interface ContractDAO extends GenericDAO<Contract, Integer> {

    public List<Contract> findContractByNumber(String number);

}
