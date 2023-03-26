package Pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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
        int bCrdW = 250;
        int bCrdH = 125;
        

        card balanceCard = new card(10, 25, bCrdW, bCrdH, Color.BLUE);

            //Balance Title Holder
            balLabel = new JLabel("Total Balance");

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
        int netCrdW = 250;
        int netCrdH = 125;
        

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
        int tblCrdW = 250;
        int tblCrdH = 300;

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
}


