package Pk2;

import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VittoriaDama extends JFrame implements ActionListener {

    //String GVincente;
    JLabel giocatorevin=new JLabel();
    JLabel vinto=new JLabel("HA VINTO!!");
    JButton rigioca=new JButton("NUOVA PARTITA");
    JButton tornamenu=new JButton("MENU PRINCIPALE");
    DBManager db;
    ResultSet nvittorie;
    {
        try {
            db = new DBManager(DBManager.JDBC_Driver,DBManager.JDBC_URL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    VittoriaDama(String GVincente) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,250);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        //giocatore vincente
        giocatorevin.setBounds(160,40,150,40);
        giocatorevin.setText(GVincente); //guardare come scrivere l'accapo
        vinto.setBounds(160,60,150,40);
        try {
            nvittorie=db.executeQuery("SELECT vittorie from mydb.classificadama where(nome='"+GVincente+"') ");
            nvittorie.next();      //prima di usare un result set usare sempre il .next altrimenti è null
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {   //aggiorno le vittorie del giocatore vincente
            db.executeUpdate("UPDATE mydb.classificadama set vittorie = "+(nvittorie.getInt("vittorie")+1)+" where(nome='"+GVincente+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //nuova partita
        rigioca.setBounds(40,100,150,30);
        rigioca.setFocusable(false);
        rigioca.addActionListener(this);
        //torna al menu
        tornamenu.setBounds(200,100,150,30);
        tornamenu.setFocusable(false);
        tornamenu.addActionListener(this);

        this.add(giocatorevin);
        this.add(vinto);
        this.add(rigioca);
        this.add(tornamenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==rigioca){
            this.dispose();
            try {
                SelezionaGiocatore_Dama selezionaGiocatore=new SelezionaGiocatore_Dama();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==tornamenu){
            this.dispose();
            MenùPrincipale menùPrincipale=new MenùPrincipale();
        }
    }
}
