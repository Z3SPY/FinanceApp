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
    pageFive peoplePage;

    Dashboard() {
        this.setTitle("Application Dashboard");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getPreferredSize();






        //Instantiate Menu Choices
        menuChoices = new choiceList();
        menuChoices.setBounds(25, 125, (int) Login.getDimen(width, .25) - 60, (int) Login.getDimen(height, .90));
        menuChoices.setFont(new Font("DIALOG", Font.BOLD, 16));


        //Instantiate and Define Profile Section 
        profileSect = new ProfileSection(width - 80, height, sidePanel, menuChoices);
        
        
        //Side Navigation Holds Profile and Menu
        sidePanel = new sideNavMenu(menuChoices);    
        sidePanel.setBounds(0, 0, Login.getDimen(Dashboard.width, .22), (int) height);
        sidePanel.add(menuChoices);
        sidePanel.add(profileSect);
        //End Naviationg
        
        //Main Panel Holds Pages
        mainPanel = new JTabbedPane();
        mainPanel.setBounds(Login.getDimen(Dashboard.width, .22), -38, Login.getDimen(Dashboard.width, .77), (int) height);
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
        peoplePage = new pageFive((int) width, (int) height);

        mainPanel.add(pageFinance);
        mainPanel.add(pageCommunity);
        mainPanel.add(peoplePage);
        mainPanel.add(pageAbout);
        mainPanel.add(pageAccount);


        this.add(mainPanel);
        this.add(sidePanel);
        //Always at the bottom
        this.setVisible(true);
    }

}

class ProfileSection extends JPanel {
    
    ProfileSection(float frameW, float frameH, sideNavMenu sideNavRef, choiceList choiceListRef) {
        this.setBounds(0, 10, Login.getDimen(frameW, .25) - 40, Login.getDimen(frameH, .13)); 
        this.setBackground(Color.yellow);
        this.setVisible(true);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                choiceListRef.switchPageGlobal(7);
                //new profileFrame();
            }
        });
    }

    
}


class sideNavMenu extends JPanel{

    static ArrayList<navItem> buttonComp = new ArrayList<navItem>();

    int drawX = 140;
    int drawY = -100;
    int TargetY;
    private int speed = 50;
    int basePos = 0; // Animation Bar Text
    Boolean startSequence = false;
    Boolean hideAnim = false;

    int selectedIndex = -1;
    int menuX = (int) Login.getDimen(Dashboard.width, .22), menuY = (int) Dashboard.height;
    boolean toUp;

    Timer timer;
    Timer basePosTimer;
    int timerCheck = 0;
    int basePosChecker = 0;

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

                    speed += 5;
                    selectedIndex = index;
                    TargetY = selectedIndex * 50 + List.getY();
                    if (!timer.isRunning()) {
                        timer.start();
                        basePosTimer.start();
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
                        startSequence = true;
                        drawY -= speed * 1.5;
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
                        startSequence = true;
                        drawY += speed * 1.5;
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

        basePosChecker = 0;
        basePosTimer = new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 //BasePos Checker
                if (basePos > 0) {
                    basePos = 0;
                    drawX = 140;
                } else if (basePos < -5) {
                    basePos = -5;
                    drawX = 100;
                }

                if (hideAnim == true) {
                    basePos -= 1;
                    drawX -= 4;

                    repaint();
                    System.out.print("y");  
                } else if (hideAnim == false && basePos != 0) {
                    basePos += 1;
                    drawX += 4;
                    if (basePos == 0) {
                        basePosChecker = 0;
                        drawX = 140;
                        startSequence = false;
                        basePosTimer.stop();
                    }
                    repaint();
                    System.out.print("f");
                }

                if (startSequence == true) {
                    if (basePos == 0 && hideAnim == false) {
                       hideAnim = true;
                    }

                    if (basePos == -5 && hideAnim == true) {
                        hideAnim = false;
                    }
                }
               
                

                basePosChecker += 1;
                if (basePosChecker > 1000) {
                    basePosTimer.stop();                    
                    basePos = 0;
                    repaint();
                }
            }}
        );



        initComp();
        this.List.populate(buttonComp);
        
        
    }

    public void initComp() {
        buttonComp.add(new navItem("  Finances", "App-Images/logo_3.png", MenuType.MENU));
        buttonComp.add(new navItem("  Community", "App-Images/logo_3.png", MenuType.MENU));
        buttonComp.add(new navItem("  People", "App-Images/logo_3.png", MenuType.MENU));
        buttonComp.add(new navItem("  About", "App-Images/logo_3.png", MenuType.MENU));
        buttonComp.add(new navItem("", "Icon Sample", MenuType.EMPTY));
        buttonComp.add(new navItem("", "Icon Sample", MenuType.EMPTY));
        buttonComp.add(new navItem("", "Icon Sample", MenuType.EMPTY));
        buttonComp.add(new navItem("  Profile", "App-Images/profile.png", MenuType.MENU));

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
        g2.fillRoundRect(drawX, drawY, 70, 60, 180, 180); // Round Tip


        g = new GradientPaint(0, 0, Color.decode("#FF7B54"), 0, getHeight(), Color.decode("#FFFFFF")); //Square Surronding Text
        g2.setPaint(g);
        g2.fillRoundRect(0, drawY + 4, 190, 50, 30, 30); // Round Tip

        

        g2.setPaint(Color.white);//Base Selection
        g2.fillRect(basePos, drawY + 4, 10, 50);

        //End

    }




}
