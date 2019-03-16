package org.thoughtworks;

import org.thoughtworks.business.Inventory;
import org.thoughtworks.business.InventoryBrazil;
import org.thoughtworks.business.OrderTransaction;
import org.thoughtworks.model.Country;
import org.thoughtworks.model.Customer;
import org.thoughtworks.model.Order;

import java.io.*;
import java.util.StringTokenizer;

public class IPODInventoryClient {

    File file;

    public IPODInventoryClient() {
        setFileFromPath("result.txt");
    }

    public void mainClass() {
        System.out.println("hello");

        // product interface // implemented by ipad and iphone
        // Customer model class

        // create an Order
    }

    public Order createOrderOne() throws FileNotFoundException {
        return readOrderFromFile();
    }

    public String inputReader(String filePath) throws FileNotFoundException {
        setFileFromPath(filePath);
        return file.getName();
    }

    public void setFileFromPath(String filePath) throws NullPointerException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        file = new File(classLoader.getResource(filePath).getPath());
    }

    public File getFile() {
        return file;
    }

    public Order readOrderFromFile() throws FileNotFoundException {
        Order order = new Order();
        Customer customer = new Customer();
        readFile(order, customer);

        return order;
    }

    public void applyOrder() throws FileNotFoundException {
        OrderTransaction orderTransaction = new OrderTransaction();
        orderTransaction.applyOrder(readOrderFromFile());
    }

    public void readFile(Order readOrder, Customer customer) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            int inputLine = 0;
            String inputCommand;
            // INPUT 1: BRAZIL:B123AB1234567:IPHONE:20:IPOD:10 OUTPUT 1: 2650:90:100:80:50
//            while (reader.) {
                inputCommand = line.substring(0, line.indexOf(" OUTPUT"));
                int command = 0;
                String[] inputOrder = inputCommand.split(":");
//                readOrder.setPurchaseCountry(inputOrder[1]);
//                readOrder.setCustomerNumber(inputLine);
//                readOrder.setPurchasedIphone(Integer.parseInt(inputOrder[4]));
//                readOrder.setPurchsedIpod(Integer.parseInt(inputOrder[6]));

                customer.setPassportNumber(inputOrder[2]);
                customer.setCustomerId(inputLine);
                inputLine++;
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
