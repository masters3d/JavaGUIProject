package team4;

import java.io.Serializable;
import java.util.Objects;

/**
 * Single-item instantiable class for IceCreamFridgeProject, representing one
 * type of Ice Cream
 *
 *
 * @author SZE LOK TAM
 * @author FRANCESKA S ONG
 * @author JOSE E JIMENEZ
 * @version Dec 2015
 */
public class IceCreamFridgeItem implements Comparable, Serializable {

     /**
     * Internal fields to keep track of name, stock, cost and sales price. 
     */
    
    private String iceCreamFlavor;
    private double stockInLiters;
    private double salePricePerLiter;

    /**
     * Default constructor with some starting data that will get rewritten
     */
    public IceCreamFridgeItem() {
        this.iceCreamFlavor = "Chocolate";
        this.salePricePerLiter = 2.5; // 2.5x is the usual list price for sales iteams
        this.stockInLiters = 1;
    }

    public Double getPotencialSales(){
        return stockInLiters * salePricePerLiter;
    }
    
    
     /**
     * Helper method that helps to convert the string to doubles and caching the
     * exception by return a default value.
     * @param input the string value being converted to double
     * @return double returns the value converted or Zero.
     */
    
    private Double stringToDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }


    /**
     * get the Sale price
     * @return double with sale price per liter
     */
    public double getSalePricePerLiter() {
        return salePricePerLiter;
    }

    /**
     * Gets the current stock in liters
     * @return double of the stock
     */
    public double getStockInLiters() {
        return stockInLiters;
    }

    /**
     * Gets the flavor name
     * @return a string of the name of iceCream
     */
    public String getFlavor() {
        return iceCreamFlavor;
    }

    /**
     * Sets the flavor name
     * @param value the string to set the flavor
     * @return true or false if the if it was successful
     */
    public Boolean setFlavor(String value) {
        // Limiting the length of the name to 30 characters
        if (value.length() < 31) {
            iceCreamFlavor = value;
            return true;
        } else {
            return false;
        }
    }

    /**
     * This sets the sale price using a string
     * @param value the string to set the sale price. It gets converted to double
     * @return true or false if the if it was successful
     */
    public Boolean setSalePriceWithString(String value) {
        Double toUpdate = stringToDouble(value);
        if (toUpdate == 0.0) {

            return false;
        } else {
            return setSalePrice(toUpdate);
        }
    }

    /**
     * This sets the sale price
     * @param value the string to set the sale price. 
     * @return true or false if the if it was successful
     */
    public Boolean setSalePrice(Double value) {
        if (value < 0) {
            return false;
        } else {
            salePricePerLiter = value;
            return true;
        }

    }


    /**
     * Sets the stock using a string
     * @param value string gets converted to double
     * @return true or false if the if it was successful
     */
    public Boolean setStockWithString(String value) {
        Double toUpdate = stringToDouble(value);
        if (toUpdate == 0.0) {
            return false;
        } else {
            return setStock(toUpdate);
        }
    }

    /**
     * Sets the stock value, does not accept negative values
     * @param value double used to update the stock value
     * @return true or false if the if it was successful
     */
    public Boolean setStock(double value) {
        if (value < 0) {
            return false;
        }
        stockInLiters = value;
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
    
     /**
     * Defines what it means to have two iceCreams that are equal.
     * Set to just compare names so that the same name cannot
     * be added twice.
     * 
     * @param obj the contact to compare this one to.
     * 
     * @return boolean - true if equal, false if not 
     */

    @Override
    public boolean equals(Object obj) {
                if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IceCreamFridgeItem other = (IceCreamFridgeItem) obj;
        if (!Objects.equals(this.iceCreamFlavor, other.iceCreamFlavor)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.iceCreamFlavor);
        hash = 31 * hash + Objects.hashCode(this.stockInLiters);
        hash = 31 * hash + Objects.hashCode(this.salePricePerLiter);
        return hash;
        
    }
    
    
    

}
