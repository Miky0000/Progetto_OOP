package Pk2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.awt.Color.RED;
import static java.awt.Color.red;



public class Scacchi_2 {

    JFrame f = new JFrame("Scacchiera");
    JPanel gui = new JPanel(new BorderLayout());

    //variabili gestione pezzi
    final NWbotton[] traccia = {new NWbotton()};    //NWbotton per tenere traccia
    boolean turno=true; //true=bianco false=nero
    boolean win=false;

    //label per tenere conto dei pezzi mangiati
    JLabel torre=new JLabel("0");
    //creo i pannelli
    JPanel p1=new JPanel(new GridLayout(8,8,2,2));
    JPanel p2=new JPanel(new GridLayout(1,8,2,2));
    JPanel p3=new JPanel(new GridLayout(8,1,2,2));
    JPanel p4=new JPanel(new BorderLayout());
    JPanel p5=new JPanel(new GridLayout(6,2,2,2));
    JButton tornaindietro=new JButton("MENU PRINCIPALE");

    Scacchi_2() {
        //creo le label 1-8
        JLabel label = new JLabel("");
        p2.add(label);
        for (int g = 0; g < 8; ++g) {
            p2.add(new Label("" + (g + 1)));
            p3.add(new Label("" + (g + 1)));
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

                        if (e.getActionCommand().equals("azione1"))
                        {
                            NWbotton b= (NWbotton) e.getSource();
                            if (b.getPezzo()==null || (turno==true && b.getPezzo().getColor().equalsIgnoreCase("black") || (turno==false && b.getPezzo().getColor().equalsIgnoreCase("white"))) ){
                                System.out.println("il pezzo non appartiene al giocatore che sta compiendo il turno");
                                return;
                            }

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
                                turno=!turno;
                                System.out.println(turno);
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
                if (i % 2 == 0) {
                    s.setBackground(Color.black); //nere
                    s.setColore(Color.black);
                }
                else {
                    s.setBackground(Color.white);   //bianche
                    s.setColore(Color.white);
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
                //torri nere
                if (j==0 || j==7){
                    s.setPezzo(new Torre("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                    torre.setIcon(new ImageIcon(s.getPezzo().getImg()));
                }
                //cavalli neri
                if (j==1 || j==6){
                    s.setPezzo(new Cavallo("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                //alfieri neri
                if (j==2 || j==5){
                    s.setPezzo(new Alfiere("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                //regina nera
                if (j==3){
                    s.setPezzo(new Regina("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                //re nero
                if (j==4){
                    s.setPezzo(new Re("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                //torri bianche
                if (j==56 || j==63){
                    s.setPezzo(new Torre("white"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                //cavalli bianchi
                if (j==57 || j==62){
                    s.setPezzo(new Cavallo("white"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                //alfieri bianchi
                if (j==58 || j==61){
                    s.setPezzo(new Alfiere("white"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                //regina bianca
                if (j==59){
                    s.setPezzo(new Regina("white"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                //re bianco
                if (j==60){
                    s.setPezzo(new Re("white"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }

                griglia.put(j,s);   //li inserisco nella hashmap
                ++i;
                ++j;
                p1.add(s);  //aggiungo i bottoni al pannello ad ogni ciclo
            }
        }

        //attacco tutto al pannello principale
        tornaindietro.setFocusable(false);
        tornaindietro.addActionListener(new ActionListener() {  //torno al menù principale
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==tornaindietro){
                    f.dispose();
                    MenùPrincipale nuovo=new MenùPrincipale();
                }
            }
        });


        //agiungo la scacchiera
        p4.add(p1,BorderLayout.CENTER);
        p4.add(p2,BorderLayout.NORTH);
        p4.add(p3,BorderLayout.WEST);
        gui.add(p4,BorderLayout.CENTER);
        //aggiungo il torna al menù principale
        gui.add(tornaindietro,BorderLayout.NORTH);
        //aggiungo i pezzi mangiati

        p5.add(torre);

        gui.add(p5,BorderLayout.WEST);

        //attacco il pannello principale al frame
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(400, 400);
        f.setVisible(true);
        f.setContentPane(gui);

        // gestione dei turni




    }
}
