package Pk2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Regina extends PezzoScacchi {
    private String color;
    private Image img;

    public String getColor() {
        return color;
    }

    public Image getImg() {
        return img;
    }

    public Regina(String color) {
        this.color = color;
        {
            try {
                if (color.equalsIgnoreCase("black"))
                    this.img = ImageIO.read(Torre.class.getResource("../Immagini/QueenNero.png"));
                else
                    this.img = ImageIO.read(Torre.class.getResource("../Immagini/QueenBianco.png"));

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Integer> getMoves(Integer I, Map<Integer, NWbotton> griglia) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        out.addAll(new Alfiere(this.color).getMoves(I,griglia));
        out.addAll(new Torre(this.color).getMoves(I,griglia));
        return out;
    }
}