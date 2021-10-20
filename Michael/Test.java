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

import static java.awt.Color.*;


public class Test{

    public static void main(String[] args) {

        MenùPrincipale Menuprincipale=new MenùPrincipale();
/*        JFrame f = new JFrame("Scacchiera");
        JPanel gui = new JPanel(new BorderLayout());

        //variabili gestione pezzi
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
                        NWbotton b=(NWbotton) e.getSource();

                        if(e.getActionCommand()=="azione1"){    //premo un pezzo
                            ArrayList<Integer> mosse=b.getPezzo().getMoves(b.getIndex(), griglia);  //genero l'array di mosse possibili
                            for(Integer f:mosse){   //setto le mosse possibili a rosso ed ad azione2
                                griglia.get(f).setBackground(red);
                                griglia.get(f).setActionCommand("azione2");
                            }
                            traccia[0]=b;   //tengo traccia del bottone
                        }

                        if(e.getActionCommand()=="azione2"){    //sposto il pezzo sul rosso
                            ArrayList<Integer> mosse=traccia[0].getPezzo().getMoves(traccia[0].getIndex(), griglia);    //genero l'array di mosse possibili del pedone traccia
                            b.setPezzo(traccia[0].getPezzo());  //setto pezzo
                            traccia[0].setIcon(null);   //cancello immagine
                            traccia[0].setActionCommand(null);     //annullo actioncommad
                            int c=0;    //variabile per scorrere il for
                            for(Integer f: mosse){
                                griglia.get(mosse.get(c)).setBackground(griglia.get(mosse.get(c)).getColore());//ripristino il colore dei bottoni non premuti
                                griglia.get(mosse.get(c)).setActionCommand(null);   //ripristino il setacitoncommand dei bottoni rossi non premuti e anche quello premuto
                                ++c;
                            }
                            b.setActionCommand("azione1");  //setto il bottone rosso appena premuto ad azione1
                        }
                    }
                });

                //creo le caselle vuote
                if (i % 2 == 0) {
                    s.setBackground(Color.black); //nere
                    s.setColore(black);
                }
                else {
                    s.setBackground(Color.white);   //bianche
                    s.setColore(white);
                }
                //riempio i pedoni neri
                if(i>=8&&i<=16) {//torri g1
                    s.setPezzo(new Pedone("black"));
                    s.setIcon(new ImageIcon(s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                //riempio i pedoni bianchi
                if(i>=54&&i<62) {//torri g2
                    s.setPezzo(new Pedone("white"));
                    s.setIcon(new ImageIcon(s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }

                griglia.put(j,s);   //li inserisco nella hashmap
                ++i;
                ++j;
                p1.add(s);  //aggiungo i bottoni al pannello ad ogni ciclo
            }
        }

        //attacco tutto al pannello principale
        gui.add(p1,BorderLayout.CENTER);
        gui.add(p2,BorderLayout.NORTH);
        gui.add(p3,BorderLayout.WEST);

        //attacco il pannello principale al frame
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(400, 400);
        f.setVisible(true);
        f.setContentPane(gui);*/
    }
}