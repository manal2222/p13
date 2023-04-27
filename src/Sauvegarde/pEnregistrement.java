package Sauvegarde;

import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 *
 * @author maxim
 */
public class pEnregistrement extends javax.swing.JPanel {
    
    //Constructeur
    public pEnregistrement() {
        initComponents();
    }

    //Getter and Setter
    public JLabel getlIterationFin() {
        return lIterationFin;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tFinSimulation = new javax.swing.JLabel();
        tEnregistrement = new javax.swing.JLabel();
        bSauvegardeFichier = new pComponents.button.MyButton();
        lIterationFin = new javax.swing.JLabel();

        setBackground(new java.awt.Color(60, 68, 115));
        setMaximumSize(new java.awt.Dimension(447, 599));
        setMinimumSize(new java.awt.Dimension(447, 599));
        setPreferredSize(new java.awt.Dimension(447, 599));

        tFinSimulation.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        tFinSimulation.setForeground(new java.awt.Color(255, 255, 255));
        tFinSimulation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tFinSimulation.setText("Fin de simulation !");
        tFinSimulation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tEnregistrement.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        tEnregistrement.setForeground(new java.awt.Color(255, 255, 255));
        tEnregistrement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tEnregistrement.setText("Voulez-vous enregistrer votre carte de simulation ?");
        tEnregistrement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        bSauvegardeFichier.setBackground(new java.awt.Color(242, 143, 56));
        bSauvegardeFichier.setBorder(null);
        bSauvegardeFichier.setForeground(new java.awt.Color(255, 255, 255));
        bSauvegardeFichier.setText("Sauvegarder votre carte");
        bSauvegardeFichier.setBorderColor(new java.awt.Color(242, 143, 56));
        bSauvegardeFichier.setColor(new java.awt.Color(242, 143, 56));
        bSauvegardeFichier.setColorClick(new java.awt.Color(222, 123, 36));
        bSauvegardeFichier.setColorOver(new java.awt.Color(242, 143, 56));
        bSauvegardeFichier.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bSauvegardeFichier.setMaximumSize(new java.awt.Dimension(174, 50));
        bSauvegardeFichier.setMinimumSize(new java.awt.Dimension(174, 50));
        bSauvegardeFichier.setPreferredSize(new java.awt.Dimension(174, 50));
        bSauvegardeFichier.setRadius(50);

        lIterationFin.setForeground(new java.awt.Color(255, 255, 255));
        lIterationFin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lIterationFin.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tFinSimulation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tEnregistrement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(bSauvegardeFichier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lIterationFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(tFinSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lIterationFin, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(tEnregistrement, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(bSauvegardeFichier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addEnregistrement(ActionListener evt){
        bSauvegardeFichier.addActionListener(evt);
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pComponents.button.MyButton bSauvegardeFichier;
    private javax.swing.JLabel lIterationFin;
    private javax.swing.JLabel tEnregistrement;
    private javax.swing.JLabel tFinSimulation;
    // End of variables declaration//GEN-END:variables
}
