package Scacchi;

import Main.MenùPrincipale;
import Utils.*;
import Scacchi.Pedine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Scacchi {

    String giocatore1;
    String giocatore2;

    JFrame f = new JFrame("Scacchiera");
    JPanel gui = new JPanel(new BorderLayout());

    //variabili gestione pezzi
    final NWbotton[] traccia = {new NWbotton()};    //NWbotton per tenere traccia
    boolean turno=false; //true=bianco false=nero
    boolean tracciat=true;

    boolean scacconero=false;
    boolean scaccobianco=false;
    int posizionerebianco=-1;
    int posizionerenero=-1;

    //variabili per tenere conto dei pezzi mangiati
    int pn=0;
    int tn=0;
    int cn=0;
    int an=0;
    int qn=0;
    int kn=0;
    int pb=0;
    int tb=0;
    int cb=0;
    int ab=0;
    int qb=0;
    int kb=0;

    //label per tenere conto dei pezzi mangiati
    NWlabel pen=new NWlabel();
    NWlabel ton=new NWlabel();
    NWlabel can=new NWlabel();
    NWlabel aln=new NWlabel();
    NWlabel qun=new NWlabel();
    NWlabel kin=new NWlabel();
    NWlabel peb=new NWlabel();
    NWlabel tob=new NWlabel();
    NWlabel cab=new NWlabel();
    NWlabel alb=new NWlabel();
    NWlabel qub=new NWlabel();
    NWlabel kib=new NWlabel();

    //creo i pannelli
    JPanel p1=new JPanel(new GridLayout(8,8,2,2));
    JPanel p2=new JPanel(new GridLayout(1,8,2,2));
    JPanel p3=new JPanel(new GridLayout(8,1,2,2));
    JPanel p4=new JPanel(new BorderLayout());
    JPanel p5=new JPanel(new GridLayout(7,3,2,2));
    JPanel p6=new JPanel();
    JPanel p7=new JPanel();
    JButton tornaindietro=new JButton("MENU PRINCIPALE");

    public Scacchi(String giocatore1, String giocatore2) {

        this.giocatore1 = giocatore1;
        this.giocatore2 = giocatore2;

        //creo le label 1-8
        JLabel label = new JLabel("");
        p2.add(label);
        for (int g = 0; g < 8; ++g) {
            p2.add(new Label("" + (g + 1)));
            p3.add(new Label("" + (g + 1)));
        }
        //creo la griglia di NWbotton
        Map<Integer,NWbotton> griglia= new HashMap<>();
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
                            for(int c=0;c<64;++c){  //setto il colore normale quando cambio mossa
                                if(griglia.get(c).getBackground()==Color.red) {

                                    System.out.println("controllo se ci sono rossi in più");
                                    for(Integer j:mosse){
                                        if(!mosse.contains(griglia.get(c).getIndex())){ //controllo che le mosse che non sono valide e le ripristino
                                            System.out.println("cancella cambio"+griglia.get(c).getIndex());
                                            griglia.get(c).setBackground(griglia.get(c).getColore());//ripristino il colore dei bottoni non premuti
                                            griglia.get(c).setActionCommand(null);   //ripristino il setacitoncommand dei bottoni rossi non premuti e anche quello premuto
                                            break;
                                        }
                                    }
                                }
                            }
                            if(traccia[0].getPezzo()!=null){        //setto il pezzo non premuto ad azione1
                                traccia[0].setActionCommand("azione1");
                            }
                            else{       //setto il bottone senza pezzo non premuto a null
                                traccia[0].setActionCommand(null);
                            }
                            traccia[0]=b;   //tengo traccia del bottone
                        }

                        if(e.getActionCommand()=="azione2"){    //sposto il pezzo sul rosso
                            ArrayList<Integer> mosse=traccia[0].getPezzo().getMoves(traccia[0].getIndex(), griglia);    //genero l'array di mosse possibili del pedone traccia
                            System.out.println("azione2");
                            if(b.getIndex()!=traccia[0].getIndex()) {
                                if(b.getPezzo()!=null){ //controllo i pezzi mangiati
                                    System.out.println("mangiato");
                                    if(b.getPezzo().equals(pen.getPezzo()) && b.getPezzo().getColor().equals(pen.getPezzo().getColor())) {
                                        ++pn;  //incremento la variabile
                                        pen.setText(": "+pn);   //risetto la label
                                    }
                                    if(b.getPezzo().equals(ton.getPezzo()) && b.getPezzo().getColor().equals(ton.getPezzo().getColor())) {
                                        ++tn;  //incremento la variabile
                                        ton.setText(": "+tn);   //risetto la label
                                    }
                                    if(b.getPezzo().equals(can.getPezzo()) && b.getPezzo().getColor().equals(can.getPezzo().getColor())) {
                                        ++cn;  //incremento la variabile
                                        can.setText(": "+cn);   //risetto la label
                                    }
                                    if(b.getPezzo().equals(aln.getPezzo()) && b.getPezzo().getColor().equals(aln.getPezzo().getColor())) {
                                        ++an;  //incremento la variabile
                                        aln.setText(": "+an);   //risetto la label
                                    }
                                    if(b.getPezzo().equals(qun.getPezzo()) && b.getPezzo().getColor().equals(qun.getPezzo().getColor())) {
                                        ++qn;  //incremento la variabile
                                        qun.setText(": "+qn);   //risetto la label
                                    }
                                    if(b.getPezzo().equals(kin.getPezzo()) && b.getPezzo().getColor().equals(kin.getPezzo().getColor())) {
                                        ++kn;  //incremento la variabile
                                        kin.setText(": "+kn);   //risetto la label
                                        f.dispose();
                                        new Vittoria(giocatore2);
                                    }
                                    if(b.getPezzo().equals(peb.getPezzo())&&b.getPezzo().getColor().equals(peb.getPezzo().getColor())) {
                                        ++pb;  //incremento la variabile
                                        peb.setText(": "+pb);   //risetto la label
                                    }

                                    if(b.getPezzo().equals(tob.getPezzo()) && b.getPezzo().getColor().equals(tob.getPezzo().getColor())) {
                                        ++tb;  //incremento la variabile
                                        tob.setText(": "+tb);   //risetto la label
                                    }
                                    if(b.getPezzo().equals(cab.getPezzo()) && b.getPezzo().getColor().equals(cab.getPezzo().getColor())) {
                                        ++cb;  //incremento la variabile
                                        cab.setText(": "+cb);   //risetto la label
                                    }
                                    if(b.getPezzo().equals(alb.getPezzo()) && b.getPezzo().getColor().equals(alb.getPezzo().getColor())) {
                                        ++ab;  //incremento la variabile
                                        alb.setText(": "+ab);   //risetto la label
                                    }
                                    if(b.getPezzo().equals(qub.getPezzo()) && b.getPezzo().getColor().equals(qub.getPezzo().getColor())) {
                                        ++qb;  //incremento la variabile
                                        qub.setText(": "+qb);   //risetto la label
                                    }
                                    if(b.getPezzo().equals(kib.getPezzo()) && b.getPezzo().getColor().equals(kib.getPezzo().getColor())) {
                                        ++kb;  //incremento la variabile
                                        kib.setText(": "+kb);   //risetto la label
                                        f.dispose();
                                        new Vittoria(giocatore1);
                                    }

                                }
                                b.setPezzo(traccia[0].getPezzo());  //setto pezzo
                                traccia[0].setIcon(null);   //cancello immagine
                                traccia[0].setActionCommand(null);     //annullo actioncommad
                                traccia[0].setPezzo(null);
                                tracciat=false;

                                // promozione del pedone

                                if (b.getPezzo().toString().equals("Pedone") && b.getPezzo().getColor().equals("black")&& b.getIndex()/8==7) {
                                    String[] pezzi = {"Regina", "Cavallo","Torre","Alfiere"};
                                    String scelta = (String) JOptionPane.showInputDialog(f,"trasfoma in: \n","Trasforma pedone", JOptionPane.INFORMATION_MESSAGE, null, pezzi,pezzi[0]);
                                    if (scelta.equals("Regina"))
                                        b.setPezzo(new Regina("black"));
                                    else if (scelta.equals("Cavallo"))
                                        b.setPezzo(new Cavallo("black"));
                                    else if (scelta.equals("Alfiere"))
                                        b.setPezzo(new Alfiere("black"));
                                    else
                                        b.setPezzo(new Torre("black"));
                                }
                                if (b.getPezzo().toString().equals("Pedone") && b.getPezzo().getColor().equals("white")&& b.getIndex()/8==0) {
                                    String pezzi[] = {"Regina", "Cavallo","Torre","Alfiere"};
                                    String scelta = (String) JOptionPane.showInputDialog(f,"trasfoma in: \n","Trasforma pedone", JOptionPane.INFORMATION_MESSAGE, null, pezzi,pezzi[0]);
                                    switch (scelta) {
                                        case "Regina" -> b.setPezzo(new Regina("white"));
                                        case "Cavallo" -> b.setPezzo(new Cavallo("white"));
                                        case "Alfiere" -> b.setPezzo(new Alfiere("white"));
                                        default -> b.setPezzo(new Torre("white"));
                                    }
                                }

                                scacconero=false;
                                scaccobianco=false;
                                HashSet<Integer> h =new HashSet<Integer>(); //tutte le mosse possibili del bianco
                                if (!turno) {
                                    for (int i = 0; i < 64; i++) {
                                        NWbotton button = griglia.get(i);
                                        if (button.getPezzo() != null && button.getPezzo().getColor().equals("white"))
                                            h.addAll(button.getPezzo().getMoves(i, griglia));
                                        if (button.getPezzo() != null && button.getPezzo().toString().equals("Re") && button.getPezzo().getColor().equals("black"))
                                            posizionerenero = i; //posizione re nero
                                    }
                                    if (h.contains(posizionerenero))
                                        scacconero = true;
                                    if (scacconero) {
                                        JOptionPane.showMessageDialog(f,"Scacco al re nero");
                                        HashSet<Integer> mossere = new HashSet<>();
                                        mossere.addAll(griglia.get(posizionerenero).getPezzo().getMoves(posizionerenero,griglia));
                                        mossere.removeAll(h);
                                    }
                                }
                                else{
                                    for (int i = 0; i < 64; i++) {
                                        NWbotton button = griglia.get(i);
                                        if (button.getPezzo() != null && button.getPezzo().getColor().equals("black"))
                                            h.addAll(button.getPezzo().getMoves(i, griglia));
                                        if (button.getPezzo() != null && button.getPezzo().toString().equals("Re") && button.getPezzo().getColor().equals("white"))
                                            posizionerebianco = i; //posizione re nero
                                    }
                                    if (h.contains(posizionerebianco))
                                        scaccobianco = true;
                                    if (scaccobianco) {
                                        JOptionPane.showMessageDialog(f,"Scacco al re bianco");
                                        HashSet<Integer> mossere = new HashSet<>();
                                        mossere.addAll(griglia.get(posizionerebianco).getPezzo().getMoves(posizionerebianco, griglia));
                                        mossere.removeAll(h);
                                    }
                                }
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
                                        } else {
                                            griglia.get(k).setActionCommand(null);
                                        }
                                    }
                                } else {  //turno bianchi
                                    for (int k = 0; k < 64; ++k) {
                                        if (griglia.get(k).getPezzo()!=null && griglia.get(k).getPezzo().getColor() == "white") {
                                            griglia.get(k).setActionCommand("azione1");
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

                if(i==0){   //creo i contatori per i pezzi mangiati
                    pen.setPezzo(new Pedone("black"));
                    pen.setText(": "+pn);
                    ton.setPezzo(new Torre("black"));
                    ton.setText(": "+tn);
                    can.setPezzo(new Cavallo("black"));
                    can.setText(": "+cn);
                    aln.setPezzo(new Alfiere("black"));
                    aln.setText(": "+an);
                    qun.setPezzo(new Regina("black"));
                    qun.setText(": "+qn);
                    kin.setPezzo(new Re("black"));
                    kin.setText(": "+kn);
                    peb.setPezzo(new Pedone("white"));
                    peb.setText(": "+pb);
                    tob.setPezzo(new Torre("white"));
                    tob.setText(": "+tb);
                    cab.setPezzo(new Cavallo("white"));
                    cab.setText(": "+cb);
                    alb.setPezzo(new Alfiere("white"));
                    alb.setText(": "+ab);
                    qub.setPezzo(new Regina("white"));
                    qub.setText(": "+qb);
                    kib.setPezzo(new Re("white"));
                    kib.setText(": "+kb);
                }
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
                if(i>=8&&i<=16) {
                    s.setPezzo(new Pedone("black"));
                    s.setIcon(new ImageIcon(s.getPezzo().getImg()));
                    //s.setActionCommand("azione1");
                }
                //riempio i pedoni bianchi
                if(i>=54&&i<62) {
                    s.setPezzo(new Pedone("white"));
                    s.setIcon(new ImageIcon(s.getPezzo().getImg()));
                    s.setActionCommand("azione1");
                }
                //torri nere
                if (j==0 || j==7){
                    s.setPezzo(new Torre("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                }
                //cavalli neri
                if (j==1 || j==6){
                    s.setPezzo(new Cavallo("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                }
                //alfieri neri
                if (j==2 || j==5){
                    s.setPezzo(new Alfiere("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                }
                //regina nera
                if (j==3){
                    s.setPezzo(new Regina("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
                }
                //re nero
                if (j==4){
                    s.setPezzo(new Re("black"));
                    s.setIcon(new ImageIcon( s.getPezzo().getImg()));
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
                    new MenùPrincipale();
                }
            }
        });

        //setto il colore dello sfondo
        p2.setBackground(Color.cyan);
        p3.setBackground(Color.cyan);
        p6.setBackground(Color.cyan);
        p7.setBackground(Color.cyan);

        //agiungo la scacchiera
        p4.add(p1,BorderLayout.CENTER);
        p4.add(p2,BorderLayout.NORTH);
        p4.add(p3,BorderLayout.WEST);
        p4.add(p6,BorderLayout.EAST);
        p4.add(p7,BorderLayout.SOUTH);
        gui.add(p4,BorderLayout.CENTER);

        //aggiungo il torna al menù principale
        gui.add(tornaindietro,BorderLayout.NORTH);

        //aggiungo i pezzi mangiati
        p5.add(new JLabel(giocatore1));
        p5.add(new JLabel());
        p5.add(new JLabel(giocatore2));
        p5.add(new JLabel());
        p5.add(pen);
        p5.add(new JLabel());
        p5.add(peb);
        p5.add(new JLabel());
        p5.add(ton);
        p5.add(new JLabel());
        p5.add(tob);
        p5.add(new JLabel());
        p5.add(can);
        p5.add(new JLabel());
        p5.add(cab);
        p5.add(new JLabel());
        p5.add(aln);
        p5.add(new JLabel());
        p5.add(alb);
        p5.add(new JLabel());
        p5.add(qun);
        p5.add(new JLabel());
        p5.add(qub);
        p5.add(new JLabel());
        p5.add(kin);
        p5.add(new JLabel());
        p5.add(kib);
        p5.add(new JLabel());
        p5.setBackground(Color.CYAN);
        gui.add(p5,BorderLayout.WEST);

        //attacco il pannello principale al frame
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(700, 600);
        f.setVisible(true);
        f.setContentPane(gui);

    }
}