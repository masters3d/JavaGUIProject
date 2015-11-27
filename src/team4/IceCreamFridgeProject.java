package team4;

/**
 * Launcher for Ice Cream Fridge Project
 * 
 * @author SZE LOK TAM
 * @author FRANCESKA S ONG
 * @author JOSE E JIMENEZ
 * @version Dec 2015
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
