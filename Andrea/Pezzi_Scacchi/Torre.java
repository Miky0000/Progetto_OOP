package Pezzi_Scacchi;

import Tests.NWbotton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Torre extends Pezzo_Scacchi {
    private  String color;
    private Image img;

    public String getColor() {
        return color;
    }

    public Image getImg() {
        return img;
    }

    public Torre(String color) {
        this.color = color;
        {
            try {
                if (color.equalsIgnoreCase("black") )
                    this.img = ImageIO.read(Torre.class.getResource("../Immagini/TorreNero.png"));
                else
                    this.img= ImageIO.read(Torre.class.getResource("../Immagini/TorreBianco.png"));

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
        int tmpx=x;
        int tmpy=y;
        while(tmpy>0 && tmpy<=7){
            tmpy-=1;
            out.add(tmpx+tmpy*8);
        }
        tmpx=x;
        tmpy=y;
        while(tmpy>=0 && tmpy<7){
            tmpy+=1;
            out.add(tmpx+tmpy*8);
        }
        tmpx=x;
        tmpy=y;
        while(tmpx>0 && tmpx<=7){
            tmpx-=1;
            out.add(tmpx+tmpy*8);
        }
        tmpx=x;
        tmpy=y;
        while(tmpx>=0 && tmpx<7){
            tmpx+=1;
            out.add(tmpx+tmpy*8);
        }
        return out;
    }
}
