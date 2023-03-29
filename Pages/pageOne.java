package Pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.mysql.cj.log.Log;

import Elements.card;
import Swing.Login;

public class pageOne extends JPanel{

    //Balance Card Values 
    JLabel balLabel;
    JLabel balCounter;
    
    Double Balance = 0.00;
    String balanceString;

    //Net Profit Values
    JLabel netLabel;
    JLabel netCounter;

    Double netProfit = 0.00;
    String netProfitString;


    public pageOne(int width, int height) {
        this.setBackground(Color.WHITE);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
        this.setLayout(null);

        Balance = 1000.00;

        //Balance Card
        int bCrdW = 250; // Balance Card Width
        int bCrdH = 125; // Balance Card Height
        

        card balanceCard = new card(10, 25, bCrdW, bCrdH, Color.BLUE);

            //Balance Title Holder
            balLabel = new JLabel("Total Balance"); // Balance Label

            balanceCard.setInnerCard(30, 30); // Set This First Before Deginig Card
            balanceCard.CreateCard(0, 0, bCrdW, Login.getDimen(bCrdH, .50), Color.red); // Index 0

                
            JPanel tBC = balanceCard.getPanel(0);
            tBC.add(balLabel);


            //Balance Value Holder
            balanceString = String.format("%.2f PHP",Balance);
            balCounter = new JLabel(balanceString);
            balCounter.setFont(new Font("Serif", Font.BOLD, 24));
            balCounter.setBounds(Login.getDimen(bCrdW, .22), 25, bCrdW, Login.getDimen(bCrdH, .20));
            
            balanceCard.setInnerCard(30, 0); // Set This First Before Deginig Card
            balanceCard.CreateCard(0, Login.getDimen(bCrdH, .40) , bCrdW, Login.getDimen(bCrdH, .60), Color.pink); // Index 1

            JPanel vBC = balanceCard.getPanel(1);
            vBC.setLayout(null);
            vBC.add(balCounter);

            
        
        //Balance Card End


        //Net Profit Card
        int netCrdW = 250; // Net Profit Card Width
        int netCrdH = 125; // Net Profit Card Height
        

        card netCard = new card(10, 160, bCrdW, bCrdH, Color.BLUE);

            //Net Profit Title Holder
            netLabel = new JLabel("Net Profit");

            netCard.setInnerCard(30, 30); // Set This First Before Deginig Card
            netCard.CreateCard(0, 0, netCrdW, Login.getDimen(netCrdH, .50), Color.red); // Index 0

                
            JPanel tNC = netCard.getPanel(0);
            tNC.add(netLabel);


            //Net Profit Value Holder
            netProfitString = String.format("%10.2f PHP",netProfit);
            netCounter = new JLabel(netProfitString);
            netCounter.setFont(new Font("Serif", Font.BOLD, 24));
            netCounter.setBounds(Login.getDimen(netCrdW, .22), 25, netCrdW, Login.getDimen(netCrdH, .20));
            
            netCard.setInnerCard(30, 0); // Set This First Before Deginig Card
            netCard.CreateCard(0, Login.getDimen(netCrdH, .40) , netCrdW, Login.getDimen(netCrdH, .60), Color.pink); // Index 1

            JPanel vNC = netCard.getPanel(1);
            vNC.setLayout(null);
            vNC.add(netCounter);

            
        
        //Net Profit Card



        //Jtable Portion
        int tblCrdW = 250; // Table Card Width
        int tblCrdH = 300; // Table Card Height

        card JTableCard = new card(10, 300, tblCrdW, tblCrdH, Color.BLUE);


        //JTabel End

        //Graph
        
        int grphCrdW = 450;
        int grphCrdH = 300;

        card graphCard = new card(tblCrdW + 30 , 300, grphCrdW, grphCrdH, Color.BLUE);
            

        

        //Graph End

        
        this.add(balanceCard);
        this.add(netCard);
        this.add(JTableCard);
        this.add(graphCard);
        this.setVisible(true);


       
        
    
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(), h = getHeight();
        Color color1 = new Color(237,241,214);
        Color color2 = new Color(157,192,139);
        GradientPaint gp = new GradientPaint(w/2, 0, color1, w/2, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        //future references RGB(96, 153, 102) || RGB(64, 81, 59)
    }
}


