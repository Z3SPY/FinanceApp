package Pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

//import com.mysql.cj.x.protobuf.MysqlxNotice.Frame.Scope;
//import com.mysql.cj.xdevapi.JsonArray;
import javax.swing.JLayeredPane;

import Elements.card;

import java.util.ArrayList;

import Swing.Login;

public class pageTwo extends JPanel{

    List<article> articles = new ArrayList<article>(10);
    JScrollPane scroll;

    public pageTwo(int width, int height) {
        this.setBackground(Color.pink);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
        this.setLayout(new FlowLayout());
        

        //First Articles 
        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(width, 2048));
        content.setBounds(0, 0, width, 200);
        content.setLayout(null);
        //Card 
        int cardH = 200;
        for (int i = 0; i < 7; i++) {
            card contentCard = new card(50, 50 + ((cardH + 10)*i), width - 275, cardH, Color.CYAN);    
            articles.add( new article(contentCard, "This is The Title", "This is the content")); //Adds Article to list

        } 
        

        //Article End
        for (article a : articles) {
            content.add(a.atclCont); // Adding to card
        }   
        //Card End

        articles.get(0).setTitle("Woobi");
       
       

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBounds( 80, 0, 800 , height);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

 
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setPreferredSize(new Dimension(1000, height)); // Not Dynamic

        layeredPane.add(scroll, 1);

        this.add(layeredPane, BorderLayout.WEST);

    }
}

class article {
    card atclCont;
    String title;
    String value;
    JLabel jTitle, jValue;

    article(card cardHolder, String atclTitle, String atclValue ) {
        this.atclCont = cardHolder;
        this.title = atclTitle;
        this.value = atclValue;

        jTitle = new JLabel(atclTitle);
        jTitle.setFont(new Font("SANS",Font.BOLD, 16));
        jTitle.setForeground(Color.WHITE);

        jValue = new JLabel(atclValue);
        jValue.setFont(new Font("SANS", Font.BOLD, 14));

        atclCont.setInnerCard(0, 0);
        atclCont.CreateCard(0, 0, atclCont.getWidth(), Login.getDimen((float) atclCont.getHeight(), 0.25), null); // Index 0
        atclCont.getPanel(0).add(jTitle, BorderLayout.WEST);
        atclCont.getPanel(0).setBackground(Color.BLUE);

        atclCont.setInnerCard(0, 0);
        atclCont.CreateCard(0,  Login.getDimen((float) atclCont.getHeight(), 0.25), atclCont.getWidth(), Login.getDimen((float) atclCont.getHeight(), 0.75), null);
        atclCont.getPanel(1).add(jValue);
        atclCont.getPanel(1).setBackground(Color.PINK);

        atclCont.CreateCard(0, Login.getDimen((float) atclCont.getHeight(), 0.25), atclCont.getWidth(), Login.getDimen((float) atclCont.getHeight(), 0.75) , null);
        
    }

    public void setTitle(String myString) {
        jTitle.setText(myString);
    }

    public void setValue(String myString) {
        jValue.setText(myString);
    }
}
