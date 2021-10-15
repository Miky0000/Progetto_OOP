package Pezzi_Scacchi;

import Tests.NWbotton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Re extends Pezzo_Scacchi {
    private  String color;
    private Image img;

    public String getColor() {
        return color;
    }

    public Image getImg() {
        return img;
    }

    public Re(String color) {
        this.color = color;
        {
            try {
                if (color.equalsIgnoreCase("black") )
                    this.img = ImageIO.read(Re.class.getResource("../Immagini/KingNero.png"));
                else
                    this.img= ImageIO.read(Re.class.getResource("../Immagini/KingBianco.png"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Integer> getMoves(Integer I, Map<Integer, NWbotton> griglia) {

        ArrayList<Integer>out = new ArrayList<Integer>();

        int x=I%8;
        int y=I/8;
        int values[]={-1,0,+1};
        int tmpx,tmpy;
        for (int i=0; i<3;i++){
            tmpx=x+values[i];
            for (int j=0; j<3; j++){
                tmpy=y+values[j];
                if (tmpx>=0 && tmpx <=7 && tmpy>=0 && tmpy<=7 && (Math.abs(values[i])+Math.abs(values[j])!=0)) //300iq algorithm
                    if (griglia.get(tmpx+tmpy*8).getPezzo()==null || !(griglia.get(tmpx+tmpy*8).getPezzo().getColor().equals(this.color))) // copiare questo if sopra ogni out.add
                    out.add(tmpx+tmpy*8);

            }
        }


        return out;
    }
}