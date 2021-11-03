package Utils;

import Scacchi.Pedine.Pedone;
import Scacchi.Pedine.PezzoScacchi;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Sfondo extends PezzoScacchi {


    {
        try {
            this.img = ImageIO.read(Objects.requireNonNull(Pedone.class.getResource("/Immagini/Sfondo.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public ArrayList<Integer> getMoves(Integer I, Map<Integer, NWbotton> griglia) {
        return null;
    }
}
