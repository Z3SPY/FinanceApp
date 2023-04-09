package Pages;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Swing.Login;

public class pageThree extends JPanel{
    
    JTextPane aboutCompany;
    JLabel title;

    //reminder Should make an Interface
    public pageThree(int width, int height) {
        this.setBackground(Color.ORANGE);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
        this.setLayout(null);

        aboutCompany = new JTextPane();
        aboutCompany.setEditable(false);
        StyledDocument doc = aboutCompany.getStyledDocument();
        SimpleAttributeSet fill = new SimpleAttributeSet();
        StyleConstants.setAlignment(fill, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setLineSpacing(fill, 1f);
        doc.setParagraphAttributes(0, doc.getLength(), fill, false);
        aboutCompany.setText("Howdy! I'm a simple farmer who finds joy in the great outdoors. I spend most of my days tending to the land and animals, but I also like to take long walks in the fields and forests around my farm. As much as I love the solitude of farm life, I'm also hoping to meet some new friends who share my love for nature and the simple pleasures in life. There's nothing like a good conversation over a cup of coffee or a shared meal made with fresh ingredients straight from the farm. So if you're looking for a down-to-earth friend to explore the outdoors with, I'm your guy!        ");
        aboutCompany.setBackground(Color.ORANGE);
        aboutCompany.setFont(new Font("SANS", Font.PLAIN, 16));
        aboutCompany.setBounds(25, 250, 700, 400);

        title = new JLabel("ABOUT THE COMPANY"); 
        title.setFont(new Font("SANS", Font.BOLD, 18));
        title.setBounds(25, 160, width, 100);
        
        
        this.add(title);
        this.add(aboutCompany);
    }
}
