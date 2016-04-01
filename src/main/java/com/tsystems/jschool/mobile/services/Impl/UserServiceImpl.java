package com.tsystems.jschool.mobile.services.Impl;


import com.tsystems.jschool.mobile.dao.API.UserDAO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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


//    @Transactional
//    public List<User> getUserByField(String searchText, String searchField) throws MobileServiceException {
//        List<User> users = null;
//        try {
//
//            switch (searchText){
//                case "phone": users = userDAO.getUserByPhoneNumber("%" + searchText + "%"); break;
//                case "surname": users = userDAO.getUserBySurname("%" + searchText + "%"); break;
//                case "email": users = userDAO.getUserByEmail("%" + searchText + "%"); break;
//                case "user_id": {
//                    users = new ArrayList<>();
//                    try {
//                        users.add(userDAO.findById(User.class, Integer.valueOf(searchText)));
//                    } catch (NumberFormatException e) {
//                        String message = "Can not find user. Invalid id format:" + searchText;
//                        logger.error(message);
//                        throw e;
//                    }
//                }
//            }
//            return users;
//        } catch (MobileDAOException | NumberFormatException e){
//            throw new MobileServiceException(e);
//        }
//    }

    @Transactional
    public User getUserById(String id) {
        return userDAO.findById(User.class, Integer.valueOf(id));
    }

//    @Transactional
//    public Page<User> getUserList(Integer pageNumber) {
//        PageRequest request = new PageRequest(pageNumber - 1,1);
//        return userRepo.findAll(request);
//    }
//
//    @Transactional
//    public Page<User> getUserListByContract(Integer pageNumber, String text) {
//        PageRequest request = new PageRequest(pageNumber - 1,1);
//        return userRepo.findByContract("%" + text + "%", request);
//    }
//
//    @Transactional
//    public Page<User> getUserListBySurname(Integer pageNumber, String text) {
//        PageRequest request = new PageRequest(pageNumber - 1,1);
//        return userRepo.findBySurname("%" + text + "%", request);
//    }
//
//    @Transactional
//    public Page<User> getUserListByEmail(Integer pageNumber, String text) {
//        PageRequest request = new PageRequest(pageNumber - 1,1);
//        return userRepo.findByEmail("%" + text + "%", request);
//    }
}
