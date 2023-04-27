package pComponents.slider;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JSlider;

public class JsliderCustom extends JSlider {

    public JsliderCustom() {
        setOpaque(false);
        setBackground(new Color(180, 180, 180));
        setForeground(new Color(69, 124, 235));
        setUI(new JSliderUI(this));
    }
    
    public void addActionListener(ActionListener event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
