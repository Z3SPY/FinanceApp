package Elements;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;



public class card extends JLayeredPane{
    
    public int cardX;
    int cardY;
    int cardW;
    int cardH;
    int rndWidth = 30, rndHeight = 30;
    JPanel myPanel;
    innerCard tempPanel;
    List<innerCard> cardList = new ArrayList<innerCard>();
    Color backColor;

    public card(int x, int y, int width, int height, Color clr) {

        this.cardX = x;
        this.cardY = y;
        this.cardW = width;
        this.cardH = height;
        this.backColor = clr; 

        //Sets intial Jpane
        myPanel = new JPanel();
        myPanel.setBounds(this.cardX, this.cardY, this.cardW, this.cardH);
        myPanel.setPreferredSize(new Dimension(this.cardW,this.cardH));
        myPanel.setBackground(Color.RED);

        //Sets JlayerdPAne
        this.setBounds(this.cardX, this.cardY, this.cardW, this.cardH);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(this.cardW,this.cardH));

        this.setOpaque(false);
        this.setVisible(true);
    }

    //Change the arc of the next inner card
    public void setInnerCard(int rW, int rH) {
        this.rndWidth = rW;
        this.rndHeight = rH;
    }
    

    //Change colour of card 
    public void setColor(Color clr) {
        this.backColor = clr;
        this.setBackground(clr);

    }

    public void CreateCard(int x, int y, int width, int height, Color innerCardColour) {
        Color tempClr = innerCardColour;
        tempPanel = new innerCard(x, y, width, height, tempClr);
        tempPanel.setArc(rndWidth, rndHeight); // Set Round Edge for Inner Card


        tempPanel.setBounds(x, y, width, height);
        tempPanel.setPreferredSize(new Dimension(70, 70));
        tempPanel.setBackground(tempClr);

        tempPanel.paintComponents(getGraphics());

        this.cardList.add(tempPanel);
        
        this.add(cardList.get(cardList.size() - 1));



    }

    public JPanel getPanel(int index) {
        return cardList.get(index);
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(70, 70);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(Color.red);
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
        graphics.setColor(backColor);
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
        
    }

   


}