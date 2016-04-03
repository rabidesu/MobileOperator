package com.tsystems.jschool.mobile.services.Impl;


import com.tsystems.jschool.mobile.dao.API.UserDAO;
import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.exceptions.LoginUserException;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
//import com.tsystems.jschool.mobile.repositories.UserRepo;
import com.tsystems.jschool.mobile.services.API.UserService;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;


    @Transactional
    public List<User> getAllUsers() throws MobileServiceException {
        return userDAO.findAll(User.class);
    }

    @Transactional
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email).get(0);
    }

    @Transactional
    public boolean existsUserWithEmail(String email) throws MobileServiceException {
        return  !userDAO.getUserByEmail(email).isEmpty();
    }

    public boolean checkIfUserHasContract(User loggedUser, String contractId){
        for (Contract contract : loggedUser.getContracts()) {
            if (contract.getId() == Integer.valueOf(contractId)) return true;
        }
        throw new MobileServiceException("Invalid request parameters: You can't get this contract!");
    }


    @Transactional
    public User getUserById(String id) {
        return userDAO.findById(User.class, Integer.valueOf(id));
    }

    @Transactional
    public int getCountUsers(){
        return (int) userDAO.getCountUsers();
    };

    @Transactional
    public int getCountUserByNumber(String number){
        return (int) userDAO.getCountUserByNumber(number);
    };

    @Transactional
    public int getCountUserByEmail(String email){
        return (int) userDAO.getCountUserByEmail(email);
    };

    @Transactional
    public int getCountUserBySurname(String surname){
        return (int) userDAO.getCountUserBySurname(surname);
    };

    @Transactional
    public List<User> getUserList(int pageNumber, int pageSize) {
        return userDAO.getPageUsers(pageNumber - 1, pageSize);
    }

    @Transactional
    public List<User> getUserListByContract(int pageNumber, int pageSize, String text) {
        return userDAO.getPageUsersByPhoneNumber(pageNumber - 1, pageSize, "%" + text + "%");
    }

    @Transactional
    public List<User> getUserListBySurname(int pageNumber, int pageSize, String text) {
        return userDAO.getPageUsersBySurname(pageNumber - 1, pageSize, "%" + text + "%");
    }

    @Transactional
    public List<User> getUserListByEmail(int pageNumber, int pageSize, String text) {
        return userDAO.getPageUsersByEmail(pageNumber - 1, pageSize, "%" + text + "%");
    }
}
