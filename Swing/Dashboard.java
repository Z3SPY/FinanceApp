package Swing;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import Elements.choiceList;
import Elements.navItem;
import Elements.navItem.MenuType;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;



public class Dashboard extends JFrame {
    public static float width = 1000;
    public static float height = 650;



    JPanel menuBar, mainPanel;
    ProfileSection profileSect;

    sideNavMenu sidePanel;
    choiceList menuChoices;
    ArrayList<navItem> buttonComp = new ArrayList<navItem>();

    Dashboard() {
        this.setTitle("Application Dashboard");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getPreferredSize();


        //Side Navigation Holds Profile and Menu
        sidePanel = new sideNavMenu();
        

        initComp();
        menuChoices = new choiceList(buttonComp);
        menuChoices.setBounds(10, 100, (int) Login.getDimen(width, .25) - 60, (int) Login.getDimen(height, .50));
        menuChoices.setFont(new Font("DIALOG", Font.BOLD, 16));
    
       
        
        sidePanel.add(menuChoices);
        //End Naviationg
        
        //Main Panel Hold Pages
        mainPanel = new JPanel();
        mainPanel.setBounds(Login.getDimen(Dashboard.width, .24), 0, Login.getDimen(Dashboard.width, .75), (int) height);
        mainPanel.setBackground(Color.WHITE);


        this.add(mainPanel);
        this.add(sidePanel);
        //Always at the bottom
        this.setVisible(true);
    }

    public void initComp() {
        buttonComp.add(new navItem("Finances", "Icon Sample", MenuType.MENU));
        buttonComp.add(new navItem("Community", "Icon Sample", MenuType.MENU));
        buttonComp.add(new navItem("About", "Icon Sample", MenuType.MENU));

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


class sideNavMenu extends JPanel{

    int drawX = 170;
    int drawY = 95;
    int TargetY = 145;

    int selectedIndex = 1;
    int menuX = (int) Login.getDimen(Dashboard.width, .25), menuY = (int) Dashboard.width;

    Timer timer;
    int timerCheck = 0;

    sideNavMenu() {
        setBackground(Color.WHITE);
        setBounds(0, 0, menuX, menuY);
        setLayout(null);

        timer = new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (drawY == TargetY) {
                    timer.stop();
                    repaint();
                } else {
                    drawY += 1.5;
                    repaint();
                }

                timerCheck += 1;
                if (timerCheck > 500) {
                    timer.stop();
                    repaint();
                }

            }
        });

        timer.start();

    }



   
    //Animation Components

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //Menu Colour and Border
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#FF7B54"), 0, getHeight(), Color.decode("#FFD56F"));
        g2.setPaint(g);
        g2.fillRoundRect(-30, 0, menuX, menuY, 20, 20);
        //End
        
        
        //Moving Border Based on Selection


       
        g2.fillRoundRect(drawX, drawY, 70, 60, 180, 180);

        //End

    }




}
