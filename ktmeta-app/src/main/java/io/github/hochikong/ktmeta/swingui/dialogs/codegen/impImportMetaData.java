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
public class impImportMetaData extends javax.swing.JDialog {

    /**
     * Creates new form ImportMetaData
     */
    public impImportMetaData(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        HideHead();
        this.setLocationRelativeTo(null);
    }
    
    public impImportMetaData(java.awt.Frame parent, boolean modal, Image icon){
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

        LabelTarget = new javax.swing.JLabel();
        TabbedPaneTools = new javax.swing.JTabbedPane();
        PanelOfficialTools = new javax.swing.JPanel();
        BTNImportFromDisk = new javax.swing.JButton();
        LabelImportFromDisk = new javax.swing.JLabel();
        ComboBoxTargetMetaLib = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/Dialogs/ImportMetaData_trans"); // NOI18N
        setTitle(bundle.getString("IMPORT METADATA")); // NOI18N
        setUndecorated(true);

        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("i18n/Frames/MetaAdvanceSearch_trans"); // NOI18N
        LabelTarget.setText(bundle1.getString("TARGET METADATA LIBRARY : ")); // NOI18N

        TabbedPaneTools.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Import Metadata Wizard", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft YaHei UI", 0, 14), new java.awt.Color(74, 136, 199))); // NOI18N

        BTNImportFromDisk.setText(bundle.getString("IMPORT FROM LOCAL DISK")); // NOI18N
        BTNImportFromDisk.setFocusable(false);
        BTNImportFromDisk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNImportFromDiskActionPerformed(evt);
            }
        });

        LabelImportFromDisk.setText(bundle.getString("IMPORT METADATA FROM LOCAL DISK FILES : ")); // NOI18N

        javax.swing.GroupLayout PanelOfficialToolsLayout = new javax.swing.GroupLayout(PanelOfficialTools);
        PanelOfficialTools.setLayout(PanelOfficialToolsLayout);
        PanelOfficialToolsLayout.setHorizontalGroup(
            PanelOfficialToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOfficialToolsLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(LabelImportFromDisk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTNImportFromDisk)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        PanelOfficialToolsLayout.setVerticalGroup(
            PanelOfficialToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOfficialToolsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(PanelOfficialToolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNImportFromDisk)
                    .addComponent(LabelImportFromDisk))
                .addContainerGap(229, Short.MAX_VALUE))
        );

        TabbedPaneTools.addTab("Official Tools", PanelOfficialTools);

        ComboBoxTargetMetaLib.setModel(new javax.swing.DefaultComboBoxModel<>(this.metaLibs));
        ComboBoxTargetMetaLib.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxTargetMetaLibItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TabbedPaneTools, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelTarget)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxTargetMetaLib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelTarget)
                    .addComponent(ComboBoxTargetMetaLib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(TabbedPaneTools, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<Auto-Generate>
    private void ComboBoxTargetMetaLibItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxTargetMetaLibItemStateChanged
        impComboBoxTargetMetaLibItemStateChanged(evt);
    }//GEN-LAST:event_ComboBoxTargetMetaLibItemStateChanged

    private void BTNImportFromDiskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNImportFromDiskActionPerformed
        impBTNImportFromDiskActionPerformed(evt);
    }//GEN-LAST:event_BTNImportFromDiskActionPerformed
    //</Auto-Generate>
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatSolarizedLightIJTheme.install();

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                impImportMetaData dialog = new impImportMetaData(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    //<My-Custom>   
    protected String[] metaLibs = new String[] {"Lib 1", "Lib 2"};
    
    protected void HideHead(){
        // Like IDEA style
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
    }
    //</My-Custom>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNImportFromDisk;
    protected javax.swing.JComboBox<String> ComboBoxTargetMetaLib;
    private javax.swing.JLabel LabelImportFromDisk;
    private javax.swing.JLabel LabelTarget;
    private javax.swing.JPanel PanelOfficialTools;
    protected javax.swing.JTabbedPane TabbedPaneTools;
    // End of variables declaration//GEN-END:variables

    //<Auto-Generate-Result>
    protected void impComboBoxTargetMetaLibItemStateChanged(java.awt.event.ItemEvent evt){}
    protected void impBTNImportFromDiskActionPerformed(java.awt.event.ActionEvent evt){}
    //</Auto-Generate-Result>
}
