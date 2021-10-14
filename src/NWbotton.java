package Tests;


import Pezzi_Scacchi.Pezzo_Scacchi;

import javax.swing.*;

import javax.swing.*;

public class NWbotton extends JButton {
    private int index;
    private boolean valid;
    private Pezzo_Scacchi pezzo=null;

    public Pezzo_Scacchi getPezzo() {
        return pezzo;
    }

    public void setPezzo(Pezzo_Scacchi pezzo) {
        this.pezzo = pezzo;
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



