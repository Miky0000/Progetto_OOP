package Pk2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Pedone{
    private static Image img;


    static {
        try {
            img = ImageIO.read(Test.class.getResource("/torre2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Pedone() {
        img=this.img;
    }

    public static Image getImg() {
        return img;
    }

    public static void setImg(Image img) {
        Pedone.img = img;
    }
}