package Pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Elements.card;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import org.jfree.util.Log;

import Elements.card;
import Swing.Login;
import Events.DoublyLinkedList;
import Events.node;

public class pageFive extends JPanel implements ActionListener {

    int cardX, cardY, cardOffSet;
    personCard listItems[];
    pageFive myRef = this;
    JLabel personTitle;
    JButton nxtLeft, nxtRight;
    DoublyLinkedList myPeople;

    public pageFive(int width, int height) {
        this.setBackground(Color.WHITE);
        this.setBounds(Login.getDimen(width, .23), 0, Login.getDimen(width, .75), (int) height);
        this.setLayout(null);

        myPeople = new DoublyLinkedList();

        /* Button And Linked List */
        nxtLeft = new JButton("<");
        nxtRight = new JButton(">");
        
        nxtLeft.setBounds( 5 , Login.getDimen(height, .85), Login.getDimen(width, .08), Login.getDimen(width, .05));
        nxtRight.setBounds( Login.getDimen(width, .68) , Login.getDimen(height, .85), Login.getDimen(width, .08), Login.getDimen(width, .05));
        nxtLeft.addActionListener(this);
        nxtRight.addActionListener(this);

        /*Button End */

        listItems = new personCard[3];

        cardX = Login.getDimen(width, .25);
        cardY =  Login.getDimen(height, .75);
        cardOffSet = 15;


        for (int i = 0; i < 3; i++) {
            listItems[i] = (new personCard((cardX * i) + 10, 50, cardX - 15, cardY));
            System.out.println(cardY);
            this.add(listItems[i].myCard);
        }
        
        myPeople.addNodeFront(new peopleObject("App-Images/Person2.png", "OLD MAN"));
        myPeople.addNodeFront(new peopleObject("App-Images/Person3.png", "JEROME"));
        myPeople.addNodeFront(new peopleObject("App-Images/Person4.png", "MR P."));
        myPeople.addNodeFront(new peopleObject("App-Images/Person5.png", "LILLITH"));
        myPeople.addNodeFront(new peopleObject("App-Images/Person6.png", "KLLYDE"));
        myPeople.addNodeFront(new peopleObject("App-Images/Person1.png", "LUTHER"));


        myPeople.printAll();
       

        setUpPeople();

        

        personTitle =  new JLabel("MEET NEW PEOPLE");
        personTitle.setFont(new Font("SANS", Font.BOLD, 18));
        personTitle.setBounds(290,475, 400, 200);
        this.add(nxtLeft);
        this.add(nxtRight);
        this.add(personTitle);
    }

    public void setUpPeople() {
        node curNode = myPeople.nodeCur; // Change Every Button Click

        //Prev Person
        listItems[0].SetUpPerson(((peopleObject) curNode.prev.val).img);
        listItems[0].setupName(((peopleObject) curNode.prev.val).name);
        
        //Cur Person
        listItems[1].SetUpPerson(((peopleObject) curNode.val).img);
        listItems[1].setupName(((peopleObject) curNode.val).name);

        //Next Person
        listItems[2].SetUpPerson(((peopleObject) curNode.next.val).img);
        listItems[2].setupName(((peopleObject) curNode.next.val).name);

    }

    
    public class peopleObject {
        public String img;
        public String name;
        peopleObject(String image, String n) {
            this.img = image;
            this.name = n;
        }
    }

    class personCard {
        String name;
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
        JLabel personImage1 = new JLabel(person1);
        public void SetUpPerson(String p) {
            this.personImg = p;
            person1 = new ImageIcon(new ImageIcon(this.personImg).getImage().getScaledInstance(myCard.cardW, myCard.cardH, Image.SCALE_DEFAULT));

            personImage1.setIcon(person1);
            personImage1.setBounds(0, 0, myCard.cardW, myCard.cardH - 10);
            myCard.getPanel(0).add(personImage1);

        }

        JLabel txtName = new JLabel(this.name);

        public void setupName(String name) {
            this.name = name;

            txtName.setText(this.name);
            txtName.setBounds(Login.getDimen(myCard.cardW, .40), 0, myCard.cardW, 50);
            txtName.setForeground(Color.WHITE);
            myCard.getPanel(1).add(txtName);

        }

        personCard(int cardX, int cardY, int cardW, int cardH) {
            //Create Card
            myCard = new card(cardX, cardY, cardW, cardH, Login.colorScheme2[2]);
            myCard.setInnerCard(30, 30);
            myCard.CreateCard(0, 0, cardW, cardH - 50, Color.BLUE); //Image Card // 0
            myCard.getPanel(0).setLayout(null);

            

            myCard.setInnerCard(0, 0);
            myCard.CreateCard(0,  cardH - 51, cardW, 50, Login.colorScheme2[2]); //Lower Card //1
            myCard.getPanel(1).setLayout(null);
           


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

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == nxtLeft) {
            myPeople.iterateBackward();
            setUpPeople();
            //System.out.print("Going Left");
        } 
        
        if (e.getSource() == nxtRight) {
            myPeople.iterateForward();
            setUpPeople();
            //System.out.print("Going Right");
        }

    }
}
