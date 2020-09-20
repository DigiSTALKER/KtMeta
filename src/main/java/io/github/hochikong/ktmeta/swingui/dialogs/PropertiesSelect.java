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
import java.util.ArrayList;
import java.util.List;

/**
 * @author ckhoi
 */
public class PropertiesSelect extends javax.swing.JDialog {

    /**
     * Creates new form PropertiesSelect
     */
    public PropertiesSelect(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.KpropsOnLib.add("XItem 1");
        this.KpropsOnLib.add("XItem 2");
        this.KpropsOnLib.add("XItem 3");
        this.KpropsOnLib.add("XItem 4");
        initComponents();
        this.updateLists();
        this.setLocationRelativeTo(null);
        if (!this.KpropsOnLib.isEmpty()) {
            this.ListPropsOnLibrary.setSelectedIndex(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelPropsOnLibrary = new javax.swing.JLabel();
        LabelPropsYouNeed = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListPropsOnLibrary = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListPropsYouNeed = new javax.swing.JList<>();
        BTNAddProps = new javax.swing.JButton();
        BTNRemoveProps = new javax.swing.JButton();
        BTNDiscardProps = new javax.swing.JButton();
        BTNCommitProps = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Properties Selection");
        setAlwaysOnTop(true);

        LabelPropsOnLibrary.setText("Properties on the library : ");

        LabelPropsYouNeed.setText("Properties you need : ");

        ListPropsOnLibrary.setModel(new javax.swing.AbstractListModel<String>() {
            final String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane1.setViewportView(ListPropsOnLibrary);

        ListPropsYouNeed.setModel(new javax.swing.AbstractListModel<String>() {
            final String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane2.setViewportView(ListPropsYouNeed);

        BTNAddProps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico/angle-double-right.png"))); // NOI18N
        BTNAddProps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAddPropsActionPerformed(evt);
            }
        });

        BTNRemoveProps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico/angle-double-left.png"))); // NOI18N
        BTNRemoveProps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRemovePropsActionPerformed(evt);
            }
        });

        BTNDiscardProps.setText("Cancel");
        BTNDiscardProps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNDiscardPropsActionPerformed(evt);
            }
        });

        BTNCommitProps.setText("Confirm");
        BTNCommitProps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCommitPropsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(BTNCommitProps)
                                                .addGap(20, 20, 20)
                                                .addComponent(BTNDiscardProps))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(LabelPropsOnLibrary, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(BTNRemoveProps, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(BTNAddProps, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(LabelPropsYouNeed)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(LabelPropsOnLibrary)
                                        .addComponent(LabelPropsYouNeed))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane2)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(63, 63, 63)
                                                .addComponent(BTNAddProps)
                                                .addGap(30, 30, 30)
                                                .addComponent(BTNRemoveProps)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(BTNCommitProps, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(BTNDiscardProps))
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNAddPropsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddPropsActionPerformed
        KtBTNAddPropsActionPerformed();
    }//GEN-LAST:event_BTNAddPropsActionPerformed

    private void BTNRemovePropsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRemovePropsActionPerformed
        KtBTNRemovePropsActionPerformed();
    }//GEN-LAST:event_BTNRemovePropsActionPerformed

    private void BTNCommitPropsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCommitPropsActionPerformed
        KtBTNCommitPropsActionPerformed();
    }//GEN-LAST:event_BTNCommitPropsActionPerformed

    private void BTNDiscardPropsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNDiscardPropsActionPerformed
        KtBTNDiscardPropsActionPerformed();
    }//GEN-LAST:event_BTNDiscardPropsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatSolarizedLightIJTheme.install();
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PropertiesSelect dialog = new PropertiesSelect(new javax.swing.JFrame(), true);
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

    // ***modified by me
    private DefaultListModel<String> getModel(List<String> input) {
        String[] inputArray = input.toArray(new String[input.size()]);
        return new DefaultListModel<String>() {
            final String[] strings = inputArray;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        };
    }

    public void updateLists() {
        DefaultListModel<String> left = this.getModel(KpropsOnLib);
        DefaultListModel<String> right = this.getModel(KpropsYouNeed);
        this.ListPropsOnLibrary.setModel(left);
        this.ListPropsYouNeed.setModel(right);
    }

    // Kotlin should override
    protected List<String> KpropsOnLib = new ArrayList<>();
    protected List<String> KpropsYouNeed = new ArrayList<>();

    protected void KtBTNAddPropsActionPerformed() {
        String selected = this.ListPropsOnLibrary.getSelectedValue();
        if (KpropsOnLib.contains(selected)) {
            KpropsYouNeed.add(selected);
            KpropsOnLib.remove(selected);
        }
        EventQueue.invokeLater(() -> {
            updateLists();
            if (!this.KpropsOnLib.isEmpty()) {
                this.ListPropsOnLibrary.setSelectedIndex(0);
            }
        });
    }

    protected void KtBTNRemovePropsActionPerformed() {
        String selected = this.ListPropsYouNeed.getSelectedValue();
        if (KpropsYouNeed.contains(selected)) {
            KpropsOnLib.add(selected);
            KpropsYouNeed.remove(selected);
        }
        EventQueue.invokeLater(() -> {
            updateLists();
            if (!this.KpropsYouNeed.isEmpty()) {
                this.ListPropsYouNeed.setSelectedIndex(0);
            }
        });
    }

    protected void KtBTNCommitPropsActionPerformed() {

    }

    protected void KtBTNDiscardPropsActionPerformed() {

    }
    // end

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton BTNAddProps;
    protected javax.swing.JButton BTNCommitProps;
    protected javax.swing.JButton BTNDiscardProps;
    protected javax.swing.JButton BTNRemoveProps;
    protected javax.swing.JLabel LabelPropsOnLibrary;
    protected javax.swing.JLabel LabelPropsYouNeed;
    private javax.swing.JList<String> ListPropsOnLibrary;
    private javax.swing.JList<String> ListPropsYouNeed;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}