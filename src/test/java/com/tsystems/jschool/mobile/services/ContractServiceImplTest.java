package com.tsystems.jschool.mobile.services;

import com.tsystems.jschool.mobile.dao.API.*;
import com.tsystems.jschool.mobile.entities.*;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.Impl.ContractServiceImpl;
import com.tsystems.jschool.mobile.services.Impl.OptionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ContractServiceImplTest {

    @InjectMocks
    private ContractServiceImpl contractService;

    @Mock
    private ContractDAO contractDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private RoleDAO roleDAO;

    private int contractId;
    int tariffId;
    int optionId;
    private Contract contract;
    private User user;
    private Tariff tariff;
    private Option option;
    private List<Option> options;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        contractId = 1;
        contract = new Contract(contractId);
        user = new User(1);
        tariffId = 1;
        tariff = new Tariff(tariffId);
        optionId = 1;
        option = new Option(optionId);
        options = new ArrayList<>();
        contract.setUser(user);
        contract.setOptions(options);
        contract.setTariff(tariff);
    }

    @Test(expected = MobileServiceException.class)
    public void addContractNotAvailableTariff_neg(){
        tariff.setAvailable(false);
        when(roleDAO.getRoleByName(RoleName.ROLE_CLIENT)).thenReturn(new Role(1));
        doNothing().when(userDAO).save(any(User.class));
        doNothing().when(contractDAO).save(contract);
        contractService.addContract(contract);
    }

    @Test(expected = MobileServiceException.class)
    public void addContractNotAvailableOption_neg(){
        option.setAvailable(false);
        options.add(option);
        when(roleDAO.getRoleByName(RoleName.ROLE_CLIENT)).thenReturn(new Role(1));
        doNothing().when(userDAO).save(any(User.class));
        doNothing().when(contractDAO).save(contract);
        contractService.addContract(contract);
    }

    @Test
    public void addContract_pos(){
        when(roleDAO.getRoleByName(RoleName.ROLE_CLIENT)).thenReturn(new Role(1));
        doNothing().when(userDAO).save(any(User.class));
        doNothing().when(contractDAO).save(contract);
        contractService.addContract(contract);
        verify(userDAO, times(1)).save(user);
        verify(contractDAO, times(1)).save(contract);
    }

    @Test
    public void notExistsContractWithTariff(){
        when(contractDAO.findContractWithTariff(tariffId)).thenReturn(new ArrayList<>());
        assertFalse(contractService.isExistContractWithTariff(String.valueOf(tariffId)));
    }

    @Test
    public void existsContractWithTariff(){
        List<Contract> contracts = new ArrayList<>();
        contracts.add(contract);
        when(contractDAO.findContractWithTariff(tariffId)).thenReturn(contracts);
        assertTrue(contractService.isExistContractWithTariff(String.valueOf(tariffId)));
    }

    @Test
    public void notExistsContractWithOption(){
        when(contractDAO.findContractWithTariff(optionId)).thenReturn(new ArrayList<>());
        assertFalse(contractService.isExistContractWithOption(String.valueOf(optionId)));
    }

    @Test
    public void existsContractWithOption(){
        List<Contract> contracts = new ArrayList<>();
        contracts.add(contract);
        options.add(option);
        when(contractDAO.findContractWithTariff(optionId)).thenReturn(contracts);
        assertTrue(contractService.isExistContractWithTariff(String.valueOf(optionId)));
    }

    @Test(expected = MobileServiceException.class)
    public void blockContractByAdmin_neg(){
        contract.setBlockedByAdmin(true);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(contract);
        contractService.blockContractByAdmin(String.valueOf(contractId));
    }

    @Test(expected = MobileServiceException.class)
    public void unblockContractByAdmin_neg(){
        contract.setBlockedByAdmin(false);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(contract);
        contractService.unblockContractByAdmin(String.valueOf(contractId));
    }

    @Test
    public void blockContractByAdmin_pos(){
        contract.setBlockedByAdmin(false);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(contract);
        contractService.blockContractByAdmin(String.valueOf(contractId));
        assertTrue(contract.isBlockedByAdmin());
    }

    @Test
    public void unblockContractByAdmin_pos(){
        contract.setBlockedByAdmin(true);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(contract);
        contractService.unblockContractByAdmin(String.valueOf(contractId));
        assertFalse(contract.isBlockedByAdmin());
    }

    @Test
    public void blockContractByClient_pos(){
        contract.setBlockedByClient(false);
        contract.setBlockedByAdmin(false);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(contract);
        contractService.blockContractByClient(String.valueOf(contractId));
        assertTrue(contract.isBlockedByClient());
    }

    @Test
    public void unblockContractByClient_pos(){
        contract.setBlockedByClient(true);
        contract.setBlockedByAdmin(false);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(contract);
        contractService.unblockContractByClient(String.valueOf(contractId));
        assertFalse(contract.isBlockedByClient());
    }

    @Test(expected = MobileServiceException.class)
    public void blockContractByClient_neg(){
        contract.setBlockedByAdmin(true);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(contract);
        contractService.blockContractByClient(String.valueOf(contractId));
    }

    @Test(expected = MobileServiceException.class)
    public void unblockContractByClient_neg(){
        contract.setBlockedByAdmin(true);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(contract);
        contractService.unblockContractByClient(String.valueOf(contractId));
    }

    @Test(expected = MobileServiceException.class)
    public void changeContractBlockedByClient_neg(){
        Contract oldContract = new Contract(contractId);
        oldContract.setBlockedByClient(true);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(oldContract);
        doNothing().when(contractDAO).merge(oldContract);
        contractService.changeContract(String.valueOf(contractId), contract);
    }

    @Test(expected = MobileServiceException.class)
    public void changeContractBlockedByAdmin_neg(){
        Contract oldContract = new Contract(contractId);
        oldContract.setBlockedByAdmin(true);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(oldContract);
        doNothing().when(contractDAO).merge(oldContract);
        contractService.changeContract(String.valueOf(contractId), contract);
    }

    @Test(expected = MobileServiceException.class)
    public void changeContractNotAvailableTariff_neg(){
        Contract oldContract = new Contract(contractId);
        oldContract.setTariff(tariff);
        Tariff newTariff = new Tariff(2);
        newTariff.setAvailable(false);
        contract.setTariff(newTariff);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(oldContract);
        doNothing().when(contractDAO).merge(oldContract);
        contractService.changeContract(String.valueOf(contractId), contract);
    }

    @Test
    public void changeContractNotAvailableOldTariff_pos(){
        Contract oldContract = new Contract(contractId);
        oldContract.setTariff(tariff);
        contract.setTariff(tariff);
        tariff.setAvailable(false);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(oldContract);
        doNothing().when(contractDAO).merge(oldContract);
        contractService.changeContract(String.valueOf(contractId), contract);
        verify(contractDAO, times(1)).merge(oldContract);
    }

    @Test(expected = MobileServiceException.class)
    public void changeContractNotAvailableOption_neg(){
        Contract oldContract = new Contract(contractId);
        oldContract.setTariff(tariff);
        oldContract.setOptions(new ArrayList<>());
        contract.setTariff(tariff);
        option.setAvailable(false);
        options.add(option);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(oldContract);
        doNothing().when(contractDAO).merge(oldContract);
        contractService.changeContract(String.valueOf(contractId), contract);
    }

    @Test(expected = MobileServiceException.class)
    public void changeContractTariffNotContainsOption_neg(){
        Contract oldContract = new Contract(contractId);
        oldContract.setTariff(tariff);
        oldContract.setOptions(new ArrayList<>());
        contract.setTariff(tariff);
        tariff.setOptions(new ArrayList<>());
        options.add(option);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(oldContract);
        doNothing().when(contractDAO).merge(oldContract);
        contractService.changeContract(String.valueOf(contractId), contract);
    }

    @Test
    public void changeContractOldNotAvailableOption_pos(){
        Contract oldContract = new Contract(contractId);
        oldContract.setTariff(tariff);
        List<Option> oldOptions = new ArrayList<>();
        oldOptions.add(option);
        oldContract.setOptions(oldOptions);
        contract.setTariff(tariff);
        options.add(option);
        option.setAvailable(false);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(oldContract);
        doNothing().when(contractDAO).merge(oldContract);
        contractService.changeContract(String.valueOf(contractId), contract);
        verify(contractDAO, times(1)).merge(oldContract);
    }

    // TODO: false?
    @Test
    public void changeContractTariffNotContainsOldOption_pos(){
        Contract oldContract = new Contract(contractId);
        oldContract.setTariff(tariff);
        List<Option> oldOptions = new ArrayList<>();
        oldOptions.add(option);
        oldContract.setOptions(oldOptions);
        contract.setTariff(tariff);
        tariff.setOptions(new ArrayList<>());
        options.add(option);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(oldContract);
        doNothing().when(contractDAO).merge(oldContract);
        contractService.changeContract(String.valueOf(contractId), contract);
        verify(contractDAO, times(1)).merge(oldContract);
    }

    @Test
    public void changeContract_pos(){
        Contract oldContract = new Contract(contractId);
        oldContract.setTariff(new Tariff(2));
        List<Option> oldOptions = new ArrayList<>();
        oldOptions.add(new Option(2));
        oldContract.setOptions(oldOptions);
        contract.setTariff(tariff);
        tariff.setOptions(options);
        options.add(option);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(oldContract);
        doNothing().when(contractDAO).merge(oldContract);
        contractService.changeContract(String.valueOf(contractId), contract);
        verify(contractDAO, times(1)).merge(oldContract);
    }






}
