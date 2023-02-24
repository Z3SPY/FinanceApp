import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.*;



public class Login extends JFrame implements ActionListener, FocusListener {

    float width = 500, height = 700;
    JTextField passText, userText;
    JLabel registerLabel;
    JButton submit; 
    JPanel imagePanel, inputPanel;


    public int getDimen(float n, double d) {
        return (int) Math.round(n * d);
    }
    
    Login() {
        this.setTitle("Login and Registration Page");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);



        inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(getDimen(width, .52), 0, getDimen(width, .45), (int)height);
        inputPanel.setBackground(Color.GREEN);


        userText = new HintTextField("Username");
        userText.setBounds(20, (getDimen(height, .05) * 0) + 10, getDimen(width, .35), getDimen(height, .05));

        passText = new HintTextField("Password");
        passText.setBounds(20, (getDimen(height, .05) * 1) + 10 * 2, getDimen(width, .35), getDimen(height, .05));

        submit = new JButton("Login");
        submit.setBounds(20, (getDimen(height, .05) * 2) + 10 * 3, getDimen(width, .35), getDimen(height, .05));

        registerLabel = new JLabel("Click here to register");
        registerLabel.setBounds(50, (getDimen(height, .05) * 4) + 10 * 1, getDimen(width, .35), getDimen(height, .05));
        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.out.println("Yo");
              }

            public void mouseEntered(MouseEvent me) {
                registerLabel.setForeground(Color.RED);

            }

            public void mouseExited(MouseEvent me) {
                registerLabel.setForeground(Color.BLACK);
            }
        });


        
        inputPanel.add(passText);
        inputPanel.add(userText);
        inputPanel.add(submit);
        inputPanel.add(registerLabel);

        this.add(inputPanel);
        



        //Always at the bottom
        this.setVisible(true);
    }

    @Override
    public void focusGained(FocusEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'focusGained'");
    }

    @Override
    public void focusLost(FocusEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'focusLost'");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
