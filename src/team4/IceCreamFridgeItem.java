/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4;

import static java.lang.Math.abs;

/**
 *  Single-item instantiable class for IceCreamFridgeProject, representing one type of Ice Cream

 * 
 * @author SZE LOK TAM 
 * @author FRANCESKA S ONG
 * @author JOSE E JIMENEZ
 */

public class IceCreamFridgeItem {
    
    private double costPerLiter;
    private double salePricePerLiter;
    private double stockInLiters;
    private String iceCreamFlavor;


    private double setStaringSalesPrice(){
        return costPerLiter*2.5;
    }
    
    public IceCreamFridgeItem(double costPerLiter, String flavor) {
        this.costPerLiter = costPerLiter;
        this.iceCreamFlavor = flavor;
        this.salePricePerLiter = setStaringSalesPrice();
    }

    public IceCreamFridgeItem(double costPerLiter, double quantityInLiters, String flavor) {
        this.costPerLiter = costPerLiter;
        this.stockInLiters = quantityInLiters;
        this.iceCreamFlavor = flavor;
        this.salePricePerLiter = setStaringSalesPrice();
    }

    public double getCostPerLiter() {
        return costPerLiter;
    }

    public double getSalePricePerLiter() {
        return salePricePerLiter;
    }

    public double getQuantityInLiters() {
        return stockInLiters;
    }

    public String getFlavor() {
        return iceCreamFlavor;
    }
    
    public Boolean updateCurrentStock(double value){
    
        if (stockInLiters + value < 0) {
            return false;
        }
        
        stockInLiters += value;
        return true;
    }
        
    
    
    
}
