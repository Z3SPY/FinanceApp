package Pages;

import java.awt.Color;

import javax.swing.JPanel;

import Swing.Login;

public class pageFour extends JPanel {
    public pageFour(int width, int height) {
        this.setBackground(Color.PINK);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
    }
}
