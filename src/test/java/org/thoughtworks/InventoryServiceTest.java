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
    public void testCalculateLocalInventoryPrice() {
        Order order1 = service.readOrder(InventoryConstants.input1);
        service.checkOutCart(order1);
        assertEquals(2650, service.getSalesPrice());
//        assertEquals(80, service.getBrazilInventory().getIphoneStock());
//        assertEquals(90, service.getBrazilInventory().getIpodStock());
    }

    @Test
    public void testCalculatePricePartialOrderFromDifferentInventories() {
        String partialOrder = "INPUT 1: BRAZIL:B123AB1234567:IPHONE:110:IPOD:110 OUTPUT 1: 2650:90:100:80:50";
        Order order1 = service.readOrder(partialOrder);
        service.checkOutCart(order1);
        assertEquals(19000, service.getSalesPrice());
//        assertEquals(0, service.getBrazilInventory().getIphoneStock());
//        assertEquals(0, service.getBrazilInventory().getIpodStock());
//        assertEquals(40, service.getArgentinaInventory().getIphoneStock());
//        assertEquals(90, service.getArgentinaInventory().getIpodStock());
    }






    @Test
    public void testTransportationCharge() {
        int amount1=3;
        int amount2=10;
        int amount3=15;
        int amount4=39;
        int amount5=44;
        int amount6=0;
        int amount7=1;

        assertEquals(400, service.getTransportaionCharge(amount1));
        assertEquals(400, service.getTransportaionCharge(amount2));
        assertEquals(800, service.getTransportaionCharge(amount3));
        assertEquals(1600, service.getTransportaionCharge(amount4));
        assertEquals(2000, service.getTransportaionCharge(amount5));
        assertEquals(0, service.getTransportaionCharge(amount6));
        assertEquals(400, service.getTransportaionCharge(amount7));

    }

    @Test
    public void testForeignInventoryPrice() throws Exception {
        Order order1 = service.readOrder("INPUT 1: BRAZIL:B123AB1234567:IPHONE:110:IPOD:110 OUTPUT 1: 2650:90:100:80:50");
        assertEquals(3300, service.calculateForeignInventoryPrice(-10, -10,order1));

        Order order2 = service.readOrder("INPUT 1: BRAZIL:B123AB1234567:IPHONE:100:IPOD:100 OUTPUT 1: 2650:90:100:80:50");
        assertEquals(0, service.calculateForeignInventoryPrice(0,0,order2));
    }

    @Test
    public void testIphonePriceFromLocalInventory() {

        // 10 * 100
        // 10 * 65
        // 1650 * .20 = 1600
        Order order1 = service.readOrder(InventoryConstants.input1);
        service.applyTransaction(order1);
        assertEquals(2650, service.getSalesPrice());
    }

    @Test
    public void testInput2() {
        // 22 * 150 3300
        // 10 * 100 1000
        // total 4300
        Order order2 = service.readOrder(InventoryConstants.input2);
        service.applyTransaction(order2);
        assertEquals(3910, service.getSalesPrice());
    }

    @Test
    public void testInput5() {
        // 50 * 100 5000
        // 100 * 65 6500
        // 50 * 100 5000
        // 5 * 400 2000
        // total 16500 + 2000 = 18500
        Order order2 = service.readOrder(InventoryConstants.input5);
        service.applyTransaction(order2);
        assertEquals(18500, service.getSalesPrice());
    }
}