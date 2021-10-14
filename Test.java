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

import static java.awt.Color.red;


public class Test extends NWbotton implements ActionListener {

    public static void main(String[] args) {



        JFrame f = new JFrame("Scacchiera");
        JPanel gui = new JPanel(new BorderLayout());

        //variabili gestione pezzi
        Pedone p=new Pedone();  //oggetto per creare le immagini
        final NWbotton[] traccia = {new NWbotton()};    //NWbotton per tenere traccia

        //creo i pannelli
        JPanel p1=new JPanel(new GridLayout(8,8,2,2));
        JPanel p2=new JPanel(new GridLayout(1,8,2,2));
        JPanel p3=new JPanel(new GridLayout(8,1,2,2));

        //creo le label 1-8
        JLabel label =new JLabel("");
        p2.add(label);
        for(int g=0;g<8;++g) {
            p2.add(new Label(""+(g+1)));
            p3.add(new Label(""+(g+1)));
        }

        //creo la griglia di NWbotton
        Map<Integer,NWbotton> griglia=new HashMap<Integer,NWbotton>();
        int j=0;    //indice pedoni 0-63
        for(int i=0;i<64;++i) {
            for(int d=0;d<8;++d){
                NWbotton s=new NWbotton();  //bottone corrente
                s.setIndex(j);
                s.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getActionCommand()=="torre bianco g1"){    //azioni quando viene premuto un pezzo del g1
                            NWbotton b=(NWbotton) e.getSource();    //bottone corrente per gestire gli actionevent
                            System.out.println(b.getIndex());   //stampa di prova per visualizzare l'index corrente con getIndex di NWbotoon
                            griglia.get(b.getIndex()+8).setBackground(red); //settaggio bottone rosso per la mossa corrente
                            griglia.get(b.getIndex()+8).setValid(true); //settaggio a true dell bottone rosso per mossa valida
                            traccia[0] =b;  //tengo traccia del bottone corrente per distinguere le mosse dei giocatori
                        }
                        if(e.getActionCommand()=="torre nero g1"){      //uguali alla prima per g1
                            NWbotton b=(NWbotton) e.getSource();
                            System.out.println(b.getIndex());
                            griglia.get(b.getIndex()+8).setBackground(red);
                            griglia.get(b.getIndex()+8).setValid(true);
                            traccia[0] =b;
                        }
                        if(e.getActionCommand()=="torre bianco g2"){    //uguali alla prima per g2
                            NWbotton b=(NWbotton) e.getSource();
                            System.out.println(b.getIndex());
                            griglia.get((b.getIndex())-8).setBackground(red);
                            griglia.get((b.getIndex())-8).setValid(true);
                            traccia[0] =b;
                        }
                        if(e.getActionCommand()=="torre nero g2"){      //uguali alla prima per g2
                            NWbotton b=(NWbotton) e.getSource();
                            System.out.println(b.getIndex());
                            griglia.get((b.getIndex())-8).setBackground(red);
                            griglia.get((b.getIndex())-8).setValid(true);
                            traccia[0] =b;
                        }
                        if(e.getActionCommand()=="vuoto bianco"){   //azioni quando viene premuta una una casella vuota bianca
                            NWbotton b=(NWbotton) e.getSource();    //ottone corrrente
                            if(b.isValid()==true && traccia[0].getActionCommand()=="torre nero g2"){    //controllo se il bottone è valido come mossa e con traccia distinguo i giocatori
                                griglia.get(traccia[0].getIndex()).setIcon(null);   //setto il bottone precedente con l'icona a null
                                griglia.get(b.getIndex()).setIcon(new ImageIcon(p.getImg()));   //sposto l'icona sul nuovo bottone
                                b.setActionCommand("torre bianco g2");  //aggiorno actioncommand da vuoto bianco a torre
                                traccia[0].setActionCommand("vuoto bianco");    //aggiorno actioncommand del bottone traccia da torre a vuoto
                                b.setBackground(Color.white);   //aggiorno il colore da rosso a quello precedente in base al actioncommand
                                b.setValid(false);  //invalido il bottone con la torre
                                System.out.println(b.getIndex());   //stampa di prova

                            }
                            else if(b.isValid()==true && traccia[0].getActionCommand()=="torre nero g1"){   //uguale a prima solo che nell'else if distingue i giocatori con il bottone traccia
                                griglia.get(traccia[0].getIndex()).setIcon(null);
                                griglia.get(b.getIndex()).setIcon(new ImageIcon(p.getImg()));
                                b.setActionCommand("torre bianco g1");
                                traccia[0].setActionCommand("vuoto bianco");
                                b.setBackground(Color.white);
                                b.setValid(false);
                                System.out.println(b.getIndex());

                            }
                        }
                        if(e.getActionCommand()=="vuoto nero"){
                            NWbotton b=(NWbotton) e.getSource();
                            if(b.isValid()==true && traccia[0].getActionCommand()=="torre bianco g2"){
                                griglia.get(traccia[0].getIndex()).setIcon(null);
                                griglia.get(b.getIndex()).setIcon(new ImageIcon(p.getImg()));
                                b.setActionCommand("torre nero g2");
                                traccia[0].setActionCommand("vuoto nero");
                                b.setBackground(Color.black);
                                b.setValid(false);
                                System.out.println(b.getIndex());

                            }
                            else if(b.isValid()==true && traccia[0].getActionCommand()=="torre bianco g1"){
                                griglia.get(traccia[0].getIndex()).setIcon(null);
                                griglia.get(b.getIndex()).setIcon(new ImageIcon(p.getImg()));
                                b.setActionCommand("torre nero g1");
                                traccia[0].setActionCommand("vuoto nero");
                                b.setBackground(Color.black);
                                b.setValid(false);
                                System.out.println(b.getIndex());

                            }
                        }
                    }
                });

                if (i % 2 == 0) {
                    s.setBackground(Color.black);
                    s.setActionCommand("vuoto nero");
                    if(i>=0&&i<=7) {//torri g1
                        s.setIcon(new ImageIcon(p.getImg()));
                        s.setActionCommand("torre nero g1");
                    }
                    if(i>=63&&i<71) {//torri g2
                        s.setIcon(new ImageIcon(p.getImg()));
                        s.setActionCommand("torre nero g2");
                    }
                }
                else {
                    s.setBackground(Color.white);
                    s.setActionCommand("vuoto bianco");
                    if(i>=0&&i<=7) {//torrig1
                        s.setIcon(new ImageIcon(p.getImg()));
                        s.setActionCommand("torre bianco g1");
                    }
                    if(i>=63&&i<71) {//torri g2
                        s.setIcon(new ImageIcon(p.getImg()));
                        s.setActionCommand("torre bianco g2");
                    }
                }
                if(i>=63&&i<71) {
                    s.setIcon(new ImageIcon(p.getImg()));
                }
                griglia.put(j,s);
                ++i;
                ++j;
                p1.add(s);
            }
        }



        gui.add(p1,BorderLayout.CENTER);
        gui.add(p2,BorderLayout.NORTH);
        gui.add(p3,BorderLayout.WEST);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(400, 400);
        f.setVisible(true);
        f.setContentPane(gui);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

/*come spostare i pezzi
        griglia.get(7).setIcon(null);
        griglia.get(15).setIcon(new ImageIcon(ni.getImg()));
*/
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