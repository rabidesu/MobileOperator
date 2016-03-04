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
                            String password, String phone_number, String tariff_id,
                            String[] options) throws MobileServiceException {

        boolean isUserDataEmpty = name.isEmpty() || surname.isEmpty() || date.isEmpty() || passport.isEmpty()
                || address.isEmpty() || email.isEmpty() || password.isEmpty() || phone_number.isEmpty();

        if (isUserDataEmpty) {
            String message = "Invalid contract parameters";
            logger.error(message);
            throw new MobileServiceException(message);
        }

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setPassport(passport);
        user.setAddress(address);

        String salt = BCrypt.gensalt();
        String hashPassword = BCrypt.hashpw(password, salt);
        user.setPassword(hashPassword);

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

        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();

            List<User> users = userDAO.getUserByEmail(email, em);
            if (!users.isEmpty()) throw new MobileServiceException("User with this email already exists");
            user.setEmail(email);

            List<Role> roles =  roleDAO.getRoleByName(RoleName.CLIENT, em);
            if (!roles.isEmpty()) {
                Role role = roles.get(0);
                user.setRole(role);
            }

            List<Contract> contracts = contractDAO.findContractByNumber(phone_number, em);
            if (!contracts.isEmpty()) throw new MobileServiceException("Contract with this number already exists");
            contract.setNumber(phone_number);

            Tariff tariff = tariffDAO.findById(Tariff.class, Integer.valueOf(tariff_id), em);
            contract.setTariff(tariff);

            try {
                checkAndSetContractOptions(options, contract, em);
            } catch (CompatibilityOptionException e){
                logger.error(e.getMessage());
                throw new MobileServiceException(e.getMessage(), e);
            }

            userDAO.save(user, em);
            contractDAO.save(contract, em);

            JpaUtil.commitTransaction(em);
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    /**
     *
     * @param user_id
     * @param phone_number
     * @param tariff_id
     * @param options
     * @throws MobileServiceException
     */

    public void addContractForUser(String user_id, String phone_number, String tariff_id, String[] options)
            throws MobileServiceException {

        if (phone_number.isEmpty()){
            String message = "Недопустимые параметры контракта";
            logger.error(message);
            throw new MobileServiceException(message);
        }

        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();

            Tariff tariff = tariffDAO.findById(Tariff.class, Integer.valueOf(tariff_id), em);
            Contract contract = new Contract();
            contract.setTariff(tariff);

            List<Contract> contracts = contractDAO.findContractByNumber(phone_number, em);
            if (!contracts.isEmpty()) throw new MobileServiceException("Contract with this number already exists");
            contract.setNumber(phone_number);

            try {
                checkAndSetContractOptions(options, contract, em);
            } catch (CompatibilityOptionException e){
                logger.error(e.getMessage());
                throw new MobileServiceException(e.getMessage(), e);
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
            Contract contract;
            if (tariff != null && id != null) {
                contract = contractDAO.findById(Contract.class, Integer.valueOf(id), em);
                contract.setTariff(tariffDAO.findById(Tariff.class, Integer.valueOf(tariff), em));

            } else {
                String message = "Недопустимые параметры контракта";
                logger.error(message);
                throw new MobileServiceException(message);
            }

            try {
                checkAndSetContractOptions(options, contract, em);
            } catch (CompatibilityOptionException e){
                logger.error(e.getMessage());
                throw new MobileServiceException(e.getMessage(), e);
            }

            if (block != null){
                switch (block){
                    case "block":
                        contract.setBlockedByAdmin(true);
                        break;
                    case "unblock":
                        contract.setBlockedByAdmin(false);
                        break;
                    case "unblockByClient":
                        contract.setBlockedByClient(false);
                        break;
                    case "blockByClient":
                        contract.setBlockedByClient(true);
                        break;
                    default:
                        logger.error("Unexpected block string");
                }
            }

            JpaUtil.commitTransaction(em);
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    private void checkAndSetContractOptions(String[] options, Contract contract, EntityManager em)
            throws CompatibilityOptionException, MobileDAOException {

        List<Option> contractOptions = new ArrayList<>();

        if (options != null) {
            for (String optionId : options) {
                contractOptions.add(optionDAO.findById(Option.class, Integer.valueOf(optionId), em));
            }

            CompatibilityOptionChecker.checkOptionCompatibility(contractOptions);
            CompatibilityOptionChecker.checkAllRequiredOptionAvailable(contractOptions);

            contract.setOptions(contractOptions);
        }
    }

}
