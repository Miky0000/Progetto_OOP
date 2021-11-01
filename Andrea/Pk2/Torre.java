package Pk2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Torre extends PezzoScacchi {
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
            if(griglia.get(tmpx+tmpy*8).getPezzo()!=null) {     //controllo che non ci sia un pezzo
                break;
            }
            out.add(tmpx+tmpy*8);
        }
        if(griglia.get(tmpx+tmpy*8).getPezzo()!=null && griglia.get(tmpx+tmpy*8).getPezzo().getColor()!=this.color)  //controllo che il pezzo sia avversario o meno e che sia diverso da null
            out.add(tmpx+tmpy*8);
        tmpx=x;
        tmpy=y;
        while(tmpy>=0 && tmpy<7){
            tmpy+=1;
            if(griglia.get(tmpx+tmpy*8).getPezzo()!=null) {     //controllo che non ci sia un pezzo
                break;
            }
            out.add(tmpx+tmpy*8);
        }
        if(griglia.get(tmpx+tmpy*8).getPezzo()!=null && griglia.get(tmpx+tmpy*8).getPezzo().getColor()!=this.color)  //controllo che il pezzo sia avversario o meno e che sia diverso da null
            out.add(tmpx+tmpy*8);
        tmpx=x;
        tmpy=y;
        while(tmpx>0 && tmpx<=7){
            tmpx-=1;
            if(griglia.get(tmpx+tmpy*8).getPezzo()!=null) {     //controllo che non ci sia un pezzo
                break;
            }
            out.add(tmpx+tmpy*8);
        }
        if(griglia.get(tmpx+tmpy*8).getPezzo()!=null && griglia.get(tmpx+tmpy*8).getPezzo().getColor()!=this.color)  //controllo che il pezzo sia avversario o meno e che sia diverso da null
            out.add(tmpx+tmpy*8);
        tmpx=x;
        tmpy=y;
        while(tmpx>=0 && tmpx<7){
            tmpx+=1;
            if(griglia.get(tmpx+tmpy*8).getPezzo()!=null) {     //controllo che non ci sia un pezzo
                break;
            }
            out.add(tmpx+tmpy*8);
        }
        if(griglia.get(tmpx+tmpy*8).getPezzo()!=null && griglia.get(tmpx+tmpy*8).getPezzo().getColor()!=this.color)  //controllo che il pezzo sia avversario o meno e che sia diverso da null
            out.add(tmpx+tmpy*8);
        return out;
    }

    @Override
    public String toString() {
        return "Torre";
    }
}
