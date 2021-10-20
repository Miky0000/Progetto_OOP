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

        sfondo.setBounds(0,0,400,400);
        sfondo.setIcon(new ImageIcon(image.getImg()));
        sfondo.setBackground(Color.white);
        sfondo.setOpaque(true);
        sfondo.setBorder(border);
        sfondo.setHorizontalAlignment(JLabel.CENTER);
        sfondo.setVerticalAlignment(JLabel.CENTER);


        DamaButton.setBounds(75,160,100,40);
        DamaButton.addActionListener(this);
        DamaButton.setFocusable(false);
        ScacchiButton.setBounds(250,160,100,40);
        ScacchiButton.addActionListener(this);
        ScacchiButton.setFocusable(false);


        frame.add(DamaButton);
        frame.add(ScacchiButton);
        frame.add(sfondo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(null);
        frame.setVisible(true);
//        frame.setResizable(false);
 //       frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==ScacchiButton){
            frame.dispose();
            Scacchi Scacchi=new Scacchi();
        }

    }
}
