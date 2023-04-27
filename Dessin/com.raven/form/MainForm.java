package com.raven.form;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import pAccueil.pAccueil;

public class MainForm extends javax.swing.JPanel {

    public MainForm() {
        initComponents();
        show(new pAccueil());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header1 = new com.raven.component.Header();
        body = new javax.swing.JPanel();

        setBackground(new java.awt.Color(250, 250, 250));
        setOpaque(false);

        header1.setMinimumSize(new java.awt.Dimension(1300, 47));
        header1.setPreferredSize(new java.awt.Dimension(1300, 47));

        body.setMaximumSize(new java.awt.Dimension(1300, 700));
        body.setMinimumSize(new java.awt.Dimension(1300, 700));
        body.setOpaque(false);
        body.setPreferredSize(new java.awt.Dimension(1300, 700));
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addEventMenu(ActionListener event) {
        header1.addEventMenu(event);
    }
    
    public void addEventHome(ActionListener event) {
        header1.addEventHome(event);
    }

    public void addEventAbout(ActionListener event) {
        header1.addEventAbout(event);
    }
    
    public void initMoving(JFrame fram) {
        header1.initMoving(fram);
        
    }

    public void show(Component com) {
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private com.raven.component.Header header1;
    // End of variables declaration//GEN-END:variables
}
