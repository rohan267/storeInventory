package org.thoughtworks.util;

import org.thoughtworks.model.Customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerValidation {

    private static String validAlphabetPattern = "[A-Z][a-zA-Z]*";

    public static boolean isValidFirstName(Customer customer1) {

        if(customer1.getFirstName().matches(validAlphabetPattern)) {
            return true;
        }
        return false;
    }

    public static boolean isValidLastName(Customer customer1) {

        if(customer1.getLastName().matches(validAlphabetPattern)) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmptyName(Customer customer1) {

        if(customer1.getFirstName().length() == 0 || customer1.getLastName().length() == 0) {
            return false;
        }

        return true;
    }
}
