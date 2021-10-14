package Pezzi_Scacchi;

import java.awt.*;
import java.util.ArrayList;

public abstract class Pezzo_Scacchi {
    String color;
    Image img;
    public abstract ArrayList<Integer> getMoves(Integer I);
}
