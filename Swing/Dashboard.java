package Swing;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import Elements.choiceList;
import Elements.navItem;
import Elements.navItem.MenuType;
import Events.navBarEventCallBack;
import Events.navBarEventMenu;
import Events.navBarEventSelected;
import Pages.*;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;



public class Dashboard extends JFrame {
    public static float width = 1000;
    public static float height = 650;



    JPanel menuBar;
    public static JTabbedPane mainPanel;
    ProfileSection profileSect;

    sideNavMenu sidePanel;
    choiceList menuChoices;

    //Pages
    pageOne pageFinance;
    pageTwo pageCommunity;
    pageThree pageAbout;
    pageFour pageAccount;

    Dashboard() {
        this.setTitle("Application Dashboard");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getPreferredSize();

        //Instantiate and Define Profile Section 
        profileSect = new ProfileSection(width, height, sidePanel);
        
        
        //Instantiate Menu Choices
        menuChoices = new choiceList();
        menuChoices.setBounds(10, 100, (int) Login.getDimen(width, .25) - 60, (int) Login.getDimen(height, .50));
        menuChoices.setFont(new Font("DIALOG", Font.BOLD, 16));
        
       
        //Side Navigation Holds Profile and Menu
        sidePanel = new sideNavMenu(menuChoices);    
        sidePanel.add(menuChoices);
        sidePanel.add(profileSect);
        //End Naviationg
        
        //Main Panel Hold Pages
        mainPanel = new JTabbedPane();
        mainPanel.setBounds(Login.getDimen(Dashboard.width, .25), -38, Login.getDimen(Dashboard.width, .77), (int) height);
        mainPanel.setBackground(Color.WHITE);

        //Removes JTabbed Pain Styling
        mainPanel.setUI(new BasicTabbedPaneUI() {
            private final Insets borderInsets = new Insets(0, 0, 0, 0);
            @Override
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
            }
            @Override
            protected Insets getContentBorderInsets(int tabPlacement) {
                return borderInsets;
            }
        });


        pageFinance = new pageOne((int) width, (int) height);
        pageCommunity = new pageTwo((int) width, (int) height);
        pageAbout = new pageThree((int) width, (int) height);
        pageAccount = new pageFour((int) width, (int) height);

        mainPanel.add(pageFinance);
        mainPanel.add(pageCommunity);
        mainPanel.add(pageAbout);
        mainPanel.add(pageAccount);


        this.add(mainPanel);
        this.add(sidePanel);
        //Always at the bottom
        this.setVisible(true);
    }

}

class ProfileSection extends JPanel {
    
    ProfileSection(float frameW, float frameH, sideNavMenu sideNavRef) {
        this.setBounds(0, 10, Login.getDimen(frameW, .25) - 40, Login.getDimen(frameH, .13)); 
        this.setBackground(Color.yellow);
        this.setVisible(true);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                new profileFrame();
            }
        });
    }

    
}


class sideNavMenu extends JPanel{

    static ArrayList<navItem> buttonComp = new ArrayList<navItem>();

    int drawX = 170;
    int drawY = -100;
    int TargetY;
    private int speed = 50;

    int selectedIndex = -1;
    int menuX = (int) Login.getDimen(Dashboard.width, .25), menuY = (int) Dashboard.width;
    boolean toUp;

    Timer timer;
    int timerCheck = 0;
    private navBarEventCallBack callBack;
    private navBarEventMenu event;
    private choiceList List;

    sideNavMenu(choiceList newList) {
        setBackground(Color.WHITE);
        setBounds(0, 0, menuX, menuY);
        setLayout(null);

        
        //Obtain and Change List Values;
        this.List = newList;
        this.List.setOpaque(false);
        this.List.addEventMenu(new navBarEventSelected() {
            @Override
            public void menuSelected(int index, navBarEventCallBack callBack) {
               if(index != selectedIndex) {
                    sideNavMenu.this.callBack = callBack;
                    toUp = selectedIndex > index;
                    System.out.println("Selected Index // Previous Index: " + selectedIndex);
                    System.out.println("Index: " + index);

                    System.out.println(toUp);
                    if (selectedIndex == -1) {
                        speed = 50;
                    }  else {
                        speed = selectedIndex - index;
                        if (speed < 0) {
                            speed *= -1;
                        }
                     }

                    speed+= 5;
                    selectedIndex = index;
                    TargetY = selectedIndex * 50 + List.getY();
                    if (!timer.isRunning()) {
                        timer.start();
                    }
                } 
            }
            
        });


        timer = new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (toUp) {
                    if (drawY <= TargetY - 5) {
                        drawY = TargetY;
                        repaint();
                        timer.stop();
                        callBack.call(selectedIndex); // Here
                        if (event != null) {
                            event.menuIndexChange(selectedIndex);
                        }
                    } else {
                        drawY -= speed;
                        repaint();
                    }
                } 
                else {
                    if (drawY >= TargetY + 5) {
                        drawY = TargetY;
                        repaint();
                        timer.stop();
                        callBack.call(selectedIndex);
                        if(event != null) {
                            event.menuIndexChange(selectedIndex);
                        }
                    } else {
                        drawY += speed;
                        repaint();
                    }

                } 
                
                timerCheck += 1;
                if (timerCheck > 2000) {
                    timer.stop();
                    repaint();
                }

            }
        });

        initComp();
        this.List.populate(buttonComp);
        
        
    }

    public void initComp() {
        buttonComp.add(new navItem("Finances", "Icon Sample", MenuType.MENU));
        buttonComp.add(new navItem("Community", "Icon Sample", MenuType.MENU));
        buttonComp.add(new navItem("About", "Icon Sample", MenuType.MENU));
        buttonComp.add(new navItem("Account", "Icon Sample", MenuType.MENU));

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
