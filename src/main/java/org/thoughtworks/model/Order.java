package org.thoughtworks.model;

import org.thoughtworks.business.Inventory;

public class Order {
    private int orderNumber;
    private Country purchaseCountry;
    private int purchasedIphone;
    private int purchsedIpod;
    private String passportNumber;
    private int customerNumber;
    private Inventory localInventory;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Country getPurchaseCountry() {
        return purchaseCountry;
    }

    public Inventory getLocalInventory() {
        return localInventory;
    }

    public void setPurchaseCountry(Country purchaseCountry) {
        this.purchaseCountry = purchaseCountry;
    }

    public int getPurchasedIphone() {
        return purchasedIphone;
    }

    public void setPurchasedIphone(int purchasedIphone) {
        this.purchasedIphone = purchasedIphone;
    }

    public int getPurchsedIpod() {
        return purchsedIpod;
    }

    public void setPurchsedIpod(int purchsedIpod) {
        this.purchsedIpod = purchsedIpod;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}