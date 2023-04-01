package Pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableCellRenderer;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


//import com.mysql.cj.log.Log;

import Elements.card;
import Swing.Login;

public class pageOne extends JPanel{
	
	//Font
	Font myFont = new Font("Georgia", Font.BOLD, 23);

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
    
    //Some JLabel
    JLabel graphLabel;


    public pageOne(int width, int height) {
        this.setBackground(Color.WHITE);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
        this.setLayout(null);

        Balance = 1000.00;

        //Balance Card
        int bCrdW = 210; // Balance Card Width
        int bCrdH = 125; // Balance Card Height
        
        // x , y, width, height, color of panel ( parameter of card )
        card balanceCard = new card(10, 25, bCrdW, bCrdH, new Color(64, 81, 59));

            //Balance Title Holder
            balLabel = new JLabel("Total Balance"); // Balance Label
            balLabel.setForeground(Color.white);
            balLabel.setFont(myFont);
           

            balanceCard.setInnerCard(15, 15); // Set This First Before Deginig Card - border-radius
            balanceCard.CreateCard(0, 0, bCrdW, Login.getDimen(bCrdH, .50), new Color(64, 81, 59)); // Index 0  // card sa loob ng card

                
            JPanel tBC = balanceCard.getPanel(0);
            tBC.add(balLabel);


            //Balance Value Holder
            balanceString = String.format("%.2f PHP",Balance);
            balCounter = new JLabel(balanceString);
            balCounter.setFont(new Font("Serif", Font.BOLD, 24));
            balCounter.setBounds(Login.getDimen(bCrdW, .22), 25, bCrdW, Login.getDimen(bCrdH, .20));
            
            balanceCard.setInnerCard(30, 0); // Set This First Before Deginig Card
            balanceCard.CreateCard(0, Login.getDimen(bCrdH, .37) , bCrdW, Login.getDimen(bCrdH, .63), new Color(96, 153, 102)); // Index 1

            JPanel vBC = balanceCard.getPanel(1);
            vBC.setLayout(null);
            vBC.add(balCounter);

            
        
        //Balance Card End


        //Net Profit Card
        int netCrdW = 210; // Net Profit Card Width
        int netCrdH = 125; // Net Profit Card Height
        

        card netCard = new card(10, 160, bCrdW, bCrdH, Color.GREEN);

            //Net Profit Title Holder
            netLabel = new JLabel("Net Profit");
            netLabel.setForeground(Color.white);
            netLabel.setFont(myFont);

            netCard.setInnerCard(15, 15); // Set This First Before Deginig Card
            netCard.CreateCard(0, 0, netCrdW, Login.getDimen(netCrdH, .50), new Color(64, 81, 59)); // Index 0

                
            JPanel tNC = netCard.getPanel(0);
            tNC.add(netLabel);


            //Net Profit Value Holder
            netProfitString = String.format("%10.2f PHP",netProfit);
            netCounter = new JLabel(netProfitString);
            netCounter.setFont(new Font("Serif", Font.BOLD, 24));
            netCounter.setBounds(Login.getDimen(netCrdW, .22), 25, netCrdW, Login.getDimen(netCrdH, .20));
            
            netCard.setInnerCard(30, 0); // Set This First Before Deginig Card
            netCard.CreateCard(0, Login.getDimen(netCrdH, .37) , netCrdW, Login.getDimen(netCrdH, .63), new Color(96, 153, 102)); // Index 1

            JPanel vNC = netCard.getPanel(1);
            vNC.setLayout(null);
            vNC.add(netCounter);

            
        
        //Net Profit Card



       //Jtable Portion
       int tblCrdW = 740;
       int tblCrdH = 200;
       String tablecolumn[] = {"Transaction ID","Amount", "Date", "Transaction Type", "From"};
       String tabledata[][] = {{"101", "5000","March 18 2020","Last Harvest's Profits", "Agri Inc"},
               {"102", "2000","June 18 2020","Last Harvest's Profits", "Agri Inc"},
               {"103", "8000","August 18 2020","Last Harvest's Profits", "Agri Inc"}
       };


       card JTableCard = new card(10, 300, tblCrdW, tblCrdH, Color.GREEN);
       JTableCard.CreateCard(0,0,tblCrdW,tblCrdH, Color.WHITE);//index 0
       JTableCard.getPanel(0).setLayout(null);
       JTable conttable = new JTable(tabledata,tablecolumn);
       JScrollPane sp=new JScrollPane(conttable);
       JLabel historyLabel = new JLabel("Transaction History");

//        conttable.setBounds(10,100,190,400);


       conttable.setOpaque(false);
       ((DefaultTableCellRenderer)conttable.getDefaultRenderer(Object.class)).setOpaque(false);
       conttable.setForeground(Color.black);
       sp.setOpaque(false);
       sp.getViewport().setOpaque(false);


       resizeColumnWidth(conttable);
       sp.setBounds(0,0,tblCrdW,tblCrdH);
       JTableCard.getPanel(0).add(historyLabel);
       JTableCard.getPanel(0).add(sp);

       JButton addTransBttn = new JButton("Create Transaction");
       addTransBttn.setBounds(150, 525, 500, 70);
       this.add(addTransBttn);
       //JTabel End







       
        //Graph
        
        int grphCrdW = 510;
        int grphCrdH = 260;

        card graphCard = new card(bCrdW + 30 , 25, grphCrdW, grphCrdH, Color.BLUE);
        graphCard.setInnerCard(15, 15); // Set This First Before Deginig Card - border-radius
        graphCard.CreateCard(0, 0, grphCrdW, Login.getDimen(grphCrdH, .20), new Color(64, 81, 59)); // Index 0  // card sa loob ng card
        
        //NAME OF GRAPH
        graphLabel = new JLabel("Finance Graph"); 
        graphLabel.setForeground(Color.white);
        graphLabel.setFont(myFont);
        
        JPanel gfPanel = graphCard.getPanel(0);
        gfPanel.add(graphLabel);
        //End Name


        // please add changes to size of jchart or the card 
        graphCard.setInnerCard(15, 15); // Set This First Before Deginig Card - border-radius
        graphCard.CreateCard(0, Login.getDimen(grphCrdH, .10), grphCrdW, Login.getDimen(grphCrdH, .90), new Color(96, 153, 102)); // Index 1  // card sa loob ng card
        JPanel graphPanel = graphCard.getPanel(1);
        graphPanel.setLayout(null);


        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Category 1", 50);
        dataset.setValue("Category 2", 25);
        dataset.setValue("Category 3", 25);
        
        JFreeChart chart = ChartFactory.createPieChart("", dataset, true, true, false);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(0, 25, grphCrdW,Login.getDimen(grphCrdH, .80));
        

        graphPanel.add(chartPanel);
        

        //Graph End

        
        this.add(balanceCard);
        this.add(netCard);
        this.add(JTableCard);
        this.add(graphCard);
        this.setVisible(true);


       
        
    
    }

    //Table Width Auto Resize - Do no Touch
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
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
