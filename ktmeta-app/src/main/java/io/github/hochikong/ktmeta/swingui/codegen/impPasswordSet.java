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
package io.github.hochikong.ktmeta.swingui.codegen;

import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ckhoi
 */
public class impPasswordSet extends javax.swing.JFrame {

    /**
     * Creates new form LaunchInital
     */
    public impPasswordSet() {
        initComponents();
        HideHead();
        this.setLocationRelativeTo(null);
    }
    
    public impPasswordSet(Image icon){
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

        LabelSetTips = new javax.swing.JLabel();
        LabelPassword = new javax.swing.JLabel();
        PasswordFieldFirst = new javax.swing.JPasswordField();
        LabelEnterAgain = new javax.swing.JLabel();
        PasswordFieldSecond = new javax.swing.JPasswordField();
        BTNFinishSetting = new javax.swing.JButton();
        LabelHelper = new javax.swing.JLabel();
        BTNUpdatePasswd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/Frames/PasswordSet_trans"); // NOI18N
        setTitle(bundle.getString("PASSWORD SETTING")); // NOI18N
        setAlwaysOnTop(true);
        setUndecorated(true);

        LabelSetTips.setText(bundle.getString("SET / UPDATE YOUR MAIN PASSWORD : ")); // NOI18N

        LabelPassword.setText(bundle.getString("PASSWORD : ")); // NOI18N

        LabelEnterAgain.setText(bundle.getString("ENTER AGAIN: ")); // NOI18N

        BTNFinishSetting.setText(bundle.getString("FINISH")); // NOI18N
        BTNFinishSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNFinishSettingActionPerformed(evt);
            }
        });

        LabelHelper.setForeground(new java.awt.Color(74, 136, 199));
        LabelHelper.setText(bundle.getString("THE MAIN PASSWORD USED BY KTMETA TO PROTECT YOUR RESOURCES")); // NOI18N

        BTNUpdatePasswd.setText(bundle.getString("UPDATE")); // NOI18N
        BTNUpdatePasswd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNUpdatePasswdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTNUpdatePasswd)
                        .addGap(18, 18, 18)
                        .addComponent(BTNFinishSetting))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(LabelEnterAgain)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(PasswordFieldSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(LabelPassword)
                            .addGap(18, 18, 18)
                            .addComponent(PasswordFieldFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(LabelSetTips)
                        .addComponent(LabelHelper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(LabelSetTips)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPassword)
                    .addComponent(PasswordFieldFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEnterAgain)
                    .addComponent(PasswordFieldSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(LabelHelper)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNUpdatePasswd)
                    .addComponent(BTNFinishSetting))
                .addGap(71, 71, 71))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<Auto-Generate>
    private void BTNUpdatePasswdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNUpdatePasswdActionPerformed
        impBTNUpdatePasswdActionPerformed(evt);
    }//GEN-LAST:event_BTNUpdatePasswdActionPerformed

    private void BTNFinishSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNFinishSettingActionPerformed
        impBTNFinishSettingActionPerformed(evt);
    }//GEN-LAST:event_BTNFinishSettingActionPerformed
    //</Auto-Generate>
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatSolarizedLightIJTheme.install();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new impPasswordSet().setVisible(true);
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
    protected javax.swing.JButton BTNFinishSetting;
    protected javax.swing.JButton BTNUpdatePasswd;
    private javax.swing.JLabel LabelEnterAgain;
    private javax.swing.JLabel LabelHelper;
    private javax.swing.JLabel LabelPassword;
    private javax.swing.JLabel LabelSetTips;
    protected javax.swing.JPasswordField PasswordFieldFirst;
    protected javax.swing.JPasswordField PasswordFieldSecond;
    // End of variables declaration//GEN-END:variables

    //<Auto-Generate-Result>
    protected void impBTNUpdatePasswdActionPerformed(java.awt.event.ActionEvent evt){}
    protected void impBTNFinishSettingActionPerformed(java.awt.event.ActionEvent evt){}
    //</Auto-Generate-Result>
}
