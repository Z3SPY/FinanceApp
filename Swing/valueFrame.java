package Swing;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Elements.HintTextField;
import Pages.pageOne;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

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
    HintTextField amountTextField, fromTextField, dateTextField;
    JButton transButton;

    public valueFrame() {
        this.setTitle("Profile Information");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        typeChoice = new JComboBox<>(trnsType.values());
        typeChoice.setBounds(300, 0, 300, 50);

        fromTextField = new HintTextField("Water Inc.");
        fromTextField.setBounds(300, 50, 300, 50);

        dateTextField = new HintTextField("18 MAR 22");
        dateTextField.setBounds(300, 100, 300, 50);

        amountTextField = new HintTextField("-1000.00");
        amountTextField.setBounds(0, 0, 300, 150);
        amountTextField.setForeground(Color.green);
        amountTextField.setFont(new Font("Arial", Font.BOLD, 40));

        transButton = new JButton("Finish Transaction");
        transButton.setBounds(200, 150, 200, 50);
        

        transButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                

                //Handles Amount 
                //Checks if Value has negative in first character
                Boolean isAddition = false;
                String amtString = amountTextField.getText(); // Base Text
                if (amtString.charAt(0) == '-') { isAddition = false;} else {
                    isAddition = true;
                }

                Double value = Double.parseDouble(amtString.replaceAll("[^0-9]", ""));// Filtered String
                //Amount Script End

                //Handles Date
                String myDate = dateTextField.getText();
                //Date Script End


                //Handles From
                String fromName = fromTextField.getText();
                //From Script End


                //Handles Type
                trnsType typeValue = (trnsType) typeChoice.getSelectedItem();
                //Type Script End


                pageOne.transactionLogic(isAddition, value, myDate, fromName, typeValue);
                pageOne.selectionState(false);

                dispose();
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
        this.add(dateTextField);

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
