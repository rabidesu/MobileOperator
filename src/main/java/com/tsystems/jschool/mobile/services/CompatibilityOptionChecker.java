package com.tsystems.jschool.mobile.services;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.CompatibilityOptionException;

import java.util.List;

/**
 * Created by Alexandra on 03.03.2016.
 */
public class CompatibilityOptionChecker {

    public static void checkOptionCompatibility(List<Option> options) throws CompatibilityOptionException {
        if (!options.isEmpty()){
            for (Option cur_option : options){
                for (Option incompatible_option : cur_option.getOptionsIncompatible()) {
                    if (options.contains(incompatible_option)) throw new CompatibilityOptionException("Selected incompatible options");
                }
            }
        }
    }

    public static void checkAllRequiredOptionAvailable(List<Option> options) throws CompatibilityOptionException {
        if (!options.isEmpty()){
            for (Option cur_option : options){
                for (Option required_option : cur_option.getOptionsRequired()) {
                    if (!options.contains(required_option)) throw new CompatibilityOptionException("Some required options is not available");
                }
            }
        }
    }

    public static void checkReqAndIncompIntersection(Option option) throws CompatibilityOptionException {
        for (Option required_option : option.getOptionsRequired()){
            if (option.getOptionsRequired().contains(required_option)) throw new CompatibilityOptionException("Required and Incompatible options intersection");
        }
    }
}
