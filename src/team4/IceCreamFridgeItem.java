/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4;

import java.io.Serializable;
import static java.lang.Math.abs;

/**
 * Single-item instantiable class for IceCreamFridgeProject, representing one
 * type of Ice Cream
 *
 *
 * @author SZE LOK TAM
 * @author FRANCESKA S ONG
 * @author JOSE E JIMENEZ
 */
public class IceCreamFridgeItem implements Comparable, Serializable {

    private String iceCreamFlavor;
    private double stockInLiters;
    private double costPerLiter;
    private double salePricePerLiter;

    public IceCreamFridgeItem() {
        this.costPerLiter = 1;
        this.iceCreamFlavor = "Chocolate";
        this.salePricePerLiter = 2.5; // 2.5x is the usual list price for sales iteams
        this.stockInLiters = 1;
    }

    public IceCreamFridgeItem(String flavor, String stock, String cost, String sale) {
        setFlavor(flavor);
        setStockWithString(stock);
        setCostWithString(cost);
        setSalePriceWithString(sale);
    }

    private Double stringToDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public double getCostPerLiter() {
        return costPerLiter;
    }

    public double getSalePricePerLiter() {
        return salePricePerLiter;
    }

    public double getStockInLiters() {
        return stockInLiters;
    }

    public String getFlavor() {
        return iceCreamFlavor;
    }

    public Boolean setFlavor(String value) {
        // Limiting the length of the name to 30 characters
        if (value.length() < 31) {
            iceCreamFlavor = value;
            return true;
        } else {
            return false;
        }
    }

    public Boolean setSalePriceWithString(String value) {
        Double toUpdate = stringToDouble(value);
        if (toUpdate == 0.0) {

            return false;
        } else {
            return setSalePrice(toUpdate);
        }
    }

    public Boolean setSalePrice(Double value) {
        if (value < costPerLiter) {
            salePricePerLiter = costPerLiter; // Sets the sale price to even price
            return false;
        } else {
            salePricePerLiter = value;
            return true;
        }

    }

    public Boolean setCostWithString(String value) {
        Double toUpdate = stringToDouble(value);
        if (toUpdate == 0.0) {
            return false;
        } else {
            return setCost(toUpdate);
        }
    }

    public Boolean setCost(Double value) {
        if (value > salePricePerLiter && (salePricePerLiter > 0)) {
            costPerLiter = salePricePerLiter; //Sets the cost price to even sale price
            return false;
        } else {
            this.costPerLiter = value;
            return true;
        }
    }

    public Boolean setStockWithString(String value) {
        Double toUpdate = stringToDouble(value);
        if (toUpdate == 0.0) {
            return false;
        } else {
            return setStock(toUpdate);
        }
    }

    public Boolean setStock(double value) {
        if (stockInLiters + value < 0) {
            return false;
        }
        stockInLiters += value;
        return true;
    }

    @Override
    public int compareTo(Object obj) {
        if (obj != null && obj instanceof IceCreamFridgeItem) {
            IceCreamFridgeItem other = (IceCreamFridgeItem) obj;
            return iceCreamFlavor.compareTo(other.iceCreamFlavor);
        }
        return 1;
    }

    /**
     * Provides access to the string representation of this IceCream.
     *
     * @return String - this iceCream's name
     */
    @Override
    public String toString() {
        return iceCreamFlavor;
    }

}
