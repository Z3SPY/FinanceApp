package Pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

    List<card> articles = new ArrayList<card>(10);
    JScrollPane scroll;

    public pageTwo(int width, int height) {
        this.setBackground(Color.pink);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
        this.setLayout(new FlowLayout());
        
        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(width, 2048));
        content.setBounds(0, 0, width, 200);

        content.setLayout(null);
        //Card 
        card contentCard = new card(50, 50, width - 275, 100, Color.CYAN);  
        contentCard.setBackground(Color.YELLOW);



        content.add(contentCard); // Adding to card
        //Card End
       

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBounds( 80, 0, 800, height);
 
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setPreferredSize(new Dimension(1000, height)); // Not Dynamic

        layeredPane.add(scroll, 1);

        this.add(layeredPane, BorderLayout.WEST);

    }
}
