package Pages;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Elements.card;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

import Elements.card;
import Swing.Login;

public class pageFive extends JPanel {

    int cardX, cardY, cardOffSet;
    personCard listItems[];
    pageFive myRef = this;

    public pageFive(int width, int height) {
        this.setBackground(Color.WHITE);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
        this.setLayout(null);

        listItems = new personCard[3];

        cardX = Login.getDimen(width, .25);
        cardY =  Login.getDimen(height, .75);
        cardOffSet = 15;

        for (int i = 0; i < 3; i++) {
            listItems[i] = (new personCard((cardX * i) + 15, 50, cardX - 15, cardY));
            this.add(listItems[i].myCard);
        }
    

        
    }

    

    class personCard {
        public card myCard;
        boolean timerStartEnter = false, timerStartExit = false;
        Timer moveTimeDown = new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                myCard.setLocation(myCard.getLocation().x, myCard.getLocation().y + 5);
                
                if (myCard.getLocation().y > 70) {
                    moveTimeDown.stop();
                    timerStartEnter = false;
                }
            }
            
        });

        Timer moveTimeUp = new Timer(0, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                myCard.setLocation(myCard.getLocation().x, myCard.getLocation().y - 5);
                
                if (myCard.getLocation().y <= 50) {
                    moveTimeUp.stop();
                    timerStartExit = false;
                }
            }
        });
        personCard(int cardX, int cardY, int cardW, int cardH) {
            myCard = new card(cardX, cardY, cardW, cardH, Color.red);
            
            myCard.CreateCard(0, 0, cardW, cardH, Color.BLACK); //Inner Card

            myCard.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                   // myCard.updateHeight();
                   if (timerStartEnter == false) {
                        moveTimeDown.start();
                        timerStartEnter = true;
                   }
                   moveTimeUp.stop();
                   timerStartExit = false;

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (timerStartExit == false) {
                        moveTimeUp.start();
                        timerStartExit = true;
                    }
                    moveTimeDown.stop();
                    timerStartEnter = false;
                }
                
            });

        }

        
    }
}
