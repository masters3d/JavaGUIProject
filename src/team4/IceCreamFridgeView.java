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
    private JTextArea textArea;
    private JTextField txtFlavor;
    private JTextField txtStock;
    private JTextField txtSalePrice;
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;
    private final static String newline = "\n";
    private final JLabel nuLine = new JLabel(" ");
    private final JLabel lblTitle = new JLabel("Ice Cream Fridge Program");
    private final JLabel lblNames = new JLabel("By: Jose Jimenez, Franceska Ong, Sze Lok Tam ");
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
        // main panel
        JPanel mainPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel rightPnlFields = new JPanel();
        JPanel leftPnlFields = new JPanel();        
        //combo box
        itemNames = new JComboBox<>(iceCreams.getSortedArray());
        leftPnlFields.add(itemNames, BorderLayout.NORTH);
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

        rightPnlFields.setLayout(new BoxLayout(rightPnlFields, BoxLayout.Y_AXIS));
        leftPnlFields.setLayout(new BoxLayout(leftPnlFields, BoxLayout.Y_AXIS));
        //Flavor of the ice cream
        JPanel pnlFlavor = new JPanel();
        pnlFlavor.add(new JLabel("Ice Cream Flavor: "));
        txtFlavor = new JTextField(15);
        pnlFlavor.add(txtFlavor);
        
        //Stock of the ice cream
        JPanel pnlStock = new JPanel();
        pnlStock.add(new JLabel("     Current Stock: "));
        txtStock = new JTextField(15);
        pnlStock.add(txtStock);
        //Sale Price of the ice cream
        JPanel pnlSalePrice = new JPanel();
        pnlSalePrice.add(new JLabel("           Sale Price: "));
        txtSalePrice = new JTextField(15);
        pnlSalePrice.add(txtSalePrice);
        //add to right panel
        // set fonts for title and names
        lblTitle.setFont(new Font("Arial", Font.BOLD, 15));
        lblNames.setFont(new Font("Arial", Font.BOLD, 12));
        rightPnlFields.add(lblTitle);
        rightPnlFields.add(lblNames);
        rightPnlFields.add(nuLine);
        rightPnlFields.add(pnlFlavor);
        rightPnlFields.add(pnlStock);
        rightPnlFields.add(pnlSalePrice);
        // make textarea
        textArea = new JTextArea(10,18);
        textArea.setEditable(false);

        // add to left side
        leftPnlFields.add(textArea);
        //add to container

        
        rightPanel.add(rightPnlFields, BorderLayout.CENTER);
        
        leftPanel.add(leftPnlFields, BorderLayout.CENTER);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        container.add(mainPanel, BorderLayout.CENTER);        
        
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
                        String flavor = c.getFlavor();
                        String price = Double.toString(c.getSalePricePerLiter());
                        String stock = Double.toString(c.getStockInLiters());
                        txtFlavor.setText(flavor);
                        txtStock.setText(stock);
                        txtSalePrice.setText(price);
                        textArea.setText(null);
                        UpdateTxtArea(flavor, stock, price);
                        
                    } else {
                        txtFlavor.setText("");
                        txtStock.setText("");
                        txtSalePrice.setText("");
                        textArea.setText(null);
                    }
                }
            }
        });
        //update button event handler
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IceCreamFridgeItem c = (IceCreamFridgeItem) itemNames.getSelectedItem();
                String flavor = c.getFlavor();
                String price = Double.toString(c.getSalePricePerLiter());
                String stock = Double.toString(c.getStockInLiters());
                if (!txtFlavor.getText().isEmpty()) {

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
                    textArea.setText(null);
                    UpdateTxtArea(flavor, stock, price);
                }
            }
        });
        //new/add button event handler
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnNew.getText().equals("New")) {
                    txtFlavor.setEnabled(true);
                    txtFlavor.setText("");
                    txtStock.setEnabled(true);
                    txtStock.setText("");
                    txtSalePrice.setEnabled(true);
                    txtSalePrice.setText("");
                    btnNew.setText("Add");
                    btnDelete.setEnabled(false);
                    btnUpdate.setEnabled(false);
                    txtFlavor.requestFocus();
                } else {
                    if (!txtFlavor.getText().isEmpty() ) {
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
                    btnNew.setText("New");
                }
            }
        });
        //delete button event handler
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (itemNames.getItemCount() > 0) {
                    int answer = JOptionPane.showConfirmDialog(IceCreamFridgeView.this,
                            "Are you sure you want to delete this item?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (answer == JOptionPane.YES_OPTION) {
                        iceCreams.clearAllObjects();
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
    
    private void UpdateTxtArea(String flavor, String stock, String price)
    {
        textArea.append(newline + "Ice Cream Flavor: " + flavor + newline + newline);
        textArea.append("Current Stock Available: " + stock + newline + newline);
        textArea.append("Sale Price of item: " + price + newline + newline );
    }
    
}
