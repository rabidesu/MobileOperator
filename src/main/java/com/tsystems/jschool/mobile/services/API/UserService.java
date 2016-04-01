package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.exceptions.LoginUserException;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Alexandra on 15.02.2016.
 */
public interface UserService {

//    Page<User> getUserList(Integer pageNumber);
//
//    Page<User> getUserListByContract(Integer pageNumber, String text);
//
//    Page<User> getUserListBySurname(Integer pageNumber, String text);
//
//    Page<User> getUserListByEmail(Integer pageNumber, String text);


    List<User> getAllUsers();
//
//    List<User> getUserByField(String searchText, String searchField) throws MobileServiceException;

    User getUserById(String id);

    User getUserByEmail(String email);

    boolean existsUserWithEmail(String email);

}
