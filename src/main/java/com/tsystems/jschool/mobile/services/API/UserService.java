package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.exceptions.LoginUserException;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;


import java.util.List;

/**
 * Created by Alexandra on 15.02.2016.
 */
public interface UserService {

    List<User> getUserList(int pageNumber, int pageSize);

    List<User> getUserListByContract(int pageNumber, int pageSize, String text);

    List<User> getUserListBySurname(int pageNumber, int pageSize, String text);

    List<User> getUserListByEmail(int pageNumber, int pageSize, String text);

    int getCountUsers();

    int getCountUserByNumber(String number);

    int getCountUserByEmail(String email);

    int getCountUserBySurname(String surname);

    List<User> getAllUsers();

    User getUserById(String id);

    User getUserByEmail(String email);

    boolean existsUserWithEmail(String email);

    boolean checkIfUserHasContract(User loggedUser, String contractId);

}
