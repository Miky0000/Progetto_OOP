package Pk2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Cavallo extends PezzoScacchi {
    private  String color;
    private Image img;

    public String getColor() {
        return color;
    }

    public Image getImg() {
        return img;
    }

    public Cavallo(String color) {
        this.color = color;
        {
            try {
                if (color.equalsIgnoreCase("black") )
                    this.img = ImageIO.read(Cavallo.class.getResource("../Immagini/CavalloNero.png"));
                else
                    this.img= ImageIO.read(Cavallo.class.getResource("../Immagini/CavalloBianco.png"));

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Cavallo";
    }

    @Override
    public ArrayList<Integer> getMoves(Integer I, Map<Integer, NWbotton> griglia) {
        ArrayList<Integer>out = new ArrayList<Integer>();

        int x=I%8;
        int y=I/8;
        int values[]={-2,-1,+1,+2};
        int tmpx,tmpy;
        for (int i=0; i<4;i++){
            tmpx=x+values[i];
            for (int j=0; j<4; j++) {
                tmpy = y + values[j];
                if (tmpx >= 0 && tmpx <= 7 && tmpy >= 0 && tmpy <= 7 && (Math.abs(values[i]) + Math.abs(values[j]) == 3)) //300iq algorithm
                    if (griglia.get(tmpx + tmpy * 8).getActionCommand() != "azione1") {
                        out.add(tmpx + tmpy * 8);
                    }
                    else if (griglia.get(tmpx + tmpy * 8).getPezzo() != null && griglia.get(tmpx + tmpy * 8).getPezzo().getColor() != griglia.get(I).getPezzo().getColor()) {  //300 iq per capire + 300 per implementare = 600 iq condizion controllo che il pezzo sia avversario o meno e che sia diverso da null
                        out.add(tmpx + tmpy * 8);
                    }

            }
        }

        return out;
    }
}