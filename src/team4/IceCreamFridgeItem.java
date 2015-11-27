package team4;

import java.io.Serializable;

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
    private double costPerLiter;
    private double salePricePerLiter;

    /**
     * Default constructor with some starting data that will get rewritten
     */
    public IceCreamFridgeItem() {
        this.costPerLiter = 1;
        this.iceCreamFlavor = "Chocolate";
        this.salePricePerLiter = 2.5; // 2.5x is the usual list price for sales iteams
        this.stockInLiters = 1;
    }

    /**
     *  Constructor
     * @param flavor is the name of the icreCream
     * @param stock is the current stock level in 
     * @param cost current cost of buying the ice cream
     * @param sale Sale price for the iceCream
     */
    public IceCreamFridgeItem(String flavor, String stock, String cost, String sale) {
        setFlavor(flavor);
        setStockWithString(stock);
        setCostWithString(cost);
        setSalePriceWithString(sale);
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
     * Gets the cost per liter
     * @return Double with cost per liter
     */
    public double getCostPerLiter() {
        return costPerLiter;
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
        if (value < costPerLiter) {
            salePricePerLiter = costPerLiter; // Sets the sale price to even price
            return false;
        } else {
            salePricePerLiter = value;
            return true;
        }

    }

    /**
     * Sets the cost using a string that gets comverted to a double
     * @param value string valued to need to be parsed to a double
     * @return true or false if the if it was successful
     */
    public Boolean setCostWithString(String value) {
        Double toUpdate = stringToDouble(value);
        if (toUpdate == 0.0) {
            return false;
        } else {
            return setCost(toUpdate);
        }
    }

    /**
     * Sets the cost of the iceCream
     * @param value double that gets set
     * @return true or false if the if it was successful
     */
    public Boolean setCost(Double value) {
        if (value > salePricePerLiter && (salePricePerLiter > 0)) {
            costPerLiter = salePricePerLiter; //Sets the cost price to even sale price
            return false;
        } else {
            this.costPerLiter = value;
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
