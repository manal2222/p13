/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dessin;

import java.awt.*;
import projet_simulation_feu.*;

/**
 *
 * @author Think
 */
public class pToille extends javax.swing.JPanel {


    
    private Color Prairie = new Color(30,120,0);
    private Color Foret = new Color(20,70,45);
    private Color Maison = new Color(120,135,150);
    private Color Eau = new Color(43,86,193);
    private Color Terre = new Color(130,109,92);
    private boolean fond;
    private int couleur_fond;
    private int epaisseur;
    private int pinceau;
    private int [][] grille;
            
    //Constructeur
    public pToille() throws Erreur {
        initComponents();
        
        this.fond = false;
        this.couleur_fond = 0;
        this.epaisseur = 10;
        this.pinceau = 0;
        grille = new int [512][512];
    }

    //Getter and Setter

    public void setFond(boolean fond) {
        this.fond = fond;
    }

    public void setPinceau(int pinceau) {
        this.pinceau = pinceau;
    }

    public boolean isFond() {
        return fond;
    }

    public int getPinceau() {
        return pinceau;
    }

    public void setCouleur_fond(int couleur_fond) {
        this.couleur_fond = couleur_fond;
    }

    public void setEpaisseur(int epaisseur) {
        this.epaisseur = epaisseur;
    }
    
    public int[][] getGrille() {
        return grille;
    }
    


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(isFond() == false){              //sans fond de map selectionné, couleur grise par default
            grille = new int [512][512];
            Remplissage(couleur_fond);
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, 30, 30);
        }
        for (int j = 0; j < grille.length; j++) {
            for(int i = 0; i < grille[0].length; i++){
                switch(grille[j][i]){
                    case -2:     //eau
                        g.setColor(Eau);
                        g.fillRect(i, j, 1, 1);
                        break;
                    case -1:     //terre
                        g.setColor(Terre);
                        g.fillRect(i, j, 1, 1);
                        break;
                    case 1:
                        g.setColor(Prairie);
                        g.fillRect(i, j, 1, 1);
                        break;
                    case 2:
                        g.setColor(Foret);
                        g.fillRect(i, j, 1, 1);
                        break;
                    case 3:
                        g.setColor(Maison);
                        g.fillRect(i, j, 1, 1);
                        break;
                    default :
                        g.setColor(Color.GRAY);
                        g.fillRect(i, j, 1, 1);
                        break;
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

    public void Remplissage(int valeur){                    //rempli toute la grille avec la valeur du type de terrain par default
        for (int j = 0; j < grille.length; j++) {
            for(int i = 0; i < grille[0].length; i++){
                grille[j][i] = valeur;
            }
        }        
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
