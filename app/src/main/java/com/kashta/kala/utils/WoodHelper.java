package com.kashta.kala.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class WoodHelper {

    public static class WoodType {
        public String name;
        public int pricePerSqFt;  // INR
        public String durability;
        public String density;

        public WoodType(String name, int pricePerSqFt, String durability, String density) {
            this.name = name;
            this.pricePerSqFt = pricePerSqFt;
            this.durability = durability;
            this.density = density;
        }
    }

    public static final Map<String, WoodType> WOOD_TYPES = new LinkedHashMap<>();

    static {
        WOOD_TYPES.put("Teak",     new WoodType("Teak",     850, "Excellent",  "High"));
        WOOD_TYPES.put("Sheesham", new WoodType("Sheesham", 600, "Very Good",  "Medium-High"));
        WOOD_TYPES.put("Sal",      new WoodType("Sal",      500, "Very Good",  "High"));
        WOOD_TYPES.put("Mango",    new WoodType("Mango",    400, "Good",       "Medium"));
        WOOD_TYPES.put("Pine",     new WoodType("Pine",     300, "Fair",       "Low-Medium"));
        WOOD_TYPES.put("Bamboo",   new WoodType("Bamboo",   250, "Good",       "Medium"));
    }

    public static String[] getWoodNames() {
        return WOOD_TYPES.keySet().toArray(new String[0]);
    }

    // Core estimation formula
    public static class EstimateResult {
        public double squareFeet;
        public double cubicFeet;
        public double woodCost;
        public double laborCost;
        public double totalCost;
        public int pricePerSqFt;
    }

    public static EstimateResult calculate(double lengthFt, double widthFt, double heightFt,
                                           String woodType, int laborRatePerSqFt) {
        WoodType wood = WOOD_TYPES.get(woodType);
        if (wood == null) return null;

        EstimateResult r = new EstimateResult();
        r.squareFeet  = lengthFt * widthFt;
        r.cubicFeet   = (heightFt > 0) ? r.squareFeet * heightFt : 0;
        r.pricePerSqFt = wood.pricePerSqFt;
        r.woodCost    = r.squareFeet * wood.pricePerSqFt;
        r.laborCost   = r.squareFeet * laborRatePerSqFt;
        r.totalCost   = r.woodCost + r.laborCost;
        return r;
    }
}
