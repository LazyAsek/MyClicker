import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;


public class ClickerMain {
    public static void main(String[] args) throws Exception {
        
        new ClickerMain();
    }
    JPanel buttonPanel ,counterPanel,buildingsPanel,messagePanel;
    JLabel counterLabel,perSecLabel;
    JButton mouses,soldiers,bears,robots,cars,swordsmen;
    int buttonCounter,timerSpeed;
    int mouseNumber,mousePrice,soldiersNumber,soldiersPrice,bearsNumber,bearsPrice,robotsNumber,robotsPrice,carsNumber,
    carsPrice,swordsmenNumber,swordsmenPrice;
    double perSec;
    double mousePerSec,soldierPerSec,bearsPerSec,robotsPerSec,carsPerSec,swordsmenPerSec;
    Font font1,font2;
    boolean timerOn,soldiersUnlocked,bearsUnlocked,robotsUnlocked,carsUnlocked,swordsmenUnlocked,betterTimer;
    clickHandler cHandler = new clickHandler();
    Timer timer, timerScreen;
    JTextArea messageText;
    MouseHandler mHandler=new MouseHandler();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public int screenWidth ;
    public int screenHeight ;

    public ClickerMain(){

        timerOn=false;
        betterTimer=false;
        
        perSec=0;
        mousePerSec=0;
        soldierPerSec=0;
        bearsPerSec=0;
        robotsPerSec=0;
        carsPerSec=0;
        swordsmenPerSec=0;
        buttonCounter=9000;

        mouseNumber=0;
        mousePrice=10;

        soldiersNumber=0;
        soldiersPrice=100;
        soldiersUnlocked=false;

        bearsNumber=0;
        bearsPrice=500;
        bearsUnlocked=false;

        robotsNumber=0;
        robotsPrice=1000;
        robotsUnlocked=false;

        carsNumber=0;
        carsPrice=1500;
        carsUnlocked=false;

        swordsmenNumber=0;
        swordsmenPrice=2000;
        swordsmenUnlocked=false;

        
        getScreenSize();
        creatFont();
        createUI();

        
    }

    public void setTimer(){
        timer = new Timer(timerSpeed,new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(betterTimer==false){
                buttonCounter++;
                counterLabel.setText(buttonCounter+"");
                }
                else{
                buttonCounter+=perSec;
                counterLabel.setText(buttonCounter+"");
                }

                if(soldiersUnlocked==false){
                    if(buttonCounter>=100){
                        soldiersUnlocked=true;

                        soldiers.setText("Soldier("+soldiersNumber+")");
                    }
                }
                if(bearsUnlocked==false){
                    if(buttonCounter>=500){
                        bearsUnlocked=true;

                        bears.setText("Bears("+bearsNumber+")");
                    }
                }
                if(robotsUnlocked==false){
                    if(buttonCounter>=1000){
                        robotsUnlocked=true;

                        robots.setText("Robots("+robotsNumber+")");
                    }
                }
                if(carsUnlocked==false){
                    if(buttonCounter>=1500){
                        carsUnlocked=true;

                        cars.setText("Cars("+carsNumber+")");
                    }
                }
                if(swordsmenUnlocked==false){
                    if(buttonCounter>=2000){
                        swordsmenUnlocked=true;

                        swordsmen.setText("Swordsmen("+swordsmenNumber+")");
                    }
                }
            }
        });
    }
    //updates per sec
    public void timerUpdate(){
        perSec = mousePerSec+soldierPerSec+bearsPerSec+robotsPerSec+carsPerSec+swordsmenPerSec;
        if(timerOn==false){
            timerOn=true;
        }
        else {
            timer.stop();
        }
        String s = String.format("%.1f", perSec);
        perSecLabel.setText(s+"/s");

        if(perSec>20){
            timerSpeed=1000;
            betterTimer=true;
        }else{
            double speed=1/perSec*1000;
            timerSpeed=(int)Math.round(speed);
        }
        
        setTimer();
        timer.start();
    }

    public class clickHandler implements ActionListener{

        
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            switch(action){
                case "click":
                buttonCounter++;
                counterLabel.setText(buttonCounter+"");
                break;
                case "Mouses":
                 if(buttonCounter>=mousePrice){
                 mouseNumber++;
                 buttonCounter = buttonCounter - mousePrice;
                 mousePrice=(int)(mousePrice + mousePrice*0.10);
                 counterLabel.setText(buttonCounter+"");
                 mouses.setText("Mouses ("+mouseNumber+")");
                 mousePerSec = mousePerSec+0.2;
                 messageText.setText("Mouse\nPrice-"+mousePrice+"\n Clicks once per 10 secounds");
                 timerUpdate();
                }else{
                    messageText.setText("You need more clicks");
                }
                break;
                case "Soldiers":
                if(buttonCounter>=soldiersPrice){
                    soldiersNumber++;
                    buttonCounter = buttonCounter- soldiersPrice;
                    soldiersPrice = (int)(soldiersPrice + soldiersPrice*0.10);
                    counterLabel.setText(buttonCounter+"");
                    soldiers.setText("Soldiers("+soldiersNumber+")");
                    soldierPerSec=soldierPerSec+2;
                    messageText.setText("Soldiers\nPrice"+soldiersPrice+"\nClicks 2 times per sec");
                    timerUpdate();
                }else{
                    messageText.setText("You need more clicks");
                }
                break;
                case "Bears":
                if(buttonCounter>=bearsPrice){
                    bearsNumber++;
                    buttonCounter = buttonCounter -bearsPrice;
                    bearsPrice = (int)(bearsPrice+bearsPrice*0.10);
                    counterLabel.setText(buttonCounter+"");
                    bears.setText("Bears("+bearsNumber+")");
                    bearsPerSec+=20;
                    messageText.setText("Bears\nPrice"+bearsPrice+"\nClicks 2 times per sec");
                    timerUpdate();
                }else{
                    messageText.setText("You need more clicks");
                }
                break;
                case "Robots":
                if(buttonCounter>=robotsPrice){
                    robotsNumber++;
                    buttonCounter = buttonCounter -robotsPrice;
                    robotsPrice = (int)(robotsPrice+robotsPrice*0.10);
                    counterLabel.setText(buttonCounter+"");
                    robots.setText("Robots("+robotsNumber+")");
                    robotsPerSec+=40;
                    messageText.setText("Robots\nPrice"+robotsPrice+"\nClicks 2 times per sec");
                    timerUpdate();
                }else{
                    messageText.setText("You need more clicks");
                }
                break;
                case "Cars":
                if(buttonCounter>=carsPrice){
                    carsNumber++;
                    buttonCounter = buttonCounter -carsPrice;
                    carsPrice = (int)(carsPrice+carsPrice*0.10);
                    counterLabel.setText(buttonCounter+"");
                    cars.setText("Cars("+carsNumber+")");
                    carsPerSec+=80;
                    messageText.setText("Cars\nPrice"+carsPrice+"\nClicks 2 times per sec");
                    timerUpdate();
                }else{
                    messageText.setText("You need more clicks");
                }
                break;
                case "Swordsmen":
                if(buttonCounter>=swordsmenPrice){
                    swordsmenNumber++;
                    buttonCounter = buttonCounter -swordsmenPrice;
                    swordsmenPrice = (int)(swordsmenPrice+swordsmenPrice*0.10);
                    counterLabel.setText(buttonCounter+"");
                    swordsmen.setText("Swordsmen("+swordsmenNumber+")");
                    swordsmenPerSec+=160;
                    messageText.setText("Swordsmen\nPrice"+swordsmenPrice+"\nClicks 2 times per sec");
                    timerUpdate();
                }else{
                    messageText.setText("You need more clicks");
                }
                break;
            }
        }
    }

    public class MouseHandler implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e){
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
           
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
           
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
           JButton button = (JButton)e.getSource();
           if(button==mouses){
                messageText.setText("Mouse\nPrice-"+mousePrice+"\n Clicks once per 10secounds");
           }
            if(button==soldiers){
            if(soldiersUnlocked==false){
                messageText.setText("This item is curently locked!");
            }
            else{
                messageText.setText("Soldiers\nPrice"+soldiersPrice+"\nClicks 2 times per sec");
            }
           }
           if(button==bears){
           if(bearsUnlocked==false){
                messageText.setText("This item is curently locked!");
            }
            else{
                messageText.setText("bears\nPrice"+bearsPrice+"\nClicks 2 times per sec");
            }
           }
           if(button==robots){
           if(robotsUnlocked==false){
                messageText.setText("This item is curently locked!");
            }
            else{
                messageText.setText("robots\nPrice"+robotsPrice+"\nClicks 2 times per sec");
            }
           }
           if(button==cars){
           if(carsUnlocked==false){
                messageText.setText("This item is curently locked!");
            }
            else{
                messageText.setText("cars\nPrice"+carsPrice+"\nClicks 2 times per sec");
            }
           }
           if(button==swordsmen){
           if(swordsmenUnlocked==false){
                messageText.setText("This item is curently locked!");
            }
            else{
                messageText.setText("swordsmen\nPrice"+swordsmenPrice+"\nClicks 2 times per sec");
            }
           }
           
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
           JButton button=(JButton)e.getSource();
           if(button==mouses){
            messageText.setText(null);
           }
           if(button==soldiers){
            messageText.setText(null);
           }
           if(button==bears){
            messageText.setText(null);
           }
           if(button==robots){
            messageText.setText(null);
           }
           if(button==cars){
            messageText.setText(null);
           }
           if(button==swordsmen){
            messageText.setText(null);
           }
            
        }
    

    }

    public void creatFont (){
        font1= new Font("Comic Sans MS",Font.PLAIN,32);
        font2= new Font("Comic Sans MS",Font.PLAIN,16);
    }

    public void getScreenSize(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          screenWidth = (int)screenSize.getWidth();
          screenHeight = (int)screenSize.getHeight();
    }

    
    Color transparent = new Color(0,0,0,0);

    public void createUI(){
        JFrame window = new JFrame("Pain");
        window.setSize(screenWidth, screenHeight);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(new Color(118,219,139));
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setLayout(null);

        
        
        buttonPanel = new JPanel();
        buttonPanel.setBounds((int)(screenWidth-screenWidth*0.95), (int)(screenHeight-screenHeight*0.35), 320, 320);
        buttonPanel.setBackground(transparent);
        buttonPanel.setOpaque(false);
        
        window.add(buttonPanel);
        
        ImageIcon button = new ImageIcon("res/myButton.png");
        
        JButton buttonButton = new JButton();
        buttonButton.setBackground(Color.darkGray);
        buttonButton.setBorderPainted(false);
        buttonButton.setFocusPainted(false);
        buttonButton.setBorder(null);
        buttonButton.addActionListener(cHandler);
        buttonButton.setActionCommand("click");
        buttonButton.setIcon(button);
        buttonButton.setContentAreaFilled(false);
        buttonButton.setContentAreaFilled(false);

        buttonPanel.add(buttonButton);

        ImageIcon counterpanelBackground = new ImageIcon("res/counterpanelBackground.jpg");

        JLabel counterbackground = new JLabel();
        counterbackground.setBounds((int)(screenWidth-screenWidth*0.98), (int)(screenHeight-screenHeight*0.96), 350, 150);
        counterbackground.setIcon(counterpanelBackground);


        counterPanel = new JPanel();
        counterPanel.setBounds((int)(screenWidth-screenWidth*0.95), (int)(screenHeight-screenHeight*0.96), 350, 150);
        counterPanel.setLayout(new GridLayout(2,1));
        counterPanel.setBackground(transparent);
        counterPanel.setOpaque(false);
        
        window.add(counterPanel);
        window.add(counterbackground);

        counterLabel = new JLabel(buttonCounter+"");
        counterLabel.setForeground(Color.white);
        counterLabel.setFont(font1);

        counterPanel.add(counterLabel);

        perSecLabel = new JLabel(perSec+"/s");
        perSecLabel.setForeground(Color.white);
        perSecLabel.setFont(font2);

        counterPanel.add(perSecLabel);

        buildingsPanel = new JPanel();
        buildingsPanel.setBounds((int)(screenWidth-screenWidth*0.35),(int)(screenHeight-screenHeight*0.96),600,900);
        buildingsPanel.setBackground(transparent);
        buildingsPanel.setOpaque(false);
        buildingsPanel.setLayout(new GridLayout(6,1));

        ImageIcon ButtonBackground = new ImageIcon("res/buttonBuilding.jpg");

        window.add(buildingsPanel);

        mouses = new JButton("Mouses");
        mouses.setIcon(ButtonBackground);
        mouses.setBorder(null);
        mouses.setFont(font1);
        mouses.setFocusPainted(false);
        mouses.addActionListener(cHandler);
        mouses.setActionCommand("Mouses");
        mouses.addMouseListener(mHandler);
        mouses.setBackground(transparent);
        mouses.setHorizontalTextPosition(JButton.CENTER);
        mouses.setVerticalTextPosition(JButton.CENTER);

        buildingsPanel.add(mouses);

        soldiers = new JButton("?");
        soldiers.setIcon(ButtonBackground);
        soldiers.setBorder(null);
        soldiers.setFont(font1);
        soldiers.setFocusPainted(false);
        soldiers.addActionListener(cHandler);
        soldiers.setActionCommand("Soldiers");
        soldiers.addMouseListener(mHandler);
        soldiers.setBackground(transparent);
        soldiers.setHorizontalTextPosition(JButton.CENTER);
        soldiers.setVerticalTextPosition(JButton.CENTER);

        buildingsPanel.add(soldiers);

        bears = new JButton("?");
        bears.setIcon(ButtonBackground);
        bears.setBorder(null);
        bears.setFont(font1);
        bears.setFocusPainted(false);
        bears.addActionListener(cHandler);
        bears.setActionCommand("Bears");
        bears.setBackground(transparent);
        bears.addMouseListener(mHandler);
        bears.setHorizontalTextPosition(JButton.CENTER);
        bears.setVerticalTextPosition(JButton.CENTER);

        buildingsPanel.add(bears);

        robots = new JButton("?");
        robots.setIcon(ButtonBackground);
        robots.setBorder(null);
        robots.setFont(font1);
        robots.setFocusPainted(false);
        robots.addActionListener(cHandler);
        robots.setActionCommand("Robots");
        robots.setBackground(transparent);
        robots.addMouseListener(mHandler);
        robots.setHorizontalTextPosition(JButton.CENTER);
        robots.setVerticalTextPosition(JButton.CENTER);

        buildingsPanel.add(robots);

        cars = new JButton("?");
        cars.setIcon(ButtonBackground);
        cars.setBorder(null);
        cars.setFont(font1);
        cars.setFocusPainted(false);
        cars.addActionListener(cHandler);
        cars.setActionCommand("Cars");
        cars.setBackground(transparent);
        cars.addMouseListener(mHandler);
        cars.setHorizontalTextPosition(JButton.CENTER);
        cars.setVerticalTextPosition(JButton.CENTER);

        buildingsPanel.add(cars);

        swordsmen = new JButton("?");
        swordsmen.setIcon(ButtonBackground);
        swordsmen.setBorder(null);
        swordsmen.setFont(font1);
        swordsmen.setFocusPainted(false);
        swordsmen.addActionListener(cHandler);
        swordsmen.setActionCommand("Swordsmen");
        swordsmen.setBackground(transparent);
        swordsmen.addMouseListener(mHandler);
        swordsmen.setHorizontalTextPosition(JButton.CENTER);
        swordsmen.setVerticalTextPosition(JButton.CENTER);

        buildingsPanel.add(swordsmen);

        messagePanel=new JPanel();
        messagePanel.setBounds((int)(screenWidth-screenWidth*0.4555),(int)(screenHeight-screenHeight*0.96),200,150);
        messagePanel.setBackground( new Color(28,132,41));

        window.add(messagePanel);

        messageText = new JTextArea();
        messageText.setBounds((int)(screenWidth-screenWidth*0.4555),(int)(screenHeight-screenHeight*0.96),200,150);
        messageText.setBackground( new Color(28,132,41));
        messageText.setForeground(Color.black);
        messageText.setFont(font2);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messagePanel.add(messageText);



        window.setVisible(true);
    }


}
