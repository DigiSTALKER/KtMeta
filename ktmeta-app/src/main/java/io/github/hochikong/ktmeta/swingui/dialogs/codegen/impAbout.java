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

import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;

/**
 *
 * @author ckhoi
 */
public class impAbout extends javax.swing.JDialog {

    /**
     * Creates new form About
     */
    public impAbout(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        // display About at the centre of screen
        this.setLocationRelativeTo(null);
    }

    public impAbout(java.awt.Frame parent, boolean modal, Image icon) {
        super(parent, modal);
        initComponents();
        this.setIconImage(icon);
    }

    protected String BottomText = "OS: " + system.getName()
            + " ; Arch: " + system.getArch()
            + " ; Cores: " + system.getAvailableProcessors()
            + "\n"
            + runtime.getVmName()
            + " ; " + runtime.getVmVendor()
            + " ; \n" + runtime.getSpecVersion() + runtime.getVmVersion();

    //<Auto-Generate>
    private void LabelPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelPhotoMouseClicked
        impLabelPhotoMouseClicked(evt);
    }//GEN-LAST:event_LabelPhotoMouseClicked

    private void TextPaneInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextPaneInfoMouseClicked
        impTextPaneInfoMouseClicked(evt);
    }//GEN-LAST:event_TextPaneInfoMouseClicked
    //</Auto-Generate>

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatSolarizedLightIJTheme.install();

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                impAbout dialog = new impAbout(new javax.swing.JFrame(), true);
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
    OperatingSystemMXBean system = ManagementFactory.getOperatingSystemMXBean();
    RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelPhoto = new javax.swing.JLabel();
        LabelInfo = new javax.swing.JPanel();
        InfoScrollPane = new javax.swing.JScrollPane();
        TextPaneInfo = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About KtMeta");
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);

        LabelPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/AboutHead.png"))); // NOI18N
        LabelPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelPhotoMouseClicked(evt);
            }
        });

        TextPaneInfo.setEditable(false);
        TextPaneInfo.setBorder(null);
        TextPaneInfo.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 16)); // NOI18N
        TextPaneInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextPaneInfoMouseClicked(evt);
            }
        });
        InfoScrollPane.setViewportView(TextPaneInfo);
        TextPaneInfo.setText(BottomText);

        javax.swing.GroupLayout LabelInfoLayout = new javax.swing.GroupLayout(LabelInfo);
        LabelInfo.setLayout(LabelInfoLayout);
        LabelInfoLayout.setHorizontalGroup(
            LabelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InfoScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        LabelInfoLayout.setVerticalGroup(
            LabelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabelInfoLayout.createSequentialGroup()
                .addComponent(InfoScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(LabelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(LabelPhoto)
                                .addGap(0, 0, 0)
                                .addComponent(LabelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected void updateButtomText(String text) {
        this.BottomText = text;
        this.TextPaneInfo.setText(this.BottomText);
    }
    //</My-Custom>


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JScrollPane InfoScrollPane;
    private javax.swing.JPanel LabelInfo;
    private javax.swing.JLabel LabelPhoto;
    protected javax.swing.JTextPane TextPaneInfo;
    // End of variables declaration//GEN-END:variables

    //<Auto-Generate-Result>
    protected void impLabelPhotoMouseClicked(java.awt.event.MouseEvent evt){}
    protected void impTextPaneInfoMouseClicked(java.awt.event.MouseEvent evt){}
    //</Auto-Generate-Result>
}
