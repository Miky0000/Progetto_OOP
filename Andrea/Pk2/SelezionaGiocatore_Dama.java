package Pk2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelezionaGiocatore_Dama extends JFrame implements ActionListener {

    boolean g1=true;
    String giocatore1;
    String giocatore2;
    JButton seleziona=new JButton("Seleziona Giocatore");
    JButton eliminag=new JButton("Elimina Giocatore");
    JComboBox <String> scelta=new JComboBox<>();
    JComboBox <String> elimina=new JComboBox<>();
    JTextField AggiungiGiocatore=new JTextField(" Nuovo Giocatore");
    JButton tornamenu=new JButton("MENU PRINCIPALE");
    JButton Crea=new JButton("Aggiungi Giocatore");
    JLabel sfondo2=new JLabel();
    Sfondo3 image =new Sfondo3();
    Border border= BorderFactory.createLineBorder(Color.black,5);
    JLabel cla=new JLabel("CLASSIFICA");
    JList<String> classifica=new JList<>();
    DefaultListModel <String> model=new DefaultListModel<>();
    DBManager db;

    {
        try {
            db = new DBManager(DBManager.JDBC_Driver,DBManager.JDBC_URL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    SelezionaGiocatore_Dama() throws SQLException{

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(550,500);
        this.setLayout(null);
        this.setResizable(false);

        //resultset
        ResultSet rs=db.executeQuery("select * from ClassificaDama order by vittorie desc");

        //sfondo
        sfondo2.setBounds(0,0,536,463);
        sfondo2.setIcon(new ImageIcon(image.getImg()));
        sfondo2.setBackground(Color.white);
        sfondo2.setOpaque(true);
        sfondo2.setBorder(border);
        sfondo2.setHorizontalAlignment(JLabel.CENTER);
        sfondo2.setVerticalAlignment(JLabel.CENTER);
        //bottoneer tornare al menu principale
        tornamenu.setBounds(25,10,150,30);
        tornamenu.setFocusable(false);
        tornamenu.addActionListener(this);
        //selezione giocatore
        seleziona.setBounds(25,120,150,30);
        seleziona.setFocusable(false);
        scelta=new JComboBox<>();
        rs.absolute(0); //setto il resultset al primo record del db
        while(rs.next()){   //scorro il resultset per aggiungere i nomi nel db alla combobox
            scelta.addItem(rs.getString("nome"));
        }
        scelta.setBounds(190,120,150,30);
        scelta.setFocusable(false);
        scelta.addActionListener(this);
        //aggiunta nuovo giocatore
        Crea.setBounds(25,200,150,30);
        Crea.setFocusable(false);
        Crea.addActionListener(this);
        AggiungiGiocatore.setBounds(190,200,150,32);
        //elimina giocatore
        eliminag.setBounds(25,271,150,30);
        eliminag.setFocusable(false);
        elimina=new JComboBox<>();
        rs.absolute(0);
        while(rs.next()){
            elimina.addItem(rs.getString("nome"));
        }
        elimina.setBounds(190,271,150,30);
        elimina.setFocusable(false);
        elimina.addActionListener(this);
        //classifica
        classifica.setModel(model);
        rs.absolute(0);
        while(rs.next()){
            model.addElement(rs.getString("nome")+" -->  "+rs.getInt("vittorie"));
        }
        cla.setBounds(435,10,130,40);
        classifica.setBounds(400,40,130,180);
        classifica.setBorder(border);
        classifica.setFocusable(false);

        this.add(cla);
        this.add(classifica);
        this.add(tornamenu);
        this.add(AggiungiGiocatore);
        this.add(Crea);
        this.add(seleziona);
        this.add(eliminag);
        this.add(elimina);
        this.add(scelta);
        this.add(sfondo2);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==scelta){

            if(g1==true){
                giocatore1= (String) scelta.getSelectedItem();
                g1=false;
                JOptionPane.showMessageDialog(this,"Scegli giocatore 2");
            }
            else if(giocatore1!=((String) scelta.getSelectedItem())) {
                giocatore2 = (String) scelta.getSelectedItem();
                this.dispose();
                Dama Dama=new Dama(giocatore1,giocatore2);
            }
            else if(giocatore1==((String) scelta.getSelectedItem())){
                JOptionPane.showMessageDialog(this,"Non puoi scegliere lo stesso giocatore");
            }
            System.out.println(giocatore1 +" "+ giocatore2);
        }
        if(e.getSource()==Crea){
            boolean n=true;
            try {
                ResultSet rs=db.executeQuery("select * from classificadama order by vittorie desc");
                while (rs.next()){
                    if(rs.getString("nome").equals(AggiungiGiocatore.getText())){       //controllo che il giocatore da aggiungere non sia già esistente
                        n=false;
                    }
                }
                if(n==false){
                    JOptionPane.showMessageDialog(this,"Giocatore già esistente");
                    return;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            try {
                db.executeUpdate("INSERT INTO `mydb`.`classificadama` (`nome`) VALUES ('"+AggiungiGiocatore.getText()+"')");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            scelta.addItem(AggiungiGiocatore.getText());
            elimina.addItem(AggiungiGiocatore.getText());
            AggiungiGiocatore.setText(" Nuovo Giocatore");

            // aggiorno classifica
            try {
                ResultSet rs=db.executeQuery("select * from classificadama order by vittorie desc");
                rs.absolute(0);
                model.removeAllElements();
                while(rs.next()) {

                    model.addElement(rs.getString("nome") + " -->  " + rs.getInt("vittorie"));
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==tornamenu){
            this.dispose();
            MenùPrincipale nuovo=new MenùPrincipale();
        }
        if(e.getSource()==elimina){
            try {
                db.executeUpdate("DELETE FROM `mydb`.`classificadama` WHERE (`nome` = '"+(String) elimina.getSelectedItem()+"');");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            scelta.removeItem(elimina.getSelectedItem());
            elimina.removeItem(elimina.getSelectedItem());

            //aggiorno classifica
            try {
                ResultSet rs=db.executeQuery("select * from classificadama order by vittorie desc");
                rs.absolute(0);
                model.removeAllElements();
                while(rs.next()) {

                    model.addElement(rs.getString("nome") + " -->  " + rs.getInt("vittorie"));
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}