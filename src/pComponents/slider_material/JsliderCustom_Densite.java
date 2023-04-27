package pComponents.slider_material;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JSlider;

public class JsliderCustom_Densite extends JSlider {

    public JsliderCustom_Densite() {
        setOpaque(false);
        setBackground(new Color(180, 180, 180));
        setForeground(new Color(69, 124, 235));
        setUI(new JSliderUI_Densité(this));
    }

    public void addActionListener(ActionListener event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
