package services.Impl;

import dao.API.ContractDAO;
import dao.API.OptionDAO;
import dao.API.TariffDAO;
import dao.API.UserDAO;
import dao.Impl.ContractDAOImpl;
import dao.Impl.OptionDAOImpl;
import dao.Impl.TariffDAOImpl;
import dao.Impl.UserDAOImpl;
import dao.JpaUtil;
import entities.Contract;
import entities.Option;
import entities.Tariff;
import entities.User;
import org.apache.log4j.Logger;
import services.API.ContractService;
import services.API.TariffService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public class ContractServiceImpl implements ContractService {

    private TariffDAO tariffDAO;
    private OptionDAO optionDAO;
    private ContractDAO contractDAO;
    private UserDAO userDAO;

    public void addContract(String name, String surname, String date, String passport, String address, String email, String password,
                               String phone_number, String tariff_id, String[] options){
        Contract contract = new Contract();
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setPassport(passport);
        user.setAddress(address);
        user.setEmail(email);
        user.setPassword(password);
        Date birthday = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d");
        try {
            birthday = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        user.setBirthday(birthday);
        contract.setUser(user);

        contract.setNumber(phone_number);

        EntityManager em = JpaUtil.getEntityManager();
        tariffDAO = new TariffDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Tariff tariff = tariffDAO.findById(Tariff.class, Integer.valueOf(tariff_id));
        contract.setTariff(tariff);

        if (options != null) {
            optionDAO = new OptionDAOImpl(em);
            List<Option> contract_options = new ArrayList<Option>();
            for (String optionId : options) {
                contract_options.add(optionDAO.findById(Option.class, Integer.valueOf(optionId)));
            }
            contract.setOptions(contract_options);
        }

        userDAO = new UserDAOImpl(em);
        userDAO.save(user);
        contractDAO = new ContractDAOImpl(em);
        contractDAO.save(contract);
        transaction.commit();
        em.close();

    }

    public List<Contract> getAllContracts(){
        EntityManager em = JpaUtil.getEntityManager();
        contractDAO = new ContractDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Contract> contracts = contractDAO.findAll(Contract.class);
        transaction.commit();
        em.close();
        return contracts;
    }

    public List<Contract> findContractByNumber(String number){
        EntityManager em = JpaUtil.getEntityManager();
        contractDAO = new ContractDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Contract> contracts = contractDAO.findContractByNumber("%" + number + "%");
        transaction.commit();
        em.close();
        return contracts;
    }

    public Contract getContractById(String id){
        EntityManager em = JpaUtil.getEntityManager();
        contractDAO = new ContractDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Contract contract = contractDAO.findById(Contract.class, Integer.valueOf(id));
        transaction.commit();
        em.close();
        return contract;
    }

    public void changeContract(String id, String tariff, String[] options, String block){
        EntityManager em = JpaUtil.getEntityManager();
        contractDAO = new ContractDAOImpl(em);
        tariffDAO = new TariffDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Contract contract = contractDAO.findById(Contract.class, Integer.valueOf(id));
        if (tariff != null) {
            contract.setTariff(tariffDAO.findById(Tariff.class, Integer.valueOf(tariff)));
        }

        if (options != null) {
            optionDAO = new OptionDAOImpl(em);
            List<Option> contract_options = new ArrayList<Option>();
            for (String optionId : options) {
                contract_options.add(optionDAO.findById(Option.class, Integer.valueOf(optionId)));
            }
            contract.setOptions(contract_options);
        }

        if (block != null){
            if (block.equals("block")){
                contract.setBlockedByAdmin(true);
            } else if (block.equals("unblock")){
                contract.setBlockedByAdmin(false);
            }
        }

        transaction.commit();
        em.close();
    }

}
