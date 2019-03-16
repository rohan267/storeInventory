package org.thoughtworks;

import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.business.Inventory;
import org.thoughtworks.business.OrderTransaction;
import org.thoughtworks.exception.outOfStockException;
import org.thoughtworks.model.Order;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class IPODInventoryClientTest {

    IPODInventoryClient client;
    Inventory inventory;

    @Before
    public void setUp() throws outOfStockException {
        client = new IPODInventoryClient();
    }

    @Test
    public void testIfValidResultFile() throws FileNotFoundException {
        IPODInventoryClient client = new IPODInventoryClient();
        assertEquals("result.txt", client.inputReader("result.text"));
    }

    @Test
    public void readOrder01() {
        File file = client.getFile();
    }

    @Test
    public void testReadFile() throws FileNotFoundException {
        Order order = client.createOrderOne();
    }

    @Test
    public void testBrazilOrderTransaction() throws FileNotFoundException {
        Order order = client.createOrderOne();
        OrderTransaction orderTransaction = new OrderTransaction();

//        assertEquals(80, );
//        assertEquals(90, orderTransaction.applyOrder(order).getIpodStock());
    }
}