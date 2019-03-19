package org.thoughtworks.business;

import org.thoughtworks.model.Country;
import org.thoughtworks.model.Order;
import org.thoughtworks.model.Product;
import org.thoughtworks.util.InventoryConstants;

public class InventoryArgentina extends Product implements Inventory {

    public InventoryArgentina() {
        ipodStock = InventoryConstants.ARGENTINA_IPOD_STOCK;
        iphoneStock = InventoryConstants.ARGENTINA_IPHONE_STOCK;
        ipodCost = InventoryConstants.ARGENTINA_IPOD_COST;
        iphoneCost = InventoryConstants.ARGENTINA_IPHONE_COST;
    }

    @Override
    public void updateIpodStock(int purchasedIpod) {
        int newIpodStock = ipodStock - purchasedIpod;

        if(newIpodStock >=0) {
            ipodStock = newIpodStock;
        } else {
            ipodStock = 0;
        }
    }

    @Override
    public void updateIphoneStock(int purchasedIphone) {
        int newStock = iphoneStock - purchasedIphone;
        if(newStock  >= 0)  {
            iphoneStock = newStock;
        } else {
            iphoneStock = 0;
        }
    }

    @Override
    public int getIphoneStock() {
        return this.iphoneStock;
    }

    @Override
    public int getIpodStock() {
        return this.ipodStock;
    }

    @Override
    public int getIphoneCost() {
        return InventoryConstants.ARGENTINA_IPHONE_COST;
    }

    @Override
    public int getIpodCost() {
        return InventoryConstants.ARGENTINA_IPOD_COST;
    }
}