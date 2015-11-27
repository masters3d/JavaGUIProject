/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author SZE LOK TAM
 * @author FRANCESKA S ONG
 * @author JOSE E JIMENEZ
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

    /**
     * Adds the given IceCream to the list if it does not exist already.
     *
     * @param c the IceCream to add
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
