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
package io.github.hochikong.ktmeta.swingui.dialogs;

import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ckhoi
 */
public class AddMetaLibWizard extends javax.swing.JDialog {

    /**
     * Creates new form AddIndexWizard
     */
    public AddMetaLibWizard(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        HideHead();
        this.setLocationRelativeTo(null);
    }

    public AddMetaLibWizard(java.awt.Frame parent, boolean modal,
            Image icon,
            String[] SupportedPluginsList,
            String[] AvailableDBsList,
            String[] AvailableIndicesList
    ) {
        super(parent, modal);
        this.supportedPlugins = SupportedPluginsList;
        this.availableDatabases = AvailableDBsList;
        this.availableIndices = AvailableIndicesList;
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

        BTNCancelAddMetaLib = new javax.swing.JButton();
        BTNOKAddMetaLib = new javax.swing.JButton();
        LabelAlias = new javax.swing.JLabel();
        FieldAlias = new javax.swing.JTextField();
        LabelDescription = new javax.swing.JLabel();
        FieldDescription = new javax.swing.JTextField();
        LabelAssignPlugin = new javax.swing.JLabel();
        ComboBoxAvailablePlugins = new javax.swing.JComboBox<>();
        LabelAssignDB = new javax.swing.JLabel();
        ComboBoxAvailableDBs = new javax.swing.JComboBox<>();
        LabelAssignIndex = new javax.swing.JLabel();
        ComboBoxAvailableIndices = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/Dialogs/AddMetaLibWizard_trans"); // NOI18N
        setTitle(bundle.getString("ADD METADATA LIBRARY")); // NOI18N
        setUndecorated(true);

        BTNCancelAddMetaLib.setText(bundle.getString("CANCEL")); // NOI18N
        BTNCancelAddMetaLib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCancelAddMetaLibActionPerformed(evt);
            }
        });

        BTNOKAddMetaLib.setText(bundle.getString("OK")); // NOI18N
        BTNOKAddMetaLib.setFocusable(false);
        BTNOKAddMetaLib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNOKAddMetaLibActionPerformed(evt);
            }
        });

        LabelAlias.setText(bundle.getString("NAME : ")); // NOI18N

        FieldAlias.setForeground(new java.awt.Color(204, 204, 204));
        FieldAlias.setText(bundle.getString("ALIAS OF METADATA LIBRARY")); // NOI18N
        FieldAlias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldAliasMouseClicked(evt);
            }
        });

        LabelDescription.setText(bundle.getString("DESCRIPTION : ")); // NOI18N

        FieldDescription.setForeground(new java.awt.Color(204, 204, 204));
        FieldDescription.setText(bundle.getString("DESCRIPTION OF METADATA LIBRARY")); // NOI18N
        FieldDescription.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FieldDescriptionMouseClicked(evt);
            }
        });

        LabelAssignPlugin.setText(bundle.getString("ASSIGN PLUGIN : ")); // NOI18N

        ComboBoxAvailablePlugins.setModel(new javax.swing.DefaultComboBoxModel<>(supportedPlugins));
        ComboBoxAvailablePlugins.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxAvailablePluginsItemStateChanged(evt);
            }
        });

        LabelAssignDB.setText(bundle.getString("ASSIGN DB : ")); // NOI18N

        ComboBoxAvailableDBs.setModel(new javax.swing.DefaultComboBoxModel<>(availableDatabases));
        ComboBoxAvailableDBs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxAvailableDBsItemStateChanged(evt);
            }
        });

        LabelAssignIndex.setText(bundle.getString("ASSIGN INDEX : ")); // NOI18N

        ComboBoxAvailableIndices.setModel(new javax.swing.DefaultComboBoxModel<>(availableIndices));
        ComboBoxAvailableIndices.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxAvailableIndicesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTNOKAddMetaLib)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTNCancelAddMetaLib))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelDescription)
                            .addComponent(LabelAlias)
                            .addComponent(LabelAssignPlugin)
                            .addComponent(LabelAssignDB)
                            .addComponent(LabelAssignIndex))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(FieldAlias)
                            .addComponent(FieldDescription)
                            .addComponent(ComboBoxAvailablePlugins, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ComboBoxAvailableDBs, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ComboBoxAvailableIndices, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelAlias)
                    .addComponent(FieldAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelDescription)
                    .addComponent(FieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelAssignPlugin)
                    .addComponent(ComboBoxAvailablePlugins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxAvailableDBs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelAssignDB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxAvailableIndices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelAssignIndex))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNOKAddMetaLib)
                    .addComponent(BTNCancelAddMetaLib))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<Auto-Generate>
    private void BTNCancelAddMetaLibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCancelAddMetaLibActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNCancelAddMetaLibActionPerformed

    private void BTNOKAddMetaLibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNOKAddMetaLibActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNOKAddMetaLibActionPerformed

    private void FieldAliasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldAliasMouseClicked
        // TODO add your handling code here:
//        this.FieldAlias.setForeground(new java.awt.Color(0, 0, 0));
//        this.FieldAlias.selectAll();
    }//GEN-LAST:event_FieldAliasMouseClicked

    private void FieldDescriptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FieldDescriptionMouseClicked
        // TODO add your handling code here:
//        this.FieldDescription.setForeground(new java.awt.Color(0, 0, 0));
//        this.FieldDescription.selectAll
    }//GEN-LAST:event_FieldDescriptionMouseClicked

    private void ComboBoxAvailablePluginsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxAvailablePluginsItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxAvailablePluginsItemStateChanged

    private void ComboBoxAvailableDBsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxAvailableDBsItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxAvailableDBsItemStateChanged

    private void ComboBoxAvailableIndicesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxAvailableIndicesItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxAvailableIndicesItemStateChanged
    //</Auto-Generate>

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatSolarizedLightIJTheme.install();

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddMetaLibWizard dialog = new AddMetaLibWizard(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });

                dialog.BTNCancelAddMetaLib.requestFocusInWindow();
                dialog.setVisible(true);
            }
        });
    }

    //<My-Custom>
    protected String[] supportedPlugins = {"Plugin 1", "Plugin 2"};
    protected String[] availableDatabases = {"DB1", "DB2"};
    protected String[] availableIndices = {"Index 1", "Index 2"};
    
    protected void HideHead(){
        // Like IDEA style
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
    }
    //</My-Custom>


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton BTNCancelAddMetaLib;
    protected javax.swing.JButton BTNOKAddMetaLib;
    protected javax.swing.JComboBox<String> ComboBoxAvailableDBs;
    protected javax.swing.JComboBox<String> ComboBoxAvailableIndices;
    protected javax.swing.JComboBox<String> ComboBoxAvailablePlugins;
    protected javax.swing.JTextField FieldAlias;
    protected javax.swing.JTextField FieldDescription;
    protected javax.swing.JLabel LabelAlias;
    private javax.swing.JLabel LabelAssignDB;
    private javax.swing.JLabel LabelAssignIndex;
    protected javax.swing.JLabel LabelAssignPlugin;
    protected javax.swing.JLabel LabelDescription;
    // End of variables declaration//GEN-END:variables
}
