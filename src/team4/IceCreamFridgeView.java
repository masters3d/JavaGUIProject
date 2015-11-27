/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author SZE LOK TAM
 * @author FRANCESKA S ONG
 * @author JOSE E JIMENEZ
 */
public class IceCreamFridgeView extends JFrame {

    private Container container;
    private IceCreamFridgeManager iceCreams;
    private JComboBox<IceCreamFridgeItem> itemNames;
    private JTextField txtFlavors;
    private JTextField txtStock;
    private JTextField txtCostPrice;
    private JTextField txtSalePrice;
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnSave;
    private final String errorMsgFlavors = "Flavor name is too long";
    private final String errorMsgStock = "Invalid Stock number entered";
    private final String errorMsgCostPrice = "Invalid Cost value entered";
    private final String errorMsgSalePrice = "Invalid Sale Price entered";

    public IceCreamFridgeView(String title) {
        super(title);
        iceCreams = new IceCreamFridgeManager();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = this.getContentPane();
        addWindowComponents();
        addEventHandlers();
        initializeDisplay();

    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(IceCreamFridgeView.this, message);

    }

    private void addWindowComponents() {
        // make combo box
        itemNames = new JComboBox<>(iceCreams.getSortedArray());
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
        // Cost price 
        JPanel pnlCostPrice = new JPanel();
        pnlCostPrice.add(new JLabel("         Cost Price: "));
        txtCostPrice = new JTextField(25);
        pnlCostPrice.add(txtCostPrice);
        // Sale price 
        JPanel pnlSalePrice = new JPanel();
        pnlSalePrice.add(new JLabel("                Sale Price: "));
        txtSalePrice = new JTextField(25);
        pnlSalePrice.add(txtSalePrice);
        // add to pnlFields;
        pnlFields.add(pnlFlavor);
        pnlFields.add(pnlStock);
        pnlFields.add(pnlCostPrice);
        pnlFields.add(pnlSalePrice);
        // add to container
        container.add(pnlFields, BorderLayout.CENTER);
    }

    /**
     * Creates event handlers for the combo box and buttons.
     */
    private void addEventHandlers() {
        //combo box event handler
        itemNames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //populate fields
                if (itemNames.getItemCount() > 0) {
                    IceCreamFridgeItem c = (IceCreamFridgeItem) itemNames.getSelectedItem();
                    if (c != null) {
                        txtFlavors.setText(c.getFlavor());
                        txtStock.setText(Double.toString(c.getStockInLiters()));
                        txtCostPrice.setText(Double.toString(c.getCostPerLiter()));
                        txtSalePrice.setText(Double.toString(c.getSalePricePerLiter()));
                    } else {
                        txtFlavors.setText("");
                        txtStock.setText("");
                        txtCostPrice.setText("");
                        txtSalePrice.setText("");
                    }
                }
            }
        });
        //update button event handler
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtFlavors.getText().isEmpty()) {
                    IceCreamFridgeItem c = (IceCreamFridgeItem) itemNames.getSelectedItem();
                    if (!c.setFlavor(txtFlavors.getText())) {
                        showErrorMessage(errorMsgFlavors);
                    }
                    if (!c.setStockWithString(txtStock.getText())) {
                        showErrorMessage(errorMsgStock);
                    }
                    if (!c.setCostWithString(txtCostPrice.getText())) {
                        showErrorMessage(errorMsgCostPrice);
                    }
                    if (!c.setSalePriceWithString(txtSalePrice.getText())) {
                        showErrorMessage(errorMsgSalePrice);
                    }
                    itemNames.updateUI();
                }
            }
        });
        //new/add button event handler
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnNew.getText().equals("new")) {
                    txtFlavors.setEnabled(true);
                    txtFlavors.setText("");
                    txtStock.setEnabled(true);
                    txtStock.setText("");
                    txtCostPrice.setEnabled(true);
                    txtCostPrice.setText("");
                    txtSalePrice.setEnabled(true);
                    txtSalePrice.setText("");
                    btnNew.setText("add");
                    btnDelete.setEnabled(false);
                    btnUpdate.setEnabled(false);
                    txtFlavors.requestFocus();
                } else {
                    if (!txtFlavors.getText().isEmpty()) {
                        IceCreamFridgeItem c = new IceCreamFridgeItem();
                        if (!c.setFlavor(txtFlavors.getText())) {
                            showErrorMessage(errorMsgFlavors);
                        }
                        if (!c.setStockWithString(txtStock.getText())) {
                            showErrorMessage(errorMsgStock);
                        }
                        if (!c.setCostWithString(txtCostPrice.getText())) {
                            showErrorMessage(errorMsgCostPrice);
                        }
                        if (!c.setSalePriceWithString(txtSalePrice.getText())) {
                            showErrorMessage(errorMsgSalePrice);
                        }
                        itemNames.setModel(new DefaultComboBoxModel(
                                iceCreams.getSortedArray()));
                        itemNames.setSelectedItem(c);
                    }
                    btnDelete.setEnabled(true);
                    btnUpdate.setEnabled(true);
                    btnNew.setText("new");
                }
            }
        });
        //delete button event handler
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (itemNames.getItemCount() > 0) {
                    int answer = JOptionPane.showConfirmDialog(IceCreamFridgeView.this,
                            "Are you sure you want to delete this contact?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (answer == JOptionPane.YES_OPTION) {
                        itemNames.removeItemAt(itemNames.getSelectedIndex());
                        iceCreams.deleteIceCream((IceCreamFridgeItem) itemNames.getSelectedItem());
                        itemNames.updateUI();
                    }
                }
                if (itemNames.getItemCount() == 0) {
                    btnNew.doClick();
                }
            }
        });
    }

    /**
     * Sets up how the different GUI components should be viewed at startup.
     */
    private void initializeDisplay() {
        if (itemNames.getItemCount() > 0) {
            itemNames.setSelectedIndex(0);
        } else {
            txtFlavors.setEnabled(false);
            txtStock.setEnabled(false);
            txtCostPrice.setEnabled(false);
            txtSalePrice.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
            btnNew.doClick();

        }
    }

}
