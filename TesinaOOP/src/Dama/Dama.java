package Dama;

import Dama.Pedine.Damone;
import Dama.Pedine.PedinaDama;
import Main.MenùPrincipale;
import Utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
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

    //label per tenere conto dei pezzi mangiati
    NWlabel pen=new NWlabel();
    NWlabel peb=new NWlabel();

    //creo i pannelli
    JPanel p1=new JPanel(new GridLayout(8,8,2,2));
    JPanel p2=new JPanel(new GridLayout(1,8,2,2));
    JPanel p3=new JPanel(new GridLayout(8,1,2,2));
    JPanel p4=new JPanel(new BorderLayout());
    JPanel p5=new JPanel(new GridLayout(7,3,2,2));
    JPanel p6=new JPanel();
    JPanel p7=new JPanel();
    JButton tornaindietro=new JButton("MENU PRINCIPALE");

    public Dama(String giocatore1, String giocatore2) {

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
        Map<Integer,Damabotton> griglia= new HashMap<>();
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
                            if (b.getPezzo()==null)
                                return;
                            ArrayList<Integer> mosse=b.getPezzo().getMoves(griglia, b.getIndex());// array di mosse di spostamento di una casella
                            ArrayList<Integer> moremosse=b.getPezzo().getMoreMoves(b.getIndex(), griglia); // array di mosse che implicano la mangiata di un pezzo avversario
                            System.out.println("setto azione2");
                            b.setActionCommand("azione2");  //setto il bottone premuto in caso non si esegua nessuna mossa cosi da annullare le mosse in seguito
                            if(mosse.isEmpty()){
                                System.out.println("mosse è vuoto");
                            }
                            for (Integer f : mosse) {   //setto le mosse possibili a rosso ed ad azione2
                                System.out.println("coloro di rosso");
                                griglia.get(f).setBackground(Color.red);

                            }
                            for (Integer f:moremosse){
                                griglia.get(f).setBackground(Color.GREEN);
                            }
                            for (int i=0; i<64; i++)
                                griglia.get(i).setActionCommand("azione2");



                            traccia[0]=b;   //tengo traccia del bottone
                        }

                        if(e.getActionCommand()=="azione2"){
                            ArrayList<Integer> mosse=traccia[0].getPezzo().getMoves(griglia, traccia[0].getIndex());    //genero l'array di mosse possibili del pedone traccia
                            System.out.println("azione2");
                            if(b.getIndex()!=traccia[0].getIndex()) // se ho premuto un pezzo diverso rispetto ad azione1
                            {
                                if (b.getBackground()==Color.RED) // la pedina si sposta di uno spazio
                                {
                                    b.setPezzo(traccia[0].getPezzo());  //setto pezzo
                                    traccia[0].setIcon(null);   //cancello immagine
                                    traccia[0].setActionCommand(null);     //annullo actioncommad
                                    traccia[0].setPezzo(null);
                                    tracciat = false;
                                    if(b.getIndex()/8==0 && b.getPezzo().getColor().equals("white")){
                                        b.setPezzo(new Damone("white"));
                                    }
                                    if(b.getIndex()/8==7 && b.getPezzo().getColor().equals("black")){
                                        b.setPezzo(new Damone("black"));
                                    }
                                }
                                else if(b.getBackground()==Color.GREEN) // se viene mangiato un pezzo
                                {
                                    b.setPezzo(traccia[0].getPezzo());  //setto pezzo
                                    traccia[0].setIcon(null);   //cancello immagine
                                    traccia[0].setActionCommand(null);     //annullo actioncommad
                                    traccia[0].setPezzo(null);
                                    tracciat = false;

                                    for (int i =0; i<64; i++) //cancello il colore rosso/verde in altri bottoni
                                    {
                                        if (griglia.get(i).getBackground()!=griglia.get(i).getColore())
                                            griglia.get(i).setBackground(Color.black);
                                    }

                                    //localizzare la pedina mangiata e poi cancellarla dalla scacchiera

                                    int dx= b.getIndex()%8 - traccia[0].getIndex()%8;
                                    int dy= b.getIndex()/8 - traccia[0].getIndex()/8;
                                    if (dx>0 && dy>0) // se la pedina che ha mangiato è in alto a dx rispetto al pezzo mangiato, allora cancella la pedina in basso a sx
                                    {
                                        Damabotton altropezzo=griglia.get(b.getIndex()-9);
                                        if (altropezzo.getPezzo().getColor().equals("white")){
                                            pezzibianchi--;
                                        }
                                        else
                                            pezzineri--;
                                        altropezzo.setIcon(null);   //cancello immagine
                                        altropezzo.setActionCommand(null);     //annullo actioncommad
                                        altropezzo.setPezzo(null);
                                    }
                                    else if (dx<0 && dy>0) //  se la pedina che ha mangiato è in alto a sx rispetto al pezzo mangiato, allora cancella la pedina in basso a dx
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
                                    else if (dx<0 && dy<0) // se la pedina che ha mangiato è in basso a sx rispetto al pezzo mangiato, allora cancella la pedina in alto a dx
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
                                    else // se la pedina che ha mangiato è in basso a dx rispetto al pezzo mangiato, allora cancella la pedina in alto a sx
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

                                    //aggiorno le label
                                    peb.setText(": "+pezzibianchi);
                                    pen.setText(": "+pezzineri);

                                    //controllo la promozione
                                    if(b.getIndex()/8==0 && b.getPezzo().getColor().equals("white")){
                                        b.setPezzo(new Damone("white"));
                                    }
                                    if(b.getIndex()/8==7 && b.getPezzo().getColor().equals("black")){
                                        b.setPezzo(new Damone("black"));
                                    }

                                    //ricorsione: se ci sono altre pedine che possono essere mangiate, DEVONO essere mangiate

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
                                    traccia[0].setActionCommand("azione1");
                                    b.setActionCommand("azione1");
                                    mosse.addAll(traccia[0].getPezzo().getMoreMoves(traccia[0].getIndex(),griglia));
                                    for (Integer i:mosse) {
                                        griglia.get(i).setBackground(griglia.get(i).getColore());
                                        griglia.get(i).setActionCommand("azione1");
                                    }
                                    if (turno) {   //turno neri
                                        for (int k = 0; k < 64; ++k) {
                                            griglia.get(k).setBackground(griglia.get(k).getColore());
                                            if (griglia.get(k).getPezzo()!=null && griglia.get(k).getPezzo().getColor() == "black") {
                                                griglia.get(k).setActionCommand("azione1");

                                            } else {
                                                griglia.get(k).setActionCommand(null);
                                            }
                                        }
                                    } else {  //turno bianchi
                                        for (int k = 0; k < 64; ++k) {
                                            griglia.get(k).setBackground(griglia.get(k).getColore());
                                            if (griglia.get(k).getPezzo()!=null && griglia.get(k).getPezzo().getColor() == "white") {
                                                griglia.get(k).setActionCommand("azione1");
                                            } else
                                            {
                                                griglia.get(k).setActionCommand(null);
                                            }
                                        }
                                    }
                                }

                            }
                            else
                            {
                                System.out.println(turno);
                                b.setActionCommand("azione1");
                                mosse.addAll(b.getPezzo().getMoreMoves(b.getIndex(),griglia));
                                for (Integer i:mosse) {
                                    griglia.get(i).setBackground(griglia.get(i).getColore());
                                    griglia.get(i).setActionCommand("azione1");
                                }
                                if (turno) {   //turno neri
                                    for (int k = 0; k < 64; ++k) {
                                        griglia.get(k).setBackground(griglia.get(k).getColore());
                                        if (griglia.get(k).getPezzo()!=null && griglia.get(k).getPezzo().getColor() == "black") {
                                            griglia.get(k).setActionCommand("azione1");

                                        } else {
                                            griglia.get(k).setActionCommand(null);
                                        }
                                    }
                                } else {  //turno bianchi
                                    for (int k = 0; k < 64; ++k) {
                                        griglia.get(k).setBackground(griglia.get(k).getColore());
                                        if (griglia.get(k).getPezzo()!=null && griglia.get(k).getPezzo().getColor() == "white") {
                                            griglia.get(k).setActionCommand("azione1");
                                        } else {
                                            griglia.get(k).setActionCommand(null);
                                        }
                                    }
                                }
                            }

                            if(!tracciat) {   //gestione turno
                                tracciat=true;
                                System.out.println("cambio turno");
                                if (!turno) {   //turno neri
                                    for (int k = 0; k < 64; ++k) {
                                        griglia.get(k).setBackground(griglia.get(k).getColore());
                                        if (griglia.get(k).getPezzo()!=null && griglia.get(k).getPezzo().getColor() == "black") {
                                            griglia.get(k).setActionCommand("azione1");

                                        } else {
                                            griglia.get(k).setActionCommand(null);
                                        }
                                    }
                                } else {  //turno bianchi
                                    for (int k = 0; k < 64; ++k) {
                                        griglia.get(k).setBackground(griglia.get(k).getColore());
                                        if (griglia.get(k).getPezzo()!=null && griglia.get(k).getPezzo().getColor() == "white") {
                                            griglia.get(k).setActionCommand("azione1");
                                        } else {
                                            griglia.get(k).setActionCommand(null);
                                        }
                                    }
                                }
                                turno=!turno;   //cambio turno

                                if (turno) //appena finito il turno bianco
                                {
                                    ArrayList<Integer> mossedisp= new ArrayList<>();
                                    for (int i=0; i<64; i++)
                                    {
                                        Damabotton Button=griglia.get(i);
                                        if (Button.getPezzo()!=null && Button.getPezzo().getColor().equals("black")){
                                            mossedisp.addAll(Button.getPezzo().getMoves(griglia,i));
                                            mossedisp.addAll(Button.getPezzo().getMoreMoves(i,griglia));
                                        }
                                        if (!mossedisp.isEmpty()){
                                            break;
                                        }
                                    }
                                    if (mossedisp.isEmpty())
                                        pezzineri=0;
                                }
                                else //appena finito il turno nero
                                {
                                    ArrayList<Integer> mossedisp= new ArrayList<>();
                                    for (int i=0; i<64; i++) //controllo che ci siano mosse disponibili
                                    {
                                        Damabotton Button=griglia.get(i);
                                        if (Button.getPezzo()!=null && Button.getPezzo().getColor().equals("white")){
                                            mossedisp.addAll(Button.getPezzo().getMoves(griglia,i));
                                            mossedisp.addAll(Button.getPezzo().getMoreMoves(i,griglia));
                                        }
                                        if (!mossedisp.isEmpty()){
                                            break;
                                        }
                                    }
                                    if (mossedisp.isEmpty())
                                        pezzibianchi=0;
                                }
                                if (pezzineri==0) {
                                    f.dispose();
                                    new VittoriaDama(giocatore2);
                                }
                                if (pezzibianchi==0) {
                                    f.dispose();
                                    new VittoriaDama(giocatore1);
                                }
                            }
                        }
                    }
                });

                if(i==0){   //creo i contatori per i pezzi mangiati
                    pen.setPezzo(new PedinaDama("black"));
                    pen.setText(": "+pezzineri);

                    peb.setPezzo(new PedinaDama("white"));
                    peb.setText(": "+pezzibianchi);

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
                    new MenùPrincipale();
                }
            }
        });

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
        for(int i=0;i<20;++i){
            p5.add(new JLabel());
        }

        p5.setBackground(Color.cyan);
        p5.setSize(100,50);
        gui.add(p5,BorderLayout.WEST);


        //attacco il pannello principale al frame
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(700, 500);
        f.setVisible(true);
        f.setContentPane(gui);

    }
}
