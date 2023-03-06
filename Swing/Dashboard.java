package Swing;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import Elements.choiceList;
import Elements.navItem;
import Elements.navItem.MenuType;
import Events.navBarEventCallBack;
import Events.navBarEventMenu;
import Events.navBarEventSelected;

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

    Dashboard() {
        this.setTitle("Application Dashboard");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getPreferredSize();


        
        

        menuChoices = new choiceList();
        menuChoices.setBounds(10, 100, (int) Login.getDimen(width, .25) - 60, (int) Login.getDimen(height, .50));
        menuChoices.setFont(new Font("DIALOG", Font.BOLD, 16));
    
       
        //Side Navigation Holds Profile and Menu
        sidePanel = new sideNavMenu(menuChoices);    
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

    static ArrayList<navItem> buttonComp = new ArrayList<navItem>();

    int drawX = 170;
    int drawY;
    int TargetY;
    private int speed = 2;

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
                        speed = 20;
                    }  else {
                        speed = selectedIndex - index;
                        if (speed < 0) {
                            speed *= -1;
                        }
                     }

                    speed++;
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
