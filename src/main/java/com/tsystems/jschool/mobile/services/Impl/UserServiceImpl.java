package com.tsystems.jschool.mobile.services.Impl;

import com.tsystems.jschool.mobile.MobileContext;
import com.tsystems.jschool.mobile.dao.API.UserDAO;
import com.tsystems.jschool.mobile.dao.JpaUtil;
import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.exceptions.LoginUserException;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.UserService;
import com.tsystems.jschool.mobile.services.Utils;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexandra on 18.02.2016.
 */
public class UserServiceImpl implements UserService {

    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    private UserDAO userDAO;

    public UserServiceImpl(MobileContext context) {
        userDAO = context.userDAO;
    }

    public User adminExists(String email, String password) throws MobileServiceException, LoginUserException {
        return userExists(email, password, RoleName.ADMIN);
    }

    public User clientExists(String email, String password) throws MobileServiceException, LoginUserException {
        return userExists(email, password, RoleName.CLIENT);
    }

    public User userExists(String email, String password, RoleName role) throws MobileServiceException, LoginUserException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            List<User> users = userDAO.getUserByEmail(email, em);
            JpaUtil.commitTransaction(em);
            if (!users.isEmpty()) {
                User user = users.get(0);
                if (BCrypt.checkpw(password, user.getPassword()) && user.getRole().getRoleName() == role){
                    return user;
                } else {
                    String message = "Invalid password for user: " + email;
                    logger.error(message);
                    throw new LoginUserException();
                }
            } else {
                String message = "Invalid login: " + email;
                logger.error(message);
                throw new LoginUserException();
            }
        } catch (MobileDAOException e){
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public List<User> getAllUsers() throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            List<User> users = userDAO.findAll(User.class, em);
            JpaUtil.commitTransaction(em);
            return users;
        } catch (MobileDAOException e){
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }


    public List<User> getUserByField(String searchText, String searchField) throws MobileServiceException {
        List<User> users = null;
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();

            switch (searchText){
                case "phone": users = userDAO.getUserByPhoneNumber("%" + searchText + "%", em); break;
                case "surname": users = userDAO.getUserBySurname("%" + searchText + "%", em); break;
                case "email": users = userDAO.getUserByEmail("%" + searchText + "%", em); break;
                case "user_id": {
                    users = new ArrayList<>();
                    try {
                        users.add(userDAO.findById(User.class, Integer.valueOf(searchText), em));
                    } catch (NumberFormatException e) {
                        String message = "Can not find user. Invalid id format:" + searchText;
                        logger.error(message);
                        throw e;
                    }
                }
            }
            JpaUtil.commitTransaction(em);
            return users;
        } catch (MobileDAOException | NumberFormatException e){
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public User getUserById(String id) throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            User user = userDAO.findById(User.class, Integer.valueOf(id), em);
            JpaUtil.commitTransaction(em);
            return user;
        } catch (MobileDAOException e){
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }
}
