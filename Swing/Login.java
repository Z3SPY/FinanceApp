package Swing;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Elements.HintTextField;

import java.awt.Color;
import java.awt.event.*;



public class Login extends JFrame implements ActionListener {

    float width = 500, height = 700;
    JTextField passText, userText;
    JLabel registerLabel;
    JButton submit; 
    JPanel imagePanel, inputPanel;


    public static int getDimen(float n, double d) {
        return (int) Math.round(n * d);
    }

    //Clean up Code Spacing using this
    public static int[] calculateOffSet(int size, int offSet, int origY) {
        int arrOffSet[] = new int[size];
        for (int i = 0; i < size; i++) {
            arrOffSet[i] = origY + (i * offSet);
        }
        return arrOffSet;
    }
    
    Login() {
        this.setTitle("Login Page");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);



        inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(getDimen(width, .52), 0, getDimen(width, .45), (int)height);
        inputPanel.setBackground(Color.GREEN);

        //Username Text Field
        userText = new HintTextField("Username");
        userText.setBounds(20, (getDimen(height, .05) * 0) + 10, getDimen(width, .35), getDimen(height, .05));
        userText.addActionListener(this);

        //Password Text Field
        passText = new HintTextField("Password");
        passText.setBounds(20, (getDimen(height, .05) * 1) + 10 * 2, getDimen(width, .35), getDimen(height, .05));
        passText.addActionListener(this);
        
        //Login Button
        submit = new JButton("LOGIN");
        submit.setBounds(20, (getDimen(height, .05) * 2) + 10 * 3, getDimen(width, .35), getDimen(height, .05));
        submit.addActionListener(this);

        //Register button
        registerLabel = new JLabel("Click here to register");
        registerLabel.setBounds(50, (getDimen(height, .05) * 4) + 10 * 1, getDimen(width, .35), getDimen(height, .05));
        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.out.println("Yo");
                new Register(); // Create a function that closes this jframe to open another
              }

            public void mouseEntered(MouseEvent me) {
                registerLabel.setForeground(Color.RED);

            }

            public void mouseExited(MouseEvent me) {
                registerLabel.setForeground(Color.BLACK);
            }
        });


        //Adding to panel
        inputPanel.add(passText);
        inputPanel.add(userText);
        inputPanel.add(submit);
        inputPanel.add(registerLabel);

        this.add(inputPanel);
        



        //Always at the bottom
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource() == submit) {
            if (userText.getText().length() != 0 && passText.getText().length() != 0) {
                new Dashboard();
            } else {
                JOptionPane.showMessageDialog(this, "Please Input a valid username and password.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
       

        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
