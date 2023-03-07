package Pages;

import java.awt.Color;

import javax.swing.JPanel;

import Swing.Login;

public class pageTwo extends JPanel{

    public pageTwo(int width, int height) {
        this.setBackground(Color.RED);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
    }
}
