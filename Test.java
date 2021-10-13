package Pk2;

import Pk1.Car;
import Pk1.Person;
import Pk1.Point;
import Pk1.SdCar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.lang.reflect.Method;
import java.text.AttributedCharacterIterator;
import java.util.*;


public class Test extends NWbotton implements ActionListener {

    public static void main(String[] args) {

        Pedone ni=new Pedone();
        final NWbotton[] traccia = {new NWbotton()};

//        ImageIcon icon = new ImageIcon("torre2.png"); esempio non funzionante

        JFrame f = new JFrame("Scacchiera");
        JPanel gui = new JPanel(new BorderLayout());
        JPanel p1=new JPanel(new GridLayout(8,8,2,2));
        JPanel p2=new JPanel(new GridLayout(1,8,2,2));
        JPanel p3=new JPanel(new GridLayout(8,1,2,2));
//creo le label
        int j=0;
        Map<Integer,NWbotton> griglia=new HashMap<Integer,NWbotton>();
        for(int i=0;i<64;++i) {
            for(int d=0;d<8;++d){
                NWbotton s=new NWbotton();
                s.setIndex(j);
                s.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getActionCommand()=="torre"){
                            NWbotton b=(NWbotton) e.getSource();
                            System.out.println(b.getIndex());
                            griglia.get(b.getIndex()+8).setBackground(Color.red);
                            griglia.get(b.getIndex()+8).setValid(true);
                            traccia[0] =b;
                        }
                        if(e.getActionCommand()=="vuoto bianco"){
                            NWbotton b=(NWbotton) e.getSource();
                            if(b.isValid()==true){
                                griglia.get(traccia[0].getIndex()).setIcon(null);
                                griglia.get(b.getIndex()).setIcon(new ImageIcon(ni.getImg()));
                                b.setBackground(Color.white);
                                System.out.println(b.getIndex());

                            }
                        }
                    }
                });
                if(i>=0&&i<=7) {
                    s.setIcon(new ImageIcon(ni.getImg()));
                    s.setActionCommand("torre");
                }
                if(i>=63&&i<71) {
                    s.setIcon(new ImageIcon(ni.getImg()));
                }
                if (i % 2 == 0) {
                    s.setBackground(Color.black);
                //    s.setActionCommand("vuoto bianco");
                }
                else {
                    s.setBackground(Color.white);
                    s.setActionCommand("vuoto bianco");
                }
                griglia.put(j,s);
                ++i;
                ++j;
                p1.add(s);
            }
        }


        NWbotton prova=new NWbotton();
        prova.setIcon(new ImageIcon(ni.getImg()));
        prova.setIndex(1);
        prova.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand()=="torre"){
                    NWbotton b=(NWbotton) e.getSource();
                    System.out.println(b.getIndex());
                    griglia.get(b.getIndex()+8).setBackground(Color.red);
                    griglia.get(b.getIndex()+8).setValid(true);
                    traccia[0] =b;
                }
                if(e.getActionCommand()=="vuoto bianco"){
                    NWbotton b=(NWbotton) e.getSource();
                    if(b.isValid()==true){
                        griglia.get(traccia[0].getIndex()).setIcon(null);
                        griglia.get(b.getIndex()).setIcon(new ImageIcon(ni.getImg()));
                        b.setBackground(Color.white);
                    }
                }
            }
        });
        p2.add(prova);
        prova.setActionCommand("torre");
        prova.setBackground(Color.white);
        System.out.println(prova);
        System.out.println(j);

        //JLabel label =new JLabel("");
        //p2.add(label);
        for(int g=0;g<8;++g) {
            p2.add(new Label(""+(g+1)));
            p3.add(new Label(""+(g+1)));
        }
        gui.add(p1,BorderLayout.CENTER);
        gui.add(p2,BorderLayout.NORTH);
        gui.add(p3,BorderLayout.WEST);

        griglia.get(7).setIcon(null);
        griglia.get(15).setIcon(new ImageIcon(ni.getImg()));

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(400, 400);
        f.setVisible(true);
        f.setContentPane(gui);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

//coloro caselle
 /*       for(int i=0;i<64;++i) {
            for(int d=0;d<8;++d){
                JButton b = new JButton();
                if (i % 2 == 0) {
                    b.setBackground(Color.black);
                }
                else {
                    b.setBackground(Color.white);
                }
                ++i;
                p1.add(b);

            }


        }*/

        /*
        JButton button = new JButton();
        Pedone p = new Pedone();
        button.setIcon(new ImageIcon(p.getImg()));
        gui.add(button);
        gui.setBackground(Color.white);
*/