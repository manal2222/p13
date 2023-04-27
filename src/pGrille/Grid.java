/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pGrille;

import java.awt.*;
import java.util.*;
import projet_simulation_feu.*;

/**
 *
 * @author Think
 */
public class Grid extends javax.swing.JPanel {

    Terrain terrain;
    
    int pixel = 1;
    int taille  = 9;
    int x=0,y=0;

    
    //Constructeur
    public Grid() throws Erreur {
        initComponents();
        this.taille = taille;
        Terrain terrain = null;
        //this.setEnabled(false);
    }

    //Getter and Setter
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTaille(int taille) {
        this.taille = taille;
        System.out.println(this.taille);
    }

    public int getTaille() {
        return taille;
    }
  
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        pixel = 9-taille;
        System.out.println(this.taille);
        pixel = 1 << pixel;
        if(terrain == null){
            g.setColor(Color.GRAY);
            g.fillRect(x, y, 519, 519);
        }
        else {
            for (int j = 0; j < terrain.getGrille().length; j++) {
                y = j*pixel;
                for (int i = 0; i < terrain.getGrille().length; i++){
                    x = i*pixel;
                    if(terrain.getGrille()[j][i].isSurcharge() == true){         //terre car surchargé
                        g.setColor(new Color(138,114,92));
                        g.fillRect(x, y, pixel, pixel);
                    }
                    else{
                        switch(terrain.getGrille()[j][i].getType_case()){
                            case -1:     //eau
                                g.setColor(new Color(77,97,156));
                                g.fillRect(x, y, pixel, pixel);
                                break;
                            case 0:     //terre
                                g.setColor(new Color(138,114,92));
                                g.fillRect(x, y, pixel, pixel);
                                break;
                            default :     //Prairie & Foret & Maison
                                switch (terrain.getGrille()[j][i].getEtat_flamme()){
                                    case "Intacte":
                                        switch (terrain.getGrille()[j][i].getType_case()){
                                            case 1:
                                                g.setColor(new Color(65,91,66));
                                                g.fillRect(x, y, pixel, pixel);
                                                break;
                                            case 2:
                                                g.setColor(new Color(34,52,35));
                                                g.fillRect(x, y, pixel, pixel);
                                                break;
                                            case 3:
                                                g.setColor(new Color(127,132,125));
                                                g.fillRect(x, y, pixel, pixel);
                                                break;
                                        }
                                        break;
                                    case "Enflammee":
                                        g.setColor(new Color(255,0,0));
                                        g.fillRect(x, y, pixel, pixel);
                                        break;
                                    case "Brule chaud":
                                        g.setColor(new Color(255,69,0));
                                        g.fillRect(x, y, pixel, pixel);
                                        break;
                                    case "Brule froid":
                                        switch (terrain.getGrille()[j][i].getBrule_froid()){
                                            case 3:
                                                g.setColor(new Color(255,215,0));
                                                g.fillRect(x, y, pixel, pixel);
                                                break;
                                            case 2:
                                                g.setColor(new Color(255,255,0));
                                                g.fillRect(x, y, pixel, pixel);
                                                break;
                                            case 1:
                                                g.setColor(new Color(255,255,102));
                                                g.fillRect(x, y, pixel, pixel);
                                                break;
                                            case 0:
                                                g.setColor(new Color(211,211,211));
                                                g.fillRect(x, y, pixel, pixel);
                                                break;
                                        }
                                        break;
                                    case "Cendre":
                                        g.setColor(new Color(200,194,182));
                                        g.fillRect(x, y, pixel, pixel);
                                        break;
                                }
                                //System.out.print(" "+ Rouge + Test.getGrille()[i][j].getType_case() + ANSI_RESET +" ");
                                break;
                        }
                    }
                }
            }
        } 
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(60, 68, 115));
        setEnabled(false);
        setPreferredSize(new java.awt.Dimension(513, 513));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public int[] CalculCoord(java.awt.event.MouseEvent evt){
        int [] tab = {-1,-1};
        int taille_pixel = 1 << (9-taille);
        tab[0] = evt.getY() / taille_pixel;
        tab[1] = evt.getX() / taille_pixel;
        return tab;
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
