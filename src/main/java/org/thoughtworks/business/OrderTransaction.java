package org.thoughtworks.business;

import org.thoughtworks.model.Order;

public class OrderTransaction {

    Inventory inventoryBrazil = new InventoryBrazil();
    Inventory inventoryArgentina = new InventoryBrazil();

    public void applyOrder(Order order) {

        switch (order.getPurchaseCountry()) {
            case BRAZIL :
                inventoryBrazil.updateIphoneStock(order.getPurchasedIphone());
                inventoryBrazil.updateIpodStock(order.getPurchsedIpod());
                break;
            case ARGENTINA:
                inventoryArgentina.updateIphoneStock(order.getPurchasedIphone());
                inventoryArgentina.updateIpodStock(order.getPurchsedIpod());
                break;
        }
    }
}