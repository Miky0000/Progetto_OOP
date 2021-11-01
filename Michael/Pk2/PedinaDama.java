package Pk2;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class PedinaDama extends PezzoDama{

    public PedinaDama(String color) {
        this.color=color;
        try {
            if (color.equals("black"))
                this.img= ImageIO.read(PedinaDama.class.getResource("../Immagini/DamaNera.png"));
            else
                this.img= ImageIO.read(PedinaDama.class.getResource("../Immagini/DamaBianca.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Integer> getMoves (Map<Integer, Damabotton> griglia, Integer I) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        int x=I%8;
        int y=I/8;

        if (this.color.equals("white")){
            // in alto a sx
            int tmpx=x-1;
            int tmpy=y-1;
            if (x>=1 && tmpy>=0 && griglia.get((tmpx+8*tmpy)).getPezzo()==null)
                out.add(tmpx+tmpy*8);

            // in alto a dx
            tmpy=y-1;
            tmpx=x+1;
            if (x>=0 && x<=6 && tmpy>=0 && griglia.get((tmpx+8*tmpy)).getPezzo()==null)
                out.add(tmpx+8*tmpy);
        }

        if (this.color.equals("black")){
            // in basso a sx
            int tmpx=x-1;
            int tmpy=y+1;
            if (x>=1 && x<=7 && tmpy<=7 && griglia.get((tmpx+8*tmpy)).getPezzo()==null)
                out.add(tmpx+tmpy*8);

            // in alto a dx
            tmpy=y+1;
            tmpx=x+1;
            if (x>=0 && x<=6 && tmpy<=7 && griglia.get((tmpx+8*tmpy)).getPezzo()==null)
                out.add(tmpx+8*tmpy);
        }
        out.addAll(this.getMoreMoves(I,griglia));
        return out;
    }

    @Override
    public ArrayList<Integer> getMoves(Integer i, Map<Integer, NWbotton> griglia) {
        return null;
    }

    public ArrayList<Integer> getMoreMoves(Integer i, Map<Integer, Damabotton> griglia){
        ArrayList<Integer> out= new ArrayList<Integer>();
        int x=i%8;
        int y=i/8;
        int tmpx=x;
        int tmpy=y;

        if (this.color.equals("white")){
            // in alto a sx
            tmpx=x-1;
            tmpy=y-1;
            int altroindex=tmpx+tmpy*8;
            if (x>=2 && tmpy >= 0 && griglia.get((altroindex)).getPezzo()!=null && !(griglia.get(altroindex).getPezzo().getColor().equals(this.color))){
                altroindex= (tmpx-1)+(tmpy-1)*8;
                if (altroindex >= 0 && altroindex <= 63){
                    if (griglia.get(altroindex).getPezzo()==null)
                        out.add(altroindex);
                }
            }

            // in alto a dx
            tmpy=y-1;
            tmpx=x+1;
            altroindex=tmpx+tmpy*8;
            if (x<=5 && tmpy>=0 && griglia.get((altroindex)).getPezzo()!=null && !(griglia.get(altroindex).getPezzo().getColor().equals(this.color))){
                altroindex= (tmpx+1)+(tmpy-1)*8;
                if (altroindex >= 0 && altroindex <= 63){
                if (griglia.get(altroindex).getPezzo()==null)
                    out.add(altroindex);
                }
            }
        }

        if (this.color.equals("black")){
            // in basso a sx
            tmpx=x-1;
            tmpy=y+1;
            int altroindex=tmpx+tmpy*8;
            if (x>=2 && tmpy<=7 && griglia.get((altroindex)).getPezzo()!=null && !(griglia.get(altroindex).getPezzo().getColor().equals(this.color))){
                altroindex= (tmpx-1)+(tmpy+1)*8;
                if (altroindex >= 0 && altroindex <= 63){
                    if (griglia.get(altroindex).getPezzo()==null)
                    out.add(altroindex);
                }
            }

            // in basso a dx
            tmpy=y+1;
            tmpx=x+1;
            altroindex=tmpx+tmpy*8;
            if (x<=5 && tmpy<=7 && griglia.get((altroindex)).getPezzo()!=null && !(griglia.get(altroindex).getPezzo().getColor().equals(this.color))){
                altroindex= (tmpx+1)+(tmpy+1)*8;
                if (altroindex >= 0 && altroindex <= 63){
                    if (griglia.get(altroindex).getPezzo()==null)
                        out.add(altroindex);
                }
            }
        }
        return out;
    }
}
