/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pGeneration;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import pComponents.Circular_progresse.CircleProgressBar;
import pComponents.button.MyButton;
import pComponents.circularSlider.CustomSlider;
import pComponents.radio_button.RadioButtonCustom;
import pComponents.slider_material.JsliderCustom_Vitesse;
import pComponents.switch_button.SwitchButton;

/**
 *
 * @author maxim
 */
public class pSimulation extends javax.swing.JPanel {

    /**
     * Creates new form pSimulation
     */
    public pSimulation() {
        initComponents();
        CSVent1.setVisible(false);
        //scVitesse.setEnabled(false);
        //bPompier.setVisible(false);
    }

    //Getter and Setter
    public CustomSlider getCSVent1() {
        return CSVent1;
    }

    public MyButton getbModifVent() {
        return bModifVent;
    }

    public SwitchButton getSbSimulation() {
        return sbSimulation;
    }

    public JsliderCustom_Vitesse getScVitesse() {
        return scVitesse;
    }

    public CircleProgressBar getCpbFeu() {
        return cpbFeu;
    }

    public CircleProgressBar getCpbIntacte() {
        return cpbIntacte;
    }

    public MyButton getbCheminAnnul() {
        return bCheminAnnul1;
    }

    public MyButton getbCheminArret() {
        return bCheminArret;
    }

    public MyButton getbCheminDebut() {
        return bCheminDebut;
    }

    public JLabel getlSurcharge() {
        return lSurcharge;
    }
    
    public JLabel getlEpaisseurChemin() {
        return lEpaisseurChemin;
    }

    public RadioButtonCustom getRbcChemin1() {
        return rbcChemin1;
    }

    public RadioButtonCustom getRbcChemin2() {
        return rbcChemin2;
    }

    public RadioButtonCustom getRbcChemin3() {
        return rbcChemin3;
    }

    public ButtonGroup getBgTailleChemin() {
        return bgTailleChemin;
    }

    public JLabel getlIteration() {
        return lIteration;
    }

    public MyButton getbPompier() {
        return bPompier;
    }
        
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTailleChemin = new javax.swing.ButtonGroup();
        lVitesse = new javax.swing.JLabel();
        lDepartManuel = new javax.swing.JLabel();
        bModifVent = new pComponents.button.MyButton();
        scVitesse = new pComponents.slider_material.JsliderCustom_Vitesse();
        sbSimulation = new pComponents.switch_button.SwitchButton();
        lStatuts = new javax.swing.JLabel();
        lVitesse1 = new javax.swing.JLabel();
        lVitesse2 = new javax.swing.JLabel();
        lSimulation = new javax.swing.JLabel();
        lIntacte = new javax.swing.JLabel();
        lEnflammee = new javax.swing.JLabel();
        CSVent1 = new pComponents.circularSlider.CustomSlider();
        bCheminDebut = new pComponents.button.MyButton();
        lSurcharge = new javax.swing.JLabel();
        bCheminArret = new pComponents.button.MyButton();
        bCheminAnnul1 = new pComponents.button.MyButton();
        cpbIntacte = new pComponents.Circular_progresse.CircleProgressBar();
        cpbFeu = new pComponents.Circular_progresse.CircleProgressBar();
        rbcChemin2 = new pComponents.radio_button.RadioButtonCustom();
        rbcChemin3 = new pComponents.radio_button.RadioButtonCustom();
        lEpaisseurChemin = new javax.swing.JLabel();
        rbcChemin1 = new pComponents.radio_button.RadioButtonCustom();
        lIteration = new javax.swing.JLabel();
        bPompier = new pComponents.button.MyButton();

        setBackground(new java.awt.Color(60, 68, 115));
        setMaximumSize(new java.awt.Dimension(447, 599));
        setMinimumSize(new java.awt.Dimension(447, 599));
        setPreferredSize(new java.awt.Dimension(447, 599));

        lVitesse.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lVitesse.setForeground(new java.awt.Color(255, 255, 255));
        lVitesse.setText("Selectionner la vitesse de simulation");

        lDepartManuel.setText(" ");

        bModifVent.setBackground(new java.awt.Color(191, 94, 94));
        bModifVent.setForeground(new java.awt.Color(255, 255, 255));
        bModifVent.setText("Orientation du Vent");
        bModifVent.setBorderColor(new java.awt.Color(191, 94, 94));
        bModifVent.setColor(new java.awt.Color(191, 94, 94));
        bModifVent.setColorClick(new java.awt.Color(171, 74, 74));
        bModifVent.setColorOver(new java.awt.Color(191, 94, 94));
        bModifVent.setMaximumSize(new java.awt.Dimension(170, 30));
        bModifVent.setMinimumSize(new java.awt.Dimension(170, 30));
        bModifVent.setPreferredSize(new java.awt.Dimension(170, 40));
        bModifVent.setRadius(50);
        bModifVent.setRequestFocusEnabled(false);

        scVitesse.setBackground(new java.awt.Color(242, 143, 56));
        scVitesse.setForeground(new java.awt.Color(242, 143, 56));
        scVitesse.setPreferredSize(new java.awt.Dimension(309, 15));

        sbSimulation.setBackground(new java.awt.Color(242, 143, 56));

        lStatuts.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lStatuts.setForeground(new java.awt.Color(255, 255, 255));
        lStatuts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lStatuts.setText("Statuts");

        lVitesse1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lVitesse1.setForeground(new java.awt.Color(255, 255, 255));
        lVitesse1.setText("ON");
        lVitesse1.setPreferredSize(new java.awt.Dimension(100, 28));

        lVitesse2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lVitesse2.setForeground(new java.awt.Color(255, 255, 255));
        lVitesse2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lVitesse2.setText("OFF");
        lVitesse2.setPreferredSize(new java.awt.Dimension(100, 28));

        lSimulation.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lSimulation.setForeground(new java.awt.Color(255, 255, 255));
        lSimulation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lSimulation.setText("Simulation");

        lIntacte.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lIntacte.setForeground(new java.awt.Color(255, 255, 255));
        lIntacte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lIntacte.setText("Intacte");

        lEnflammee.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lEnflammee.setForeground(new java.awt.Color(255, 255, 255));
        lEnflammee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lEnflammee.setText("Enflammee");

        CSVent1.setOpaque(false);

        javax.swing.GroupLayout CSVent1Layout = new javax.swing.GroupLayout(CSVent1);
        CSVent1.setLayout(CSVent1Layout);
        CSVent1Layout.setHorizontalGroup(
            CSVent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        CSVent1Layout.setVerticalGroup(
            CSVent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        bCheminDebut.setBackground(new java.awt.Color(242, 113, 65));
        bCheminDebut.setForeground(new java.awt.Color(255, 255, 255));
        bCheminDebut.setText("Création du chemin");
        bCheminDebut.setBorderColor(new java.awt.Color(242, 113, 65));
        bCheminDebut.setColor(new java.awt.Color(242, 113, 65));
        bCheminDebut.setColorClick(new java.awt.Color(222, 93, 45));
        bCheminDebut.setColorOver(new java.awt.Color(242, 113, 65));
        bCheminDebut.setMaximumSize(new java.awt.Dimension(170, 30));
        bCheminDebut.setMinimumSize(new java.awt.Dimension(170, 30));
        bCheminDebut.setPreferredSize(new java.awt.Dimension(170, 40));
        bCheminDebut.setRadius(50);

        lSurcharge.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lSurcharge.setForeground(new java.awt.Color(255, 255, 255));
        lSurcharge.setText(" ");

        bCheminArret.setBackground(new java.awt.Color(242, 113, 65));
        bCheminArret.setForeground(new java.awt.Color(255, 255, 255));
        bCheminArret.setText("Arrêt du chemin");
        bCheminArret.setBorderColor(new java.awt.Color(242, 113, 65));
        bCheminArret.setColor(new java.awt.Color(242, 113, 65));
        bCheminArret.setColorClick(new java.awt.Color(222, 93, 45));
        bCheminArret.setColorOver(new java.awt.Color(242, 113, 65));
        bCheminArret.setMaximumSize(new java.awt.Dimension(170, 30));
        bCheminArret.setMinimumSize(new java.awt.Dimension(170, 30));
        bCheminArret.setPreferredSize(new java.awt.Dimension(170, 40));
        bCheminArret.setRadius(50);

        bCheminAnnul1.setBackground(new java.awt.Color(242, 113, 65));
        bCheminAnnul1.setForeground(new java.awt.Color(255, 255, 255));
        bCheminAnnul1.setText("Annulation du chemin");
        bCheminAnnul1.setBorderColor(new java.awt.Color(242, 113, 65));
        bCheminAnnul1.setColor(new java.awt.Color(242, 113, 65));
        bCheminAnnul1.setColorClick(new java.awt.Color(222, 93, 45));
        bCheminAnnul1.setColorOver(new java.awt.Color(242, 113, 65));
        bCheminAnnul1.setMaximumSize(new java.awt.Dimension(170, 30));
        bCheminAnnul1.setMinimumSize(new java.awt.Dimension(170, 30));
        bCheminAnnul1.setPreferredSize(new java.awt.Dimension(170, 40));
        bCheminAnnul1.setRadius(50);

        cpbIntacte.setBorder(null);
        cpbIntacte.setForeground(new java.awt.Color(255, 255, 255));
        cpbIntacte.setBorderPainted(false);
        cpbIntacte.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N

        cpbFeu.setBorder(null);
        cpbFeu.setForeground(new java.awt.Color(255, 255, 255));
        cpbFeu.setBorderPainted(false);
        cpbFeu.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N

        rbcChemin2.setBackground(new java.awt.Color(242, 113, 65));
        bgTailleChemin.add(rbcChemin2);
        rbcChemin2.setForeground(new java.awt.Color(255, 255, 255));
        rbcChemin2.setText("Epaisseur chemin : 2");

        rbcChemin3.setBackground(new java.awt.Color(242, 113, 65));
        bgTailleChemin.add(rbcChemin3);
        rbcChemin3.setForeground(new java.awt.Color(255, 255, 255));
        rbcChemin3.setText("Epaisseur chemin : 3");

        lEpaisseurChemin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lEpaisseurChemin.setForeground(new java.awt.Color(255, 255, 255));
        lEpaisseurChemin.setText("Entrer l'épaisseur du chemin souhaité");
        lEpaisseurChemin.setPreferredSize(new java.awt.Dimension(309, 16));

        rbcChemin1.setBackground(new java.awt.Color(242, 113, 65));
        bgTailleChemin.add(rbcChemin1);
        rbcChemin1.setForeground(new java.awt.Color(255, 255, 255));
        rbcChemin1.setText("Epaisseur chemin : 1");

        lIteration.setForeground(new java.awt.Color(255, 255, 255));
        lIteration.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lIteration.setText(" ");

        bPompier.setBackground(new java.awt.Color(191, 94, 94));
        bPompier.setForeground(new java.awt.Color(255, 255, 255));
        bPompier.setText("Au secour ça brule !!");
        bPompier.setBorderColor(new java.awt.Color(191, 94, 94));
        bPompier.setColor(new java.awt.Color(191, 94, 94));
        bPompier.setColorClick(new java.awt.Color(171, 74, 74));
        bPompier.setColorOver(new java.awt.Color(191, 94, 94));
        bPompier.setMaximumSize(new java.awt.Dimension(170, 30));
        bPompier.setMinimumSize(new java.awt.Dimension(170, 30));
        bPompier.setPreferredSize(new java.awt.Dimension(170, 40));
        bPompier.setRadius(50);
        bPompier.setRequestFocusEnabled(false);
        bPompier.setSelected(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bModifVent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CSVent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPompier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(rbcChemin2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rbcChemin3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rbcChemin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bCheminDebut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bCheminAnnul1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bCheminArret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(lEpaisseurChemin, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lSurcharge, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lDepartManuel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(77, 77, 77)
                            .addComponent(cpbIntacte, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(77, 77, 77)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(scVitesse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lVitesse2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(sbSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lVitesse1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cpbFeu, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lStatuts, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lEnflammee, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(98, 98, 98)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lVitesse, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addComponent(lIteration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(lIntacte, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bModifVent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCheminDebut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lEpaisseurChemin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbcChemin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbcChemin2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbcChemin3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lSurcharge)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCheminAnnul1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CSVent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCheminArret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPompier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(lSimulation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lVitesse1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lVitesse2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sbSimulation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lIteration)
                .addGap(12, 12, 12)
                .addComponent(lVitesse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lDepartManuel)
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scVitesse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lStatuts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cpbIntacte, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpbFeu, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEnflammee)
                            .addComponent(lIntacte)))))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addStartStop(MouseListener evt) {
        sbSimulation.addMouseListener(evt);
    }

    public void addDelay(MouseListener evt) {
        scVitesse.addMouseListener(evt);
    }
    
    public void addBoutonVent(ActionListener evt) {
        bModifVent.addActionListener(evt);
    }
    
    public void addSelectionOrientationVent(MouseListener evt) {
        CSVent1.addMouseListener(evt);
    }
    
    public void addSurchargeDebut(ActionListener evt) {
        bCheminDebut.addActionListener(evt);
    }
    
    public void addSurchargeArret(ActionListener evt) {
        bCheminArret.addActionListener(evt);
    }
    
    public void addSurchargeAnnul(ActionListener evt) {
        bCheminAnnul1.addActionListener(evt);
    }
    
    public void addEpaisseurChemin(ActionListener evt) {
        rbcChemin1.addActionListener(evt);
        rbcChemin2.addActionListener(evt);
        rbcChemin3.addActionListener(evt);
    }
    
    public void addPompier(ActionListener evt) {
        bPompier.addActionListener(evt);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pComponents.circularSlider.CustomSlider CSVent1;
    private pComponents.button.MyButton bCheminAnnul1;
    private pComponents.button.MyButton bCheminArret;
    private pComponents.button.MyButton bCheminDebut;
    private pComponents.button.MyButton bModifVent;
    private pComponents.button.MyButton bPompier;
    private javax.swing.ButtonGroup bgTailleChemin;
    private pComponents.Circular_progresse.CircleProgressBar cpbFeu;
    private pComponents.Circular_progresse.CircleProgressBar cpbIntacte;
    private javax.swing.JLabel lDepartManuel;
    private javax.swing.JLabel lEnflammee;
    private javax.swing.JLabel lEpaisseurChemin;
    private javax.swing.JLabel lIntacte;
    private javax.swing.JLabel lIteration;
    private javax.swing.JLabel lSimulation;
    private javax.swing.JLabel lStatuts;
    private javax.swing.JLabel lSurcharge;
    private javax.swing.JLabel lVitesse;
    private javax.swing.JLabel lVitesse1;
    private javax.swing.JLabel lVitesse2;
    private pComponents.radio_button.RadioButtonCustom rbcChemin1;
    private pComponents.radio_button.RadioButtonCustom rbcChemin2;
    private pComponents.radio_button.RadioButtonCustom rbcChemin3;
    private pComponents.switch_button.SwitchButton sbSimulation;
    private pComponents.slider_material.JsliderCustom_Vitesse scVitesse;
    // End of variables declaration//GEN-END:variables
}
