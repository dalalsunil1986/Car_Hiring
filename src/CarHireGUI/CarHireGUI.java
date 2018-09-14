/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarHireGUI;

/**
 * * Supplied GUI code for ass2, coit11222, T2 2018 * You are going to complete
 * the code for each required method * *
 */
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

// class definition 
public class CarHireGUI extends JFrame implements ActionListener {
    //GUI components building        

    private JLabel nameLabel = new JLabel("Customer name");
    private JLabel licenseLabel = new JLabel("License number");
    private JLabel daysLabel = new JLabel("Hired days");
    private JTextField nameField = new JTextField(28);
    private JTextField licenseField = new JTextField(14);
    private JTextField daysField = new JTextField(7);
    private JButton enterButton = new JButton("Enter");
    private JButton displayButton = new JButton("Display");
    private JButton searchButton = new JButton("Search");
    private JButton exitButton = new JButton("Exit");
    private JTextArea textArea = new JTextArea(16, 38);
    private JScrollPane scrollPane; // scroll pane for the text area  
    CarHire carhire = new CarHire();
    private int currentCustomer = 0;
    private static final int FRAME_WIDTH = 490;// window size       
    private static final int FRAME_HEIGHT = 430;
    //Constructs        
    int max_num = 10;
    private CarHire[] carHireArray = new CarHire[max_num];

    public CarHireGUI() {
        super("  XYZ Car Hire App   ");

        setLayout(new FlowLayout()); //FlowLayout            
        add(nameLabel);              //add componts to JFrame           
        add(nameField);
        add(licenseLabel);
        add(licenseField);
        add(daysLabel);
        add(daysField);
        scrollPane = new JScrollPane(textArea);  // add text area to the scroll pane     
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // just need vertical scrolling        
        add(scrollPane);
        add(enterButton);
        add(displayButton);
        add(searchButton);
        add(exitButton);
        add(textArea);
        enterButton.addActionListener(this);  //listen to event           
        displayButton.addActionListener(this);
        searchButton.addActionListener(this);
        exitButton.addActionListener(this);

    }
    //event handling method        

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionString = e.getActionCommand();
        switch (actionString) {
            case "Enter":
                enterData();
                break;
            case "Display":
                displayAll();
                break;
            case "Search":
                search();
                break;
            case "Exit":
                exit();
                break;
            default:
                System.out.println("invalid input");
        }
    }

    // process input data        
    public void enterData() {
        try{
        if (currentCustomer < 10) {
            if (nameField.getText().compareTo("") == 0) {
                String message = "You must enter a customer name";
                JOptionPane.showMessageDialog(this, message, "XYZ Car Hire App",
                        JOptionPane.ERROR_MESSAGE);
                nameField.requestFocus();
            } else if (licenseField.getText().compareTo("") == 0) {
                String message = "You must enter a license number";
                JOptionPane.showMessageDialog(this, message, "XYZ Car Hire App",
                        JOptionPane.ERROR_MESSAGE);
                licenseField.requestFocus();
            } else if (daysField.getText().compareTo("") == 0) {
                String message = "You must enter days hired";
                JOptionPane.showMessageDialog(this, message, "XYZ Car Hire App",
                        JOptionPane.ERROR_MESSAGE);
                daysField.requestFocus();
            } else {

                String customerName = nameField.getText();
                carhire.setCustomerName(customerName);

                String licenseNumber = licenseField.getText();
                carhire.setLicenseNumber(licenseNumber);

                int days = Integer.parseInt(daysField.getText());
                carhire.setDays_hired(days);

                carHireArray[currentCustomer] = new CarHire(customerName, licenseNumber, days);
                double rental = carHireArray[currentCustomer].calculateHireRental();

                displayHeading();
                textArea.append(String.format("%-23s %-25s %-28s %-26s %n", customerName, licenseNumber, days, rental));
                currentCustomer++;
            }
        } else {

            String message = "You can not entered more then ten values";
            JOptionPane.showMessageDialog(this, message, "XYZ Car Hire App",
                    JOptionPane.ERROR_MESSAGE);

        }
        }catch(NumberFormatException e){
            String message = "You must enter a Integer value of days hired";
                JOptionPane.showMessageDialog(this, message, "XYZ Car Hire App",
                        JOptionPane.ERROR_MESSAGE);
        }

        nameField.setText("");
        licenseField.setText("");
        daysField.setText("");

    }

    // Display all bookings  
    public void displayAll() {
        if (currentCustomer != 0) {

            double total_days = 0;
            double total_rantal = 0;
            displayHeading();

            for (int i = 0; i < currentCustomer; i++) {
                textArea.append(String.format("%-23s %-25s %-28s %-26s %n", carHireArray[i].getCustomerName(), carHireArray[i].getLicenseNumber(), carHireArray[i].getDays_hired(), carHireArray[i].calculateHireRental()));
                total_days += carHireArray[i].getDays_hired();
                total_rantal += carHireArray[i].calculateHireRental();
            }

            textArea.append("\n-----------------------------------------------------------------------------------------------------\n");
            textArea.append(currentCustomer + " Entries\n");
            textArea.append("Average days hired: " + (total_days / currentCustomer) + "\n");
            textArea.append("Total rantal received: " + total_rantal);

        } else {
            JOptionPane.showMessageDialog(this, "No customer entered", "XYZ Car Hire App",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
    //search a particular customer booking     

    public void search() {
        boolean responce = true;
        String customerName = null;

        customerName = JOptionPane.showInputDialog("Enter a customer name");
        displayHeading();

        for (int i = 0; i < currentCustomer; i++) {
            if (customerName.equals(carHireArray[i].getCustomerName())) {
                textArea.append(String.format("%-23s %-25s %-28s %-26s %n", carHireArray[i].getCustomerName(), carHireArray[i].getLicenseNumber(), carHireArray[i].getDays_hired(), carHireArray[i].calculateHireRental()));
                responce = false;
            } 
        }
        if (responce) {
            JOptionPane.showMessageDialog(this, "No such a customer found!", "XYZ Car Hire App",
                    JOptionPane.ERROR_MESSAGE);
        }
        nameField.setText("");
        licenseField.setText("");
        daysField.setText("");

    }//end of method 
    // exit the app    

    public void exit() {
        JOptionPane.showMessageDialog(this, "Thank you for using the XYZ Car Hire App", "XYZ Car Hire App",
                JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }        //helper metho dto display heading       

    private void displayHeading() {
        textArea.setText(String.format("%-23s %-25s %-28s %-26s %n", "Customer name", "License number", "Days hired", "Rental"));
        textArea.append("-----------------------------------------------------------------------------------------------------\n");
    }

    //main method       
    public static void main(String[] args) {
        JFrame frame = new CarHireGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}// end of class definition

