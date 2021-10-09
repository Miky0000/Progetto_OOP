import java.awt.*;
import javax.swing.*;

public class Scacchiera_Micheal {
    public static void main(String[] args){


        JFrame f=new JFrame("Scacchiera");
        JPanel gui =new JPanel(new BorderLayout());
        JPanel p1=new JPanel(new GridLayout(8,8,2,2));
        JPanel p2=new JPanel(new GridLayout(1,8,2,2));
        JPanel p3=new JPanel(new GridLayout(8,1,2,2));
//creo le label
        p2.add(new Label(""));
        for(int g=0;g<8;++g) {
            p2.add(new Label(""+ ((char)(g+'A'))));
            p3.add(new Label(""+(g+1)));
        }
        gui.add(p1,BorderLayout.CENTER);
        gui.add(p2,BorderLayout.NORTH);
        gui.add(p3,BorderLayout.WEST);
//coloro caselle
        for(int i=0;i<64;++i) {
            for(int d=0;d<8;++d){
                JButton b = new JButton();
                if (i % 2 == 0) {
                    b.setBackground(Color.black);
                }
                else {
                    b.setBackground(Color.white);
                }
                ++i;
                p1.add(b);

            }


        }
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(400,400);
        f.setVisible(true);
        f.setContentPane(gui);


    }
}
