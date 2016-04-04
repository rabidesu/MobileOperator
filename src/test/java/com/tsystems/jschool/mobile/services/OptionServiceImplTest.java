package com.tsystems.jschool.mobile.services;

import com.tsystems.jschool.mobile.dao.API.ContractDAO;
import com.tsystems.jschool.mobile.dao.API.OptionDAO;
import com.tsystems.jschool.mobile.dao.API.TariffDAO;
import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.Impl.OptionServiceImpl;
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

public class OptionServiceImplTest {

    @InjectMocks
    private OptionServiceImpl optionService;

    @Mock
    private OptionDAO optionDAO;

    @Mock
    private ContractDAO contractDAO;

    @Mock
    private TariffDAO tariffDAO;

    List<Option> options;
    List<Option> incompatibleOptions;
    List<Option> requiredOptions;
    Option option;
    Option option_2;
    Option option_3;
    Option option_4;
    List<Contract> contracts;
    Contract contract;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        options = new ArrayList<>();
        incompatibleOptions = new ArrayList<>();
        requiredOptions = new ArrayList<>();
        option = new Option(1);
        option_2 = new Option(2);
        option_3 = new Option(3);
        option_4 = new Option(4);
        contracts = new ArrayList<>();
        contract = new Contract(1);
    }


    @Test(expected = MobileServiceException.class)
    public void saveOptionWithNotAvailableIncompatible_neg(){
        option_2.setAvailable(false);
        incompatibleOptions.add(option_2);
        option.setOptionsIncompatible(incompatibleOptions);
        optionService.saveOption(option);
        doNothing().when(optionDAO).save(option);
        doNothing().when(optionDAO).merge(option);
    }

    @Test(expected = MobileServiceException.class)
    public void saveOptionWithNotAvailableRequired_neg(){
        option_2.setAvailable(false);
        requiredOptions.add(option_2);
        option.setOptionsRequired(requiredOptions);
        optionService.saveOption(option);
        doNothing().when(optionDAO).save(option);
        doNothing().when(optionDAO).merge(option);
    }

    @Test
    public void incompatibleOptionsAddedOnSave_pos(){
        incompatibleOptions.add(option_2);
        option.setOptionsIncompatible(incompatibleOptions);
        option_2.setOptionsIncompatible(new ArrayList<>());
        optionService.saveOption(option);
        doNothing().when(optionDAO).save(option);
        doNothing().when(optionDAO).merge(option);
        assertTrue(option_2.getOptionsIncompatible().contains(option));
    }

    @Test
    public void saveOption_pos(){
        incompatibleOptions.add(option_2);
        option.setOptionsIncompatible(incompatibleOptions);
        option_2.setOptionsIncompatible(new ArrayList<>());
        requiredOptions.add(option_3);
        option.setOptionsRequired(requiredOptions);
        optionService.saveOption(option);
        doNothing().when(optionDAO).save(option);
        doNothing().when(optionDAO).merge(option);
        verify(optionDAO, times(1)).save(option);
    }

    @Test(expected = MobileServiceException.class)
    public void changeOptionNotAvailable_neg(){
        int optionId = 1;
        Option option = new Option(optionId);
        option.setAvailable(false);
        doNothing().when(optionDAO).merge(any(Option.class));
        when(optionDAO.findById(Option.class, optionId)).thenReturn(option);
        when(tariffDAO.getTariffsWithOption(optionId)).thenReturn(new ArrayList<>());
        optionService.changeOption(String.valueOf(optionId), option);
    }

    @Test(expected = MobileServiceException.class)
    public void changeUsingOptionPrice_neg(){
        int optionId = 1;
        Option oldOption = new Option(optionId);
        oldOption.setPrice(100);
        contracts.add(contract);
        Option option = new Option(optionId);
        option.setPrice(50);
        doNothing().when(optionDAO).merge(any(Option.class));
        when(optionDAO.findById(Option.class, optionId)).thenReturn(oldOption);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(contracts);
        when(tariffDAO.getTariffsWithOption(optionId)).thenReturn(new ArrayList<>());
        optionService.changeOption(String.valueOf(optionId), option);
    }

    @Test(expected = MobileServiceException.class)
    public void changeUsingOptionName_neg(){
        int optionId = 1;
        Option oldOption = new Option(optionId);
        oldOption.setName("Option 1");
        contracts.add(contract);
        Option option = new Option(optionId);
        option.setName("Option 2");
        doNothing().when(optionDAO).merge(any(Option.class));
        when(optionDAO.findById(Option.class, optionId)).thenReturn(oldOption);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(contracts);
        when(tariffDAO.getTariffsWithOption(optionId)).thenReturn(new ArrayList<>());
        optionService.changeOption(String.valueOf(optionId), option);
    }

    @Test(expected = MobileServiceException.class)
    public void changeUsingOptionAddIncompatible_neg(){
        contracts.add(contract);
        int optionId = 1;
        Option oldOption = new Option(optionId);
        List<Option> oldIncompatible = new ArrayList<>();
        oldIncompatible.add(option_2);
        oldOption.setOptionsIncompatible(oldIncompatible);
        Option option = new Option(optionId);
        incompatibleOptions.add(option_2);
        incompatibleOptions.add(option_3);
        option.setOptionsIncompatible(incompatibleOptions);
        doNothing().when(optionDAO).merge(any(Option.class));
        when(optionDAO.findById(Option.class, optionId)).thenReturn(oldOption);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(contracts);
        when(tariffDAO.getTariffsWithOption(optionId)).thenReturn(new ArrayList<>());
        optionService.changeOption(String.valueOf(optionId), option);
    }

    @Test(expected = MobileServiceException.class)
    public void changeUsingOptionAddRequired_neg(){
        contracts.add(contract);
        int optionId = 1;
        Option oldOption = new Option(optionId);
        List<Option> oldRequired = new ArrayList<>();
        oldRequired.add(option_2);
        oldOption.setOptionsRequired(oldRequired);
        Option option = new Option(optionId);
        requiredOptions.add(option_2);
        requiredOptions.add(option_3);
        option.setOptionsRequired(requiredOptions);
        doNothing().when(optionDAO).merge(any(Option.class));
        when(optionDAO.findById(Option.class, optionId)).thenReturn(oldOption);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(contracts);
        when(tariffDAO.getTariffsWithOption(optionId)).thenReturn(new ArrayList<>());
        optionService.changeOption(String.valueOf(optionId), option);
    }

    @Test(expected = MobileServiceException.class)
    public void changeOptionAddNotAvailableRequired_neg(){
        int optionId = 1;
        Option oldOption = new Option(optionId);
        Option option = new Option(optionId);
        option_2.setAvailable(false);
        requiredOptions.add(option_2);
        option.setOptionsRequired(requiredOptions);
        doNothing().when(optionDAO).merge(any(Option.class));
        when(optionDAO.findById(Option.class, optionId)).thenReturn(oldOption);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(new ArrayList<>());
        when(tariffDAO.getTariffsWithOption(optionId)).thenReturn(new ArrayList<>());
        optionService.changeOption(String.valueOf(optionId), option);
    }

    @Test(expected = MobileServiceException.class)
    public void changeOptionAddNotAvailableIncompatible_neg(){
        int optionId = 1;
        Option oldOption = new Option(optionId);
        Option option = new Option(optionId);
        option_2.setAvailable(false);
        incompatibleOptions.add(option_2);
        option.setOptionsIncompatible(incompatibleOptions);
        doNothing().when(optionDAO).merge(any(Option.class));
        when(optionDAO.findById(Option.class, optionId)).thenReturn(oldOption);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(new ArrayList<>());
        when(tariffDAO.getTariffsWithOption(optionId)).thenReturn(new ArrayList<>());
        optionService.changeOption(String.valueOf(optionId), option);
    }

    @Test(expected = MobileServiceException.class)
    public void changeOptionIncompatibleItself_neg(){
        int optionId = 1;
        Option oldOption = new Option(optionId);
        Option option = new Option(optionId);
        incompatibleOptions.add(option);
        option.setOptionsIncompatible(incompatibleOptions);
        doNothing().when(optionDAO).merge(any(Option.class));
        when(optionDAO.findById(Option.class, optionId)).thenReturn(oldOption);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(new ArrayList<>());
        when(tariffDAO.getTariffsWithOption(optionId)).thenReturn(new ArrayList<>());
        optionService.changeOption(String.valueOf(optionId), option);
    }

    @Test(expected = MobileServiceException.class)
    public void changeOptionRequiredItself_neg(){
        int optionId = 1;
        Option oldOption = new Option(optionId);
        Option option = new Option(optionId);
        requiredOptions.add(option);
        option.setOptionsRequired(requiredOptions);
        doNothing().when(optionDAO).merge(any(Option.class));
        when(optionDAO.findById(Option.class, optionId)).thenReturn(oldOption);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(new ArrayList<>());
        when(tariffDAO.getTariffsWithOption(optionId)).thenReturn(new ArrayList<>());
        optionService.changeOption(String.valueOf(optionId), option);
    }

    @Test
    public void changeOptionAddIncompatible_neg(){
        int optionId = 1;
        Option oldOption = new Option(optionId);
        oldOption.setOptionsIncompatible(new ArrayList<>());
        Option option = new Option(optionId);
        incompatibleOptions.add(option_3);
        option.setOptionsIncompatible(incompatibleOptions);
        doNothing().when(optionDAO).merge(any(Option.class));
        when(optionDAO.findById(Option.class, optionId)).thenReturn(oldOption);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(new ArrayList<>());
        when(tariffDAO.getTariffsWithOption(optionId)).thenReturn(new ArrayList<>());
        for (Option opt : option.getOptionsIncompatible()) {
            when(optionDAO.findById(Option.class, opt.getId())).thenReturn(opt);
        }
        optionService.changeOption(String.valueOf(optionId), option);
        for (Option opt : option.getOptionsIncompatible()){
            assertTrue(opt.getOptionsIncompatible().contains(option));
        }
    }

    @Test
    public void changeOptionRemoveIncompatible_neg(){
        int optionId = 1;
        Option oldOption = new Option(optionId);
        oldOption.setOptionsIncompatible(new ArrayList<>());
        List<Option> oldIncompatible = new ArrayList<>();
        oldIncompatible.add(option_2);
        oldOption.setOptionsIncompatible(oldIncompatible);
        List<Option> incompatibleFor_2 = new ArrayList<>();
        incompatibleFor_2.add(oldOption);
        option_2.setOptionsIncompatible(incompatibleFor_2);
        Option option = new Option(optionId);
        option.setOptionsIncompatible(new ArrayList<>());
        doNothing().when(optionDAO).merge(any(Option.class));
        when(optionDAO.findById(Option.class, optionId)).thenReturn(oldOption);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(new ArrayList<>());
        when(tariffDAO.getTariffsWithOption(optionId)).thenReturn(new ArrayList<>());
        for (Option opt : oldOption.getOptionsIncompatible()) {
            when(optionDAO.findById(Option.class, opt.getId())).thenReturn(opt);
        }
        optionService.changeOption(String.valueOf(optionId), option);
        for (Option opt : oldOption.getOptionsIncompatible()){
            assertFalse(opt.getOptionsIncompatible().contains(option));
        }
    }

    @Test
    public void changeUsingOption_pos(){
        contracts.add(new Contract());
        int optionId = 1;
        Option oldOption = new Option(optionId);
        oldOption.setName("Option 1");
        oldOption.setPrice(100);
        oldOption.setConnectPrice(50);
        incompatibleOptions.add(option_2);
        oldOption.setOptionsIncompatible(incompatibleOptions);
        requiredOptions.add(option_3);
        oldOption.setOptionsRequired(requiredOptions);
        List<Option> incompatibleFor_2 = new ArrayList<>();
        incompatibleFor_2.add(oldOption);
        option_2.setOptionsIncompatible(incompatibleFor_2);
        Option option = new Option(optionId);
        option.setName("Option 1");
        option.setPrice(100);
        option.setConnectPrice(150);
        option.setOptionsRequired(new ArrayList<>());
        option.setOptionsIncompatible(new ArrayList<>());
        doNothing().when(optionDAO).merge(any(Option.class));
        when(optionDAO.findById(Option.class, optionId)).thenReturn(oldOption);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(contracts);
        when(tariffDAO.getTariffsWithOption(optionId)).thenReturn(new ArrayList<>());
        optionService.changeOption(String.valueOf(optionId), option);
        verify(optionDAO, times(1)).merge(option);
    }

    @Test
    public void changeNotUsingOption_pos(){
        int optionId = 1;
        Option oldOption = new Option(optionId);
        oldOption.setName("Option 1");
        oldOption.setPrice(100);
        oldOption.setConnectPrice(50);
        oldOption.setOptionsRequired(new ArrayList<>());
        oldOption.setOptionsIncompatible(new ArrayList<>());
        Option option = new Option(optionId);
        option.setName("Option 2");
        option.setPrice(200);
        option.setConnectPrice(150);
        incompatibleOptions.add(option_2);
        option.setOptionsIncompatible(incompatibleOptions);
        requiredOptions.add(option_3);
        option.setOptionsRequired(requiredOptions);
        doNothing().when(optionDAO).merge(any(Option.class));
        when(optionDAO.findById(Option.class, optionId)).thenReturn(oldOption);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(new ArrayList<>());
        when(tariffDAO.getTariffsWithOption(optionId)).thenReturn(new ArrayList<>());
        for (Option opt : option.getOptionsIncompatible()) {
            when(optionDAO.findById(Option.class, opt.getId())).thenReturn(opt);
        }
        optionService.changeOption(String.valueOf(optionId), option);
        verify(optionDAO, times(1)).merge(option);
    }


    @Test
    public void mergeIncompatibleOptionsOnSave_pos(){
        incompatibleOptions.add(option_2);
        incompatibleOptions.add(option_3);
        option.setOptionsIncompatible(incompatibleOptions);
        option_2.setOptionsIncompatible(new ArrayList<>());
        option_3.setOptionsIncompatible(new ArrayList<>());
        optionService.saveOption(option);
        doNothing().when(optionDAO).save(option);
        doNothing().when(optionDAO).merge(option);
        for (Option opt : option.getOptionsIncompatible()) {
            verify(optionDAO, times(1)).merge(opt);
        }
    }

    @Test
    public void removeNotUsedOption_pos(){
        int optionId = 1;
        Option option = new Option(optionId);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(new ArrayList<>());
        doNothing().when(optionDAO).removeOptionById(optionId);
        when(optionDAO.findById(Option.class, optionId)).thenReturn(option);
        when(optionDAO.getAnotherOptions(optionId)).thenReturn(new ArrayList<>());
        doNothing().when(optionDAO).merge(option);
        when(tariffDAO.findAll(Tariff.class)).thenReturn(new ArrayList<>());
        optionService.removeOptionById(String.valueOf(optionId));
        verify(optionDAO, times(1)).removeOptionById(optionId);
    }


    @Test
    public void optionBecomesNotAvailable_pos(){
        Contract contract = new Contract(1);
        int optionId = 1;
        Option option = new Option(optionId);
        options.add(option);
        contract.setOptions(options);
        contracts.add(contract);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(contracts);
        doNothing().when(optionDAO).removeOptionById(optionId);
        when(optionDAO.findById(Option.class, optionId)).thenReturn(option);
        when(optionDAO.getAnotherOptions(optionId)).thenReturn(new ArrayList<>());
        doNothing().when(optionDAO).merge(option);
        when(tariffDAO.findAll(Tariff.class)).thenReturn(new ArrayList<>());
        optionService.removeOptionById(String.valueOf(optionId));
        assertFalse(option.isAvailable());
    }

    @Test
    public void notRemoveUsedOption_pos(){
        Contract contract = new Contract(1);
        int optionId = 1;
        Option option = new Option(optionId);
        options.add(option);
        contract.setOptions(options);
        contracts.add(contract);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(contracts);
        doNothing().when(optionDAO).removeOptionById(optionId);
        when(optionDAO.findById(Option.class, optionId)).thenReturn(option);
        when(optionDAO.getAnotherOptions(optionId)).thenReturn(new ArrayList<>());
        doNothing().when(optionDAO).merge(option);
        when(tariffDAO.findAll(Tariff.class)).thenReturn(new ArrayList<>());
        optionService.removeOptionById(String.valueOf(optionId));
        verify(optionDAO, times(0)).removeOptionById(optionId);
    }

    @Test
    public void RemoveDependenceFromOption_pos(){
        Contract contract = new Contract(1);
        int optionId = 1;
        Option option = new Option(optionId);
        options.add(option);
        contract.setOptions(options);
        contracts.add(contract);
        requiredOptions.add(option);
        option_2.setOptionsRequired(requiredOptions);
        List<Option> requiredOptionsFor_3 = new ArrayList<>();
        requiredOptionsFor_3.add(option);
        option_3.setOptionsRequired(requiredOptionsFor_3);
        ArrayList<Option> anotherOptions = new ArrayList<>();
        anotherOptions.add(option_2);
        anotherOptions.add(option_3);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(contracts);
        doNothing().when(optionDAO).removeOptionById(optionId);
        when(optionDAO.findById(Option.class, optionId)).thenReturn(option);
        when(optionDAO.getAnotherOptions(optionId)).thenReturn(anotherOptions);
        doNothing().when(optionDAO).merge(option);
        when(tariffDAO.findAll(Tariff.class)).thenReturn(new ArrayList<>());
        optionService.removeOptionById(String.valueOf(optionId));
        for (Option opt : anotherOptions){
            assertFalse(opt.getOptionsRequired().contains(option));
        }
    }

    @Test
    public void optionRemovesFromTariffs_pos(){
        Contract contract = new Contract(1);
        int optionId = 1;
        Option option = new Option(optionId);
        options.add(option);
        contract.setOptions(options);
        contracts.add(contract);
        Tariff tariff_1 = new Tariff(1);
        tariff_1.setOptions(options);
        Tariff tariff_2 = new Tariff(2);
        tariff_2.setOptions(options);
        List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(tariff_1);
        tariffs.add(tariff_2);
        when(contractDAO.findContractWithOption(optionId)).thenReturn(contracts);
        doNothing().when(optionDAO).removeOptionById(optionId);
        when(optionDAO.findById(Option.class, optionId)).thenReturn(option);
        when(optionDAO.getAnotherOptions(optionId)).thenReturn(new ArrayList<>());
        doNothing().when(optionDAO).merge(option);
        when(tariffDAO.findAll(Tariff.class)).thenReturn(tariffs);
        optionService.removeOptionById(String.valueOf(optionId));
        for (Tariff tariff : tariffs){
            assertFalse(tariff.getOptions().contains(option));
        }
    }

    @Test
    public void getAllNotSelectedOptions(){
        int contractId = 1;
        Contract contract = new Contract(contractId);
        Tariff tariff = new Tariff(1);
        contract.setTariff(tariff);
        options.add(option);
        options.add(option_2);
        contract.setOptions(options);
        List<Option> tariffOptions = new ArrayList<>(Arrays.asList(option_2, option_3, option_4));
        tariff.setOptions(tariffOptions);
        when(contractDAO.findById(Contract.class, contractId)).thenReturn(contract);
        assertEquals(new HashSet<>(Arrays.asList(option_3, option_4)),
                new HashSet<>(optionService.getNotSelectedOption(String.valueOf(contractId))));
    }

    @Test
    public void getAllRequiredOptions() {
        requiredOptions.add(option_2);
        requiredOptions.add(option_3);
        option.setOptionsRequired(requiredOptions);
        List<Option> requiredOptionsFor_2 = new ArrayList<>();
        requiredOptionsFor_2.add(option_4);
        option_2.setOptionsRequired(requiredOptionsFor_2);
        assertEquals(new HashSet<>(Arrays.asList(option_2, option_3, option_4)),
                new HashSet<>(optionService.getAllRequiredOption(option)));
    }

    @Test
    public void getAllIncompatibleOptions(){
        Option option_5 = new Option(5);
        requiredOptions.add(option_2);
        incompatibleOptions.add(option_3);
        option.setOptionsRequired(requiredOptions);
        option.setOptionsIncompatible(incompatibleOptions);
        List<Option> incompatibleOptionsFor_2 = new ArrayList<>();
        incompatibleOptionsFor_2.add(option_4);
        option_2.setOptionsIncompatible(incompatibleOptionsFor_2);
        List<Option> incompatibleOptionsFor_3 = new ArrayList<>();
        incompatibleOptionsFor_3.add(option_5);
        option_3.setOptionsIncompatible(incompatibleOptionsFor_3);
        assertEquals(new HashSet<>(Arrays.asList(option_3, option_4)),
                new HashSet<>(optionService.getAllIncompatibleOption(option)));
    }


}
