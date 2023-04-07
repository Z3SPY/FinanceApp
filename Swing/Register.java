package Swing;
import javax.lang.model.element.Element;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Elements.HintTextField;

import java.awt.Color;
import java.awt.Font;




import java.awt.event.*;

public class Register extends JFrame implements ActionListener {  

    
    JLabel descLabel, rtrnLgn; 
    HintTextField pasTxtFld, cnfPasTxtFld,
    usrNmTxtFld, emlTxtFld;
    JButton crtAcBut;

    float width = 500, height = 600;

    Register() {
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
        rtrnLgn.setBounds(160, (int)height - 80, (int) width - 140, Login.getDimen( height, .07));


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
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

   
   


}
