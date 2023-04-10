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

import org.jfree.util.Log;

import Swing.Login;

public class pageThree extends JPanel{
    
    JTextPane aboutCompany;
    JLabel title;
    ImageIcon design;

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
        aboutCompany.setText("We are a company that specializes in providing financial convenience to farmers. Our team consists of experienced financial experts who understand the unique challenges that farmers face when managing their finances. We offer tailored financial solutions to help farmers manage their cash flow, secure financing for equipment and land purchases, and navigate the complexities of agricultural finance. " +
        "In addition to our financial expertise, we prioritize making financial management as simple and convenient as possible for our customers. We offer a range of services, including online banking, mobile apps, and automated payment systems. We believe in building strong relationships with our customers and taking the time to understand their unique financial situations. Whether you're a small family farm or a large commercial operation, we're committed to helping you succeed.");

        aboutCompany.setBackground(Color.ORANGE);
        aboutCompany.setFont(new Font("SANS", Font.PLAIN, 14));
        aboutCompany.setBounds(25, 275, 700, 400);

        title = new JLabel("ABOUT THE COMPANY"); 
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SANS", Font.BOLD, 18));
        title.setBounds(25, 200, width, 100);

        design =  new ImageIcon(new ImageIcon("App-Images/Garden.png").getImage().getScaledInstance(width, Login.getDimen(height, .50), Image.SCALE_DEFAULT));
        JLabel topDesign = new JLabel(design);
        topDesign.setBounds(0, 0, width, Login.getDimen(height, .35));
        
        
        this.add(title);
        this.add(aboutCompany);
        this.add(topDesign);
    }
}
