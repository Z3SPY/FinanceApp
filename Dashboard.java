import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Dashboard extends JFrame {
    float width = 1000, height = 800;

    JPanel sideNav, mainPanel;
    navItem financeItem, newsItem, connectItem;

    Dashboard() {
        this.setTitle("Application Dashboard");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);


        //Side Nav
        sideNav = new JPanel(); 
        sideNav.setBackground(Color.black);
        sideNav.setBounds(0, 0, Login.getDimen(width, .25), (int)height);
        sideNav.setLayout(new GridLayout(10,1, 1 ,1));

        //Side Nav Components   
        financeItem = new navItem("Finances");
        newsItem = new navItem("News");
        connectItem = new navItem("Connect");


        sideNav.add(financeItem);
        sideNav.add(newsItem);
        sideNav.add(connectItem);

        this.add(sideNav);

        //Always at the bottom
        this.setVisible(true);
    }
}
