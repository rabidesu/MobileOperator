package com.tsystems.jschool.mobile.services;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.CompatibilityOptionException;
import com.tsystems.jschool.mobile.services.CompatibilityOptionChecker;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 03.03.2016.
 */
public class CompabilityOptionCheckerTest {

    private Option generalOption;
    private Option option1;
    private Option option2;
    private Option option3;
    private Option option4;
    private List<Option> options;
    private List<Option> emptyOptions;
    private List<Option> incompatibleOptions;
    private List<Option> requiredOptions;
    private List<Option> incompatibleOptions1;
    private List<Option> requiredOptions1;
    private List<Option> incompatibleOptions2;
    private List<Option> requiredOptions2;
    private List<Option> incompatibleOptions3;
    private List<Option> requiredOptions3;
    private List<Option> allOptions;

    @Before
    public void init() {
        generalOption = new Option();
        option1 = new Option();
        option2 = new Option();
        option3 = new Option();
        option4 = new Option();
        options = new ArrayList<>();
        emptyOptions = new ArrayList<>();
        incompatibleOptions = new ArrayList<>();

        requiredOptions = new ArrayList<>();
        incompatibleOptions1 = new ArrayList<>();
        requiredOptions1 = new ArrayList<>();
        incompatibleOptions2 = new ArrayList<>();
        requiredOptions2 = new ArrayList<>();
        incompatibleOptions3 = new ArrayList<>();
        requiredOptions3 = new ArrayList<>();
        allOptions = new ArrayList<>();
    }

    /*
    ** Check method checkReqAndIncompIntersection()
    **
     */

    @Test(expected = CompatibilityOptionException.class)
    public void requireOptIsIncompatible() throws CompatibilityOptionException {
        incompatibleOptions.add(option1);
        requiredOptions.add(option1);
        setOptions(generalOption, requiredOptions, incompatibleOptions);
        CompatibilityOptionChecker.checkRequiredAndIncompatibleIntersection(generalOption);
    }

    @Test
    public void requireOptIsNotIncompatible() throws CompatibilityOptionException {
        incompatibleOptions.add(option1);
        requiredOptions.add(option2);
        setOptions(generalOption, requiredOptions, incompatibleOptions);
        CompatibilityOptionChecker.checkRequiredAndIncompatibleIntersection(generalOption);
    }

    /*
    ** Check method checkOptionCompatibility()
    **
     */

    @Test(expected = CompatibilityOptionException.class)
    public void selectedIncompOptions() throws CompatibilityOptionException {
        incompatibleOptions1.add(option1);
        incompatibleOptions2.add(option2);
        option1.setOptionsIncompatible(incompatibleOptions2);
        option2.setOptionsIncompatible(incompatibleOptions1);
        options.add(option1);
        options.add(option2);
        CompatibilityOptionChecker.checkOptionCompatibility(options);
    }

    @Test
    public void notSelectedIncompOptions() throws CompatibilityOptionException {
        incompatibleOptions1.add(option3);
        incompatibleOptions2.add(option4);
        option1.setOptionsIncompatible(incompatibleOptions1);
        option2.setOptionsIncompatible(incompatibleOptions2);
        options.add(option1);
        options.add(option2);
        CompatibilityOptionChecker.checkOptionCompatibility(options);
    }

    /*
    ** Check method checkOptionCompatibility()
    **
     */

    @Test(expected = CompatibilityOptionException.class)
    public void notSelectedRequiredOptions() throws CompatibilityOptionException {
        requiredOptions.add(option1);
        option2.setOptionsRequired(requiredOptions);
        options.add(option2);
        CompatibilityOptionChecker.checkAllRequiredOptionAvailable(options);
    }

    @Test
    public void selectedAllRequiredOptions() throws CompatibilityOptionException {
        requiredOptions2.add(option1);
        option2.setOptionsRequired(requiredOptions2);
        option1.setOptionsRequired(emptyOptions);
        options.add(option1);
        options.add(option2);
        CompatibilityOptionChecker.checkAllRequiredOptionAvailable(options);
    }

    /*
    ** Test method checkInterdependentOptions()
    **
     */

    @Test(expected = CompatibilityOptionException.class)
    public void interdependentOptions() throws CompatibilityOptionException {
        requiredOptions1.add(generalOption);
        option2.setOptionsRequired(requiredOptions1);
        requiredOptions2.add(option2);
        generalOption.setOptionsRequired(emptyOptions);
        allOptions.add(option2);
        allOptions.add(generalOption);
        CompatibilityOptionChecker.checkInterdependentOptions(generalOption, allOptions, requiredOptions2);
    }

    @Test
    public void notInterdependentOptions() throws CompatibilityOptionException {
        requiredOptions1.add(option1);
        option2.setOptionsRequired(requiredOptions1);
        requiredOptions2.add(option2);
        generalOption.setOptionsRequired(emptyOptions);
        allOptions.add(option2);
        allOptions.add(generalOption);
        CompatibilityOptionChecker.checkInterdependentOptions(generalOption, allOptions, requiredOptions2);
    }

    @Test(expected = CompatibilityOptionException.class)
    public void deepInterdependentOptions() throws CompatibilityOptionException {
        requiredOptions1.add(generalOption);
        option1.setOptionsRequired(requiredOptions1);
        requiredOptions2.add(option1);
        option2.setOptionsRequired(requiredOptions2);
        requiredOptions.add(option2);
        generalOption.setOptionsRequired(emptyOptions);
        allOptions.add(generalOption);
        allOptions.add(option1);
        allOptions.add(option2);
        CompatibilityOptionChecker.checkInterdependentOptions(generalOption, allOptions, requiredOptions);
    }

    @Test(expected = CompatibilityOptionException.class)
    public void incompatibleWithDeepInterdependentOptions() throws CompatibilityOptionException {
        requiredOptions1.add(generalOption);
        option1.setOptionsRequired(requiredOptions1);
        requiredOptions2.add(option1);
        option2.setOptionsRequired(requiredOptions2);
        requiredOptions.add(option2);
        generalOption.setOptionsRequired(emptyOptions);
        incompatibleOptions.add(option1);
        generalOption.setOptionsIncompatible(emptyOptions);
        allOptions.add(generalOption);
        allOptions.add(option1);
        allOptions.add(option2);
        CompatibilityOptionChecker.checkIncompatibleWithRequiredOptions(allOptions, requiredOptions, incompatibleOptions);
    }


    private void setOptions(Option option, List<Option> requiredOptions, List<Option> incompatibleOptions){
        option.setOptionsIncompatible(incompatibleOptions);
        option.setOptionsRequired(requiredOptions);
    }
}
