/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4;

/**
 *
 * @author SZE LOK TAM 
 * @author FRANCESKA S ONG
 * @author JOSE E JIMENEZ
 */
public class IceCreamFridgeProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IceCreamFridgeView view = new IceCreamFridgeView("Ice Cream");
        view.pack();
        view.setLocation(200, 200);
        view.setVisible(true);
    }
    
}
