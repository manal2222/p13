package pGeneration;
//VPompier1
import Pompier.GridPompier;
import Sauvegarde.pEnregistrement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import pGrille.Grid;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import projet_simulation_feu.*;

/**
 *
 * @author maxim
 */
public class GenerationProcedurale extends javax.swing.JPanel {

    private Terrain terrain;
    private int taille_terrain;
    private long seed;
    private DiamondSquare DS;
    private Vent vent;
    private Humidite humidite;
    private Archive archive;
    private int densite;
    private int cpt = 0;
    Timer horloge = new Timer();    
    int DELAY;
    Surcharge surcharge = new Surcharge();
    private int epaisseur;
    //private GridPompier pgridPompier1;
    //private ArrayList<int[]> DepartFeuManuel = new ArrayList<int[]>();
       
    public GenerationProcedurale(){
        initComponents();
        pParametreMeteo1.setVisible(false);
        pSimulation1.setVisible(false);
        pEnregistrement1.setVisible(false);
        pgridPompier1.setVisible(false);
        pPompier1.setVisible(false);
        
        GridNonClickTerrain();
        
        this.taille_terrain = (1 << 1) + 1;
        this.densite = 50;
        this.seed = (long) new Random().nextLong();
        this.DS = new DiamondSquare(taille_terrain,seed);
        this.vent = null;
        this.humidite = new Humidite(0);
        this.terrain = new Terrain(taille_terrain,vent,humidite,densite,DS);
        this.DELAY = 100;
        
        pParametreTerrain1.addSelectionTaille(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                GridNonClickTerrain(); //securite si changement durant surcharge
                
                CreationDS(seed);
                CreationMap();
                pParametreTerrain1.getScDensite().setVisible(true);
                pParametreTerrain1.getlDensite().setVisible(true);
            }
        });
        
        pParametreTerrain1.addSelectionDensite(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                GridNonClickTerrain(); //securite si changement durant surcharge

                terrain.setDensite(pParametreTerrain1.getScDensite().getValue());
                CreationMap();
                pParametreTerrain1.getbChangement().setVisible(true);
                pParametreTerrain1.getbParametres().setVisible(true);
                pParametreTerrain1.getlSurcharge().setVisible(true);
                pParametreTerrain1.getbSurchargeDebut().setVisible(true);
            }
        });
        
        pParametreTerrain1.addSelectionChangement(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                GridNonClickTerrain(); //securite si changement durant surcharge
                
                seed = (long) new Random().nextLong();
                CreationDS(seed);
                CreationMap();
            }
        });
        
        pParametreTerrain1.addSurchargeDebut(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                surcharge = new Surcharge();
                pParametreTerrain1.getbSurchargeDebut().setEnabled(false);
                //pParametreTerrain1.getlSurcharge().setText("Choisir l'épaisseur du chemin");
                
                VisibleCheminiEpaisseurTerrain(true);
                ResetCheminiEpaisseurTerrain();
                ActiveCheminEpaisseurTerrain(true);
                
            }
        });
        
        pParametreTerrain1.addEpaisseurChemin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                surcharge = new Surcharge();
                pParametreTerrain1.getbSurchargeDebut().setEnabled(false);
                pParametreTerrain1.getlSurcharge().setVisible(true);
                pParametreTerrain1.getlSurcharge().setText("Cliquer sur 2 cases du terrain au minimum");
                if(pParametreTerrain1.getRbcChemin1().isSelected()){
                    epaisseur = 1;
                }
                else if(pParametreTerrain1.getRbcChemin2().isSelected()){
                    epaisseur = 2;
                }
                else if(pParametreTerrain1.getRbcChemin3().isSelected()){
                    epaisseur = 3;
                }
                else{
                    //pParametreTerrain1.getlSurcharge().setText("Choisir l'épaisseur du chemin");
                    pParametreTerrain1.getlSurcharge().setText(" ");
                }
                pGrid.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        ActiveCheminEpaisseurTerrain(false);
                        int [] coord = pGrid.CalculCoord(evt);
                        pGridMousePressedSurchargeTerrain(coord,epaisseur);
                        pGrid.repaint();
                    }
                });
            }
        });
        
        pParametreTerrain1.addSurchargeArret(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                pParametreTerrain1.getbSurchargeDebut().setEnabled(true);
                pParametreTerrain1.getbSurchargeArret().setVisible(false);
                pParametreTerrain1.getbCheminAnnul().setVisible(false);
                surcharge.CreationTerre(terrain);
                pGrid.repaint();
                
                GridNonClickTerrain();
            }
        });
        
        pParametreTerrain1.addSurchargeAnnul(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                pParametreTerrain1.getbSurchargeDebut().setEnabled(true);
                pParametreTerrain1.getbSurchargeArret().setVisible(false);
                pParametreTerrain1.getbCheminAnnul().setVisible(false);
                surcharge.Suppression(terrain);
                pGrid.repaint();
                
                GridNonClickTerrain();
            }
        });
        
        pParametreTerrain1.addParametresSimu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                GridNonClickTerrain(); //securite si changement durant surcharge
                pParametreTerrain1.setVisible(false);
                pParametreMeteo1.setVisible(true);

                //FermetureElements();
            }
        });
        
        /////////////////////Panel 2
        pParametreMeteo1.addSelectionForceVent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                CreationForceVent();               
                pParametreMeteo1.getlOrientation().setVisible(true);
                pParametreMeteo1.getCSVent().setVisible(true);
            }
        });
        
        pParametreMeteo1.addSelectionOrientationVent(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                try {
                    vent = new Vent(vent.getForce(),(int) pParametreMeteo1.getCSVent().getAngle());
                } catch (Erreur ex) {
                    Logger.getLogger(GenerationProcedurale.class.getName()).log(Level.SEVERE, null, ex);
                }
                terrain.setVent(vent);
                pParametreMeteo1.getLhumidite().setVisible(true);
                pParametreMeteo1.getScHumidite().setVisible(true);
            }
        });
        
        pParametreMeteo1.addSelectionHumidite(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                double valeur = 1-pParametreMeteo1.getScHumidite().getValue()*0.01;
                terrain.setNiveau(new Humidite(valeur));
                AffectationHumidite(valeur);
                pParametreMeteo1.getLfeu().setVisible(true);
                pParametreMeteo1.getScFeu().setVisible(true);
            }
        });
        
        pParametreMeteo1.addSelectionNbFeu(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                pParametreMeteo1.getbFeuAleatoire().setVisible(true);
                pParametreMeteo1.getbFeuManuel().setVisible(true);
                
            }
        });
        
        pParametreMeteo1.addFeuAleatoire(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                int nb_depart = pParametreMeteo1.getScFeu().getValue();
                terrain.CreationFeu(2, nb_depart);
                pParametreMeteo1.getScFeu().setEnabled(false);
                pParametreMeteo1.getbFeuAleatoire().setEnabled(false);
                pParametreMeteo1.getbFeuManuel().setEnabled(false);
                pGrid.repaint();
                pParametreMeteo1.getbSimulation().setVisible(true);
                
            }
        });
        
        pParametreMeteo1.addFeuManuel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                pGrid.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        int valeur = pParametreMeteo1.getScFeu().getValue() - terrain.getNombre_enflammee() - 1;
                        pParametreMeteo1.getlChoixFeu().setText("Il reste "+valeur+" départ(s) à placer");
                        pGridMousePressedFeu(evt);
                    }
                });
                pParametreMeteo1.getScFeu().setEnabled(false);
                pParametreMeteo1.getbFeuAleatoire().setEnabled(false);
                pParametreMeteo1.getlChoixFeu().setVisible(true);
                pParametreMeteo1.getlChoixFeu().setText("Placer vos départ(s) de feux");
                
            }
        });
        
        pParametreMeteo1.addSimulation(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                pParametreMeteo1.setVisible(false);
                pSimulation1.setVisible(true);
                pSimulation1.getbCheminAnnul().setVisible(false);
                pSimulation1.getbCheminArret().setVisible(false);
                pSimulation1.getlSurcharge().setVisible(false);
                VisibleCheminiEpaisseurSimu(false);
                Statuts();
                pSimulation1.getlIteration().setText("Itération numero : " + cpt);
            }
        });
        
        /////////////////////Panel 3
        pSimulation1.addStartStop(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                GridNonClickSimu(); //securite si changement durant surcharge
                if(pSimulation1.getSbSimulation().isSelected()){
                    start();
                    Statuts();
                }
                else{
                    stop();
                }
            }
        });
        
        pSimulation1.addDelay(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                GridNonClickSimu(); //securite si changement durant surcharge
                setDELAY(pSimulation1.getScVitesse().getValue());
                if(pSimulation1.getSbSimulation().isSelected()){
                    stop();                                       //Reset du TimeTask pour update le Delay
                    start();
                }
            }
        });
        
        pSimulation1.addBoutonVent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {   
                GridNonClickSimu(); //securite si changement durant surcharge
                pSimulation1.getCSVent1().setVisible(true);
            }
        });
         
        pSimulation1.addSelectionOrientationVent(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                GridNonClickSimu(); //securite si changement durant surcharge
                try {
                    vent = new Vent(vent.getForce(),(int) pSimulation1.getCSVent1().getAngle());
                } catch (Erreur ex) {
                    Logger.getLogger(GenerationProcedurale.class.getName()).log(Level.SEVERE, null, ex);
                }
                terrain.setVent(vent);
            }
        });
        
        pSimulation1.addSurchargeDebut(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                
                if(pSimulation1.getSbSimulation().isSelected()){
                    stop();
                    pSimulation1.getSbSimulation().setSelected(false);
                }
                
                surcharge = new Surcharge();
                pSimulation1.getbCheminDebut().setEnabled(false);
                pSimulation1.getlSurcharge().setVisible(true);
                                
                VisibleCheminiEpaisseurSimu(true);
                ResetCheminiEpaisseurSimu();
                ActiveCheminEpaisseurSimu(true);
                
            }
        });
        
        pSimulation1.addEpaisseurChemin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                surcharge = new Surcharge();
                pSimulation1.getbCheminDebut().setEnabled(false);
                pSimulation1.getlSurcharge().setText("Cliquer sur 2 cases du terrain au minimum");
                if(pSimulation1.getRbcChemin1().isSelected()){
                    epaisseur = 1;
                }
                else if(pSimulation1.getRbcChemin2().isSelected()){
                    epaisseur = 2;
                }
                else if(pSimulation1.getRbcChemin3().isSelected()){
                    epaisseur = 3;
                }
                else{
                    //pSimulation1.getlSurcharge().setText("Choisir l'épaisseur du chemin");
                    pSimulation1.getlSurcharge().setText(" ");
                }
                pGrid.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        ActiveCheminEpaisseurSimu(false);
                        int [] coord = pGrid.CalculCoord(evt);
                        pGridMousePressedSurchargeSimulation(coord,epaisseur);
                        pGrid.repaint();
                    }
                });
            }
        });
        
        pSimulation1.addSurchargeArret(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                pSimulation1.getbCheminDebut().setEnabled(true);
                pSimulation1.getbCheminArret().setVisible(false);
                pSimulation1.getbCheminAnnul().setVisible(false);
                surcharge.CreationTerre(terrain);
                pGrid.repaint();
                
                GridNonClickSimu();
            }
        });
        
        pSimulation1.addSurchargeAnnul(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                pSimulation1.getbCheminDebut().setEnabled(true);
                pSimulation1.getbCheminArret().setVisible(false);
                pSimulation1.getbCheminAnnul().setVisible(false);
                surcharge.Suppression(terrain);
                pGrid.repaint();
                
                GridNonClickSimu();
            }
        });
        
        pSimulation1.addPompier(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                
                pSimulation1.getScVitesse().setValue(2000);
                setDELAY(2000);
                
                pgridPompier1.setVisible(true);
                pgridPompier1.setTerrain(terrain);
                pgridPompier1.setTaille(pGrid.getTaille());
                
                pGrid.setVisible(false);
                pSimulation1.setVisible(false);
                
                pgridPompier1.setVisible(true);
                pPompier1.setVisible(true);
                
                pgridPompier1.setThread(null);
                pgridPompier1.setWater(new ArrayList());
                pgridPompier1.setCpt_eau(0);
                
                pgridPompier1.start();
                
                FinBombardemanent();
                
                GridNonClickSimu();
                
                int valeur = (int) (pgridPompier1.getArret_eau() - pgridPompier1.getCpt_eau())*100/pgridPompier1.getArret_eau();
                
                pPompier1.getLpReservoir().setValue(valeur);
                            
                if(pSimulation1.getSbSimulation().isSelected()){
                    stop();                                       //Reset du TimeTask pour update le Delay
                    start();
                    //pSimulation1.getSbSimulation().setSelected(false);
                }
                
                
            }
        });
        
        // PANEL POMPIER \\
        pPompier1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                
                    pGrid.setVisible(true);
                    pSimulation1.setVisible(true);
                    pGrid.repaint();
                    pgridPompier1.setVisible(false);
                    pPompier1.setVisible(false);
                    pgridPompier1.stop();
                    KeyBoardeEnableFalse();
            }
        });    

        pgridPompier1.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                int valeur = (int) (pgridPompier1.getArret_eau() - pgridPompier1.getCpt_eau())*100/pgridPompier1.getArret_eau();
                
                pPompier1.getLpReservoir().setValue(valeur);
                if(!pgridPompier1.isStart()){
                    pGrid.setVisible(true);
                    pSimulation1.setVisible(true);
                    pGrid.repaint();
                    pgridPompier1.setVisible(false);
                    pPompier1.setVisible(false);
                    pgridPompier1.stop();
                   
                    KeyBoardeEnableFalse();
                }
            }
        });

        
        // PANEL SAUVEGARDE \\
        pEnregistrement1.addEnregistrement(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                try {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(pEnregistrement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JFileChooser fileChooser = new JFileChooser("user.home");

                    fileChooser.setDialogTitle("Sauvegarde de la carte dans un fichier texte");
                    fileChooser.setAcceptAllFileFilterUsed(false);
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Fichier Texte","txt","TXT"));

                    int result = fileChooser.showSaveDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File FichierASauvegarder = fileChooser.getSelectedFile();
                        String adresse = FichierASauvegarder.getAbsolutePath()+".txt";
                        archive = new Archive();
                        archive.versFichierGraphique(adresse,terrain);
                    }
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException  ex) {
                    Logger.getLogger(pEnregistrement.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GenerationProcedurale.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(GenerationProcedurale.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });        
    }
    
    //getter and setter
    public void setDELAY(int DELAY) {
        this.DELAY = DELAY;
    }

    public pParametreTerrain getpParametreTerrain1() {
        return pParametreTerrain1;
    }

    public pParametreMeteo getpParametreMeteo1() {
        return pParametreMeteo1;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
        pGrid.setTerrain(terrain);
    }

    public Grid getGrid() {
        return pGrid;
    }

    public void setPgridPompier1(GridPompier pgridPompier1) {
        this.pgridPompier1 = pgridPompier1;
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        try {
            grid1 = new pGrille.Grid();
        } catch (projet_simulation_feu.Erreur e1) {
            e1.printStackTrace();
        }
        try {
            pGrid = new pGrille.Grid();
        } catch (projet_simulation_feu.Erreur e1) {
            e1.printStackTrace();
        }
        pParametreTerrain1 = new pGeneration.pParametreTerrain();
        pParametreMeteo1 = new pGeneration.pParametreMeteo();
        pSimulation1 = new pGeneration.pSimulation();
        pEnregistrement1 = new Sauvegarde.pEnregistrement();
        pPompier1 = new Pompier.pPompier();
        pgridPompier1 = new Pompier.GridPompier();

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

        pGrid.setMaximumSize(new java.awt.Dimension(512, 512));
        pGrid.setPreferredSize(new java.awt.Dimension(512, 512));

        javax.swing.GroupLayout pGridLayout = new javax.swing.GroupLayout(pGrid);
        pGrid.setLayout(pGridLayout);
        pGridLayout.setHorizontalGroup(
            pGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );
        pGridLayout.setVerticalGroup(
            pGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pgridPompier1Layout = new javax.swing.GroupLayout(pgridPompier1);
        pgridPompier1.setLayout(pgridPompier1Layout);
        pgridPompier1Layout.setHorizontalGroup(
            pgridPompier1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );
        pgridPompier1Layout.setVerticalGroup(
            pgridPompier1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(pGrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pgridPompier1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(pParametreTerrain1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pParametreMeteo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pSimulation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pPompier1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pEnregistrement1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pEnregistrement1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(pParametreTerrain1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pSimulation1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pParametreMeteo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pPompier1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pgridPompier1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pGrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    public void AffectationHumidite(double valeur){
        for (int i = 0; i < terrain.getGrille().length; i++) {
            for (int j = 0; j < terrain.getGrille().length; j++) {
                terrain.getGrille()[i][j].setHumidite_pourcent(valeur);
            }
        }
    }
    
    public void FinBombardemanent(){
        for (int i = 0; i < terrain.getGrille().length; i++) {
            for (int j = 0; j < terrain.getGrille().length; j++) {
                terrain.getGrille()[i][j].setBombarde_eau(false);
            }
        }
    }
    
    private void pGridMousePressedSurchargeTerrain(int[] coord, int dimension){
        if(surcharge.getNb_point() >= 2){
            pParametreTerrain1.getbSurchargeArret().setVisible(true);
            pParametreTerrain1.getbCheminAnnul().setVisible(true);
            pParametreTerrain1.getlSurcharge().setText(" ");
        }
        surcharge.SurchargeGraphique(terrain, coord, dimension);
    }
    
    private void pGridMousePressedSurchargeSimulation(int[] coord, int dimension){
        if(surcharge.getNb_point() >= 2){
            pSimulation1.getbCheminArret().setVisible(true);
            pSimulation1.getbCheminAnnul().setVisible(true);
            pSimulation1.getlSurcharge().setText(" ");
        }
        surcharge.SurchargeGraphique(terrain, coord, dimension);
    }
    
    private void pGridMousePressedFeu(MouseEvent evt) {        
        int [] DepartFeuManuel = pGrid.CalculCoord(evt);
        int verif = terrain.ValidationFeuManuel(DepartFeuManuel);
        switch (verif){
            case 1 :
                //DepartFeuManuel.add(tab);
                terrain.CreationFeuManuelle(DepartFeuManuel);
                pGrid.repaint();
                break;
            case 2 :
                pParametreMeteo1.getlChoixFeu().setText("La case est déjà enflammée");
                break;
            case 3 :
                pParametreMeteo1.getlChoixFeu().setText("La case n'est pas enflammable");
                break;
        }
        
        if(terrain.getNombre_enflammee() >= pParametreMeteo1.getScFeu().getValue()){
            pParametreMeteo1.getlChoixFeu().setText("Vous avez selectionné tous les départs de feux");

            GridNonClick();
            pParametreMeteo1.getbFeuManuel().setEnabled(false);
            pParametreMeteo1.getbSimulation().setVisible(true);
        }
    }
        
    public void CreationMap(){
        terrain.GenerationTerrainProcedurale();
        pGrid.setTerrain(terrain);
        pGrid.repaint();
    }
    
    public void CreationDS(long seed){
        int n = pParametreTerrain1.getCbTailleCarte().getSelectedIndex()+3;
        pGrid.setTaille(n);
        
        int taille = (1 << n) +1;
        DS = new DiamondSquare(taille,seed);
        terrain.setDS(DS);
        terrain.setGrille(new Case[taille][taille]);
    }
    
    public void CreationForceVent(){
        if(vent == null){
            try {
                vent = new Vent(pParametreMeteo1.getCbForceVent().getSelectedIndex(),0); //valeur du combo Box + orientation 0 par default
                terrain.setVent(vent);
            } catch (Erreur ex) {
                Logger.getLogger(GenerationProcedurale.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else{
            try {
                vent = new Vent(pParametreMeteo1.getCbForceVent().getSelectedIndex(), (int) pParametreMeteo1.getCSVent().getAngle()); //valeur du combo Box + orientation 0 par default
                terrain.setVent(vent);
            } catch (Erreur ex) {
                Logger.getLogger(GenerationProcedurale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    
    public void Statuts(){
        pSimulation1.getCpbIntacte().setValue((int) terrain.getNombre_intacte()*100/(terrain.getGrille().length*terrain.getGrille().length));
        int En_Feu = (int) terrain.getNombre_enflammee()*100/(terrain.getGrille().length*terrain.getGrille().length) + terrain.getNombre_case_brule_chaud()*100/(terrain.getGrille().length*terrain.getGrille().length)+terrain.getNombre_case_brule_froid()*100/(terrain.getGrille().length*terrain.getGrille().length)+terrain.getNombre_case_cendre()*100/(terrain.getGrille().length*terrain.getGrille().length);
        pSimulation1.getCpbFeu().setValue(En_Feu);
    }
    
    class TacheAffichage extends TimerTask{
        @Override
        public void run(){
            cpt ++;
            pSimulation1.getlIteration().setText("Itération numero : " + cpt);
            terrain.CalculPourcentageFeu();
            terrain.Update();
            Statuts();
            repaint();
            if((terrain.getNombre_enflammee() <= 0) && (terrain.getNombre_case_brule_chaud() <= 0) && (terrain.getNombre_case_brule_froid() <= 0)){
                terrain.CalculPourcentageFeu();
                terrain.Update();
                Statuts();
                repaint();
                pSimulation1.setVisible(false);
                pPompier1.setVisible(false);
                pEnregistrement1.setVisible(true);
                pEnregistrement1.getlIterationFin().setText("Il y a eu " + cpt+" itérations");
                stop();
            }
        }
    }
    
    public void start(){
        horloge = new Timer();
        horloge.schedule(new TacheAffichage(),0,DELAY);
    }
    
    public void stop(){
        horloge.cancel();
    }
    
    public void GridNonClick(){
        MouseListener[] mouseListeners = pGrid.getMouseListeners(); //desactive le click sur le panel
        for (MouseListener mouseListener : mouseListeners) {
            pGrid.removeMouseListener(mouseListener);
        }
    }
    
    public void GridNonClickTerrain(){
        MouseListener[] mouseListeners = pGrid.getMouseListeners(); //desactive le click sur le panel
        for (MouseListener mouseListener : mouseListeners) {
            pGrid.removeMouseListener(mouseListener);
        }
        pParametreTerrain1.getbSurchargeDebut().setEnabled(true);
        pParametreTerrain1.getbSurchargeArret().setVisible(false);
        pParametreTerrain1.getbCheminAnnul().setVisible(false);
        pParametreTerrain1.getlSurcharge().setVisible(false);
        VisibleCheminiEpaisseurTerrain(false);
        
        ResetCheminiEpaisseurTerrain();
    }
    
    public void GridNonClickSimu(){
        MouseListener[] mouseListeners = pGrid.getMouseListeners(); //desactive le click sur le panel
        for (MouseListener mouseListener : mouseListeners) {
            pGrid.removeMouseListener(mouseListener);
        }
        surcharge.Suppression(terrain);
        pGrid.repaint();

        
        pSimulation1.getbCheminDebut().setEnabled(true);
        pSimulation1.getbCheminArret().setVisible(false);
        pSimulation1.getbCheminAnnul().setVisible(false);
        pSimulation1.getlSurcharge().setVisible(false);
        VisibleCheminiEpaisseurSimu(false);
        
        ResetCheminiEpaisseurSimu();
    }
    
    public void KeyBoardeEnableFalse(){
        KeyListener[] keyListeners = pGrid.getKeyListeners(); //desactive le click sur le panel
        for (KeyListener keyListener : keyListeners) {
            removeKeyListener(keyListener);
        }
    }
   
    
    public void ActiveCheminEpaisseurTerrain(boolean etat){
        pParametreTerrain1.getRbcChemin1().setEnabled(etat);
        pParametreTerrain1.getRbcChemin2().setEnabled(etat);
        pParametreTerrain1.getRbcChemin3().setEnabled(etat);
    }
    
    public void VisibleCheminiEpaisseurTerrain(boolean etat){
        pParametreTerrain1.getRbcChemin1().setVisible(etat);
        pParametreTerrain1.getRbcChemin2().setVisible(etat);
        pParametreTerrain1.getRbcChemin3().setVisible(etat);
        pParametreTerrain1.getlEpaisseurChemin().setVisible(etat);
    }
    
    public void ResetCheminiEpaisseurTerrain(){
        pParametreTerrain1.getBgTailleChemin().clearSelection();
    }
    
    public void ActiveCheminEpaisseurSimu(boolean etat){
        pSimulation1.getRbcChemin1().setEnabled(etat);
        pSimulation1.getRbcChemin2().setEnabled(etat);
        pSimulation1.getRbcChemin3().setEnabled(etat);
    }
    
    public void VisibleCheminiEpaisseurSimu(boolean etat){
        pSimulation1.getRbcChemin1().setVisible(etat);
        pSimulation1.getRbcChemin2().setVisible(etat);
        pSimulation1.getRbcChemin3().setVisible(etat);
        pSimulation1.getlEpaisseurChemin().setVisible(etat);
    }
    
    public void ResetCheminiEpaisseurSimu(){
        pSimulation1.getBgTailleChemin().clearSelection();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pGrille.Grid grid1;
    private Sauvegarde.pEnregistrement pEnregistrement1;
    private pGrille.Grid pGrid;
    private pGeneration.pParametreMeteo pParametreMeteo1;
    private pGeneration.pParametreTerrain pParametreTerrain1;
    private Pompier.pPompier pPompier1;
    private pGeneration.pSimulation pSimulation1;
    private Pompier.GridPompier pgridPompier1;
    // End of variables declaration//GEN-END:variables
}
