package com.tsystems.jschool.mobile.services.Impl;

import com.tsystems.jschool.mobile.MobileContext;
import com.tsystems.jschool.mobile.dao.API.*;
import com.tsystems.jschool.mobile.dao.Impl.*;
import com.tsystems.jschool.mobile.dao.JpaUtil;
import com.tsystems.jschool.mobile.entities.*;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.exceptions.CompatibilityOptionException;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.CompatibilityOptionChecker;
import com.tsystems.jschool.mobile.services.Utils;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

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

    private final static Logger logger = Logger.getLogger(ContractServiceImpl.class);

    private TariffDAO tariffDAO;
    private OptionDAO optionDAO;
    private ContractDAO contractDAO;
    private UserDAO userDAO;
    private RoleDAO roleDAO;

    public ContractServiceImpl(MobileContext context) {
        tariffDAO = context.tariffDao;
        optionDAO = context.optionDAO;
        contractDAO = context.contractDAO;
        userDAO = context.userDAO;
        roleDAO = context.roleDAO;

    }

    public void addContract(String name, String surname, String date, String passport, String address, String email,
                            String password, String phone_number, String tariff_id, String[] options) throws MobileServiceException {

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setPassport(passport);
        user.setAddress(address);
        user.setEmail(email);

        String mysalt = BCrypt.gensalt();
        String hashpass = BCrypt.hashpw(password, mysalt);
        user.setPassword(hashpass);

        try {
            Date birthday = Utils.parseDate(date);
            user.setBirthday(birthday);
        } catch (ParseException e) {
            String message = "Can not add new user. Invalid birthday data format:" + date;
            logger.error(message);
            throw new MobileServiceException(message, e);
        }
        Contract contract = new Contract();
        contract.setUser(user);
        contract.setNumber(phone_number);

        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            List<Role> roles =  roleDAO.getRoleByName(RoleName.CLIENT, em);
            if (!roles.isEmpty()) {
                Role role = roles.get(0);
                user.setRole(role);
            }
            Tariff tariff = tariffDAO.findById(Tariff.class, Integer.valueOf(tariff_id), em);
            contract.setTariff(tariff);
            if (options != null) {
                List<Option> contract_options = new ArrayList<>();
                for (String optionId : options) {
                    contract_options.add(optionDAO.findById(Option.class, Integer.valueOf(optionId), em));
                }
                contract.setOptions(contract_options);
            }
            userDAO.save(user, em);
            contractDAO.save(contract, em);
            JpaUtil.commitTransaction(em);
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public void addContractForUser(String user_id, String phone_number, String tariff_id, String[] options) throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            Contract contract = new Contract();
            Tariff tariff = tariffDAO.findById(Tariff.class, Integer.valueOf(tariff_id), em);
            contract.setTariff(tariff);
            contract.setNumber(phone_number);
            if (options != null) {
                List<Option> contract_options = new ArrayList<>();
                for (String optionId : options) {
                    contract_options.add(optionDAO.findById(Option.class, Integer.valueOf(optionId), em));
                }
                try {
                    CompatibilityOptionChecker.checkOptionCompatibility(contract_options);
                    CompatibilityOptionChecker.checkAllRequiredOptionAvailable(contract_options);
                } catch (CompatibilityOptionException e){
                    logger.error(e.getMessage());
                    throw new MobileServiceException(e.getMessage(), e);
                }
                contract.setOptions(contract_options);
            }
            User user = userDAO.findById(User.class, Integer.valueOf(user_id), em);
            contract.setUser(user);
            contractDAO.merge(contract, em);
            JpaUtil.commitTransaction(em);
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public List<Contract> getAllContracts() throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            List<Contract> contracts = contractDAO.findAll(Contract.class, em);
            JpaUtil.commitTransaction(em);
            return contracts;
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public List<Contract> findContractByNumber(String number) throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            List<Contract> contracts = contractDAO.findContractByNumber("%" + number + "%", em);
            JpaUtil.commitTransaction(em);
            return contracts;
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public Contract getContractById(String id) throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            Contract contract = contractDAO.findById(Contract.class, Integer.valueOf(id), em);
            JpaUtil.commitTransaction(em);
            return contract;
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public void changeContract(String id, String tariff, String[] options, String block) throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            Contract contract = contractDAO.findById(Contract.class, Integer.valueOf(id), em);
            if (tariff != null) {
                contract.setTariff(tariffDAO.findById(Tariff.class, Integer.valueOf(tariff), em));
            }
            List<Option> contract_options = new ArrayList<Option>();
            if (options != null) {
                for (String optionId : options) {
                    contract_options.add(optionDAO.findById(Option.class, Integer.valueOf(optionId), em));
                }
            }
            contract.setOptions(contract_options);

            if (block != null){
                switch (block){
                    case "block": contract.setBlockedByAdmin(true); break;
                    case "unblock":  contract.setBlockedByAdmin(false); break;
                    case "unblockByClient": contract.setBlockedByClient(false); break;
                    case "blockByClient": contract.setBlockedByClient(true);
                }
            }
            JpaUtil.commitTransaction(em);
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

}
