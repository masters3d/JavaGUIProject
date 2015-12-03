package team4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Presentation layer for Ice Cream Fridge
 *
 * @author SZE LOK TAM
 * @author FRANCESKA S ONG
 * @author JOSE E JIMENEZ
 * @version Dec 2015
 */
public class IceCreamFridgeView extends JFrame {

    private Container container;
    private IceCreamFridgeManager iceCreams;
    private JComboBox<IceCreamFridgeItem> itemNames;
    private JTextField txtFlavor;
    private JTextField txtStock;
    private JTextField txtSalePrice;
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;
    private final String errorMsgFlavors = "Flavor name is too long";
    private final String errorMsgStock = "Invalid Stock number entered";
    private final String errorMsgSalePrice = "Invalid Sale Price entered";

     /**
     * The constructor that creates the JFrame and calls the other methods
     * of this class to build the GUI, wire the components, and initialize
     * the display.
     * 
     * @param title the title of the underlying JFrame
     */
    
    public IceCreamFridgeView(String title) {
        super(title);
        iceCreams = new IceCreamFridgeManager();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = this.getContentPane();
        addWindowComponents();
        addEventHandlers();
        initializeDisplay();
        
    }

     /**
     * Show errors based on the message string
     * 
     * @param message message to display
     */   
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(IceCreamFridgeView.this, message);
    }

    
    /**
     * Builds the GUI containers and components.
     */
    
    private void addWindowComponents() {
  //combo box
        itemNames = new JComboBox<>(iceCreams.getSortedArray());
        container.add(itemNames, BorderLayout.NORTH);
        //buttons
        JPanel pnlButtons = new JPanel();
        btnNew = new JButton("New");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        pnlButtons.add(btnNew);
        pnlButtons.add(btnUpdate);
        pnlButtons.add(btnDelete);
        container.add(pnlButtons, BorderLayout.SOUTH);
        //fields
        JPanel pnlFields = new JPanel();
        pnlFields.setLayout(new BoxLayout(pnlFields, BoxLayout.Y_AXIS));
        //Flavor of the ice cream
        JPanel pnlFlavor = new JPanel();
        pnlFlavor.add(new JLabel("Ice Cream Flavor: "));
        txtFlavor = new JTextField(25);
        pnlFlavor.add(txtFlavor);
        //Stock of the ice cream
        JPanel pnlStock = new JPanel();
        pnlStock.add(new JLabel("     Current Stock: "));
        txtStock = new JTextField(25);
        pnlStock.add(txtStock);
        //Sale Price of the ice cream
        JPanel pnlSalePrice = new JPanel();
        pnlSalePrice.add(new JLabel("                Sale Price: "));
        txtSalePrice = new JTextField(25);
        pnlSalePrice.add(txtSalePrice);
        //add to pnlFields
        pnlFields.add(pnlFlavor);
        pnlFields.add(pnlStock);
        pnlFields.add(pnlSalePrice);
        //add to container
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
                        txtFlavor.setText(c.getFlavor());
                        txtStock.setText(Double.toString(c.getStockInLiters()));
                        txtSalePrice.setText(Double.toString(c.getSalePricePerLiter()));
                    } else {
                        txtFlavor.setText("");
                        txtStock.setText("");
                        txtSalePrice.setText("");
                    }
                }
            }
        });
        //update button event handler
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtFlavor.getText().isEmpty()) {
                    IceCreamFridgeItem c = (IceCreamFridgeItem) itemNames.getSelectedItem();
                    if (!c.setFlavor(txtFlavor.getText())) {
                        showErrorMessage(errorMsgFlavors);
                    }
                    if (!c.setStockWithString(txtStock.getText())) {
                        showErrorMessage(errorMsgStock);
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
                    txtFlavor.setEnabled(true);
                    txtFlavor.setText("");
                    txtStock.setEnabled(true);
                    txtStock.setText("");
                    txtSalePrice.setEnabled(true);
                    txtSalePrice.setText("");
                    btnNew.setText("add");
                    btnDelete.setEnabled(false);
                    btnUpdate.setEnabled(false);
                    txtFlavor.requestFocus();
                } else {
                    if (!txtFlavor.getText().isEmpty()) {
                        IceCreamFridgeItem c = new IceCreamFridgeItem();
                        if (!c.setFlavor(txtFlavor.getText())) {
                            showErrorMessage(errorMsgFlavors);
                        }
                        if (!c.setStockWithString(txtStock.getText())) {
                            showErrorMessage(errorMsgStock);
                        }
                        if (!c.setSalePriceWithString(txtSalePrice.getText())) {
                            showErrorMessage(errorMsgSalePrice);
                        }
                        iceCreams.addIceCream(c);
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
            txtFlavor.setEnabled(false);
            txtStock.setEnabled(false);
            txtSalePrice.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
            btnNew.doClick();

        }
    }

}
