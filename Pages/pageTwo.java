package Pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.Timer;


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
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(width, 2048));
        content.setBounds(0, 0, width, 200);
        content.setLayout(null);
        //Card 
        int cardH = 200;
        for (int i = 0; i < 7; i++) {
            card contentCard = new card(50, 50 + ((cardH + 10)*i), width - 275, cardH, null);    
            articles.add( new article(contentCard, "This is The Title", "This is the content")); //Adds Article to list
        } 

        

        //Article End
        //Article Animations
       
        for (article a : articles) {

            content.add(a.atclCont); // Adding to card
           
        }   
        //Card End


        //Setup Fake Articles
        articles.get(0).setTitle("Your Agricultural Upstart");
        articles.get(0).setValue("Are you passionate about agriculture and looking to start your own agribusiness? " +
        "Starting an agricultural upstart can be a rewarding and fulfilling venture, but it requires careful planning and preparation. " +
        "Here are some steps to follow to make a successful agricultural upstart.");

        articles.get(1).setTitle("Identify a need or opportunity");
        articles.get(1).setValue("Start by identifying a need or opportunity in the agriculture industry." +
        " This could be a gap in the market, an unmet demand, or a problem that needs solving.");

        articles.get(2).setTitle("Research the market");
        articles.get(2).setValue("Conduct market research to gain a better understanding of the industry and "+
        "the potential customers. This will help you identify the target audience, the competitors, and the current trends.");


        articles.get(3).setTitle("Develop a business plan ");
        articles.get(3).setValue("Develop a comprehensive business plan that outlines the goals, objectives, and strategies of"+
        " your upstart. This plan should include a detailed financial analysis, marketing plan, and operational plan.");

        articles.get(4).setTitle("Choose a legal structure");
        articles.get(4).setValue("Decide on the legal structure of your upstart, such as sole proprietorship, partnership, " +
        "or limited liability company (LLC). This will depend on your personal preferences, financial situation, and the type of business you want to start.");

        articles.get(5).setTitle("Secure funding");
        articles.get(5).setValue("Determine the amount of funding you will need to start and grow your upstart. This may" + 
        " include personal savings, loans, grants, or investments from partners or investors.");

        articles.get(6).setTitle("Start growing");
        articles.get(6).setValue("Start growing your crops, raising your animals, or producing your agricultural products. Monitor your progress, "+
        "adjust your strategies as needed, and continue to innovate and improve....Read");
       
       

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

    article(card cardHolder, String atclTitle, String atclValue ) {
        this.atclCont = cardHolder;
        this.title = atclTitle;
        this.value = atclValue;
        boolEnter = false;
        boolEnter = false;

        //Title Of Article  
        jTitle = new JLabel(atclTitle.toUpperCase());
        jTitle.setFont(new Font("SANS",Font.BOLD, 16));
        jTitle.setBounds(10,10, atclCont.getWidth(),Login.getDimen((float) atclCont.getHeight(), 0.10));
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
        jValue.setBackground(Color.PINK);
        
        jValue.setFont(new Font("SANS", Font.BOLD, 14));
        jValue.setBounds(10,10, atclCont.getWidth() - 20,Login.getDimen((float) atclCont.getHeight(), 0.50));
        //Value Of Article End

        atclCont.setInnerCard(20, 20);
        atclCont.CreateCard(0, 10, atclCont.getWidth(), Login.getDimen((float) atclCont.getHeight(), 0.25), null);// Index 0
        atclCont.getPanel(0).setLayout(null);
        atclCont.getPanel(0).add(jTitle);
        atclCont.getPanel(0).setBackground(Color.BLUE);

        atclCont.setInnerCard(20, 20);
        atclCont.CreateCard(0,  Login.getDimen((float) atclCont.getHeight(), 0.25), atclCont.getWidth(), Login.getDimen((float) atclCont.getHeight(), 0.75), null);
        atclCont.getPanel(1).setLayout(null);
        atclCont.getPanel(1).add(jValue);
        atclCont.getPanel(1).setBackground(Color.PINK);

       
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
