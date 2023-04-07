package Swing;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Elements.HintTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import javax.swing.Timer;
import javax.swing.border.Border;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;




public class Login extends JFrame implements ActionListener {

    public static Color colorScheme[] = {Color.decode("#76515E"), Color.decode("#517669"), Color.decode("#385148"), Color.decode("#27332F"), Color.decode("#d7f8c2")};

    //Logo
    Icon companyLogo;
    JLabel companyLabel, companySlogan;
    //Logo End

    float width = 750, height = 700;
    JTextField passText, userText;
    JLabel registerLabel;
    JButton submit; 
    JPanel imagePanel, inputPanel;
    static Boolean registration = false;

    String imageString[] = {"App-Images/PlaceHolder.png", "App-Images/PlaceHolder.png", "App-Images/PlaceHolder.png"};

    public static void closeRegistration() {
        registration = false;
        System.out.println(registration);
    }

    public static int getDimen(float n, double d) {
        return (int) Math.round(n * d);
    }

    //Clean up Code Spacing using this
    public static int[] calculateOffSet(int size, int offSet, int origY) {
        int arrOffSet[] = new int[size];
        for (int i = 0; i < size; i++) {
            arrOffSet[i] = origY + (i * offSet);
        }
        return arrOffSet;
    }
    
    Login() {
        companyLogo = new ImageIcon(new ImageIcon("App-Images/CompanyLogo.png").getImage().getScaledInstance(200, 250, Image.SCALE_DEFAULT));
        companyLabel = new JLabel(companyLogo);
        companyLabel.setBounds(57, 25, 200, 250);

        companySlogan = new JLabel("<html>FINANCE<br>&nbsp;&nbsp;&nbsp;&nbsp;THE<br>&nbsp;PEOPLE</html>");
        companySlogan.setForeground(colorScheme[4]);
        companySlogan.setFont(new Font("Serif", Font.BOLD, 30));
        companySlogan.setPreferredSize(new Dimension(250, 250));
        companySlogan.setBounds(75, 200, 250, 250);


        this.setTitle("Login Page");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        //Instantiates image Holder
        imagePanel = new imgHolder(getDimen(width, .58), (int)height);
        ((imgHolder) imagePanel).setImage(imageString);


        //Input Values 
        int lineOffset = 10; //spacing between text
        int posOffset = 450; //X position of text fields

        //Instantiates input Holder
        inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(getDimen(width, .58), 0, getDimen(width, .40), (int)height);
        inputPanel.setBackground(colorScheme[0]);
        inputPanel.setFocusable(true);
        inputPanel.requestFocus();

        //Username Text Field
        userText = new HintTextField("  Username");
        userText.setBounds(20, ((getDimen(height, .05) * 0) + posOffset) + lineOffset * 0, getDimen(width, .35), getDimen(height, .05));
        userText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        userText.addActionListener(this);

        //Set Desgin
        userText.setBackground(colorScheme[1]);
        userText.setForeground(colorScheme[4]);



        //Password Text Field
        passText = new HintTextField("  Password");
        passText.setBounds(20, ((getDimen(height, .05) * 1) + posOffset) + lineOffset * 1, getDimen(width, .35), getDimen(height, .05));
        passText.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Removes TextField Border
        passText.addActionListener(this);


        //Set Desgin
        passText.setBackground(colorScheme[1]);
        passText.setForeground(colorScheme[4]);
        


        //Login Button
        submit = new JButton("LOGIN");
        submit.setBounds(40, ((getDimen(height, .05) * 2) + posOffset) + lineOffset * 2, getDimen(width, .30), getDimen(height, .05));
        submit.addActionListener(this);
submit.setBorder(javax.swing.BorderFactory.createEmptyBorder());


        submit.setBackground(colorScheme[3]);
        submit.setForeground(colorScheme[4]);


        //Keylistener for Enter Key
        KeyListener listener =  new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submit.doClick();
                }
            }
        };

        submit.addKeyListener(listener);
        passText.addKeyListener(listener);
        userText.addKeyListener(listener);

        submit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                submit.setForeground(colorScheme[2]);
                submit.setBackground(colorScheme[3]);

            }

            public void mouseExited(MouseEvent me) {
                submit.setBackground(colorScheme[3]);
                submit.setForeground(colorScheme[4]);

            }        
        });

        //Register button
        registerLabel = new JLabel("Click here to register");
        registerLabel.setBounds(getDimen(width, .12), ((getDimen(height, .05) * 3) + posOffset) + lineOffset * 3, getDimen(width, .35), getDimen(height, .05));
        registerLabel.setForeground(colorScheme[4]);
        registerLabel.addMouseListener(new MouseAdapter() { //Register Button Action Listener
            public void mouseClicked(MouseEvent me) {

                if (registration == false) {
                    new Register(); // Create a function that closes this jframe to open another
                    registration = true;
                } else {
                    System.out.println(registration);
                }
                
              }

            public void mouseEntered(MouseEvent me) {
                registerLabel.setForeground(Color.RED);

            }

            public void mouseExited(MouseEvent me) {
                registerLabel.setForeground(colorScheme[4]);
            }
        });

        inputPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submit.doClick();
                }
            }
        });

        //Adding to panel
        inputPanel.add(passText);
        inputPanel.add(userText);
        inputPanel.add(submit);
        inputPanel.add(registerLabel);
        inputPanel.add(companyLabel);
        inputPanel.add(companySlogan);

        this.add(imagePanel);
        this.add(inputPanel);
        



        //Always at the bottom
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource() == submit) {
            if (userText.getText().length() != 0 && passText.getText().length() != 0) {
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                this.dispatchEvent(new WindowEvent(new Dashboard(), WindowEvent.WINDOW_CLOSING));
                imgHolder.animateAgain.stop();
            } else {
                JOptionPane.showMessageDialog(this, "Please Input a valid username and password.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
       

        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


    class imgHolder extends JPanel {

        int width, height;

        private BufferedImage image[];

        static Timer animateAgain;
        boolean animateRight;
        Timer animTimer;
        int delay = 8;
        int curTime = 0;

        int imageWidth = this.width;
        int imageHeigth = this.height;
        int offSet[];
        //Positional Calculation
        // if in 0 position = stationary, -width = previous, width = next;
        int imgPovPrev;
        int imgPovPres;
        int imgPovNext;
        int cur;
        boolean started = false;


        int Count;
        imgHolder(int width, int height) {
            //Define Class Variables
            this.width = width;
            this.height = height;
            Count = 0;

            //Animation Values
            offSet = new int[]{-this.width, 0, this.width};
            
            imgPovPrev = offSet[0];
            imgPovPres = offSet[1];
            imgPovNext = offSet[2];
            this.animateRight = false;

            //Define JPanel
            this.setBackground(Color.black);
            this.setBounds(0, 0, this.width, this.height);

            animTimer = new Timer(10, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int speed= 10;
                    
                    if (animateRight) {
                        imgPovPrev += speed;
                        imgPovPres += speed;
                        imgPovNext += speed;

                        //Is called When Initiliazing Value
                        if (cur == 0 && started == false) {
                            cur = imgPovNext;
                            started = true;
                            Count++;
                        }
                        cur += speed;
                        if (cur >  offSet[2] + width ) { // offSet[2] + width + 5
                            animateRight = false;
                            System.out.print(" Cur: " + imgPovPres + " Prev: " + imgPovPrev + " Next: " + imgPovNext + " ");
                            System.out.println("The Current "+ cur);
                            System.out.println("The Count "+ Count);

                            //Checks if Image has looped, if it has looped we change the cur value reference
                           switch (Count) {
                                case 0: 
                                    //cur = imgPovPrev;
                                    cur = imgPovNext;
                                    Count++;
                                    System.out.println("0");
                                    break;
                                case 1:
                                    //cur = imgPovNext;
                                    cur = imgPovPres;
                                    System.out.println("1");
                                    Count++;
                                    break;
                                case 2:
                                    //cur = imgPovPres;
                                    cur = imgPovPrev;
                                    System.out.println("2");
                                    Count++;
                                    break;
                                default:
                                    break;
                           }

                           if (Count >= 3) {
                                Count = 0;
                           }

                        }

                        if (imgPovPres >= offSet[2] + width) {
                            imgPovPres = offSet[0];

                        }

                        if (imgPovNext >= offSet[2] + width) {
                            imgPovNext = offSet[0] ;

                        }

                        if (imgPovPrev >= offSet[2] + width) {
                            imgPovPrev = offSet[0];

                        }

                    } 


                    repaint();

                }
                
            });


            animateAgain = new Timer(1000, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    curTime++;
                    System.out.print(curTime);

                    if (curTime >= delay) {
                        curTime = 0;
                        animateRight = true;
                        animTimer.start();
                    }
                }
                
            });

            if (this.animateRight == false) {
                this.animateRight = true;
                animateAgain.start();
                System.out.print("starting");
            } 
            
        }

    
        public void setImage(String imgString[]) {
            
            int n = imgString.length;
            image = new BufferedImage[n];
            try {
                for (int i = 0; i < n; i++) {
                    this.image[i] = ImageIO.read(new File(imgString[i]));
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }


        } 



        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(this.image[0], imgPovPrev, 0, this.width, this.height, this);
            g.drawImage(this.image[1], imgPovPres, 0, this.width, this.height, this);
            g.drawImage(this.image[2], imgPovNext, 0, this.width, this.height, this);

        }        
    }
}
