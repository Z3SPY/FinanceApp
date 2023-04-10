package Swing;
import javax.lang.model.element.Element;
import javax.swing.*;
import Database.SQLiteDB;
import Elements.HintTextField;

import java.awt.Color;
import java.awt.Font;




import java.awt.event.*;
import java.sql.SQLException;

public class Register extends JFrame implements ActionListener {

    
    JLabel descLabel, rtrnLgn; 
    HintTextField pasTxtFld, cnfPasTxtFld,
    usrNmTxtFld, emlTxtFld;
    JButton crtAcBut;
    String uName, email, pass, confirm;

    SQLiteDB DB = new SQLiteDB();
    float width = 500, height = 600;

    Register() throws SQLException, ClassNotFoundException {
        this.setTitle("Registration Page");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        //Input Values
        int spaceDiff[] = Login.calculateOffSet(4, 40, Login.getDimen(height, .10));

        usrNmTxtFld = new HintTextField("Enter Username");
        emlTxtFld = new HintTextField("Enter Email");
        pasTxtFld = new HintTextField("Enter Password");
        cnfPasTxtFld = new HintTextField("Confirm Password");
        crtAcBut = new JButton("CREATE AN ACCOUNT");
        rtrnLgn = new JLabel("Already have an account"); // Note Create a class that does clickable Jlabel


        //Component Spacing
        usrNmTxtFld.setBounds(60, spaceDiff[0], (int) width - 120 , Login.getDimen( height, .04));
        emlTxtFld.setBounds(60, spaceDiff[1], (int) width - 120 , Login.getDimen( height, .04));
        pasTxtFld.setBounds(60, spaceDiff[2], (int) width - 120 , Login.getDimen( height, .04));
        cnfPasTxtFld.setBounds(60, spaceDiff[3], (int) width - 120 , Login.getDimen( height, .04));
        crtAcBut.setBounds(70, (int)height - 120, (int) width - 140, Login.getDimen( height, .07));
        crtAcBut.addActionListener(this);
        rtrnLgn.setBounds(160, (int)height - 80, (int) width - 140, Login.getDimen( height, .07));

        KeyListener listener =  new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    crtAcBut.doClick();
                }
            }
        };

        crtAcBut.addKeyListener(listener);
        usrNmTxtFld.addKeyListener(listener);
        emlTxtFld.addKeyListener(listener);
        pasTxtFld.addKeyListener(listener);
        cnfPasTxtFld.addKeyListener(listener);

        //Component Properties
        rtrnLgn.setFont(new Font("Arial", Font.BOLD, 16));
        rtrnLgn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.out.println("Yo");
                Login.closeRegistration();
                dispose(); // Create a function that closes this jframe to open another
            }

            public void mouseEntered(MouseEvent me) {
                rtrnLgn.setForeground(Color.RED);

            }

            public void mouseExited(MouseEvent me) {
                rtrnLgn.setForeground(Color.BLACK);
            }
        });


        this.add(usrNmTxtFld);
        this.add(emlTxtFld);
        this.add(pasTxtFld);
        this.add(cnfPasTxtFld);
        this.add(crtAcBut);
        this.add(rtrnLgn);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (e != null) {
                    Login.closeRegistration();

                }
            }
        });


        //Always at the bottom
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Variables
        uName = usrNmTxtFld.getText();
        email = emlTxtFld.getText();
        pass = pasTxtFld.getText();
        confirm = cnfPasTxtFld.getText();
        if (e.getSource() == crtAcBut) {
            if (usrNmTxtFld.getText().length() != 0 && pasTxtFld.getText().length() != 0&& emlTxtFld.getText().length() != 0&& cnfPasTxtFld.getText().length() != 0) {
                try {
                    if(!DB.isUserExists(uName)){
                        if (!pasTxtFld.getText().equals(cnfPasTxtFld.getText())) {
                            JOptionPane.showMessageDialog(this, "Passwords don't match.", "Error", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        else {
                            try {
                                DB.addUser(uName, email, pass);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            } catch (ClassNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                            Login.closeRegistration();
                            dispose();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Username Taken", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Please Input a valid username and password.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

        }
}


}
