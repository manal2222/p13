package pGoogleMaps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import projet_simulation_feu.DiamondSquare;
import projet_simulation_feu.Eau;
import projet_simulation_feu.Foret;
import projet_simulation_feu.Humidite;
import projet_simulation_feu.Maison;
import projet_simulation_feu.Prairie;
import projet_simulation_feu.Terrain;
import projet_simulation_feu.Terre;
import projet_simulation_feu.Vent;

/**
 *
 * @author maxim
 */
public class pTranscription extends javax.swing.JPanel {

    private int TailleX = 512;
    private int TailleY = 512;
    private boolean Satellite = false;
    private File FichierSelectionne;
    private BufferedImage img;

    private Terrain terrain;
    private DiamondSquare DS;
    private Vent vent;
    private Humidite humidite;
    private int densite;
       
    
    //Construceteur
    public pTranscription() {
        initComponents();
        this.FichierSelectionne = null;
        this.img = null;
        this.DS = null;
        this.vent = null;
        this.humidite = new Humidite(0);
        
        this.terrain = new Terrain(TailleX,vent,humidite,densite,DS);
    }

    //Getter and setter
    public void setFichierSelectionne(File FichierSelectionne) {
        this.FichierSelectionne = FichierSelectionne;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public void setTailleX(int TailleX) {
        this.TailleX = TailleX;
    }

    public void setTailleY(int TailleY) {
        this.TailleY = TailleY;
    }

    public void setSatellite(boolean Satellite) {
        this.Satellite = Satellite;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        /*
            Cette méthode dessine dans le panel à partir d'une image.
            Elle génére également un terrain en fonction de la couleur des pixels de l'image.
        */
        super.paintComponent(g);
        if ((FichierSelectionne == null) || (img == null)){
            g.setColor(new Color(60,68,115));
            g.fillRect(0, 0, 512, 512);
        }
        else {
            terrain.setNombre_intacte(0);
            terrain.setIntacte(new ArrayList<>());
            for (int i=0; i < terrain.getGrille().length; i++){
                for (int j=0; j < terrain.getGrille()[0].length; j++){
                    int couleur = img.getRGB(j,i);
                    //Récuperer les couleurs RGB
                    int bleu = (couleur & 0xff);
                    int vert = (couleur & 0xff00) >> 8;
                    int rouge = (couleur & 0xff0000) >> 16;
                    
                    if (Satellite){
                        // SATELITTE \\
                        if (((rouge & vert & bleu)>90) && (vert-30<bleu) && (vert+50>bleu) && (vert-30<rouge) && (vert+50>rouge)){
                            //Maison
                            g.setColor(new Color(120,135,150));
                            g.fillRect(j, i, 1, 1);
                            terrain.getGrille()[i][j] = new Maison(i,j, terrain.getNiveau().getNiveau());
                           
                        }
                        else if ((rouge<vert) && (bleu<vert) && (vert<85)){
                            //Foret
                            g.setColor(new Color(20,70,45));
                            g.fillRect(j, i, 1, 1);
                            terrain.getGrille()[i][j] = new Foret(i,j,terrain.getNiveau().getNiveau());
                       
                                                    }
                        else if ((rouge<vert) && (bleu<vert) && (vert>=85)){
                            //Pairie
                            g.setColor(new Color(30,120,0));
                            g.fillRect(j, i, 1, 1);
                            terrain.getGrille()[i][j] = new Prairie(i,j,terrain.getNiveau().getNiveau());
                        
                            
                        }
                        else if((vert & rouge)<100 && (bleu>120) || (rouge<vert && vert<bleu)){
                            //Eau
                            g.setColor(new Color(43,86,193));
                            g.fillRect(j, i, 1, 1);
                            terrain.getGrille()[i][j] = new Eau(i,j,terrain.getNiveau().getNiveau());
                           
                         
                        }
                        else {
                            //Terre
                            g.setColor(new Color(130,109,92));
                            g.fillRect(j, i, 1, 1);
                            terrain.getGrille()[i][j] = new Terre(i,j,terrain.getNiveau().getNiveau());
                        }
                    } else {
                        // PLAN \\
                        if ( (220<rouge && rouge<250) && (230<bleu && bleu<250) && (220<vert && vert<250) ){
                            //Maison
                            g.setColor(new Color(210,214,220));
                            g.fillRect(j, i, 1, 1);
                            terrain.getGrille()[i][j] = new Maison(i,j,terrain.getNiveau().getNiveau());
                            
                         
                        }
                        else if ( (140<rouge && rouge<170) && (160<bleu && bleu<190) && (200<vert && vert<230) ){
                            //Foret
                            g.setColor(new Color(103,189,134));
                            g.fillRect(j, i, 1, 1);
                            terrain.getGrille()[i][j] = new Foret(i,j,terrain.getNiveau().getNiveau());
                            
                          
                        }
                        else if ( (170<rouge && rouge<200) && (180<bleu && bleu<210) && (210<vert && vert<240) ){
                            //Pairie
                            g.setColor(new Color(169,219,183));
                            g.fillRect(j, i, 1, 1);
                            terrain.getGrille()[i][j] = new Prairie(i,j,terrain.getNiveau().getNiveau());
                            
                            
                        }
                        else if( (130<rouge && rouge<170) && (230<bleu && bleu<260) && (170<vert && vert<210) ){
                            //Eau
                            g.setColor(new Color(110,162,246));
                            g.fillRect(j, i, 1, 1);
                            terrain.getGrille()[i][j] = new Eau(i,j,terrain.getNiveau().getNiveau());
                           
                            
                        }
                        else {
                            //Terre
                            g.setColor(new Color(227,166,15));
                            g.fillRect(j, i, 1, 1);
                            terrain.getGrille()[i][j] = new Terre(i,j,terrain.getNiveau().getNiveau());
                            
                           
                        }
                    }
                    terrain.getIntacte().add(terrain.getGrille()[i][j]);
                    terrain.setNombre_intacte(terrain.getNombre_intacte()+ 1);
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMaximumSize(new java.awt.Dimension(512, 512));
        setMinimumSize(new java.awt.Dimension(512, 512));
        setPreferredSize(new java.awt.Dimension(512, 512));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents




    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
