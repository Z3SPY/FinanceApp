package Events;

import Elements.card;
import Swing.Login;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class article {
    public card atclCont;
    String title;
    String value;
    JLabel jTitle;
    JTextPane jValue;

    //Animation Values
    Boolean boolEnter;
    Boolean boolExit;

    Timer timeEnter =  new Timer(0, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            atclCont.getPanel(0).setLocation(atclCont.getPanel(0).getLocation().x, atclCont.getPanel(0).getLocation().y + (int)(1 * 1.2));
            atclCont.getPanel(1).setLocation(atclCont.getPanel(1).getLocation().x, atclCont.getPanel(1).getLocation().y + (int)(1 * 1.5));
            jTitle.setFont(new Font("Sans", Font.BOLD, 20));

            if (atclCont.getPanel(0).getLocation().y > 10 &&  atclCont.getPanel(1).getLocation().y > 50) {
                timeEnter.stop();
                boolEnter = false;
            }

        }}
    );

    Timer timeExit  = new Timer(0, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            atclCont.getPanel(0).setLocation(atclCont.getPanel(0).getLocation().x, atclCont.getPanel(0).getLocation().y - (int)(1 * 1.2));
            atclCont.getPanel(1).setLocation(atclCont.getPanel(1).getLocation().x, atclCont.getPanel(1).getLocation().y - (int)(1 * 1.5));
            jTitle.setFont(new Font("Sans", Font.BOLD, 16));

            if (atclCont.getPanel(0).getLocation().y <= 0 && atclCont.getPanel(1).getLocation().y <= 100) {
                timeExit.stop();
                boolExit = false;
            }

        }}
    );

    public article(card cardHolder, String atclTitle, String atclValue) {
        this.atclCont = cardHolder;
        this.title = atclTitle;
        this.value = atclValue;
        boolEnter = false;
        boolEnter = false;

        //Title Of Article
        jTitle = new JLabel(atclTitle.toUpperCase());
        jTitle.setFont(new Font("SANS",Font.BOLD, 16));
        jTitle.setBounds(10,10, atclCont.getWidth(), Login.getDimen((float) atclCont.getHeight(), 0.10));
        jTitle.setForeground(Color.WHITE);
        //Title Of Article End

        //Value Of Article
        jValue = new JTextPane();

        //Fixing Wrapping and Justification
        jValue.setEditable(false);
        StyledDocument doc = jValue.getStyledDocument();
        SimpleAttributeSet fill = new SimpleAttributeSet();
        StyleConstants.setAlignment(fill, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setLineSpacing(fill, 1f);
        doc.setParagraphAttributes(0, doc.getLength(), fill, false);
        jValue.setText(atclValue);
        jValue.setBackground(Login.colorScheme2[4]);

        jValue.setFont(new Font("SANS", Font.BOLD, 14));
        jValue.setBounds(10,10, atclCont.getWidth() - 20,Login.getDimen((float) atclCont.getHeight(), 0.50));
        //Value Of Article End

        atclCont.setInnerCard(20, 20);
        atclCont.CreateCard(0, 10, atclCont.getWidth(), Login.getDimen((float) atclCont.getHeight(), 0.25), null);// Index 0
        atclCont.getPanel(0).setLayout(null);
        atclCont.getPanel(0).add(jTitle);
        atclCont.getPanel(0).setBackground(Login.colorScheme2[1]);

        atclCont.setInnerCard(20, 20);
        atclCont.CreateCard(0,  Login.getDimen((float) atclCont.getHeight(), 0.25), atclCont.getWidth(), Login.getDimen((float) atclCont.getHeight(), 0.75), null);
        atclCont.getPanel(1).setLayout(null);
        atclCont.getPanel(1).add(jValue);
        atclCont.getPanel(1).setBackground(Login.colorScheme2[4]);


        //Initialize Mouse Listener for animation
        MouseListener selectAnim = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (boolEnter == false) {
                    boolEnter = true;
                    timeEnter.start();
                }
                timeExit.stop();
                boolExit = false;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (boolExit == false) {
                    boolExit = true;
                    timeExit.start();
                }

                timeEnter.stop();
                boolEnter = false;
            }
        };

        atclCont.addMouseListener(selectAnim);
        jValue.addMouseListener(selectAnim);

    }




    //Animation Values End

    public void setTitle(String myString) {
        jTitle.setText(myString.toUpperCase());
    }

    public void setValue(String myString) {
        jValue.setText(myString);
    }
}
