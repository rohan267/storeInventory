package org.thoughtworks;

import org.thoughtworks.business.CalculationService;
import org.thoughtworks.business.Inventory;
import org.thoughtworks.business.InventoryArgentina;
import org.thoughtworks.business.InventoryBrazil;
import org.thoughtworks.model.Country;
import org.thoughtworks.model.Order;
import org.thoughtworks.util.InventoryConstants;

import static org.thoughtworks.util.InventoryConstants.TRANSPORTATION_COST;

public class InventoryService extends CalculationService {

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
    private int salesPrice = 0;

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
        if (inputArray.length == 7) {
            order.setPurchaseCountry(Country.valueOf(inputArray[1]));
            order.setPassportNumber(inputArray[2]);
            order.setPurchasedIphone(Integer.parseInt(inputArray[4]));
            order.setPurchsedIpod(Integer.parseInt(inputArray[6]));
        } else if (inputArray.length == 6) {
            order.setPurchaseCountry(Country.valueOf(inputArray[1]));
            order.setPassportNumber("");
            order.setPurchasedIphone(Integer.parseInt(inputArray[3]));
            order.setPurchsedIpod(Integer.parseInt(inputArray[5]));
        }

        return order;
    }

    public void applyTransaction(Order order) {
        if (isStockAvailable(order)) {
            int iphoneStockDifference = 0;
            int ipodStockDifference = 0;
            int totalPrice = 0;
            if (order.getPurchaseCountry().equals(Country.BRAZIL)) {
                iphoneStockDifference = brazilInventory.getIphoneStock() - order.getPurchasedIphone();
                ipodStockDifference = brazilInventory.getIpodStock() - order.getPurchsedIpod();

                if (iphoneStockDifference < 0) {
                    totalPrice += calculateForeignInventoryPrice(iphoneStockDifference, 0, order);
                } else {
                    totalPrice += getIphonePriceFromLocalInventory(brazilInventory, order);
                }

                if (ipodStockDifference < 0) {
                    totalPrice += calculateForeignInventoryPrice(0, ipodStockDifference, order);
                } else {
                    totalPrice += getIpodPriceFromLocalInventory(brazilInventory, order);
                }
            } else if (order.getPurchaseCountry().equals(Country.ARGENTINA)) {
                iphoneStockDifference = argentinaInventory.getIphoneStock() - order.getPurchasedIphone();
                ipodStockDifference = argentinaInventory.getIpodStock() - order.getPurchsedIpod();
                if (iphoneStockDifference < 0) {
                    totalPrice += calculateForeignInventoryPrice(iphoneStockDifference, 0, order);
                } else {
                    totalPrice += getIphonePriceFromLocalInventory(argentinaInventory, order);
                }

                if (ipodStockDifference < 0) {
                    totalPrice += calculateForeignInventoryPrice(0, ipodStockDifference, order);
                } else {
                    totalPrice += getIpodPriceFromLocalInventory(argentinaInventory, order);
                }
            }

            salesPrice = totalPrice;
            if (isDiscountApplicable(order)) {
                calculateDiscountedPrice(order);
            }
//            else {
//                calculateNonDiscountedPrice(order);
//            }
        }
    }


    public void checkOutCart(Order order) {
        int iphoneOrderPriceLocalInventory = 0;
        int ipodOrderPrice = 0;

        Inventory localInventory = order.findLocalInventory(brazilInventory, argentinaInventory);
        Inventory foreignInventory = order.findForeignInventory(brazilInventory, argentinaInventory);

        iphoneOrderPriceLocalInventory = getIphoneOrderPrice(order, localInventory);

        int numberOfIphoneForeignInventory = order.getPurchasedIphone() - localInventory.getIphoneStock();

        int totalIphoneCostForeignInventory=0;
        if(numberOfIphoneForeignInventory > 0) {
            totalIphoneCostForeignInventory = calculateIphonePriceForeignInventory(numberOfIphoneForeignInventory, argentinaInventory);
        }

        ipodOrderPrice = getIpodOrderPrice(order, localInventory);
        int numberOfIpodForeignInventory = order.getPurchsedIpod() - localInventory.getIpodStock();

        int totalIpodCostForeignInventory=0;
        if(numberOfIpodForeignInventory > 0) {
            totalIpodCostForeignInventory = calculateIpodPriceForeignInventory(numberOfIphoneForeignInventory, argentinaInventory);
        }

        // TODO : Inventory stock update
        salesPrice = iphoneOrderPriceLocalInventory + totalIphoneCostForeignInventory + ipodOrderPrice + totalIpodCostForeignInventory;

    }

    private int getIpodOrderPrice(Order order, Inventory localInventory) {
        int ipodOrderPrice = calculateIpodPrice(order.getPurchsedIpod(), localInventory);
        return ipodOrderPrice;
    }

    private int getIphoneOrderPrice(Order order, Inventory localInventory) {
        int iphoneOrderPrice = calculateIphonePrice(order.getPurchasedIphone(), localInventory);
        return iphoneOrderPrice;
    }


    /** prior code **/
    public int calculateForeignInventoryPrice(int iphoneStockDifference, int ipodStockDifference, Order order) {
        int foreignInventoryIphonePice = 0;
        int foreignInventoryIpodPice = 0;
        switch (order.getPurchaseCountry()) {
            case BRAZIL:
                if (iphoneStockDifference < 0) {
                    foreignInventoryIphonePice = getIphonePriceFromForeignInventory(iphoneStockDifference * -1, argentinaInventory);
                }
                if (ipodStockDifference < 0) {
                    foreignInventoryIpodPice = getIpodPriceFromForeignInventory(ipodStockDifference * -1, argentinaInventory);
                }
                break;
            case ARGENTINA:
                if (iphoneStockDifference < 0) {
                    foreignInventoryIphonePice = getIphonePriceFromForeignInventory(iphoneStockDifference * -1, brazilInventory);
                }
                if (ipodStockDifference < 0) {
                    foreignInventoryIpodPice = getIpodPriceFromForeignInventory(ipodStockDifference * -1, brazilInventory);
                }
                break;
        }

        return foreignInventoryIphonePice + foreignInventoryIpodPice;
    }

    private int getIpodPriceFromLocalInventory(Inventory localInventory, Order order) {
        return order.getPurchsedIpod() * localInventory.getIpodCost();
    }

    private int getIphonePriceFromLocalInventory(Inventory localInventory, Order order) {
        return order.getPurchasedIphone() * localInventory.getIphoneCost();
    }

    private int getIphonePriceFromForeignInventory(int iphoneStockDifference, Inventory foreignInventory) {
        return iphoneStockDifference * foreignInventory.getIphoneCost()
                + getTransportaionCharge(iphoneStockDifference);
    }

    private int getIpodPriceFromForeignInventory(int ipodStockDifference, Inventory foreignInventory) {
        return ipodStockDifference * foreignInventory.getIpodCost()
                + getTransportaionCharge(ipodStockDifference);
    }

    public int getTransportaionCharge(int itemAmount) {
        return itemAmount > 0 ? ((itemAmount - 1) / 10 + 1) * TRANSPORTATION_COST : 0;
    }

    public void remainderTest(int num) {
        System.out.println(num / 10);
    }

    private int calculateDiscountedPrice(Order order) {
        return new Double(salesPrice * InventoryConstants.DISCOUNT_VALUE).intValue();
    }

    public boolean isDiscountApplicable(Order order) {

        if (order.getPassportNumber().length() > 0
                && order.getPurchaseCountry().equals(Country.BRAZIL)
                && order.getPassportNumber().charAt(0) == 'B') {
            return true;
        }

        if (order.getPassportNumber().length() > 0
                && order.getPurchaseCountry().equals(Country.ARGENTINA)
                && order.getPassportNumber().charAt(0) == 'A') {
            return true;
        }

        return false;
    }

    public boolean isStockAvailable(Order order) {

        int totalIphoneStock = brazilInventory.getIphoneStock() + argentinaInventory.getIphoneStock();
        int totalIpodStock = brazilInventory.getIpodStock() + argentinaInventory.getIpodStock();

        if (order.getPurchasedIphone() <= totalIphoneStock
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

    public int getSalesPrice() {
        return salesPrice;
    }
}
