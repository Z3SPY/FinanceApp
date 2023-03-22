package Pages;

import java.awt.Color;

import javax.swing.JPanel;

import Swing.Login;

public class pageOne extends JPanel{

    public pageOne(int width, int height) {
        this.setBackground(Color.BLUE);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
    }
}


