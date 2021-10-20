package Pk2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scacchi {

    JFrame f = new JFrame("Scacchiera");
    JPanel gui = new JPanel(new BorderLayout());

    //variabili gestione pezzi
    final NWbotton[] traccia = {new NWbotton()};    //NWbotton per tenere traccia
    boolean turno=false; //true=bianco false=nero
    boolean tracciat=true;

    //label per tenere conto dei pezzi mangiati
    JLabel torre=new JLabel("0");
    //creo i pannelli
    JPanel p1=new JPanel(new GridLayout(8,8,2,2));
    JPanel p2=new JPanel(new GridLayout(1,8,2,2));
    JPanel p3=new JPanel(new GridLayout(8,1,2,2));
    JPanel p4=new JPanel(new BorderLayout());
    JPanel p5=new JPanel(new GridLayout(6,2,2,2));
    JButton tornaindietro=new JButton("MENU PRINCIPALE");

    Scacchi() {
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
                        NWbotton b=(NWbotton) e.getSource();
                        if(e.getActionCommand()=="azione1"){    //premo un pezzo
                            ArrayList<Integer> mosse=b.getPezzo().getMoves(b.getIndex(), griglia);  //genero l'array di mosse possibili
                            System.out.println("setto azione2");
                            b.setActionCommand("azione2");  //setto il bottone premuto in caso non si esegua nessuna mossa cosi da annullare le mosse in seguito
                                    if(mosse.isEmpty()){
                                        System.out.println("mosse è vuoto");
                                    }
                                    for (Integer f : mosse) {   //setto le mosse possibili a rosso ed ad azione2
                                        System.out.println("coloro di rosso");
                                        griglia.get(f).setBackground(Color.red);
                                        griglia.get(f).setActionCommand("azione2");
                                    }

                                //variabile per scorrere il for
                            for(int c=0;c<64;++c){  //setto il colore normale quando cambio mossa
                                if(griglia.get(c).getBackground()==Color.red) {
                                    System.out.println("controllo se ci sono rossi in più");
                                    for(Integer j:mosse){
                                        if(!mosse.contains(griglia.get(c).getIndex())){ //controllo che le mosse che non sono valide e le ripristino
                                            System.out.println("cancella cambio"+griglia.get(c).getIndex());
                                            griglia.get(c).setBackground(griglia.get(c).getColore());//ripristino il colore dei bottoni non premuti
                                            griglia.get(c).setActionCommand(null);   //ripristino il setacitoncommand dei bottoni rossi non premuti e anche quello premuto
                                            if(traccia[0].getPezzo()!=null){
                                                traccia[0].setActionCommand("azione1");
                                            }
                                            else{
                                                traccia[0].setActionCommand(null);
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                            traccia[0]=b;   //tengo traccia del bottone
                        }

                        if(e.getActionCommand()=="azione2"){    //sposto il pezzo sul rosso
                            ArrayList<Integer> mosse=traccia[0].getPezzo().getMoves(traccia[0].getIndex(), griglia);    //genero l'array di mosse possibili del pedone traccia
                            System.out.println("azione2");
                            if(b.getIndex()!=traccia[0].getIndex()) {
                                b.setPezzo(traccia[0].getPezzo());  //setto pezzo
                                traccia[0].setIcon(null);   //cancello immagine
                                traccia[0].setActionCommand(null);     //annullo actioncommad
                                traccia[0].setPezzo(null);
                                tracciat=false;
                            }
                                int c=0;    //variabile per scorrere il for
                            for(Integer f: mosse){  //pulisco i rossi se ripremo il bottone senza spostare il pezzo
                                griglia.get(mosse.get(c)).setBackground(griglia.get(mosse.get(c)).getColore());//ripristino il colore dei bottoni non premuti
                                griglia.get(mosse.get(c)).setActionCommand(null);   //ripristino il setacitoncommand dei bottoni rossi non premuti e anche quello premuto
                                ++c;
                            }
                            b.setActionCommand("azione1");  //setto il bottone rosso appena premuto ad azione1
                            if(tracciat==false) {   //gestione turno
                                tracciat=true;
                                System.out.println("cambio turno");
                                if (turno == false) {   //turno neri
                                    for (int k = 0; k < 64; ++k) {
                                        if (griglia.get(k).getPezzo()!=null && griglia.get(k).getPezzo().getColor() == "black") {
                                            griglia.get(k).setActionCommand("azione1");
                                            System.out.println(k+"-"+traccia[0].getIndex());
                                        } else {
                                            griglia.get(k).setActionCommand(null);
                                        }
                                    }
                                } else {  //turno bianchi
                                    for (int k = 0; k < 64; ++k) {
                                        if (griglia.get(k).getPezzo()!=null && griglia.get(k).getPezzo().getColor() == "white") {
                                            griglia.get(k).setActionCommand("azione1");
                                            System.out.println(k+"-"+traccia[0].getIndex());
                                        } else {
                                            griglia.get(k).setActionCommand(null);
                                        }
                                    }
                                }
                                turno=!turno;   //cambio turno
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

    }
}
