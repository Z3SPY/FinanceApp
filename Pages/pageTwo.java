package Pages;

import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;

import Elements.card;

import java.util.ArrayList;

import Swing.Login;

public class pageTwo extends JPanel{

    List<card> articles = new ArrayList<card>();

    public pageTwo(int width, int height) {
        this.setBackground(Color.RED);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
    }
}
