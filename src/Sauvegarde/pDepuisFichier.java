package Sauvegarde;

import java.awt.event.ActionListener;

/**
 *
 * @author maxim
 */
public class pDepuisFichier extends javax.swing.JPanel {
    
    public pDepuisFichier() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tFinSimulation = new javax.swing.JLabel();
        bChoixFichier = new pComponents.button.MyButton();

        setBackground(new java.awt.Color(40, 34, 64));
        setMaximumSize(new java.awt.Dimension(20000, 20000));
        setMinimumSize(new java.awt.Dimension(1300, 700));
        setPreferredSize(new java.awt.Dimension(1300, 700));

        tFinSimulation.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        tFinSimulation.setForeground(new java.awt.Color(255, 255, 255));
        tFinSimulation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tFinSimulation.setText("Selectionner le fichier pour la simulation");
        tFinSimulation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        bChoixFichier.setBackground(new java.awt.Color(242, 143, 56));
        bChoixFichier.setBorder(null);
        bChoixFichier.setForeground(new java.awt.Color(255, 255, 255));
        bChoixFichier.setText("Choisir un fichier");
        bChoixFichier.setBorderColor(new java.awt.Color(242, 143, 56));
        bChoixFichier.setBorderPainted(false);
        bChoixFichier.setColor(new java.awt.Color(242, 143, 56));
        bChoixFichier.setColorClick(new java.awt.Color(222, 123, 36));
        bChoixFichier.setColorOver(new java.awt.Color(242, 143, 56));
        bChoixFichier.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bChoixFichier.setMaximumSize(new java.awt.Dimension(174, 50));
        bChoixFichier.setMinimumSize(new java.awt.Dimension(174, 50));
        bChoixFichier.setPreferredSize(new java.awt.Dimension(174, 50));
        bChoixFichier.setRadius(50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tFinSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(563, 563, 563)
                .addComponent(bChoixFichier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(563, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(tFinSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bChoixFichier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(478, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addFichier(ActionListener evt){
        bChoixFichier.addActionListener(evt);
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pComponents.button.MyButton bChoixFichier;
    private javax.swing.JLabel tFinSimulation;
    // End of variables declaration//GEN-END:variables
}
