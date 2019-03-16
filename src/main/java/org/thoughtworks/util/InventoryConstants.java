package org.thoughtworks.util;

public class InventoryConstants {
    // Brazil
    public static final int BRAZIL_IPHONE_COST  = 100;
    public static final int BRAZIL_IPOD_COST  = 65;
    public static final int BRAZIL_IPOD_STOCK  = 100;
    public static final int BRAZIL_IPHONE_STOCK  = 100;

    // Argentina
    public static final int ARGENTINA_IPHONE_COST  = 150;
    public static final int ARGENTINA_IPOD_COST  = 100;
    public static final int ARGENTINA_IPOD_STOCK  = 100;
    public static final int ARGENTINA_IPHONE_STOCK  = 50;

    // Discounts
    public static final double DISCOUNT_VALUE  = 0.20; // 20%

    // Transportation cost
    public static final int TRANSPORTATION_COST = 400;

    // Input
    public static final String input1 = "INPUT 1: BRAZIL:B123AB1234567:IPHONE:20:IPOD:10 OUTPUT 1: 2650:90:100:80:50";
    public static final String input2 = "INPUT 2: ARGENTINA:B123AB1234567:IPHONE:22:IPOD:10 OUTPUT 2: 3910:90:100:80:48";
    public static final String input3 = "INPUT 3: BRAZIL:AAB123456789:IPHONE:125:IPOD:70 OUTPUT 3: 19260:30:100:0:25";
    public static final String input4 = "INPUT 4: ARGENTINA:AAB123456789:IPOD:50:IPHONE:25 OUTPUT 4: 8550:100:50:80:45";
    public static final String input5 = "INPUT 5: BRAZIL:IPHONE:50:IPOD:150 OUTPUT 5: 18500:0:50:50:50";
    public static final String input6 = "INPUT 6: BRAZIL:IPHONE:250:IPOD:150 OUTPUT 6: OUT_OF_STOCK:100:100:100:50";


}
