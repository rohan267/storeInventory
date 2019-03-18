package org.thoughtworks.business;

import org.thoughtworks.model.Order;

public class CalculationService {
    public int calculateIphonePrice(Order order, Inventory inventory) {
        if (order.getPurchasedIphone() < inventory.getIphoneStock()) {
            return inventory.getIphoneCost() * order.getPurchasedIphone();
        }
        return 0;
    }

    public int calculateIpodPrice(Order order, Inventory localInventory) {
        if (order.getPurchsedIpod() < localInventory.getIpodStock()) {
            return localInventory.getIpodCost() * order.getPurchsedIpod();
        }
        return 0;
    }
}
