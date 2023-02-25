import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
<<<<<<< HEAD
import java.awt.*;

public class Dashboard extends JFrame {
    float width = 1000, height = 800;

    JPanel sideNav, mainPanel;
    navItem financeItem, newsItem, connectItem;
=======
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;


public class Dashboard extends JFrame {
    float width = 1000, height = 650;

    JPanel sideNav, sidePanel, mainPanel;
    navItem financeItem, newsItem, connectItem;
    ProfileSection profileSect;
>>>>>>> da9356ff0f01a7b72997e021facb05a21cf48d53

    Dashboard() {
        this.setTitle("Application Dashboard");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);


        //Side Nav
<<<<<<< HEAD
        sideNav = new JPanel(); 
        sideNav.setBackground(Color.black);
        sideNav.setBounds(0, 0, Login.getDimen(width, .25), (int)height);
        sideNav.setLayout(new GridLayout(10,1, 1 ,1));

        //Side Nav Components   
=======
        sidePanel = new JPanel();
        sidePanel.setBackground(Color.black);
        sidePanel.setBounds(0, 0, Login.getDimen(width, .25), (int)height);
        sidePanel.setLayout(null);

        sideNav = new JPanel(); 
        sideNav.setBackground(Color.red);
        sideNav.setBounds(0,Login.getDimen(height, .20), Login.getDimen(width, .25), (int)height);
        sideNav.setLayout(new GridLayout(20,1, 1 ,-20));

        //Side Nav Components  
        profileSect = new ProfileSection((int)width, (int)height);
>>>>>>> da9356ff0f01a7b72997e021facb05a21cf48d53
        financeItem = new navItem("Finances");
        newsItem = new navItem("News");
        connectItem = new navItem("Connect");

<<<<<<< HEAD
=======
        sidePanel.add(profileSect);
        sidePanel.add(sideNav);
>>>>>>> da9356ff0f01a7b72997e021facb05a21cf48d53

        sideNav.add(financeItem);
        sideNav.add(newsItem);
        sideNav.add(connectItem);

<<<<<<< HEAD
        this.add(sideNav);
=======
        this.add(sidePanel);
>>>>>>> da9356ff0f01a7b72997e021facb05a21cf48d53

        //Always at the bottom
        this.setVisible(true);
    }
}
<<<<<<< HEAD
=======

class ProfileSection extends JPanel {
    
    ProfileSection(int frameW, int frameH) {
        this.setBounds(0, 0, Login.getDimen(frameW, .25), Login.getDimen(frameH, .17)); 
        this.setBackground(Color.yellow);
        this.setVisible(true);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.out.println("Yo");
                new profileFrame();
            }
        });
    }

    
}
>>>>>>> da9356ff0f01a7b72997e021facb05a21cf48d53
