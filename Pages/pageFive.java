package Pages;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Elements.card;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
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
            System.out.println(cardY);
            this.add(listItems[i].myCard);
        }
    
        listItems[0].SetUpPerson("App-Images/Person1.png");
        listItems[1].SetUpPerson("App-Images/Person2.png");
        listItems[2].SetUpPerson("App-Images/Person3.png");

    }

    

    class personCard {
        String personImg;
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

        ImageIcon person1;
        public void SetUpPerson(String p) {
            this.personImg = p;
            person1 = new ImageIcon(new ImageIcon(this.personImg).getImage().getScaledInstance(myCard.cardW, myCard.cardH, Image.SCALE_DEFAULT));

            JLabel personImage1 = new JLabel(person1);
            personImage1.setBounds(0, 0, myCard.cardW, myCard.cardH - 10);
            myCard.getPanel(0).add(personImage1);

        }


        personCard(int cardX, int cardY, int cardW, int cardH) {

            //Create Card
            myCard = new card(cardX, cardY, cardW, cardH, Color.red);
            myCard.setInnerCard(30, 30);
            myCard.CreateCard(0, 0, cardW, cardH - 50, Color.BLUE); //Image Card // 0
            myCard.getPanel(0).setLayout(null);


            myCard.setInnerCard(0, 0);
            myCard.CreateCard(0,  cardH - 51, cardW, 50, Color.RED); //Lower Card //1


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
