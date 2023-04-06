package Elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class innerCard extends JPanel implements ActionListener{


    public int inX, inY, inW, inH;
    public Color inClr;
    public int rndW, rndH;

    innerCard(int x, int y, int w, int h, Color clr) {
        this.inX = x;
        this.inY = y;
        this.inW = w;
        this.inH = h;
        this.inClr = clr;

        this.setOpaque(false);
        super.setBounds(this.inX, this.inY, this.inW, this.inH);
        this.setBackground(inClr);
    }

    public void setArc(int RoundW, int RoundH) {
        this.rndW = RoundW;
        this.rndH = RoundH;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(this.rndW, this.rndW);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
        graphics.setColor(this.inClr);
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
