/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author SZE LOK TAM 
 * @author FRANCESKA S ONG
 * @author JOSE E JIMENEZ
 */
public class IceCreamFridgeView extends JFrame {
    private Container container;
    private IceCreamFridgeItem flavors;
    private JComboBox<IceCreamFridgeItem> itemNames;
    private JTextField txtFlavors;
    private JTextField txtStock;
    private JTextField txtPrice;
    private JTextField txtPricePerLiter;
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnSave;

    public IceCreamFridgeView(String title)
    {
        super(title);
        flavors = new IceCreamFridgeItem();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = this.getContentPane();
        addWindowComponents();
        
    }
    
    private void addWindowComponents() {
        // make combo box
        itemNames = new JComboBox<>();
        container.add(itemNames, BorderLayout.NORTH);
        // make buttons
        JPanel pnlButtons = new JPanel();
        btnNew = new JButton("New");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnSave = new JButton("Save");
        pnlButtons.add(btnNew);
        pnlButtons.add(btnUpdate);
        pnlButtons.add(btnSave);    
        pnlButtons.add(btnDelete);

        container.add(pnlButtons, BorderLayout.SOUTH);
        
        // fields
        JPanel pnlFields = new JPanel();
        pnlFields.setLayout(new BoxLayout(pnlFields, BoxLayout.Y_AXIS));
        // flavor of the ice cream
        JPanel pnlFlavor = new JPanel();
        pnlFlavor.add(new JLabel("Ice Cream Flavor: "));
        txtFlavors = new JTextField(25);
        pnlFlavor.add(txtFlavors);
        // stock of that flavor
        JPanel pnlStock = new JPanel();
        pnlStock.add(new JLabel("     Current Stock: "));
        txtStock = new JTextField(25);
        pnlStock.add(txtStock);
        // price 
        JPanel pnlPricePerLiter = new JPanel();
        pnlPricePerLiter.add(new JLabel("    Price Per Liter: "));
        txtPricePerLiter = new JTextField(25);
        pnlPricePerLiter.add(txtPricePerLiter);
        // price 
        JPanel pnlPrice = new JPanel();
        pnlPrice.add(new JLabel("                     Price: "));
        txtPrice = new JTextField(25);
        pnlPrice.add(txtPrice);
        // add to pnlFields;
        pnlFields.add(pnlFlavor);
        pnlFields.add(pnlStock);
        pnlFields.add(pnlPricePerLiter);
        pnlFields.add(pnlPrice);
        // add to container
        container.add(pnlFields, BorderLayout.CENTER);
        
        
        
        
    }
}
