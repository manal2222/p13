/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pompier;

import pComponents.LiquidProgressBar.LiquidProgress;

/**
 *
 * @author maxim
 */
public class pPompier extends javax.swing.JPanel {

    /**
     * Creates new form pSimulation
     */
    public pPompier() {
        initComponents();
        //scVitesse.setEnabled(false);
    }

    //Getter and Setter
    public LiquidProgress getLpReservoir() {
        return lpReservoir;
    }

    public void setLpReservoir(LiquidProgress lpReservoir) {
        this.lpReservoir = lpReservoir;
    }
    

    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lDepartManuel = new javax.swing.JLabel();
        tFinSimulation = new javax.swing.JLabel();
        lpReservoir = new pComponents.LiquidProgressBar.LiquidProgress();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(60, 68, 115));
        setMaximumSize(new java.awt.Dimension(447, 599));
        setMinimumSize(new java.awt.Dimension(447, 599));
        setPreferredSize(new java.awt.Dimension(447, 599));

        lDepartManuel.setText(" ");

        tFinSimulation.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        tFinSimulation.setForeground(new java.awt.Color(255, 255, 255));
        tFinSimulation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tFinSimulation.setText("Pompiers");
        tFinSimulation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Réservoire du Canadaire");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tFinSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lDepartManuel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lpReservoir, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tFinSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lDepartManuel)
                    .addComponent(lpReservoir, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lDepartManuel;
    public static pComponents.LiquidProgressBar.LiquidProgress lpReservoir;
    private javax.swing.JLabel tFinSimulation;
    // End of variables declaration//GEN-END:variables
}
