package Pezzi_Scacchi;

import Tests.NWbotton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Alfiere extends Pezzo_Scacchi {
    private  String color;
    private Image img;

    public String getColor() {
        return color;
    }

    public Image getImg() {
        return img;
    }

    public Alfiere(String color) {
        this.color = color;
        {
            try {
                if (color.equalsIgnoreCase("black") )
                    this.img = ImageIO.read(Alfiere.class.getResource("../Immagini/b_bishop_png_128px.png"));
                else
                    this.img= ImageIO.read(Alfiere.class.getResource("../Immagini/w_bishop_png_128px.png"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Integer> getMoves(Integer I, Map<Integer, NWbotton> griglia) {
        ArrayList<Integer>out = new ArrayList<Integer>();
        int i=I;
        while (i%8!=0 && i/8!=7) // diagonale in basso a SX
        {
            i+=7;
            if(I!=i)
                out.add(i);
        }
        if(I!=i)
            out.add(i);
        i=I;
        while (i%8!=7 && i/8!=7) //diagonale in basso a dx
        {
            i+=9;
            if(I!=i)
                out.add(i);
        }
        if(I!=i)
            out.add(i);
        i=I;

        while (i%8!=0 && i/8!=0) //diagonale in alto a sx
        {
            i-=9;
            if(I!=i)
                out.add(i);
        }
        if(I!=i)
            out.add(i);
        i=I;

        while (i%8!=7 && i/8!=0) //diagonale in alto a dx
        {
            i-=7;
            if(I!=i)
                out.add(i);
        }
        if(I!=i)
            out.add(i);


        return out;
    }
}