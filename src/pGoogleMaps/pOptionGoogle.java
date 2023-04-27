package pGoogleMaps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author maxim
 */
public class pOptionGoogle extends javax.swing.JPanel {

    public pOptionGoogle() {
        initComponents();
        generationProcedurale1.setVisible(false);
        //pMaps1.getbParametre().setVisible(true);
        
        pMaps1.addSimu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                pMaps1.setVisible(false);
                generationProcedurale1.setVisible(true);
                generationProcedurale1.setTerrain(pMaps1.getTerrain());
                generationProcedurale1.getpParametreTerrain1().setVisible(false);
                generationProcedurale1.getpParametreMeteo1().setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pMaps1 = new pGoogleMaps.pMaps();
        generationProcedurale1 = new pGeneration.GenerationProcedurale();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(generationProcedurale1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pMaps1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pMaps1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(generationProcedurale1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pGeneration.GenerationProcedurale generationProcedurale1;
    private pGoogleMaps.pMaps pMaps1;
    // End of variables declaration//GEN-END:variables
}
