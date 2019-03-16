package org.thoughtworks.model;


import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.util.CustomerValidation;

import static org.junit.Assert.assertEquals;


public class CustomerTest {

    private Customer emptyCustomer = new Customer();
    private Customer invalidCustomer = new Customer();

    @Before
    public void setUp() throws Exception {
        emptyCustomer.setFirstName("");
        emptyCustomer.setLastName("");

        invalidCustomer.setFirstName("123");
        invalidCustomer.setLastName("123");
    }

    /**
     * check if customer name is not empty
     */
    @Test
    public void testCustomerNameNotEmpty() {

        assertEquals(false, CustomerValidation.isNotEmptyName(emptyCustomer));
    }

    // valid customer first name
    @Test
    public void testCustomerFirstNameValid() {

        assertEquals(false, CustomerValidation.isValidFirstName(invalidCustomer));
    }

    // valid customer last name
    @Test
    public void testCustomerFLastNameValid() {

        assertEquals(false, CustomerValidation.isValidLastName(invalidCustomer));
    }

    // valid customer passportNumber

    // valid customer country
}