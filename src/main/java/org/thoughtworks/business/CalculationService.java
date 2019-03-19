package org.thoughtworks.business;

import org.thoughtworks.model.Order;

public class CalculationService {
    public int calculateIphonePrice(int numOfIphones, Inventory inventory) {
        if (numOfIphones <= inventory.getIphoneStock()) {
            return inventory.getIphoneCost() * numOfIphones;
        } else {
            return inventory.getIphoneCost() * inventory.getIphoneStock();
        }
    }

    public int calculateIpodPrice(int numOfIpods, Inventory inventory) {
        if (numOfIpods <= inventory.getIpodStock()) {
            return inventory.getIpodCost() * numOfIpods;
        } else {
            return inventory.getIpodCost() * inventory.getIpodStock();
        }
    }

    public int calculateIphonePriceForeignInventory(int numOfIphones, Inventory foreignInventory) {
        return this.calculateIphonePrice(numOfIphones, foreignInventory);
    }

    public int calculateIpodPriceForeignInventory(int numOfIpod, Inventory foreignInventory) {
        return this.calculateIpodPrice(numOfIpod, foreignInventory);
    }

    private void updateIphoneStock(int numOfIphones, Inventory foreignInventory) {
        foreignInventory.updateIphoneStock(numOfIphones);
    }

    private void updateIpodStock(int numOfIpod, Inventory foreignInventory) {
        foreignInventory.updateIpodStock(numOfIpod);
    }
}