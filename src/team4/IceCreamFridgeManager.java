package team4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Instantiable class managing a collection of contacts for IceCream
 *
 * @author SZE LOK TAM
 * @author FRANCESKA S ONG
 * @author JOSE E JIMENEZ
 * @version Dec 2015
 */
public class IceCreamFridgeManager {

    private ArrayList<IceCreamFridgeItem> iceCreamList;

    private String fileLocation = "iceCream.ser";

    /**
     * The default constructor.
     */
    public IceCreamFridgeManager() {
        iceCreamList = new ArrayList<>();
        readCollection();
    }

    public IceCreamFridgeItem getMostExpensiveIceCream() {
        if (iceCreamList.isEmpty()) {
            return new IceCreamFridgeItem();
        }
        IceCreamFridgeItem temp = iceCreamList.get(0);

        for (int i = 0; i < iceCreamList.size(); i++) {
            if (iceCreamList.get(i).getSalePricePerLiter() > temp.getSalePricePerLiter()) {
                temp = iceCreamList.get(i);
            }
        }
        return temp;

    }

    public IceCreamFridgeItem getMostInStockIceCream() {

        if (iceCreamList.isEmpty()) {
            return new IceCreamFridgeItem();
        }

        IceCreamFridgeItem temp = iceCreamList.get(0);

        for (int i = 0; i < iceCreamList.size(); i++) {
            if (iceCreamList.get(i).getStockInLiters() > temp.getStockInLiters()) {
                temp = iceCreamList.get(i);
            }
        }
        return temp;
    }

    public Double getTotalPotentialSales() {

        if (iceCreamList.isEmpty()) {
            return 0.0;
        }

        Double sum = 0.0;

        for (int i = 0; i < iceCreamList.size(); i++) {
            sum += iceCreamList.get(i).getPotencialSales();
        }
        return sum;

    }

    public Double getTotalStock() {

        if (iceCreamList.isEmpty()) {
            return 0.0;
        }

        Double sum = 0.0;

        for (int i = 0; i < iceCreamList.size(); i++) {
            sum += iceCreamList.get(i).getStockInLiters();
        }
        return sum;

    }

    /**
     * Adds the given IceCream to the list if it does not exist already.
     *
     * @param c the IceCream to add
     * @return True or False depending if the write is successful
     */
    public boolean addIceCream(IceCreamFridgeItem c) {
        boolean success = false;
        if (!iceCreamList.contains(c)) {
            iceCreamList.add(c);
            success = writeCollection();
        }
        return success;
    }

    /**
     * Deletes the given IceCream from the list.
     *
     * @param c the IceCream to delete
     */
    public void deleteIceCream(IceCreamFridgeItem c) {
        iceCreamList.remove(c);
        writeCollection();
    }

    /**
     * Sorts the current list of IceCreams and converts it to an array.
     *
     * @return IceCreamFridgeItem[] the current list of IceCreams as a sorted
     * array
     */
    public IceCreamFridgeItem[] getSortedArray() {
        Collections.sort(iceCreamList);
        return iceCreamList.toArray(new IceCreamFridgeItem[iceCreamList.size()]);
    }

    /**
     * Serializes the IceCreams array list.
     *
     * @return boolean - true if saved, false if not
     */
    private boolean writeCollection() {
        boolean success = true;
        try (FileOutputStream fos
                = new FileOutputStream(fileLocation);
                ObjectOutputStream output
                = new ObjectOutputStream(fos)) {
            output.writeObject(iceCreamList);
        } catch (Exception ex) {
            System.out.println("Cannot write to file:\n"
                    + ex.getMessage());
            success = false;
        }
        return success;
    }

    /**
     * Populates the IceCreams list with the serialized file contents.
     *
     * @return boolean - true if successful, false if not
     */
    private boolean readCollection() {
        boolean success = true;
        File ser = new File(fileLocation);
        if (ser.exists()) {
            try (FileInputStream fis
                    = new FileInputStream(fileLocation);
                    ObjectInputStream input
                    = new ObjectInputStream(fis)) {
                iceCreamList = (ArrayList<IceCreamFridgeItem>) input.readObject();
            } catch (Exception ex) {
                System.out.println("Cannot read from file:\n"
                        + ex.getMessage());
                success = false;
            }
        }
        return success;
    }

}
