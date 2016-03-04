package com.tsystems.jschool.mobile.services.Impl;


import com.tsystems.jschool.mobile.AppService;
import com.tsystems.jschool.mobile.SingleAppService;
import com.tsystems.jschool.mobile.dao.API.TariffDAO;
import com.tsystems.jschool.mobile.dao.Impl.TariffDAOImpl;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.TariffService;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TariffServiceTest {

    AppService app = SingleAppService.get();
    private TariffService service;
    private TariffDAO dao;
    private EntityManager em;

    @Before
    public void setup() {
        dao = mock(TariffDAOImpl.class);
        service = app.tariffService;
        em = mock(EntityManager.class);

    }

//    @Test
//    public void testFindAll() throws MobileDAOException, MobileServiceException {
//        List<Tariff> all = new ArrayList<>();
//        all.add(new Tariff("Tariff", 200 , new ArrayList<>()));
//        all.add(new Tariff("Tariff", 100, new ArrayList<>()));
//        when(dao.findAll(Tariff.class, em)).thenReturn(all);
//        List<Tariff> result = service.getAllTariffs();
//        verify(dao).findAll(Tariff.class, em);
//    }
}
