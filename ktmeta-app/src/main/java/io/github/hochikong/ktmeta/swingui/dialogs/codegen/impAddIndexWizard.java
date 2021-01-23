/*
 * Copyright 2020 Hochikong
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.hochikong.ktmeta.swingui.dialogs.codegen;

import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ckhoi
 */
public class impAddIndexWizard extends javax.swing.JDialog {

    /**
     * Creates new form AddIndexWizard
     */
    public impAddIndexWizard(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        HideHead();
        this.setLocationRelativeTo(null);
    }
    
    public impAddIndexWizard(java.awt.Frame parent, boolean modal, Image icon){
        super(parent, modal);
        initComponents();
        HideHead();
        this.setIconImage(icon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelIndexName = new javax.swing.JLabel();
        FieldIndexName = new javax.swing.JTextField();
        LabelIndexDesc = new javax.swing.JLabel();
        FieldIndexDesc = new javax.swing.JTextField();
        LabelIndexURL = new javax.swing.JLabel();
        FieldIndexURL = new javax.swing.JTextField();
        BTNIndexTestConn = new javax.swing.JButton();
        BTNCancelAddIndex = new javax.swing.JButton();
        BTNOKAddIndex = new javax.swing.JButton();
        ProgressBarTestConn = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/Dialogs/AddIndexWizard_trans"); // NOI18N
        setTitle(bundle.getString("ADD ELASTICSEARCH INDEX")); // NOI18N
        setUndecorated(true);

        LabelIndexName.setText(bundle.getString("INDEX NAME : ")); // NOI18N

        FieldIndexName.setForeground(new java.awt.Color(204, 204, 204));
        FieldIndexName.setText(bundle.getString("ALIAS OF ELASTICSEARCH INDEX")); // NOI18N
        FieldIndexName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldIndexNameMouseClicked(evt);
            }
        });

        LabelIndexDesc.setText(bundle.getString("DESCRIPTION : ")); // NOI18N

        FieldIndexDesc.setForeground(new java.awt.Color(204, 204, 204));
        FieldIndexDesc.setText(bundle.getString("DESCRIPTION OF ELASTICSEARCH INDEX")); // NOI18N
        FieldIndexDesc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldIndexDescMouseClicked(evt);
            }
        });

        LabelIndexURL.setText(bundle.getString("INDEX URL :")); // NOI18N

        FieldIndexURL.setForeground(new java.awt.Color(204, 204, 204));
        FieldIndexURL.setText("http://xxxxx/xxx:9200");
        FieldIndexURL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldIndexURLMouseClicked(evt);
            }
        });

        BTNIndexTestConn.setText(bundle.getString("TEST CONNECTION")); // NOI18N
        BTNIndexTestConn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNIndexTestConnActionPerformed(evt);
            }
        });

        BTNCancelAddIndex.setText(bundle.getString("CANCEL")); // NOI18N
        BTNCancelAddIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCancelAddIndexActionPerformed(evt);
            }
        });

        BTNOKAddIndex.setText(bundle.getString("OK")); // NOI18N
        BTNOKAddIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNOKAddIndexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelIndexURL, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelIndexName)
                            .addComponent(LabelIndexDesc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(FieldIndexURL)
                            .addComponent(FieldIndexDesc, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                            .addComponent(FieldIndexName)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTNIndexTestConn)
                        .addGap(26, 26, 26)
                        .addComponent(ProgressBarTestConn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTNOKAddIndex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTNCancelAddIndex)
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelIndexName)
                    .addComponent(FieldIndexName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FieldIndexDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelIndexDesc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FieldIndexURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelIndexURL))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(ProgressBarTestConn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(BTNIndexTestConn)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNOKAddIndex)
                    .addComponent(BTNCancelAddIndex))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //</Auto-Generate>
    private void BTNCancelAddIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCancelAddIndexActionPerformed
        impBTNCancelAddIndexActionPerformed(evt);
    }//GEN-LAST:event_BTNCancelAddIndexActionPerformed

    private void BTNIndexTestConnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNIndexTestConnActionPerformed
        impBTNIndexTestConnActionPerformed(evt);
    }//GEN-LAST:event_BTNIndexTestConnActionPerformed

    private void BTNOKAddIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNOKAddIndexActionPerformed
        impBTNOKAddIndexActionPerformed(evt);
    }//GEN-LAST:event_BTNOKAddIndexActionPerformed

    private void FieldIndexNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldIndexNameMouseClicked
        impFieldIndexNameMouseClicked(evt);
//        this.FieldIndexName.setForeground(new java.awt.Color(0, 0, 0));
//        this.FieldIndexName.selectAll();
    }//GEN-LAST:event_FieldIndexNameMouseClicked

    private void FieldIndexDescMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldIndexDescMouseClicked
        impFieldIndexDescMouseClicked(evt);
//        this.FieldIndexDesc.setForeground(new java.awt.Color(0, 0, 0));
//        this.FieldIndexDesc.selectAll();
    }//GEN-LAST:event_FieldIndexDescMouseClicked

    private void FieldIndexURLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldIndexURLMouseClicked
        impFieldIndexURLMouseClicked(evt);
//        this.FieldIndexURL.setForeground(new java.awt.Color(0, 0, 0));
//        this.FieldIndexURL.selectAll();
    }//GEN-LAST:event_FieldIndexURLMouseClicked
    //</Auto-Generate>

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatSolarizedLightIJTheme.install();

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                impAddIndexWizard dialog = new impAddIndexWizard(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });

                dialog.BTNCancelAddIndex.requestFocusInWindow();
                dialog.setVisible(true);
            }
        });
    }
    
    //<My-Custom>
    protected void HideHead(){
        // Like IDEA style
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
    }
    //</My-Custom>


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton BTNCancelAddIndex;
    protected javax.swing.JButton BTNIndexTestConn;
    protected javax.swing.JButton BTNOKAddIndex;
    protected javax.swing.JTextField FieldIndexDesc;
    protected javax.swing.JTextField FieldIndexName;
    protected javax.swing.JTextField FieldIndexURL;
    protected javax.swing.JLabel LabelIndexDesc;
    protected javax.swing.JLabel LabelIndexName;
    protected javax.swing.JLabel LabelIndexURL;
    protected javax.swing.JProgressBar ProgressBarTestConn;
    // End of variables declaration//GEN-END:variables

    //<Auto-Generate-Result>
    protected void impBTNCancelAddIndexActionPerformed(java.awt.event.ActionEvent evt){}
    protected void impBTNIndexTestConnActionPerformed(java.awt.event.ActionEvent evt){}
    protected void impBTNOKAddIndexActionPerformed(java.awt.event.ActionEvent evt){}
    protected void impFieldIndexNameMouseClicked(java.awt.event.MouseEvent evt){}
    protected void impFieldIndexDescMouseClicked(java.awt.event.MouseEvent evt){}
    protected void impFieldIndexURLMouseClicked(java.awt.event.MouseEvent evt){}
    //</Auto-Generate-Result>
}
