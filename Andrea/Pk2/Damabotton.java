package Pk2;

import javax.swing.*;
import java.awt.*;

public class Damabotton extends JButton {
    private int index;
    private Color colore;
    private PezzoDama pezzo=null;

    public Damabotton() {
        super();
    }

    public Color getColore() {
        return colore;
    }

    public void setColore(Color colore) {
        this.colore = colore;
    }

    public Damabotton(int index) {
        this.index = index;
    }

    public PezzoDama getPezzo() {
        return pezzo;
    }

    @Override
    public String toString() {
        return "NWbotton{" +
                "colore=" + colore +
                '}';
    }

    public void setPezzo(PezzoDama pezzo) {
        this.pezzo = pezzo;
        if(pezzo!=null){
            this.setIcon(new ImageIcon(pezzo.getImg()));
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void restore() { // vuoto ma se serve
    }

    public void restore_color_only() { // come sopra
    }
}
