
package projet_simulation_feu;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;


public class Canadair {

    public static final double Player_size_x = 92;
    public static final double Player_size_y = 84;
    private double x;
    private double y;
    private float angle = 0f;
    private final Image image;
    
    public Canadair() {
        this.image = new ImageIcon(getClass().getResource("/Pompier/Canadair.png")).getImage();
    }

    public void changeLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void changeAngle(float angle) {
        if (angle < 0) {
            angle = 359;
        } else if (angle > 359) {
            angle = 0;
        }
        this.angle = angle;
    }

    public void draw(Graphics2D g2) {
        AffineTransform oldTransform = g2.getTransform();
        g2.translate(x, y);
        AffineTransform tran = new AffineTransform();
        tran.rotate(Math.toRadians(angle+90), Player_size_x/2, Player_size_y/2);
        g2.drawImage(image, tran, null);
        g2.setTransform(oldTransform);  
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public float getAngle() {
        return angle;
    }
}