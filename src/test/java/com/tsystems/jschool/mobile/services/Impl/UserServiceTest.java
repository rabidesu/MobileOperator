package com.tsystems.jschool.mobile.services.Impl;


import com.tsystems.jschool.mobile.AppService;
import com.tsystems.jschool.mobile.SingleAppService;
import com.tsystems.jschool.mobile.dao.API.UserDAO;
import com.tsystems.jschool.mobile.dao.Impl.UserDAOImpl;
import com.tsystems.jschool.mobile.services.API.UserService;
import org.junit.Before;


import static org.mockito.Mockito.*;

public class UserServiceTest {

    AppService app = SingleAppService.get();
    private UserService service;
    private UserDAO dao;

    @Before
    public void setup() {
        dao = mock(UserDAOImpl.class);
        service = app.userService;

    }

//    @Test
//    public void testFindAll() {
//        List<User> all = new ArrayList<>();
//        all.add(new Person(1,"John","Doe",null));
//        all.add(new Person(2,"Jane","Doe",null));
//
//        //MOCK ALERT: return mocked result set on find
//        when(dao.findAll()).thenReturn(all);
//
//        //call the main method you want to test
//        List result = service.findAll();
//
//        //MOCK ALERT: verify the method was called
//        verify(dao).findAll();
//    }
}
