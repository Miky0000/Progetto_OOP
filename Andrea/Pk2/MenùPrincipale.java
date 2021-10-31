package Pk2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenùPrincipale implements ActionListener {

    JFrame frame=new JFrame();
    Sfondo image=new Sfondo();
    Border border= BorderFactory.createLineBorder(Color.black,5);
    JLabel sfondo=new JLabel();
    JButton DamaButton=new JButton("DAMA");
    JButton ScacchiButton=new JButton("SCACCHI");


    MenùPrincipale(){

        sfondo.setBounds(0,0,900,758);
        sfondo.setIcon(new ImageIcon(image.getImg()));
        sfondo.setBackground(Color.white);
        sfondo.setOpaque(true);
        sfondo.setBorder(border);
        sfondo.setHorizontalAlignment(JLabel.CENTER);
        sfondo.setVerticalAlignment(JLabel.CENTER);


        DamaButton.setBounds(300,350,120,50);
        DamaButton.addActionListener(this);
        DamaButton.setFocusable(false);
        ScacchiButton.setBounds(480,350,120,50);
        ScacchiButton.addActionListener(this);
        ScacchiButton.setFocusable(false);


        frame.add(DamaButton);
        frame.add(ScacchiButton);
        frame.add(sfondo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(914,795);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==ScacchiButton){
            frame.dispose();
            SelezionaGiocatore selezione=new SelezionaGiocatore();
        }
        else if (e.getSource()==DamaButton){
            frame.dispose();
            SelezionaGiocatore_Dama selezione=new SelezionaGiocatore_Dama();
        }

    }
}
