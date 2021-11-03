package Scacchi.Pedine;

import Utils.NWbotton;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public abstract class PezzoScacchi {
    protected String color;
    protected Image img;
    public abstract ArrayList<Integer> getMoves(Integer I, Map<Integer, NWbotton> griglia);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PezzoScacchi that = (PezzoScacchi) o;
        return Objects.equals(color, that.color) && Objects.equals(img, that.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, img);
    }

    public Image getImg() {
        return img;
    }

    public String getColor() {
        return color;
    }
}
