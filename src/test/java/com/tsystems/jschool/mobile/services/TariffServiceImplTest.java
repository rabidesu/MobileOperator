package com.tsystems.jschool.mobile.services;

import com.tsystems.jschool.mobile.dao.API.ContractDAO;
import com.tsystems.jschool.mobile.dao.API.OptionDAO;
import com.tsystems.jschool.mobile.dao.API.TariffDAO;
import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.Impl.TariffServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TariffServiceImplTest {

    @InjectMocks
    private TariffServiceImpl tariffService;

    @Mock
    private TariffDAO tariffDAO;

    @Mock
    private OptionDAO optionDAO;

    @Mock
    private ContractDAO contractDAO;

    private int tariffId;
    private Tariff tariff;
    private int contractId;
    private Contract contract;
    private List<Contract> contracts;
    private List<Option> options;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        tariffId = 1;
        tariff = new Tariff(tariffId);
        options = new ArrayList<>();
        contractId = 1;
        contract = new Contract(contractId);
        contracts = new ArrayList<>();
        contracts.add(contract);
    }

    @Test(expected = MobileServiceException.class)
    public void saveTariffWithNotAvailableOption_neg(){
        Option option = new Option();
        option.setAvailable(false);
        options.add(option);
        tariff.setOptions(options);
        doNothing().when(tariffDAO).save(tariff);
        tariffService.saveTariff(tariff);
    }

    @Test
    public void saveTariffWithAvailableOption_pos(){
        Option option = new Option();
        options.add(option);
        tariff.setOptions(options);
        doNothing().when(tariffDAO).save(tariff);
        tariffService.saveTariff(tariff);
        verify(tariffDAO, times(1)).save(tariff);
    }

    @Test
    public void getAllAvailableTariffForContract(){
        contract.setTariff(tariff);
        List<Tariff> allAvailableTariffs = new ArrayList<>();
        Tariff tariff_2 = new Tariff(2);
        allAvailableTariffs.add(tariff_2);

        when(contractDAO.findById(Contract.class, contractId)).thenReturn(contract);
        when(tariffDAO.getAvailableTariffs()).thenReturn(allAvailableTariffs);
        assertEquals(new HashSet<>(tariffService.getAvailableTariffsForContract(String.valueOf(contractId))),
                new HashSet<>(Arrays.asList(tariff, tariff_2)));
    }

    @Test
    public void NotRemoveUsingTariff(){
        contract.setTariff(tariff);
        List<Contract> contracts = new ArrayList<>();
        contracts.add(contract);
        when(contractDAO.findContractWithTariff(tariffId)).thenReturn(contracts);
        doNothing().when(tariffDAO).removeTariffById(tariffId);
        when(tariffDAO.findById(Tariff.class, tariffId)).thenReturn(tariff);
        doNothing().when(tariffDAO).merge(tariff);
        tariffService.removeTariffById(String.valueOf(tariffId));
        verify(tariffDAO, times(0)).removeTariffById(tariffId);
    }

    @Test
    public void removeNotUsingTariff(){
        when(contractDAO.findContractWithTariff(tariffId)).thenReturn(new ArrayList<>());
        doNothing().when(tariffDAO).removeTariffById(tariffId);
        when(tariffDAO.findById(Tariff.class, tariffId)).thenReturn(tariff);
        doNothing().when(tariffDAO).merge(tariff);
        tariffService.removeTariffById(String.valueOf(tariffId));
        verify(tariffDAO, times(1)).removeTariffById(tariffId);
    }

    @Test
    public void setNotAvailableUsingTariff(){
        contract.setTariff(tariff);
        when(contractDAO.findContractWithTariff(tariffId)).thenReturn(contracts);
        doNothing().when(tariffDAO).removeTariffById(tariffId);
        when(tariffDAO.findById(Tariff.class, tariffId)).thenReturn(tariff);
        doNothing().when(tariffDAO).merge(tariff);
        tariffService.removeTariffById(String.valueOf(tariffId));
        assertFalse(tariff.isAvailable());
    }

    @Test(expected = MobileServiceException.class)
    public void changeNotAvailableTariff_neg(){
        Tariff oldTariff = new Tariff(tariffId);
        oldTariff.setAvailable(false);
        when(contractDAO.findContractWithTariff(tariffId)).thenReturn(new ArrayList<>());
        when(tariffDAO.findById(Tariff.class, tariffId)).thenReturn(oldTariff);
        doNothing().when(tariffDAO).merge(tariff);
        tariffService.changeTariff(String.valueOf(tariffId), tariff);
    }

    @Test(expected = MobileServiceException.class)
    public void changeUsingTariffName_neg(){
        contract.setTariff(tariff);
        Tariff oldTariff = new Tariff(tariffId);
        oldTariff.setName("tariff 1");
        tariff.setName("tariff 2");
        when(contractDAO.findContractWithTariff(tariffId)).thenReturn(contracts);
        when(tariffDAO.findById(Tariff.class, tariffId)).thenReturn(oldTariff);
        doNothing().when(tariffDAO).merge(tariff);
        tariffService.changeTariff(String.valueOf(tariffId), tariff);
    }

    @Test(expected = MobileServiceException.class)
    public void changeUsingTariffPrice_neg(){
        contract.setTariff(tariff);
        Tariff oldTariff = new Tariff(tariffId);
        oldTariff.setPrice(100);
        tariff.setPrice(200);
        when(contractDAO.findContractWithTariff(tariffId)).thenReturn(contracts);
        when(tariffDAO.findById(Tariff.class, tariffId)).thenReturn(oldTariff);
        doNothing().when(tariffDAO).merge(tariff);
        tariffService.changeTariff(String.valueOf(tariffId), tariff);
    }

    @Test(expected = MobileServiceException.class)
    public void changeUsingTariffRemoveOptions_neg(){
        contract.setTariff(tariff);
        Tariff oldTariff = new Tariff(tariffId);
        oldTariff.setOptions(options);
        options.add(new Option(1));
        options.add(new Option(2));
        tariff.setOptions(new ArrayList<>());
        when(contractDAO.findContractWithTariff(tariffId)).thenReturn(contracts);
        when(tariffDAO.findById(Tariff.class, tariffId)).thenReturn(oldTariff);
        doNothing().when(tariffDAO).merge(tariff);
        tariffService.changeTariff(String.valueOf(tariffId), tariff);
    }

    @Test
    public void changeUsingTariffAddOptions_pos(){
        contract.setTariff(tariff);
        Tariff oldTariff = new Tariff(tariffId);
        oldTariff.setOptions(new ArrayList<>());
        options.add(new Option(1));
        options.add(new Option(2));
        tariff.setOptions(options);
        when(contractDAO.findContractWithTariff(tariffId)).thenReturn(contracts);
        when(tariffDAO.findById(Tariff.class, tariffId)).thenReturn(oldTariff);
        doNothing().when(tariffDAO).merge(tariff);
        tariffService.changeTariff(String.valueOf(tariffId), tariff);
        verify(tariffDAO, times(1)).merge(tariff);
    }

    @Test(expected = MobileServiceException.class)
    public void changeTariffAddNotAvailableOptions_neg(){
        Tariff oldTariff = new Tariff(tariffId);
        Option option = new Option(1);
        option.setAvailable(false);
        options.add(option);
        tariff.setOptions(options);
        when(contractDAO.findContractWithTariff(tariffId)).thenReturn(new ArrayList<>());
        when(tariffDAO.findById(Tariff.class, tariffId)).thenReturn(oldTariff);
        doNothing().when(tariffDAO).merge(tariff);
        tariffService.changeTariff(String.valueOf(tariffId), tariff);
    }

    @Test
    public void changeNotUsingTariff_pos(){
        Tariff oldTariff = new Tariff(tariffId);
        oldTariff.setName("tariff 1");
        oldTariff.setPrice(100);
        List<Option> oldOptions = new ArrayList<>();
        oldOptions.add(new Option(1));
        oldTariff.setOptions(oldOptions);
        tariff.setName("tariff 2");
        tariff.setPrice(200);
        options.add(new Option(2));
        tariff.setOptions(options);
        when(contractDAO.findContractWithTariff(tariffId)).thenReturn(new ArrayList<>());
        when(tariffDAO.findById(Tariff.class, tariffId)).thenReturn(oldTariff);
        doNothing().when(tariffDAO).merge(tariff);
        tariffService.changeTariff(String.valueOf(tariffId), tariff);
        verify(tariffDAO, times(1)).merge(tariff);
    }




}
