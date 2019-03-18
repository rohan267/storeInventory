package org.thoughtworks.model;

import org.thoughtworks.business.Inventory;

import java.util.HashMap;
import java.util.Map;

public class Product {

    public int ipodStock;
    public int iphoneStock;
    public int iphoneCost;
    public int ipodCost;

    Map<Country, Inventory> inventoryMap = new HashMap<>();

    public Product() {
    }

    public Product(Country country, Inventory inventory) {
        this.inventoryMap.put(country, inventory);
    }

}
