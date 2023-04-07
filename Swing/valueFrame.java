package Swing;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Elements.HintTextField;
import Pages.pageOne;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class valueFrame extends JFrame {
    //Things to add
    // AMOUNT HANDLER
    // SUBMISSION DATE
    // FROM
    // TYPE 
    // SUBMIT


    int height = 250, width = 600; 
    JComboBox typeChoice;

    JLabel amountLbl;
    HintTextField amountTextField, fromTextField;
    JButton transButton, dateButton;
    JPanel datePanel;

    public valueFrame() {
        this.setTitle("Transaction Information");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        typeChoice = new JComboBox<>(trnsType.values());
        typeChoice.setBounds(300, 0, 300, 50);

        fromTextField = new HintTextField("Water Inc.");
        fromTextField.setBounds(300, 50, 300, 50);


        dateButton = new JButton("Select a Date");
        dateButton.setBounds(300, 100, 300, 50);


        JFrame selfFrame = this;
        JDateChooser dateChooser = new JDateChooser();
        dateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get selected date from date picker
                String message = "Choose a date";

                Object[] params = {message, dateChooser};
                JOptionPane.showConfirmDialog(null,params,"Start date", JOptionPane.PLAIN_MESSAGE);

                // Format selected date
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d, yyyy");
                try {
                    String formattedDate = formatter.format(((JDateChooser)params[1]).getDate()) ;
                    JOptionPane.showMessageDialog(selfFrame, "You picked " + formattedDate);
                    dateButton.setText(formattedDate);
                } catch (Exception exc) {
                    System.out.println(exc);
                }


                

            }
        });


        amountTextField = new HintTextField("-1000.00");
        amountTextField.setBounds(0, 0, 300, 150);
        amountTextField.setForeground(Color.green);
        amountTextField.setFont(new Font("Arial", Font.BOLD, 40));


        //Amount Text Field Listener
        amountTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                //System.out.println("Insert");
                if (amountTextField.getText().charAt(0) == '-') {
                    amountTextField.setForeground(Color.RED);
                } else {
                    amountTextField.setForeground(Color.GREEN);
                }

                amountTextField.repaint();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //System.out.println("Remove");
                if (amountTextField.getText().length() != 0) {
                    if (amountTextField.getText().charAt(0) == '-') {
                        amountTextField.setForeground(Color.RED);
                    } else {
                        amountTextField.setForeground(Color.GREEN);
                    }
                }
                    
                amountTextField.repaint();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("Change");

            }
            
        });


        //Transaction Button Logic For Communicating With PageOne Class
        transButton = new JButton("Finish Transaction");
        transButton.setBounds(200, 150, 200, 50);

        transButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                

                if (amountTextField.getText() != "") {

                    //Handles Amount 
                    //Checks if Value has negative in first character
                    Boolean isAddition = false;
                    String amtString = amountTextField.getText(); // Base Text
                    if (amtString.charAt(0) == '-') { isAddition = false;} else {
                        isAddition = true;
                    }
                    Double value = (amtString != null) ? Double.parseDouble(amtString.replaceAll("[^0-9.]", "")) : 0.00;// Filtered String
                    //Amount Script End

                    //Handles Date
                    String myDate =  dateButton.getText();
                    //Date Script End


                    //Handles From
                    String fromName = fromTextField.getText();
                    //From Script End

                    //Handles Type
                    trnsType typeValue = (trnsType) typeChoice.getSelectedItem();
                    //Type Script End
                    
                    if (amtString != "" && myDate != "Select a Date" && fromName != "") {
                        try {
                            pageOne.transactionLogic(isAddition, value, myDate, fromName, typeValue);
                            pageOne.selectionState(false);
                            dispose();
                        } catch (Exception exc) {
                            System.out.println(exc);
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(selfFrame, "Please Provide All Proper Inputs (HINT: Check Date, Value, and From Values).", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(selfFrame, "Please Provide All Proper Inputs (HINT: Check Date, Value, and From Values).", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        



        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (e != null) {
                    pageOne.selectionState(false);
                }
            }
        });


        //Adding Values
        this.add(typeChoice);
        this.add(fromTextField);
        this.add(dateButton);
        this.add(dateChooser);

        this.add(amountTextField);

        this.add(transButton);


        // Last Call
        this.setVisible(true);
    }

    

    public enum trnsType {
        UTILITIES,
        FOOD,
        HOBBIES,
        ACTIVITIES,
        EXPENSES,
        BANK,
        BUSINESS,
        MISC
    }

}
