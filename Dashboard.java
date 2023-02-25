import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;


public class Dashboard extends JFrame {
    float width = 1000, height = 650;

    JPanel sideNav, sidePanel, mainPanel;
    navItem financeItem, newsItem, connectItem;
    ProfileSection profileSect;

    Dashboard() {
        this.setTitle("Application Dashboard");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);


        //Side Nav
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
        financeItem = new navItem("Finances");
        newsItem = new navItem("News");
        connectItem = new navItem("Connect");

        sidePanel.add(profileSect);
        sidePanel.add(sideNav);

        sideNav.add(financeItem);
        sideNav.add(newsItem);
        sideNav.add(connectItem);

        this.add(sidePanel);

        //Always at the bottom
        this.setVisible(true);
    }
}

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
