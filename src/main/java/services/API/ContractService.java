package services.API;

import entities.Contract;
import entities.Tariff;

import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public interface ContractService {

    public List<Contract> getAllContracts();

    public void addContract(String name, String surname, String date, String passport, String address, String email, String password,
                            String phone_number, String tariff_id, String[] options);

    public List<Contract> findContractByNumber(String number);

    public Contract getContractById(String id);

    public void changeContract(String id, String tariff, String[] options, String block);

}
