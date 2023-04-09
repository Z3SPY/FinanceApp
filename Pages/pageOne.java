package Pages;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DocumentFilter;
import javax.xml.crypto.Data;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Log;

//import com.mysql.cj.log.Log;
//import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import Database.transaction;


//import com.mysql.cj.log.Log;

import Elements.card;
import Swing.Login;
import Swing.valueFrame;
import Swing.valueFrame.trnsType;

public class pageOne extends JPanel implements ActionListener{
	
	//Font
	Font myFont = new Font("Georgia", Font.BOLD, 23);

    //Balance Card Values 
    JLabel balLabel;
    static JLabel balCounter;
    
    static Double Balance = 0.00;
    static String balanceString;

    //Net Profit Values
    JLabel netLabel;
    static JLabel netCounter;

    static Double netProfit = 0.00;
    String netProfitString;

    //Goal Profit Values
    static Double goalBalance = 0.00;
    String goalBalanceString;

    JLabel glBalLabel, glBalAdd;
    JTextField glBalVal;

    //JTable
    static JTable conttable; 
    private static Map<Swing.valueFrame.trnsType, Double> graphVal;

    //Free Chart
    static JFreeChart chart;
    static DefaultPieDataset dataset;

    JButton sortAmnt, sortTyp, sortFrm, sortID;
    
    //Some JLabel
    JLabel graphLabel;

    //Selection Value
    static Boolean trnsctSelect = false;
    static List<transaction> tableData =  new ArrayList<transaction>();



    public pageOne(int width, int height) {
        this.setBackground(Color.WHITE);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
        this.setLayout(null);

        Balance = 1000.00;

        //Balance Card
        //#region
        int bCrdW = 210; // Balance Card Width
        int bCrdH = 90; // Balance Card Height
        
        // x , y, width, height, color of panel ( parameter of card )
        card balanceCard = new card(15, 25, bCrdW, bCrdH, new Color(64, 81, 59));

            //Balance Title Holder
            balLabel = new JLabel("Total Balance"); // Balance Label
            balLabel.setForeground(Color.white);
            balLabel.setFont(myFont);
           

            balanceCard.setInnerCard(15, 15); // Set This First Before Deginig Card - border-radius
            balanceCard.CreateCard(0, 0, bCrdW, Login.getDimen(bCrdH, .50), Login.colorScheme2[5]); // Index 0  // card sa loob ng card

                
            JPanel tBC = balanceCard.getPanel(0);
            tBC.add(balLabel);


            //Balance Value Holder
            balanceString = String.format("%.2f PHP",Balance);
            balCounter = new JLabel(balanceString);
            balCounter.setFont(new Font("Serif", Font.BOLD, 24));  // Font For Balance
            balCounter.setBounds(Login.getDimen(bCrdW, .22), 20, bCrdW, Login.getDimen(bCrdH, .20));
            
            balanceCard.setInnerCard(30, 0); // Set This First Before Deginig Card
            balanceCard.CreateCard(0, Login.getDimen(bCrdH, .37) , bCrdW, Login.getDimen(bCrdH, .63), Login.colorScheme2[4]); // Index 1

            JPanel vBC = balanceCard.getPanel(1);
            vBC.setLayout(null);
            vBC.add(balCounter);

            
        //#endregion
        //Balance Card End


        //Net Profit Card
        //#region
        int netCrdW = 210; // Net Profit Card Width
        int netCrdH = 90; // Net Profit Card Height
        

        card netCard = new card(15, 225, bCrdW, bCrdH, Color.GREEN);

            //Net Profit Title Holder
            netLabel = new JLabel("Net Profit");
            netLabel.setForeground(Color.white);
            netLabel.setFont(myFont);

            netCard.setInnerCard(15, 15); // Set This First Before Deginig Card
            netCard.CreateCard(0, 0, netCrdW, Login.getDimen(netCrdH, .50), Login.colorScheme2[2]); // Index 0

                
            JPanel tNC = netCard.getPanel(0);
            tNC.add(netLabel);


            //Net Profit Value Holder
            netProfitString = String.format("%10.2f%%",netProfit);
            netCounter = new JLabel(netProfitString);
            netCounter.setFont(new Font("Serif", Font.BOLD, 24)); // Font For Net Gain
            netCounter.setBounds(Login.getDimen(netCrdW, .22), 20, netCrdW, Login.getDimen(netCrdH, .20));
            
            netCard.setInnerCard(30, 0); // Set This First Before Deginig Card
            netCard.CreateCard(0, Login.getDimen(netCrdH, .37) , netCrdW, Login.getDimen(netCrdH, .63), Login.colorScheme2[3]); // Index 1

            JPanel vNC = netCard.getPanel(1);
            vNC.setLayout(null);
            vNC.add(netCounter);

        
        //#endregion
        //Net Profit Card

        //Goal Balance Region
        //#region
        int goalCrdW = 210; // Net Profit Card Width
        int goalCrdH = 90; // Net Profit Card Height
        

        card goalCard = new card(15, 125, goalCrdW, goalCrdH, Color.GREEN);
            goalBalance = 1000.00;
            //Goal Profit Title Holder
            glBalLabel = new JLabel("Goal Profit");
            glBalLabel.setForeground(Color.white);
            glBalLabel.setFont(myFont);

            goalCard.setInnerCard(15, 15); // Set This First Before Deginig Card
            goalCard.CreateCard(0, 0, goalCrdW, Login.getDimen(goalCrdH, .50), Login.colorScheme2[2]); // Index 0

                
            JPanel titleGoalCard = goalCard.getPanel(0);
            titleGoalCard.add(glBalLabel);


            //Goal Profit Value Holder
            goalBalanceString = String.format("%10.2f",goalBalance);

            //Text Field
            glBalVal = new JTextField(goalBalanceString.replaceAll("\\s", ""));
            glBalVal.setFont(new Font("Serif", Font.BOLD, 24)); // Font For Net Gain
            glBalVal.setBounds(Login.getDimen(goalCrdW, .19), 18, Login.getDimen(goalCrdW, .40), Login.getDimen(goalCrdH, .30));
            glBalVal.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Removes Border
            glBalVal.setBackground(Login.colorScheme2[3]); // Sets the background of our textfield to the same background as our card
            

            glBalVal.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

            glBalVal.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    
                    try {
                        if (glBalVal.getText().length() != 0) {
                            goalBalance = Double.parseDouble(glBalVal.getText().replaceAll("[^0-9.-]", ""));
                            glBalVal.repaint();
                            UpdateNetProfit();
                        } else {
                            goalBalance = 0.00;
                        }
                    } catch (Exception exc) {
                        System.out.println(exc);
                    }
                   
                    //System.out.println("New Goal " + goalBalance);
                    //System.out.println("Balance" + Balance);

                  

                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    try {
                        if (glBalVal.getText().length() != 0) {
                            goalBalance = Double.parseDouble(glBalVal.getText().replaceAll("[^0-9.]", ""));
                            glBalVal.repaint();
                            UpdateNetProfit();
                        } else {
                            goalBalance = 0.00;
                        }
                    } catch (Exception exc) {
                        System.out.println(exc);
                    }

                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                }
                
            });

            //End Text Field

            //Jlabel
            glBalAdd = new JLabel("PHP");
            glBalAdd.setFont(new Font("Serif", Font.BOLD, 24)); // Font For Net Gain
            glBalAdd.setBounds(Login.getDimen(goalCrdW, 0.61), 20, Login.getDimen(goalCrdW, .5), Login.getDimen(goalCrdH, .30));
            //End Jlabel Field
            
            goalCard.setInnerCard(30, 0); // Set This First Before Deginig Card
            goalCard.CreateCard(0, Login.getDimen(goalCrdH, .37) , goalCrdW, Login.getDimen(goalCrdH, .63), Login.colorScheme2[3]); // Index 1

            JPanel valueGoalCard = goalCard.getPanel(1);
            valueGoalCard.setLayout(null);
            valueGoalCard.add(glBalVal);
            valueGoalCard.add(glBalAdd);
        //#endregion
        //Goal Balance Region End


       //Jtable Portion
        //#region
       int tblCrdW = 740;
       int tblCrdH = 200;

       //Table Value Start
       int butWidth = 120;
       int butspacing = 10;
       int butBasis = 250;
       sortID = new JButton("SORT BY ID"); //Sort By Amount
       sortID.setBounds(butBasis + ((butWidth * 0) + butspacing), 300, butWidth, 20);
       sortID.setFont(new Font("Serif", Font.BOLD, 10));
       sortID.addActionListener(this);

       sortAmnt = new JButton("SORT BY VALUE"); //Sort By Amount
       sortAmnt.setBounds(butBasis + ((butWidth * 1) + butspacing), 300, butWidth, 20);
       sortAmnt.setFont(new Font("Serif", Font.BOLD, 10));
       sortAmnt.addActionListener(this);

       sortFrm = new JButton("SORT BY FROM"); //Sort By Amount
       sortFrm.setBounds(butBasis + ((butWidth * 2) + butspacing), 300, butWidth, 20);
       sortFrm.setFont(new Font("Serif", Font.BOLD, 10));
       sortFrm.addActionListener(this);

       sortTyp = new JButton("SORT BY TYPE"); //Sort By Amount
       sortTyp.setBounds(butBasis + ((butWidth * 3) + butspacing), 300, butWidth, 20);
       sortTyp.setFont(new Font("Serif", Font.BOLD, 10));
       sortTyp.addActionListener(this);

       
       

       String tablecolumn[] = {"Transaction ID","Amount", "Date", "Transaction Type", "From"};
        
       tableData.add(new transaction(101, 250, "Mon, Mar 18, 2020", "Mother", Swing.valueFrame.trnsType.EXPENSES, true));
       tableData.add(new transaction(102, 500, "Mon, Mar 18, 2020", "Valhalla Company", Swing.valueFrame.trnsType.BUSINESS, true));
       tableData.add(new transaction(103, 250, "Mon, Mar 18, 2020", "Homelessness Fund", Swing.valueFrame.trnsType.MISC, true));
      


       DefaultTableModel tableModel = new DefaultTableModel(twoDimenArray(tableData), tablecolumn) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
    
        };

       conttable = new JTable(tableModel);

       conttable.getColumnModel().getColumn(1).setCellRenderer(new StatusColumnCellRenderer());
       




       card JTableCard = new card(10, 325, tblCrdW, tblCrdH, Color.GREEN);
       JTableCard.CreateCard(0,0,tblCrdW,tblCrdH, Color.WHITE);//index 0
       JTableCard.getPanel(0).setLayout(null);
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


       //Transaction Logic
       JButton addTransBttn = new JButton("CREATE TRANSACTION");
       addTransBttn.setBackground(Login.colorScheme[3]);
       addTransBttn.setForeground(Login.colorScheme2[3]);
       addTransBttn.setBounds(150, 535, 500, 70);

            addTransBttn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    if (trnsctSelect == false) {
                        selectionState(true);
                        new valueFrame();                        
                    }


                }
            });




       //Transaction Logic End




       this.add(addTransBttn);

       //#endregion
       //JTabel End

        //Graph
        //#region

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


        //Values Update
        dataset = new DefaultPieDataset();
        graphVal = new HashMap<>();  // For our Hash Map variables
        updateGraph(dataset);

        
        
        chart = ChartFactory.createPieChart("", dataset, true, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(0, 25, grphCrdW,Login.getDimen(grphCrdH, .80));
        

        graphPanel.add(chartPanel);
        
        //#endregion
        //Graph End

        
        this.add(balanceCard);
        this.add(netCard);
        this.add(goalCard);
        this.add(JTableCard);
        
        this.add(sortAmnt);
        this.add(sortFrm);
        this.add(sortID);
        this.add(sortTyp);

        this.add(graphCard);
        this.setVisible(true);


    }


    //METHODS LIST


    
    public static void UpdateNetProfit() {
        netProfit = ((Balance - goalBalance) / goalBalance) * 100 ; // Balance Is Net // Goal Balance in Gross Profit
       // System.out.println(netProfit);
        netCounter.setText(String.format("%10.2f%%",netProfit));
        netCounter.repaint();
    }

    //Adding Column Index Colour
    public class StatusColumnCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
      
          //Cells are by default rendered as a JLabel.
          JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
          l.setFont(new Font("Serif", Font.BOLD, 14));

          //Get the status for the current row.
          if (value.toString().charAt(0) != '-') {
            l.setForeground(Color.BLUE);
            l.setText("+" + l.getText());
          } else {
            l.setForeground(Color.RED);
          }
      
        //Return the JLabel which renders the cell.
        return l;
      
        }
    }

    
    //Update Values of Graph
    public static void updateGraph(DefaultPieDataset data) {

        Swing.valueFrame.trnsType[] stateTransaction = {trnsType.ACTIVITIES, 
            trnsType.BUSINESS, 
            trnsType.FOOD,
            trnsType.MISC,
            trnsType.BANK,
            trnsType.HOBBIES,
            trnsType.EXPENSES,
            trnsType.UTILITIES};


        
        graphVal.clear(); // Cleans out Hash map
    

        for (transaction t: tableData) {

            Swing.valueFrame.trnsType dataType = t.getType();

            if (!graphVal.containsKey(dataType)) {
                graphVal.put(dataType, 0.00); // Hash Map with Key so that we can keep track of our graph values
                System.out.print("Created New Hash Map");
            } 

            double sum = graphVal.get(dataType) + t.getAmnt();
            graphVal.put(dataType, graphVal.get(dataType) + sum);

            System.out.println(dataType + ": " + graphVal.get(dataType));

        }        


        for (int i = 0; i < stateTransaction.length; i++) {
            if (graphVal.containsKey(stateTransaction[i]))
            data.setValue(stateTransaction[i].toString(), graphVal.get(stateTransaction[i]));
        }

        UpdateNetProfit();
        
    }

    public static void selectionState(boolean b) {
        trnsctSelect = b;
    }

    static int idVal = 103;
    public static void transactionLogic(Boolean isAddition, Double amount, String myDate, String fromName, Swing.valueFrame.trnsType typeValue) {
        System.out.println(isAddition + " " +  amount + " " +  myDate + " " +  fromName + " " +  typeValue);
        idVal++;


        if (isAddition) {
            Balance += amount;
        } else {
            Balance -= amount;
        }
        balanceString = String.format("%.2f PHP",Balance);
        balCounter.setText(balanceString);
        
        tableData.add(new transaction(idVal, amount, myDate, fromName, typeValue, isAddition));
        setUpTableData(conttable);
        updateGraph(dataset); // Updates Graph After Every new Input of Values
    }


    //Sorting Array -- If array is sorted table will also be sorted
    public static void sortBy(String sortingBasis) {

        System.out.println("called");
        int n = tableData.size();
        quickSort(tableData, 0, n-1, sortingBasis);

        setUpTableData(conttable);
        updateGraph(dataset);

    }


    //Quick Sort

    static void swap(List<transaction> myData, int i, int j)
    {
        Collections.swap(myData, i, j);
    }
 
 
    static int partition(List<transaction> myData, int low, int high, String sortingBasis)
    {
        int i = (low - 1);

        switch (sortingBasis.toLowerCase()) {
            case "amount":
                double pivotAmt = (myData.get(high).getIsAddition() == true) ? myData.get(high).getAmnt() : myData.get(high).getAmnt() * -1;
                for (int j = low; j <= high - 1; j++) {
 
                    double compAmt = (myData.get(j).getIsAddition() == true) ? myData.get(j).getAmnt() : myData.get(j).getAmnt() * -1;
                    if (compAmt < pivotAmt) {
                      
                        i++;
                        swap(myData, i, j);
                    }
                }
                break;
            case "type":
                String pivotTyp = myData.get(high).getType().toString().toLowerCase();
                for (int j = low; j <= high - 1; j++) {
                    String compTyp = myData.get(j).getType().toString().toLowerCase();
                    if (compTyp.charAt(0) < pivotTyp.charAt(0)) {
                        i++;
                        swap(myData, i, j);
                    }
                }
                break;
            case "from":
                String pivotFrom = myData.get(high).getFrom().toLowerCase();
                for (int j = low; j <= high - 1; j++) {
                    String compFrom = myData.get(j).getFrom().toLowerCase();
                    if (compFrom.charAt(0) < pivotFrom.charAt(0)) {
                        i++;
                        swap(myData, i, j);
                    }
                }
                break;
            case "id":
                int pivotID = myData.get(high).getId();
                    for (int j = low; j <= high - 1; j++) {
                        int compID = myData.get(j).getId();
                        if (compID < pivotID) {
                            i++;
                            swap(myData, i, j);
                        }
                    }
                break;
            default:
                break;


        }
 
 
        swap(myData, i + 1, high);
        return (i + 1);
    }
 
    
    static void quickSort(List<transaction> myData, int low, int high, String sortingBasis)
    {
        if (low < high) {
 
        
            int pi = partition(myData, low, high, sortingBasis);
 
            
            quickSort(myData, low, pi - 1, sortingBasis);
            quickSort(myData, pi + 1, high, sortingBasis);
        }
    }
 
    //Quick Sort End


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



    //Updates up Table Data
    //Adding Table
    public static void setUpTableData(JTable myTable) {
        DefaultTableModel tabelModel = (DefaultTableModel) myTable.getModel();
        ArrayList<transaction> transList = new ArrayList<transaction>(); 
        tabelModel.setRowCount(0);
        transList = (ArrayList<transaction>) tableData;

        for (transaction t : transList) {
            String[] data = new String[5];

            data[0] = Integer.toString(t.getId());
            data[1] = (t.getIsAddition()) ? Double.toString(t.getAmnt()) : "-" + Double.toString(t.getAmnt());
            data[2] = t.getDate();
            data[3] = t.getType().toString();
            data[4] = t.getFrom();

            tabelModel.addRow(data);
        }

        
        //tabelModel.fireTableChanged(new TableModelEvent(tabelModel));
        myTable.setModel(tabelModel);
    }




    //Creates A 2D Array for Table
    public Object[][] twoDimenArray(List<transaction> listReference) {
        int n  = listReference.size();

        Object[][] twoDArray = new Object[n][5]; 
        int i = 0;

        for (transaction t : listReference) {
            String[] data = new String[5];

            data[0] = Integer.toString(t.getId());
            data[1] = Double.toString(t.getAmnt());
            data[2] = t.getDate();
            data[3] = t.getType().toString();
            data[4] = t.getFrom();

            twoDArray[i] = data;
            i++;

        }


        return twoDArray;
    }
    //Adds Coloured backGround
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(), h = getHeight();
        Color color1 = new Color(237,241,214);
        Color color2 = Login.colorScheme2[1];//new Color(157,192,139);
        GradientPaint gp = new GradientPaint(w/2, 0, color1, w/2, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        //future references RGB(96, 153, 102) || RGB(64, 81, 59)
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    
        if (e.getSource() == sortAmnt) {
            sortBy("Amount");
        } else if (e.getSource() == sortFrm) {
            sortBy("From");
        } else if (e.getSource() == sortID) {
            sortBy("ID");
        } else if (e.getSource() == sortTyp) {
            sortBy("Type");
        }
      

    }
}
