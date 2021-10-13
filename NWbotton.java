package Pk2;

import javax.swing.*;

public class NWbotton extends JButton {
    private int index;
    private boolean valid;
    private JButton b;

    public NWbotton(int index) {
        this.index = index;
    }

    public NWbotton() {
        this.b=new JButton();
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
                ", b=" + b +
                '}';
    }
}
