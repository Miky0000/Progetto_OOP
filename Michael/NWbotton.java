package Pk2;

import javax.swing.*;
import java.awt.*;

public class NWbotton extends JButton {
    private int index;
    private Color colore;
    private PezzoScacchi pezzo=null;

    public NWbotton() {
        super();
    }

    public Color getColore() {
        return colore;
    }

    public void setColore(Color colore) {
        this.colore = colore;
    }

    public NWbotton(int index) {
        this.index = index;
    }

    public PezzoScacchi getPezzo() {
        return pezzo;
    }

    @Override
    public String toString() {
        return "NWbotton{" +
                "colore=" + colore +
                '}';
    }

    public void setPezzo(PezzoScacchi pezzo) {
        this.pezzo = pezzo;
        this.setIcon(new ImageIcon(pezzo.getImg()));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
