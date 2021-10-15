package Pezzi_Scacchi;

import Tests.NWbotton;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public abstract class Pezzo_Scacchi {
    String color;
    Image img;
    public abstract ArrayList<Integer> getMoves(Integer I, Map<Integer, NWbotton> griglia);

    public Image getImg() {
        return img;
    }
    public String getColor(){return color;}


}
