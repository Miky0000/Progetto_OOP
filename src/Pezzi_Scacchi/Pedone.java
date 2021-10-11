package Pezzi_Scacchi;

import javax.imageio.ImageIO;
import java.awt.*;

public class Pedone extends Pezzo_Scacchi {
    private  String color;
    private Image img;

    public String getColor() {
        return color;
    }

    public Image getImg() {
        return img;
    }

    public Pedone(String color) {
        this.color = color;
        {
            try {
                if (color.equalsIgnoreCase("black") )
                    this.img = ImageIO.read(Pedone.class.getResource("../Immagini/b_pawn_png_128px.png"));
                else
                    this.img= ImageIO.read(Pedone.class.getResource("../Immagini/w_pawn_png_128px.png"));

            } catch (Exception e) {
                System.out.println("Fuck");
                e.printStackTrace();
            }
        }
    }
}
