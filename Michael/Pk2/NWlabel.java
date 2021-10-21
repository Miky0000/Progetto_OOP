package Pk2;

import javax.swing.*;

public class NWlabel extends JLabel {

    private PezzoScacchi pezzo=null;

    public NWlabel(){super();}

    public PezzoScacchi getPezzo() {
        return pezzo;
    }

    public void setPezzo(PezzoScacchi pezzo) {
        this.pezzo = pezzo;
        if(pezzo!=null){
            this.setIcon(new ImageIcon(pezzo.getImg()));
        }
    }
}
