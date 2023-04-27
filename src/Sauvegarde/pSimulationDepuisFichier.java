package Sauvegarde;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import projet_simulation_feu.Archive;
import projet_simulation_feu.Erreur;
import projet_simulation_feu.Terrain;

/**
 *
 * @author maxim
 */
public class pSimulationDepuisFichier extends javax.swing.JPanel {

    private Archive archive;
    private Terrain terrain;
    private File FichierSelectionne = null;
    
    public pSimulationDepuisFichier() {
        initComponents();
        generationProcedurale1.setVisible(false);
        
        pDepuisFichier1.addFichier(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Permet d'avoir l'interface de l'explorateur windows 7
                    JFileChooser fileChooser = new JFileChooser("user.home"); 

                    fileChooser.setDialogTitle("Recupération de la carte via un fichier texte");
                    fileChooser.setAcceptAllFileFilterUsed(false); //Force les filtres pour la selection du fichier
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Fichier Texte","txt","TXT"));

                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        FichierSelectionne = fileChooser.getSelectedFile();
                        String adresse = FichierSelectionne.getAbsolutePath();
                        try {
                            archive = new Archive();
                            terrain = archive.depuisFichierGraphique(adresse);
                        } catch (IOException | Erreur ex) {
                            Logger.getLogger(pDepuisFichier.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(pDepuisFichier.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (FichierSelectionne != null){
                    int n = terrain.getGrille().length - 1;
                    System.out.println(n);
                    int cpt = 0;
                    while(n>2){
                        n = n/2;
                        cpt ++;
                        System.out.println(cpt);
                    }
                    generationProcedurale1.getGrid().setTaille(cpt+1);
                    generationProcedurale1.setTerrain(terrain);
                    pDepuisFichier1.setVisible(false);
                    generationProcedurale1.setVisible(true);
                    generationProcedurale1.getpParametreTerrain1().setVisible(false);
                    generationProcedurale1.getpParametreMeteo1().setVisible(true);
                   
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        generationProcedurale1 = new pGeneration.GenerationProcedurale();
        pDepuisFichier1 = new Sauvegarde.pDepuisFichier();

        setBackground(new java.awt.Color(40, 34, 64));
        setMaximumSize(new java.awt.Dimension(1300, 700));
        setMinimumSize(new java.awt.Dimension(1300, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(generationProcedurale1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pDepuisFichier1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(generationProcedurale1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pDepuisFichier1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pGeneration.GenerationProcedurale generationProcedurale1;
    private Sauvegarde.pDepuisFichier pDepuisFichier1;
    // End of variables declaration//GEN-END:variables
}
