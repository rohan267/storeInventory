package org.thoughtworks;

import org.thoughtworks.business.Inventory;
import org.thoughtworks.business.InventoryArgentina;
import org.thoughtworks.business.InventoryBrazil;
import org.thoughtworks.model.Country;
import org.thoughtworks.model.Order;
import org.thoughtworks.util.InventoryConstants;

public class InventoryService {

    // create brazil inventory - done
    // create Argentina inventory - done
    // read order
    // fill order object
    // apply transaction
    // validate stock
    // identify customer location
    // validate discount if applicable
    // calculate transportation cost
    // calculate sales price
    // update stock
    private Inventory brazilInventory;
    private Inventory argentinaInventory;
    private double salesPrice=0;

    public InventoryService() {
        this.brazilInventory = new InventoryBrazil();
        this.argentinaInventory = new InventoryArgentina();
    }

    public Order readOrder(String input) {
        Order order = new Order();
        // INPUT 1: BRAZIL:B123AB1234567:IPHONE:20:IPOD:10 OUTPUT 1: 2650:90:100:80:50
        input = input.replace(" ", "");
        input = input.substring(0, input.indexOf("OUTPUT"));
        String[] inputArray = input.split(":");
        order.setPurchaseCountry(Country.valueOf(inputArray[1]));
        order.setPassportNumber(inputArray[2]);
        order.setPurchasedIphone(Integer.parseInt(inputArray[4]));
        order.setPurchsedIpod(Integer.parseInt(inputArray[6]));

        return order;
    }

    public void applyTransaction(Order order) {

        if(isStockAvailable(order)) {
            if(isDiscountApplicable(order)) {
                calculateDiscountedPrice(order);
            } else {
                calculateNonDiscountedPrice(order);
            }
        }
    }

    private int calculateNonDiscountedPrice(Order oder) {

        return 0;
    }

    private double calculateDiscountedPrice(Order order) {
        switch (order.getPurchaseCountry()) {
            case BRAZIL:
                salesPrice = order.getPurchasedIphone() * brazilInventory.getIphoneCost()
                    + order.getPurchsedIpod() * brazilInventory.getIpodCost();
                break;
            case ARGENTINA:
                salesPrice = order.getPurchasedIphone() * argentinaInventory.getIphoneCost()
                    + order.getPurchsedIpod() * argentinaInventory.getIpodCost();
                break;
        }
        return salesPrice * InventoryConstants.DISCOUNT_VALUE;
    }

    private void getInventory(Order order) {
//        return order.get
    }

    public boolean isDiscountApplicable(Order order) {

        if(order.getPurchaseCountry().equals(Country.BRAZIL)
            && order.getPassportNumber().charAt(0) == 'B') {
            return true;
        }
        return false;
    }

    public boolean isStockAvailable(Order order) {

        int totalIphoneStock = brazilInventory.getIphoneStock() + argentinaInventory.getIphoneStock();
        int totalIpodStock = brazilInventory.getIpodStock() + argentinaInventory.getIpodStock();

        if(order.getPurchasedIphone() <= totalIphoneStock
                && order.getPurchsedIpod() <= totalIpodStock) {
            return true;
        }

        return false;
    }

    public Inventory getBrazilInventory() {
        return brazilInventory;
    }

    public Inventory getArgentinaInventory() {
        return argentinaInventory;
    }

    public double getSalesPrice() {
        return salesPrice;
    }
}
