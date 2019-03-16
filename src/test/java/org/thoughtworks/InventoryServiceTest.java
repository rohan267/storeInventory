package org.thoughtworks;

import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.exception.outOfStockException;
import org.thoughtworks.model.Country;
import org.thoughtworks.model.Order;
import org.thoughtworks.util.InventoryConstants;

import static org.junit.Assert.*;

public class InventoryServiceTest {

    InventoryService service;

    @Before
    public void setUp() throws outOfStockException {
        service = new InventoryService();
    }

    @Test
    public void testBrazilInventories() {
        // brazil
        assertEquals(100, service.getBrazilInventory().getIphoneStock());
        assertEquals(100, service.getBrazilInventory().getIpodStock());
    }

    @Test
    public void testArgentinaInventories() {
        // Argentina
        assertEquals(50, service.getArgentinaInventory().getIphoneStock());
        assertEquals(100, service.getArgentinaInventory().getIpodStock());
    }

    @Test
    public void testInput() {
        Order order = service.readOrder(InventoryConstants.input1);
        assertEquals(Country.BRAZIL, order.getPurchaseCountry());
        assertEquals("B123AB1234567", order.getPassportNumber());
        assertEquals(20, order.getPurchasedIphone());
        assertEquals(10, order.getPurchsedIpod());
    }

    @Test
    public void testIsStockAvailableTrue() {
        Order order = service.readOrder(InventoryConstants.input1);
        assertEquals(true, service.isStockAvailable(order));
    }

    @Test
    public void testIsStockAvailableFalse() {
        String inputNotAvailableIphoneStock = "INPUT 1: BRAZIL:B123AB1234567:IPHONE:220:IPOD:10 OUTPUT 1: 2650:90:100:80:50";
        Order order1 = service.readOrder(inputNotAvailableIphoneStock);
        assertEquals(false, service.isStockAvailable(order1));

        String inputNotAvailableIpodStock = "INPUT 1: BRAZIL:B123AB1234567:IPHONE:20:IPOD:300 OUTPUT 1: 2650:90:100:80:50";
        Order order2 = service.readOrder(inputNotAvailableIpodStock);
        assertEquals(false, service.isStockAvailable(order2));
    }

    @Test
    public void testIsStockAvailablePartiallyTrue() {
        String inputNotAvailableIphoneStock = "INPUT 1: BRAZIL:B123AB1234567:IPHONE:150:IPOD:10 OUTPUT 1: 2650:90:100:80:50";
        Order order1 = service.readOrder(inputNotAvailableIphoneStock);
        assertEquals(true, service.isStockAvailable(order1));

        String inputNotAvailableIpodStock = "INPUT 1: BRAZIL:B123AB1234567:IPHONE:20:IPOD:200 OUTPUT 1: 2650:90:100:80:50";
        Order order2 = service.readOrder(inputNotAvailableIpodStock);
        assertEquals(true, service.isStockAvailable(order2));
    }


    @Test
    public void testIsDiscountApplicableTrue() {
        String nonDiscountedOrder = "INPUT 1: BRAZIL:B123AB1234567:IPHONE:60:IPOD:10 OUTPUT 1: 2650:90:100:80:50";
        Order order1 = service.readOrder(nonDiscountedOrder);
        assertEquals(true, service.isDiscountApplicable(order1));

    }

    @Test
    public void testIsDiscountApplicableFalse() {
        String nonDiscountedOrder1 = "INPUT 1: ARGENTINA:B123AB1234567:IPHONE:60:IPOD:10 OUTPUT 1: 2650:90:100:80:50";
        Order order1 = service.readOrder(nonDiscountedOrder1);
        assertEquals(false, service.isDiscountApplicable(order1));

        String nonDiscountedOrder2 = "INPUT 1: BRAZIL:A123AB1234567:IPHONE:60:IPOD:10 OUTPUT 1: 2650:90:100:80:50";
        Order order2 = service.readOrder(nonDiscountedOrder2);
        assertEquals(false, service.isDiscountApplicable(order2));
    }

    @Test
    public void testCalculateDiscountedPrice() {
        Order order1 = service.readOrder(InventoryConstants.input1);
        service.applyTransaction(order1);
        assertEquals(2650, new Double(service.getSalesPrice()).intValue());
    }
}