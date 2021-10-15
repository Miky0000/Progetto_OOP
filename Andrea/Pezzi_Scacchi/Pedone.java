package Pezzi_Scacchi;

import Tests.NWbotton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

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
                    this.img = ImageIO.read(Pedone.class.getResource("../Immagini/PedoneNero.png"));
                else
                    this.img= ImageIO.read(Pedone.class.getResource("../Immagini/PedoneBianco.png"));

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Integer> getMoves(Integer I, Map<Integer, NWbotton> griglia) {
        ArrayList<Integer>out = new ArrayList<Integer>();

        if (this.color=="white") {
            if (I-8<0)
                return out; //Bugfix
            out.add(I - 8);
        }
        if (this.color=="black") {
            if (I+8>64)
                return out; // Bugfix
            out.add(I + 8);
        }

        return out;
    }
}
