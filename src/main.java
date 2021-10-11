import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) {
        JFrame f=new JFrame("Scacchiera");
        JPanel gui =new JPanel(new BorderLayout());
        JButton b= new JButton();

        JButton button = new JButton();
        try {
            Image img = ImageIO.read(main.class.getResource("Immagini/b_bishop_1x_ns.png"));
            button.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        b.setIcon(new ImageIcon("Immagini/b_bishop_1x_ns.png"));
        b.setText("ciao");
        gui.add(button);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(400,400);
        f.setVisible(true);
        f.setContentPane(gui);



    }
}
