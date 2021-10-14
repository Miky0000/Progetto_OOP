package Tests;


import Pezzi_Scacchi.Pezzo_Scacchi;

import javax.swing.*;

import javax.swing.*;
import java.awt.*;

public class NWbotton extends JButton {
    private int index;
    private boolean valid;
    private Pezzo_Scacchi pezzo=null;

    public Pezzo_Scacchi getPezzo() {
        return pezzo;
    }

    public void setPezzo(Pezzo_Scacchi pezzo) {
        this.pezzo = pezzo;
        this.setIcon(new ImageIcon(pezzo.getImg()));
    }
    public void restore_color_only()
    {
        if ((index/8 + index%8)%2==0)
            this.setBackground(Color.black);
        else
            this.setBackground(Color.white);
    }
    public void restore(){
        if ((index/8 + index%8)%2==0)
            this.setBackground(Color.black);
        else
            this.setBackground(Color.white);
        this.pezzo=null;
        this.setIcon(null);
    }

    public NWbotton(int index) {
        this.index = index;
    }

    public NWbotton() {
        super();
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "NWbotton{" +
                "index=" + index +

                '}';
    }
}



