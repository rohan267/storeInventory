package org.thoughtworks.business;

import org.thoughtworks.model.Country;

public interface Inventory {

    public Country country = null;
    
    public void updateIpodStock(int purchasedIpod);

    public void updateIphoneStock(int purchasedIphone);

    public int getIphoneStock();

    public int getIpodStock();

    public int getIphoneCost();

    public int getIpodCost();
}
