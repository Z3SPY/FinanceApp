package Pages;

import java.awt.Color;

import javax.swing.JPanel;

import Swing.Login;

public class pageThree extends JPanel{
    
    //reminder Should make an Interface
    public pageThree(int width, int height) {
        this.setBackground(Color.ORANGE);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
    }
}
