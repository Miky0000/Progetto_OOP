package Tests;
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

import Pezzi_Scacchi.*;
import Pezzi_Scacchi.Pedone;

import static java.awt.Color.*;


public class Test  { //extends NWbotton implements ActionListener

    public static void main(String[] args) {



        JFrame f = new JFrame("Scacchiera");
        JPanel gui = new JPanel(new BorderLayout());

        //variabili gestione pezzi
        Pedone p=new Pedone("black");  //oggetto per creare le immagini
        final NWbotton[] traccia=new NWbotton[1];    //NWbotton per tenere traccia

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
                //s.setPezzo(new Pedone("black"));
                s.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("azione1"))
                        {
                            NWbotton b= (NWbotton) e.getSource();
                            if (b.getPezzo()==null)
                                return;
                            traccia[0]=b;
                            ArrayList<Integer> mosse= b.getPezzo().getMoves(b.getIndex(),griglia);
                            if (mosse.isEmpty())
                                return;
                            for (Integer index: mosse) {
                                griglia.get(index).setBackground(RED);

                            }
                            for (int i=0; i<64;i++){
                                    griglia.get(i).setActionCommand("azione2");
                            }
                        }
                        if(Objects.equals(e.getActionCommand(), "azione2"))
                        {
                            NWbotton b= (NWbotton) e.getSource();

                            if (b.getBackground()==red) {
                                b.restore();
                                b.setPezzo(traccia[0].getPezzo());
                                b.setIcon(new ImageIcon(b.getPezzo().getImg()));
                                traccia[0].restore();
                            }
                            else if (traccia[0]==b);
                            else{
                                return;
                            }
                            for (int i=0; i<64;i++) {
                                if (griglia.get(i).getBackground() == red)
                                    griglia.get(i).restore_color_only();

                                griglia.get(i).setActionCommand("azione1");
                            }
                        }
                    }
                });

                //creo le caselle vuote
                if ((j/8 + j%8)%2==0) {
                    s.setBackground(Color.black); //nere

                }
                else {
                    s.setBackground(Color.white);   //bianche

                }
                if (j==0 || j==7){
                    s.setPezzo(new Torre("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                if (j==1 || j==6){
                    s.setPezzo(new Cavallo("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                if (j==2 || j==5){
                    s.setPezzo(new Alfiere("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                if (j==3){
                    s.setPezzo(new Regina("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                if (j==4){
                    s.setPezzo(new Re("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                if (j>=8 && j<=15){
                    s.setPezzo(new Pedone("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                if (j==56 || j==63){
                    s.setPezzo(new Torre("white"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                if (j==57 || j==62){
                    s.setPezzo(new Cavallo("white"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                if (j==58 || j==61){
                    s.setPezzo(new Alfiere("white"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                if (j==59){
                    s.setPezzo(new Regina("white"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                if (j==60){
                    s.setPezzo(new Re("white"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                if (j>=48 && j<=55){
                    s.setPezzo(new Pedone("white"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }

                griglia.put(j,s);   //li inserisco nella hashmap
                ++i;
                ++j;
                p1.add(s);  //aggiungo i bottoni al pannello ad ogni ciclo
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

}

