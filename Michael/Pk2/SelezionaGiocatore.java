package Pk2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelezionaGiocatore extends JFrame implements ActionListener {

    boolean g1=true;
    String giocatore1;
    String giocatore2;
    JLabel seleziona=new JLabel("Seleziona Giocatore");
    JLabel eliminag=new JLabel("Elimina Giocatore");
    JComboBox scelta=new JComboBox();
    JComboBox elimina=new JComboBox();
    String ElencoGiocatori[]={"Giocatore 1","Giocatore 2"};
    JTextField AggiungiGiocatore=new JTextField(" Nuovo Giocatore");
    JButton Crea=new JButton("Aggiungi Giocatore");

    SelezionaGiocatore(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(550,500);
        this.setLayout(null);
        this.setResizable(false);


        seleziona.setBounds(200,50,150,40);
        scelta=new JComboBox(ElencoGiocatori);
        scelta.setBounds(190,85,150,30);
        scelta.addActionListener(this);
        Crea.setBounds(100,200,150,30);
        AggiungiGiocatore.setBounds(270,200,150,32);
        Crea.setFocusable(false);
        Crea.addActionListener(this);
        eliminag.setBounds(200,265,150,40);
        elimina=new JComboBox(ElencoGiocatori);
        elimina.setBounds(190,300,150,30);
        elimina.addActionListener(this);
        this.add(AggiungiGiocatore);
        this.add(Crea);
        this.add(seleziona);
        this.add(eliminag);
        this.add(elimina);
        this.add(scelta);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==scelta){

            if(g1==true){
                giocatore1= (String) scelta.getSelectedItem();
                g1=false;
            }
            else {
                giocatore2 = (String) scelta.getSelectedItem();
                this.dispose();
                Scacchi Scacchi=new Scacchi(giocatore1,giocatore2);
            }
            System.out.println(giocatore1 +" "+ giocatore2);
        }
        if(e.getSource()==Crea){
            scelta.addItem(AggiungiGiocatore.getText());
            elimina.addItem(AggiungiGiocatore.getText());
        }
        if(e.getSource()==elimina){
            scelta.removeItem(elimina.getSelectedItem());
            elimina.removeItem(elimina.getSelectedItem());
        }
    }
}
