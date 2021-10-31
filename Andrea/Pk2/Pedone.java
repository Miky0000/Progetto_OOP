package Pk2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Pedone extends PezzoScacchi{

    private String color;
    private Image img;


    public Pedone(String color) {
        this.color = color;
        {
            try {
                if(this.color=="white") {
                    this.img = ImageIO.read(Pedone.class.getResource("../Immagini/PedoneBianco.png"));
                }
                else {
                    this.img = ImageIO.read(Pedone.class.getResource("../Immagini/PedoneNero.png"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Image getImg() {
        return img;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Pedone";
    }

    @Override
    public ArrayList<Integer> getMoves(Integer I, Map<Integer, NWbotton> griglia) {
        ArrayList<Integer> out=new ArrayList<Integer>();
        if(this.color=="black"){
            if (griglia.get(I+8).getPezzo()==null && I+8<=63)
                out.add(I+8);
            if (I/8==1 && griglia.get(I+16).getPezzo()==null)
                out.add(I+16);
            if (I%8!=1 && griglia.get(I+7).getPezzo()!=null && griglia.get(I+7).getPezzo().getColor()!=this.color )
                out.add(I+7);
            if (I%8!=7 && griglia.get(I+9).getPezzo()!=null && griglia.get(I+9).getPezzo().getColor()!=this.color )
                out.add(I+9);
        }
        if(this.color=="white"){
            if (griglia.get(I-8).getPezzo()==null && I-8 >= 0)
                out.add(I-8);
            if (I/8==6 && griglia.get(I-16).getPezzo()==null)
                out.add(I-16);
            if (I%8!=7 && griglia.get(I-7).getPezzo()!=null && griglia.get(I-7).getPezzo().getColor()!=this.color )
                out.add(I-7);
            if (I%8!=1 && griglia.get(I-9).getPezzo()!=null && griglia.get(I-9).getPezzo().getColor()!=this.color )
                out.add(I-9);
        }

        return out;
    }
}