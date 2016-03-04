package com.tsystems.jschool.mobile.services;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.CompatibilityOptionException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 03.03.2016.
 */
public class CompatibilityOptionChecker {

    public static void checkOptionCompatibility(List<Option> options) throws CompatibilityOptionException {
        if (!options.isEmpty()){
            for (Option cur_option : options){
                for (Option incompatible_option : cur_option.getOptionsIncompatible()) {
                    if (options.contains(incompatible_option))
                        throw new CompatibilityOptionException("Selected incompatible options");
                }
            }
        }
    }

    public static void checkAllRequiredOptionAvailable(List<Option> options) throws CompatibilityOptionException {
        if (!options.isEmpty()){
            for (Option cur_option : options){
                for (Option required_option : cur_option.getOptionsRequired()) {
                    if (!options.contains(required_option))
                        throw new CompatibilityOptionException("Some required options is not available");
                }
            }
        }
    }

    public static void checkRequiredAndIncompatibleIntersection(Option option) throws CompatibilityOptionException {
        for (Option required_option : option.getOptionsRequired()){
            if (option.getOptionsIncompatible().contains(required_option))
                throw new CompatibilityOptionException("Required and Incompatible options intersection");
        }
    }

    public static void checkInterdependentOptions(Option option, List<Option> allOptions,
                                                  List<Option> reqOptionsSelected) throws CompatibilityOptionException {

        List<Option> allReqOptions = new ArrayList<>();
        if (allOptions != null) {
            getAllRequiredFromOption(option, allOptions, allReqOptions);
        }

        for (Option required_option : reqOptionsSelected) {
            if (allReqOptions.contains(required_option))
                throw new CompatibilityOptionException("Options must not depend on each other");
        }
    }

    public static void checkIncompatibleWithRequiredOptions(List<Option> allOptions, List<Option> reqOptionsSelected,
                                              List<Option> incOptionsSelected) throws CompatibilityOptionException {
        for (Option oneOfRequired : reqOptionsSelected) {
            List<Option> allReqOptions = new ArrayList<>();
            if (allOptions != null) {
                getAllRequiredOptForOption(oneOfRequired, allOptions, allReqOptions);
            }
            for (Option inc_option : incOptionsSelected) {
                if (allReqOptions.contains(inc_option))
                    throw new CompatibilityOptionException("Options can not be incompatible with parent required option");
            }
        }
    }

    private static void getAllRequiredFromOption (Option option, List<Option> allOptions, List<Option> reqOptions){
        for (Option oneOfAll : allOptions){
            if (oneOfAll.getOptionsRequired().contains(option)){
                reqOptions.add(oneOfAll);
                getAllRequiredFromOption(oneOfAll, allOptions, reqOptions);
            }
        }
    }

    private static void getAllRequiredOptForOption (Option option, List<Option> allOptions, List<Option> reqOptions){
        for (Option oneOfRequired : option.getOptionsRequired()){
            reqOptions.add(oneOfRequired);
            getAllRequiredOptForOption(allOptions.get(allOptions.indexOf(oneOfRequired)), allOptions, reqOptions);
        }
    }

}
