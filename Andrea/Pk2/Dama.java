package Pk2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Dama {

    String giocatore1;
    String giocatore2;
    int pezzibianchi=12;
    int pezzineri=12;
    JFrame f = new JFrame("Scacchiera");
    JPanel gui = new JPanel(new BorderLayout());

    //variabili gestione pezzi
    final Damabotton[] traccia = {new Damabotton()};    //NWbotton per tenere traccia
    boolean turno=false; //true=bianco false=nero
    boolean tracciat=true;


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

    Dama(String giocatore1, String giocatore2) {

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
        Map<Integer,Damabotton> griglia=new HashMap<Integer,Damabotton>();
        int j=0;    //indice pedoni 0-63
        for(int i=0;i<64;++i) {
            for(int d=0;d<8;++d){
                Damabotton s=new Damabotton();  //bottone corrente
                s.setIndex(j);
                s.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Damabotton b=(Damabotton) e.getSource();
                        if(e.getActionCommand()=="azione1"){    //premo un pezzo
                            ArrayList<Integer> mosse=b.getPezzo().getMoves(griglia, b.getIndex());//genero l'array di mosse possibili
                            ArrayList<Integer> moremosse=b.getPezzo().getMoreMoves(b.getIndex(), griglia);
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
                            for (Integer f:moremosse){
                                griglia.get(f).setBackground(Color.GREEN);
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
                            ArrayList<Integer> mosse=traccia[0].getPezzo().getMoves(griglia, traccia[0].getIndex());    //genero l'array di mosse possibili del pedone traccia
                            System.out.println("azione2");
                            if(b.getIndex()!=traccia[0].getIndex()) {
                                b.setPezzo(traccia[0].getPezzo());  //setto pezzo
                                traccia[0].setIcon(null);   //cancello immagine
                                traccia[0].setActionCommand(null);     //annullo actioncommad
                                traccia[0].setPezzo(null);
                                tracciat=false;


                                if(b.getBackground()==Color.GREEN) // se viene mangiato un pezzo
                                {
                                    for (int i =0; i<64; i++){
                                        if (griglia.get(i).getBackground()!=griglia.get(i).getColore())
                                            griglia.get(i).setBackground(Color.black);
                                    }
                                    int dx= b.getIndex()%8 - traccia[0].getIndex()%8;
                                    int dy= b.getIndex()/8 - traccia[0].getIndex()/8;
                                    if (dx>0 && dy>0) // in alto a dx rispetto al pezzo mangiato
                                    {
                                        Damabotton altropezzo=griglia.get(b.getIndex()-9);
                                        if (altropezzo.getPezzo().getColor().equals("white"))
                                            pezzibianchi--;
                                        else
                                            pezzineri--;
                                        altropezzo.setIcon(null);   //cancello immagine
                                        altropezzo.setActionCommand(null);     //annullo actioncommad
                                        altropezzo.setPezzo(null);
                                    }
                                    else if (dx<0 && dy>0) // in alto a sx rispetto al pezzo mangiato
                                    {
                                        Damabotton altropezzo=griglia.get(b.getIndex()-7);
                                        if (altropezzo.getPezzo().getColor().equals("white"))
                                            pezzibianchi--;
                                        else
                                            pezzineri--;
                                        altropezzo.setIcon(null);   //cancello immagine
                                        altropezzo.setActionCommand(null);     //annullo actioncommad
                                        altropezzo.setPezzo(null);
                                    }
                                    else if (dx<0 && dy<0) // in basso a sx rispetto al pezzo mangiato
                                    {
                                        Damabotton altropezzo=griglia.get(b.getIndex()+9);
                                        if (altropezzo.getPezzo().getColor().equals("white"))
                                            pezzibianchi--;
                                        else
                                            pezzineri--;
                                        altropezzo.setIcon(null);   //cancello immagine
                                        altropezzo.setActionCommand(null);     //annullo actioncommad
                                        altropezzo.setPezzo(null);
                                    }
                                    else // in basso a dx rispetto al pezzo mangiato
                                    {
                                        Damabotton altropezzo=griglia.get(b.getIndex()+7);
                                        if (altropezzo.getPezzo().getColor().equals("white"))
                                            pezzibianchi--;
                                        else
                                            pezzineri--;
                                        altropezzo.setIcon(null);   //cancello immagine
                                        altropezzo.setActionCommand(null);     //annullo actioncommad
                                        altropezzo.setPezzo(null);
                                    }
                                    if(b.getIndex()/8==0 && b.getPezzo().getColor().equals("white")){
                                        b.setPezzo(new Damone("white"));
                                    }
                                    if(b.getIndex()/8==7 && b.getPezzo().getColor().equals("black")){
                                        b.setPezzo(new Damone("black"));
                                    }
                                    ArrayList<Integer> altremosse=b.getPezzo().getMoreMoves(b.getIndex(),griglia);
                                    if (!altremosse.isEmpty())
                                    {
                                        for (Integer f:altremosse){
                                            griglia.get(f).setBackground(Color.GREEN);
                                            griglia.get(f).setActionCommand("azione2");
                                        }
                                        traccia[0]=b;

                                        return;
                                    }
                                }
                                else
                                {
                                    if(b.getIndex()/8==0 && b.getPezzo().getColor().equals("white")){
                                        b.setPezzo(new Damone("white"));
                                    }
                                    if(b.getIndex()/8==7 && b.getPezzo().getColor().equals("black")){
                                        b.setPezzo(new Damone("black"));
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
                                            //System.out.println(k+"-"+traccia[0].getIndex());
                                        } else {
                                            griglia.get(k).setActionCommand(null);
                                        }
                                    }
                                }
                                turno=!turno;   //cambio turno
                                if (pezzineri==0) {
                                    JOptionPane.showMessageDialog(f, "vittoria di " + giocatore1);
                                    f.dispose();
                                    MenùPrincipale nuovo=new MenùPrincipale();
                                }
                                if (pezzibianchi==0) {
                                    JOptionPane.showMessageDialog(f, "vittoria di " + giocatore2);
                                    f.dispose();
                                    MenùPrincipale nuovo=new MenùPrincipale();
                                }
                            }
                        }
                    }
                });

                if(i==0){   //creo i contatori per i pezzi mangiati
                    pen.setPezzo(new PedinaDama("black"));
                    pen.setText(": "+pn);

                    peb.setPezzo(new PedinaDama("white"));
                    peb.setText(": "+pb);

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
                if (j/8<=2 && i%2==0) {
                    s.setPezzo(new PedinaDama("black"));
                    s.setActionCommand("azione1");
                }
                else if (j/8>=5 && i%2==0){
                    s.setPezzo(new PedinaDama("white"));
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

        p2.setBackground(Color.green);
        p3.setBackground(Color.green);
        p6.setBackground(Color.green);
        p7.setBackground(Color.green);
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
        p5.setBackground(Color.green);
        gui.add(p5,BorderLayout.WEST);

        //attacco il pannello principale al frame
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(700, 600);
        f.setVisible(true);
        f.setContentPane(gui);

    }
}
