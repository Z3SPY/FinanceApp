package Pages;

import java.awt.Color;

import javax.swing.JPanel;

import Swing.Login;

public class pageFive extends JPanel {
    public pageFive(int width, int height) {
        this.setBackground(Color.BLACK);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
        
        
    }
}