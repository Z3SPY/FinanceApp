package Pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Swing.Login;

public class pageFour extends JPanel {

    JLabel welcomeText, title;
    JTextPane aboutME;
    ImageIcon profileCircle;


    public pageFour(int width, int height) {
        this.setBackground(Login.colorScheme2[1]);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
        this.setLayout(null);
        
        profileCircle = new ImageIcon(new ImageIcon("App-Images/circle.png").getImage().getScaledInstance(96,96, Image.SCALE_DEFAULT));

        JLabel myEmail =  new JLabel("Email: " + Login.myProfile.getEmail());
        myEmail.setFont(new Font("SANS", Font.BOLD, 16));
        myEmail.setBounds(170, 50, 500, 100);


        JLabel myUser  = new JLabel("User Name: " + Login.myProfile.getUser());
        myUser.setFont(new Font("SANS", Font.BOLD, 16));
        myUser.setBounds(170, 75, 500, 100);


        JLabel profilePic = new JLabel(profileCircle);
        profilePic.setBounds(50, 50, 100, 100);

        aboutME = new JTextPane();
        aboutME.setEditable(false);
        StyledDocument doc = aboutME.getStyledDocument();
        SimpleAttributeSet fill = new SimpleAttributeSet();
        StyleConstants.setAlignment(fill, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setLineSpacing(fill, 1f);
        doc.setParagraphAttributes(0, doc.getLength(), fill, false);
        aboutME.setText("Howdy! I'm a simple farmer who finds joy in the great outdoors. I spend most of my days tending to the land and animals, but I also like to take long walks in the fields and forests around my farm. As much as I love the solitude of farm life, I'm also hoping to meet some new friends who share my love for nature and the simple pleasures in life. There's nothing like a good conversation over a cup of coffee or a shared meal made with fresh ingredients straight from the farm. So if you're looking for a down-to-earth friend to explore the outdoors with, I'm your guy!        ");
        aboutME.setBackground(Login.colorScheme2[1]);
        aboutME.setForeground(Color.WHITE);
        aboutME.setBounds(25, 250, 700, 500);

        title = new JLabel("ABOUT MYSELF");
        title.setFont(new Font("SANS", Font.BOLD, 24));
        title.setBounds(25, 160, 200, 100);


        this.add(profilePic);
        this.add(myUser);
        this.add(myEmail);
        this.add(aboutME);
        this.add(title);
        
    }
}
