package Dessin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import projet_simulation_feu.*;

/**
 *
 * @author maxim
 */
public class pDessin extends javax.swing.JPanel {

    private Terrain terrain;
    private int taille_terrain;
    private long seed;
    private DiamondSquare DS;
    private Vent vent;
    private Humidite humidite;
    private int densite;
    private boolean securite_generation;
    private int compteur_securite;    
    
    public pDessin(){
        initComponents();
        compteur_securite = 0;
        securite_generation = false;
        
        DS = null;
        vent = null;
        this.humidite = new Humidite(0);
        terrain = new Terrain(pToille1.getGrille().length, vent, humidite, 0, DS); //creation d'un terrain temporaire pour affecter la grille pour la generation 

        
        pPalette1.addSelectionFond(new ActionListener() {                       //action de clique sur l'ensemble de radio boutton
            @Override
            public void actionPerformed(ActionEvent me) {                       //clique radio button
                if(pPalette1.getRbcEauToile().isSelected()){                    //selon le radio button, choix du fond de la toile
                    pToille1.setCouleur_fond(-2);
                    pPalette1.getbMapDessin().setEnabled(false);                //securite pour ne pas generer une carte non inflammable
                    securite_generation = true;                                 //securite pour ne pas generer une carte non inflammable
                    compteur_securite = 0;                                      //securite a 0 car moins de 10 cases inflammables
                }
                else if(pPalette1.getRbcTerreToile().isSelected()){
                    pToille1.setCouleur_fond(-1);
                    pPalette1.getbMapDessin().setEnabled(false);                //securite pour ne pas generer une carte non inflammable
                    securite_generation = true;                                 //securite pour ne pas generer une carte non inflammable
                    compteur_securite = 0;                                      //securite a 0 car moins de 1O cases inflammables
                }
                else if(pPalette1.getRbcPrairieToile().isSelected()){
                    pToille1.setCouleur_fond(1);
                    pPalette1.getbMapDessin().setEnabled(true);                 //active la generation de map apres le changement de toile
                    securite_generation = false;                                //pas de securite, la carte peut etre generee
                    compteur_securite = 10;                                     //pas de securite car plus de 10 cases inflammables
                }
                else if(pPalette1.getRbcForetToile().isSelected()){
                    pToille1.setCouleur_fond(2);
                    pPalette1.getbMapDessin().setEnabled(true);                 //active la generation de map apres le changement de toile
                    securite_generation = false;                                //pas de securite, la carte peut etre generee
                    compteur_securite = 10;                                     //pas de securite car plus de 10 cases inflammables
                }
                else if(pPalette1.getRbcMaisonToile().isSelected()){
                    pToille1.setCouleur_fond(3);
                    pPalette1.getbMapDessin().setEnabled(true);                 //active la generation de map apres le changement de toile
                    securite_generation = false;                                //pas de securite, la carte peut etre generee
                    compteur_securite = 10;                                     //pas de securite car plus de 10 cases inflammables
                }
                else{
                    pToille1.setCouleur_fond(0);
                    pPalette1.getbMapDessin().setEnabled(false);                //securite pour ne pas generer une carte non inflammable
                    securite_generation = true;                                 //securite pour ne pas generer une carte non inflammable
                    compteur_securite = 0;                                      //securite a 0 car moins de 10 cases inflammables
                }
                repaint();
                
                
                pPalette1.getlEpaisseur().setVisible(true);                     //rend visilble le label epaisseur
                pPalette1.getScEpaisseur().setVisible(true);                    //rend visilble la scroll bar epaisseur
                pPalette1.getScEpaisseur().setEnabled(true);                    //rend actif la scroll bar
                PinceauEnable(true);                                            //rend actif le choix du pinceau
                
                
                pPalette1.getlPinceau().setVisible(true);                       //rend visilble le label pinceau
                pPalette1.getRbcEauPinceau().setVisible(true);                  //rend visilble les radio button pinceau
                pPalette1.getRbcTerrePinceau().setVisible(true);
                pPalette1.getRbcPrairiePinceau().setVisible(true);
                pPalette1.getRbcForetPinceau().setVisible(true);
                pPalette1.getRbcMaisonPinceau().setVisible(true);
                
                pPalette1.getbReset().setVisible(true);                         //rend visilble le bouton reset
                pPalette1.getbMapDessin().setVisible(true);                     //rend visilble le bouton MapDessin
                
                pPalette1.getBgPinceau().clearSelection();                      //securite clear les ancienne selection de type de pinceau
                
                System.out.println(securite_generation);
            }
        });
        
        pPalette1.addPinceau(new ActionListener() {                             //action de clique sur l'ensemble de radio boutton
            @Override
            public void actionPerformed(ActionEvent me) {                       //clique radio button
                
                PaletteEnable(false);                                           //desactive la couleur de la toile
                pToille1.setFond(true);                                         //declare le fond de map comme present
                pToille1.setEpaisseur(pPalette1.getScEpaisseur().getValue());   //recupere epaisseur pinceau
                
                if(pPalette1.getRbcEauPinceau().isSelected()){                  //selon le radio button, choix du pinceau
                    pToille1.setPinceau(-2);
                }
                else if(pPalette1.getRbcTerrePinceau().isSelected()){
                    pToille1.setPinceau(-1);
                }
                else if(pPalette1.getRbcPrairiePinceau().isSelected()){
                    pToille1.setPinceau(1);
                }
                else if(pPalette1.getRbcForetPinceau().isSelected()){
                    pToille1.setPinceau(2);
                }
                else if(pPalette1.getRbcMaisonPinceau().isSelected()){
                    pToille1.setPinceau(3);
                }
                else{
                    pToille1.setPinceau(0);
                }
               
                pToille1.addMouseListener(new java.awt.event.MouseAdapter(){    //action de souris sur la toile
                    @Override
                    public void mousePressed( MouseEvent evt ) {                //clique souris
                        CalculDistance(evt);                                    //cration de rond avec l'epaisseur demandée autour du point cliqué
                        pToille1.repaint();                                     //repaint de la toile
                        if((!securite_generation)&&(compteur_securite >= 10)){  //si la securite est off et qu'il y a plus de 10 cases inflammables
                            pPalette1.getbMapDessin().setEnabled(true);         //Fin securite : ne pas generer une carte non inflammable
                        }
                        
                    } 
                });
                
                pToille1.addMouseMotionListener(new MouseMotionAdapter(){       //deplacement souris
                    public void mouseDragged( MouseEvent evt ) {                //clique souris
                        CalculDistance(evt);                                    //cration de rond avec l'epaisseur demandée autour du point cliqué
                        pToille1.repaint();                                     //repaint de la toile
                    }
                });
            }
        });     
        
        pPalette1.addReset(new ActionListener() {                               //action de clique sur le bouton reset
            @Override
            public void actionPerformed(ActionEvent me) {                       //clique boutton
                PinceauEnable(false);                                           //desactive le pinceau
                pPalette1.getScEpaisseur().setEnabled(false);                   //desactive epaisseur
                PaletteEnable(true);                                            //reactive la palette de terraib
                
                pToille1.setFond(false);                                        //recreer un fond de map
                GridNonClick();                                                 //securite desactive le clique sur la toile
                
                pPalette1.getbMapDessin().setEnabled(false);                    //securite desactive la generation de map apres le changement de toile
                pPalette1.getBgToile().clearSelection();                        //securite clear les ancienne selection de type de toile
                pPalette1.getBgPinceau().clearSelection();                      //securite clear les ancienne selection de type de pinceau
            }
        });    
        
        pPalette1.addGenererMapDessin(new ActionListener() {                    //action de clique sur le bouton GENERATION MAP 
            @Override
            public void actionPerformed(ActionEvent me) {                       //clique boutton
                GridNonClick();                                                 //securite desactive le clique sur la toile
            }
        });     
        
    }
   
    //getter and setter
    public Terrain getTerrain() {
        return terrain;
    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        try {
            grid1 = new pGrille.Grid();
        } catch (projet_simulation_feu.Erreur e1) {
            e1.printStackTrace();
        }
        try {
            pToille1 = new Dessin.pToille();
        } catch (projet_simulation_feu.Erreur e1) {
            e1.printStackTrace();
        }
        pPalette1 = new Dessin.pPalette();

        javax.swing.GroupLayout grid1Layout = new javax.swing.GroupLayout(grid1);
        grid1.setLayout(grid1Layout);
        grid1Layout.setHorizontalGroup(
            grid1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        grid1Layout.setVerticalGroup(
            grid1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(40, 34, 64));
        setMaximumSize(new java.awt.Dimension(1300, 700));
        setPreferredSize(new java.awt.Dimension(1300, 700));
        setRequestFocusEnabled(false);

        pToille1.setBackground(new java.awt.Color(60, 68, 115));

        javax.swing.GroupLayout pToille1Layout = new javax.swing.GroupLayout(pToille1);
        pToille1.setLayout(pToille1Layout);
        pToille1Layout.setHorizontalGroup(
            pToille1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );
        pToille1Layout.setVerticalGroup(
            pToille1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(pToille1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(pPalette1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(pToille1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(pPalette1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

 
    public void GridNonClick(){  //rend le panel de dessin non cliquable
        
        MouseListener[] mouseListeners = pToille1.getMouseListeners(); //desactive le click sur le panel
        for (MouseListener mouseListener : mouseListeners) {
            pToille1.removeMouseListener(mouseListener);
        }
    }
    
    public void CalculDistance(MouseEvent evt){      //calcul les cases a colorier en fonction de la position de la souris et du rayon du cercle demandé          
        double distance_X;
        double distance_Y;
        int rayon = pPalette1.getScEpaisseur().getValue();  //recupere rayon
        int x = evt.getX();
        int y = evt.getY();
        double rayon_Carre = Math.pow(rayon, 2); //met au carrre la valeur

        for (int j = y-rayon; j < y+rayon; j++) {       //definit un carre de cote rayon autour du clique de souris
            for(int i = x-rayon; i < x+rayon; i++){
                if(i >= 0 && i < pToille1.getGrille()[0].length){   //verifie si cest dans la grille
                    if(j >= 0 && j < pToille1.getGrille().length){
                        distance_X = Math.pow((i-x), 2);  //calcul les distance au carre entre le centre du cercle et les point contenu dqns le carre
                        distance_Y = Math.pow((j-y), 2);
                        if((distance_X+distance_Y) <= rayon_Carre){  //selectionne les points dans le cercle 
                            pToille1.getGrille()[j][i] = pToille1.getPinceau(); //si ils appartiennent au cercle on les dessine
                            if(securite_generation){                            //si le tarrain n'a pas assez de cases inflammables
                                if(pToille1.getPinceau() == 1 || pToille1.getPinceau() == 2 || pToille1.getPinceau() ==3 ){  //si la case est inflammable
                                    compteur_securite += 1;                         // le nombre de case inflammables
                                    if(compteur_securite >= 10){                //si plus de 10 cases inflammables
                                        securite_generation = false;            //fin securite
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void PaletteEnable(boolean bool){     //determine si les radio-buttons de la selection de la Toile sont cliquable ou non
        pPalette1.getRbcEauToile().setEnabled(bool);
        pPalette1.getRbcTerreToile().setEnabled(bool);
        pPalette1.getRbcPrairieToile().setEnabled(bool);
        pPalette1.getRbcForetToile().setEnabled(bool);
        pPalette1.getRbcMaisonToile().setEnabled(bool);
    }
    
    public void PinceauEnable(boolean bool){   //determine si les radio-buttons de la selection du Pinceau sont cliquable ou non
        pPalette1.getRbcEauPinceau().setEnabled(bool);
        pPalette1.getRbcTerrePinceau().setEnabled(bool);
        pPalette1.getRbcPrairiePinceau().setEnabled(bool);
        pPalette1.getRbcForetPinceau().setEnabled(bool);
        pPalette1.getRbcMaisonPinceau().setEnabled(bool);
    }
    
    public void CreationTerrain(){              //genere le terrain depuis la grille de dessin faite par l'utilisateur
        terrain.setIntacte(new ArrayList<>());                //reset données intactes 
        terrain.setNombre_intacte(0);                         //reset données intactes 

        for(int i = 0; i < pToille1.getGrille().length; i++){
            for(int j = 0; j < pToille1.getGrille()[0].length; j++){	
                switch(pToille1.getGrille()[i][j]){
                    case -2 :                                       //case eau
                        terrain.getGrille()[i][j] = new Eau(i,j,terrain.getNiveau().getNiveau());
                        break;

                    case -1 :                                       //case terre
                        terrain.getGrille()[i][j] = new Terre(i,j,terrain.getNiveau().getNiveau());
                        break;

                    case 1 :                                       //case prairie
                        terrain.getGrille()[i][j] = new Prairie(i,j,terrain.getNiveau().getNiveau());
                        break;

                    case 2 :                                       //case foret
                        terrain.getGrille()[i][j] = new Foret(i,j,terrain.getNiveau().getNiveau());
                        break;

                    case 3 :                                       //case maison
                        terrain.getGrille()[i][j] = new Maison(i,j,terrain.getNiveau().getNiveau());
                        break;
                }
                terrain.getIntacte().add(terrain.getGrille()[i][j]);  //ajout de la case dans la liste intacte
                terrain.setNombre_intacte(terrain.getNombre_intacte()+ 1); //ajout de la case au compteur des cases intactes
            }
        }
    }
    
    public void addGenererMapDessin(ActionListener evt){    //ajoute un actionListener a pPalette1
        pPalette1.addGenererMapDessin(evt);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pGrille.Grid grid1;
    private Dessin.pPalette pPalette1;
    private Dessin.pToille pToille1;
    // End of variables declaration//GEN-END:variables
}
