package com.tsystems.jschool.mobile.services;

import com.tsystems.jschool.mobile.dao.API.UserDAO;
import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.Impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDAO userDAO;

    private List<User> users;

    private List<Contract> contracts;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        users = new ArrayList<>();
        contracts = new ArrayList<>();
    }

    @Test
    public void existsUserWithEmail_true() {
        String email = "email";
        users.add(new User(email));
        when(userDAO.getUserByEmail(email)).thenReturn(users);
        assertTrue(userService.existsUserWithEmail(email));
    }

    @Test
    public void existsUserWithEmail_false() {
        String email = "email";
        when(userDAO.getUserByEmail(email)).thenReturn(users);
        assertFalse(userService.existsUserWithEmail(email));
    }

    @Test
    public void IfUserHasContract_pos() {
        contracts.add(new Contract(1));
        User user = new User(1);
        user.setContracts(contracts);
        String checkingContractId = "1";
        Exception ex = null;
        try {
            userService.checkIfUserHasContract(user, checkingContractId);
        } catch (MobileServiceException e){
            ex = e;
        }
        assertEquals(null, ex);
    }

    @Test(expected = MobileServiceException.class)
    public void IfUserHasContract_neg() {
        contracts.add(new Contract(1));
        User user = new User(1);
        user.setContracts(contracts);
        String checkingContractId = "2";
        userService.checkIfUserHasContract(user, checkingContractId);
    }

}
