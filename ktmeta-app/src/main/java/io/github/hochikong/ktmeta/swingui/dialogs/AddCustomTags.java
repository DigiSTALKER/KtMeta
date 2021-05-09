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
import io.github.hochikong.ktmeta.swingui.essentials.CustomTagsTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author ckhoi
 */
public class AddCustomTags extends javax.swing.JDialog {

    //<My-Custom>
    protected List<String> tagsData;
    protected CustomTagsTableModel myModel = new CustomTagsTableModel();
    protected javax.swing.JButton BTNCancelAddTags;
    protected javax.swing.JButton BTNClear;
    protected javax.swing.JButton BTNOKAddTags;
    protected javax.swing.JButton BTNRemoveTag;
    protected javax.swing.JTable TableUserCustomTags;
    protected javax.swing.JTextField TextFieldAddTag;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNAddTag;
    private javax.swing.JLabel LabelAddTag;
    private javax.swing.JLabel LabelCurrentTags;
    //</Auto-Generate>
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;

    /**
     * Creates new form AddIndexWizard
     */
    public AddCustomTags(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        HideHead();
        this.setLocationRelativeTo(null);
    }

    public AddCustomTags(java.awt.Frame parent, boolean modal, List<String> TagsData) {
        super(parent, modal);
        this.tagsData = TagsData;
        initComponents();
        HideHead();
        this.setLocationRelativeTo(null);
    }
    //</My-Custom>

    public AddCustomTags(java.awt.Frame parent, boolean modal, List<String> TagsData, Image icon) {
        super(parent, modal);
        this.tagsData = TagsData;
        initComponents();
        HideHead();
        this.setIconImage(icon);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatSolarizedLightIJTheme.install();

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddCustomTags dialog = new AddCustomTags(new javax.swing.JFrame(), true);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BTNCancelAddTags = new javax.swing.JButton();
        BTNOKAddTags = new javax.swing.JButton();
        LabelCurrentTags = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableUserCustomTags = new javax.swing.JTable();
        LabelAddTag = new javax.swing.JLabel();
        BTNAddTag = new javax.swing.JButton();
        BTNRemoveTag = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        BTNClear = new javax.swing.JButton();
        TextFieldAddTag = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/Dialogs/AddCustomTags_trans"); // NOI18N
        setTitle(bundle.getString("ADD CUSTOM TAGS")); // NOI18N
        setUndecorated(true);

        BTNCancelAddTags.setText(bundle.getString("CANCEL")); // NOI18N
        BTNCancelAddTags.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCancelAddTagsActionPerformed(evt);
            }
        });

        BTNOKAddTags.setText(bundle.getString("OK")); // NOI18N
        BTNOKAddTags.setFocusable(false);
        BTNOKAddTags.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNOKAddTagsActionPerformed(evt);
            }
        });

        LabelCurrentTags.setText(bundle.getString("CURRENT USER CUSTOM TAGS : ")); // NOI18N

        TableUserCustomTags.setAutoCreateRowSorter(true);
        if (this.tagsData != null) {
            this.myModel = new CustomTagsTableModel(this.tagsData);
        }
        TableUserCustomTags.setModel(this.myModel);
        TableUserCustomTags.setCellSelectionEnabled(true);
        TableUserCustomTags.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TableUserCustomTags.setShowHorizontalLines(true);
        TableUserCustomTags.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableUserCustomTagsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableUserCustomTags);

        LabelAddTag.setText(bundle.getString("ADD TAG : ")); // NOI18N

        BTNAddTag.setText(bundle.getString("ADD")); // NOI18N
        BTNAddTag.setToolTipText(bundle.getString("ADD NEW TAG")); // NOI18N
        BTNAddTag.setFocusable(false);
        BTNAddTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAddTagActionPerformed(evt);
            }
        });

        BTNRemoveTag.setText("Remove Selected Tag");
        BTNRemoveTag.setFocusable(false);
        BTNRemoveTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRemoveTagActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        BTNClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico/16pix/close.png"))); // NOI18N
        BTNClear.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BTNClear.setFocusable(false);
        BTNClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNClearActionPerformed(evt);
            }
        });

        TextFieldAddTag.setToolTipText("Hit 'Enter' to quick add");
        TextFieldAddTag.setBorder(null);
        TextFieldAddTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldAddTagActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(TextFieldAddTag, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(BTNClear)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(BTNClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TextFieldAddTag, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(33, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(BTNRemoveTag)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(BTNOKAddTags)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(BTNCancelAddTags))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(LabelAddTag)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(BTNAddTag))
                                        .addComponent(LabelCurrentTags, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(LabelCurrentTags)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(LabelAddTag)
                                                .addComponent(BTNAddTag)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(BTNOKAddTags)
                                        .addComponent(BTNCancelAddTags)
                                        .addComponent(BTNRemoveTag))
                                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<Auto-Generate>
    private void TableUserCustomTagsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableUserCustomTagsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TableUserCustomTagsMouseClicked

    private void BTNAddTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddTagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNAddTagActionPerformed

    private void BTNOKAddTagsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNOKAddTagsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNOKAddTagsActionPerformed

    private void BTNCancelAddTagsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCancelAddTagsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNCancelAddTagsActionPerformed

    private void BTNRemoveTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRemoveTagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNRemoveTagActionPerformed

    private void BTNClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNClearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNClearActionPerformed

    private void TextFieldAddTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldAddTagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldAddTagActionPerformed

    protected void HideHead() {
        // Like IDEA style
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
    }
    // End of variables declaration//GEN-END:variables
}
