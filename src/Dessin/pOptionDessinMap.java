package Dessin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Think
 */
public class pOptionDessinMap extends javax.swing.JPanel {

    /**
     * Creates new form pOtionDessinMap
     */
    public pOptionDessinMap() {
        initComponents();
        generationProcedurale1.setVisible(false);                               //panel de generation/Simulation n'est pas visible
        
        pDessin1.addGenererMapDessin(new ActionListener() {                     //si bouton de generation activé
            @Override
            public void actionPerformed(ActionEvent me) {
                pDessin1.setVisible(false);                                     //panel de dessin se ferme
                generationProcedurale1.setVisible(true);                        //Affichage du panel de generation/Simulation
                pDessin1.CreationTerrain();                                     //creation du terrain de simulation a partir de la grille dessinnée par l'utilisateur
                
                generationProcedurale1.setTerrain(pDessin1.getTerrain());       //affectation du terrain dans le panel de generation/Simulation
                generationProcedurale1.getpParametreTerrain1().setVisible(false);//fermeture du panel de Parametre terrain car il a ete dessiné par l'utilisateur
                generationProcedurale1.getpParametreMeteo1().setVisible(true);  //ouverture panel ParametreMeto pour les parametres de la simulation
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pDessin1 = new Dessin.pDessin();
        generationProcedurale1 = new pGeneration.GenerationProcedurale();

        setBackground(new java.awt.Color(40, 34, 64));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pDessin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generationProcedurale1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generationProcedurale1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pDessin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pGeneration.GenerationProcedurale generationProcedurale1;
    private Dessin.pDessin pDessin1;
    // End of variables declaration//GEN-END:variables
}
