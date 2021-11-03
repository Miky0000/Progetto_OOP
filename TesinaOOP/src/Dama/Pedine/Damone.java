package Dama.Pedine;

import Utils.Damabotton;
import Utils.NWbotton;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Damone extends PezzoDama {

    public Damone(String color) {
        this.color=color;
        try {
            if (color.equals("black"))
                this.img= ImageIO.read(Objects.requireNonNull(PedinaDama.class.getResource("/Immagini/DamoneNera.png")));
            else
                this.img= ImageIO.read(Objects.requireNonNull(PedinaDama.class.getResource("/Immagini/DamoneBianca.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Integer> getMoves(Map<Integer, Damabotton> griglia, Integer I) {

        ArrayList<Integer> out= new ArrayList<>();
        out.addAll(new PedinaDama("black").getMoves(griglia,I));
        out.addAll(new PedinaDama("white").getMoves(griglia,I));
        return out;

    }

    @Override
    public ArrayList<Integer> getMoreMoves(Integer i, Map<Integer, Damabotton> griglia) {
        ArrayList<Integer> out= new ArrayList<>();
        int x=i%8;
        int y=i/8;
        int tmpx=x;
        int tmpy=y;

        tmpx++; //in alto a dx
        tmpy--;

        if (tmpx<=6 && tmpy >=1 && griglia.get(tmpx+tmpy*8).getPezzo()!=null && !griglia.get(tmpx+tmpy*8).getPezzo().getColor().equals(this.color)){
            tmpx++;
            tmpy--;
            if (griglia.get(tmpx+tmpy*8).getPezzo()==null)
                out.add(tmpx+tmpy*8);
        }

        tmpx=x; //in alto a sx
        tmpy=y;
        tmpx--;
        tmpy--;

        if (tmpx>=1 && tmpy >=1 && griglia.get(tmpx+tmpy*8).getPezzo()!=null && !griglia.get(tmpx+tmpy*8).getPezzo().getColor().equals(this.color)){
            tmpx--;
            tmpy--;
            if (griglia.get(tmpx+tmpy*8).getPezzo()==null)
                out.add(tmpx+tmpy*8);
        }
        tmpx=x; //in basso a sx
        tmpy=y;
        tmpx--;
        tmpy++;

        if (tmpx>=1 && tmpy <=6 && griglia.get(tmpx+tmpy*8).getPezzo()!=null && !griglia.get(tmpx+tmpy*8).getPezzo().getColor().equals(this.color)){
            tmpx--;
            tmpy++;
            if (griglia.get(tmpx+tmpy*8).getPezzo()==null)
                out.add(tmpx+tmpy*8);
        }
        tmpx=x; //in basso a sx
        tmpy=y;
        tmpx++;
        tmpy++;

        if (tmpx<=6 && tmpy <=6 && griglia.get(tmpx+tmpy*8).getPezzo()!=null && !griglia.get(tmpx+tmpy*8).getPezzo().getColor().equals(this.color)){
            tmpx++;
            tmpy++;
            if (griglia.get(tmpx+tmpy*8).getPezzo()==null)
                out.add(tmpx+tmpy*8);
        }


        return out;
    }

    @Override
    public ArrayList<Integer> getMoves(Integer I, Map<Integer, NWbotton> griglia) {
        return null;
    }
}
