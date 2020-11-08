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
import io.github.hochikong.ktmeta.swingui.essentials.PluginsTableModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;

/**
 *
 * @author ckhoi
 */
public class impSettings extends javax.swing.JDialog {

    /**
     * Creates new form Preference
     */
    public impSettings(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public impSettings(java.awt.Frame parent, boolean modal,
            String[] Languages,
            String[] Themes,
            Object[][] PluginsData,
            String[] PluginsDesc,
            Image icon
    ) {
        super(parent, modal);
        this.supportedLanguages = Languages;
        this.supportedThemes = Themes;
        this.pluginsData = PluginsData;
        this.pluginsDescs = PluginsDesc;
        initComponents();
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

        PanelOperations = new javax.swing.JPanel();
        BTNApply = new javax.swing.JButton();
        BTNCancel = new javax.swing.JButton();
        BTNOK = new javax.swing.JButton();
        TabsConfig = new javax.swing.JTabbedPane();
        PanelGeneral = new javax.swing.JPanel();
        LabelLanguage = new javax.swing.JLabel();
        ComboBoxLanguage = new javax.swing.JComboBox<>();
        PanelAppearance = new javax.swing.JPanel();
        LabelThemes = new javax.swing.JLabel();
        ComboBoxThemeSelector = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        CheckBoxEnableCustomFont = new javax.swing.JCheckBox();
        ComboBoxFontSelector = new javax.swing.JComboBox<>();
        LabelStyles = new javax.swing.JLabel();
        ComboBoxFontStyleSelector = new javax.swing.JComboBox<>();
        LabelFontSize = new javax.swing.JLabel();
        SpinnerFontSize = new javax.swing.JSpinner(new SpinnerNumberModel(14, 6, 26, 1));
        LabelPreviewCustomFont = new javax.swing.JLabel();
        PanelPlugins = new javax.swing.JPanel();
        PanelPluginsSearch = new javax.swing.JPanel();
        LabelSearch = new javax.swing.JLabel();
        TextFieldSearch = new javax.swing.JTextField();
        SplitPanePlugins = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablePlugins = new javax.swing.JTable();
        LabelPluginDescription = new javax.swing.JLabel();
        PanelPluginRegist = new javax.swing.JPanel();
        BTNRegistPlugin = new javax.swing.JButton();
        BTNUpgradePlugin = new javax.swing.JButton();
        BTNRemovePlugin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/Dialogs/Settings_trans"); // NOI18N
        setTitle(bundle.getString("SETTINGS")); // NOI18N

        BTNApply.setText(bundle.getString("APPLY")); // NOI18N
        BTNApply.setFocusable(false);
        BTNApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNApplyActionPerformed(evt);
            }
        });

        BTNCancel.setText(bundle.getString("CANCEL")); // NOI18N
        BTNCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCancelActionPerformed(evt);
            }
        });

        BTNOK.setText(bundle.getString("OK")); // NOI18N
        BTNOK.setFocusable(false);
        BTNOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelOperationsLayout = new javax.swing.GroupLayout(PanelOperations);
        PanelOperations.setLayout(PanelOperationsLayout);
        PanelOperationsLayout.setHorizontalGroup(
            PanelOperationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOperationsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTNOK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNApply)
                .addContainerGap())
        );
        PanelOperationsLayout.setVerticalGroup(
            PanelOperationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOperationsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelOperationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNApply)
                    .addComponent(BTNCancel)
                    .addComponent(BTNOK))
                .addContainerGap())
        );

        TabsConfig.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        LabelLanguage.setText(bundle.getString("LANGUAGE : ")); // NOI18N

        ComboBoxLanguage.setModel(new javax.swing.DefaultComboBoxModel<>(supportedLanguages));
        ComboBoxLanguage.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxLanguageItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout PanelGeneralLayout = new javax.swing.GroupLayout(PanelGeneral);
        PanelGeneral.setLayout(PanelGeneralLayout);
        PanelGeneralLayout.setHorizontalGroup(
            PanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelLanguage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(439, Short.MAX_VALUE))
        );
        PanelGeneralLayout.setVerticalGroup(
            PanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGeneralLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(PanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelLanguage)
                    .addComponent(ComboBoxLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(404, Short.MAX_VALUE))
        );

        TabsConfig.addTab("General", PanelGeneral);

        LabelThemes.setText(bundle.getString("THEME : ")); // NOI18N

        ComboBoxThemeSelector.setModel(new javax.swing.DefaultComboBoxModel<>(supportedThemes));
        ComboBoxThemeSelector.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxThemeSelectorItemStateChanged(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(61, 126, 192));

        CheckBoxEnableCustomFont.setText(bundle.getString("USE CUSTOM FONT")); // NOI18N
        CheckBoxEnableCustomFont.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CheckBoxEnableCustomFontItemStateChanged(evt);
            }
        });

        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ComboBoxFontSelector.setModel(new javax.swing.DefaultComboBoxModel<>(gEnv.getAvailableFontFamilyNames()));
        ComboBoxFontSelector.setEnabled(false);
        ComboBoxFontSelector.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxFontSelectorItemStateChanged(evt);
            }
        });

        LabelStyles.setText(bundle.getString("STYLE : ")); // NOI18N

        ComboBoxFontStyleSelector.setModel(new javax.swing.DefaultComboBoxModel<>(SupportedFontStyle));
        ComboBoxFontStyleSelector.setEnabled(false);
        ComboBoxFontStyleSelector.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxFontStyleSelectorItemStateChanged(evt);
            }
        });

        LabelFontSize.setText(bundle.getString("SIZE : ")); // NOI18N

        SpinnerFontSize.setEnabled(false);
        SpinnerFontSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerFontSizeStateChanged(evt);
            }
        });

        LabelPreviewCustomFont.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelPreviewCustomFont.setText("This is a preview  这是预览 この文字はプレビューです");
        LabelPreviewCustomFont.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(61, 126, 192), 2, true));

        javax.swing.GroupLayout PanelAppearanceLayout = new javax.swing.GroupLayout(PanelAppearance);
        PanelAppearance.setLayout(PanelAppearanceLayout);
        PanelAppearanceLayout.setHorizontalGroup(
            PanelAppearanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAppearanceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelAppearanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(PanelAppearanceLayout.createSequentialGroup()
                        .addGroup(PanelAppearanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelAppearanceLayout.createSequentialGroup()
                                .addComponent(LabelThemes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboBoxThemeSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelAppearanceLayout.createSequentialGroup()
                                .addComponent(CheckBoxEnableCustomFont)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboBoxFontSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelAppearanceLayout.createSequentialGroup()
                                .addComponent(LabelStyles)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboBoxFontStyleSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LabelFontSize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SpinnerFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 131, Short.MAX_VALUE))
                    .addComponent(LabelPreviewCustomFont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelAppearanceLayout.setVerticalGroup(
            PanelAppearanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAppearanceLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(PanelAppearanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelThemes)
                    .addComponent(ComboBoxThemeSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelPreviewCustomFont, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAppearanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckBoxEnableCustomFont)
                    .addComponent(ComboBoxFontSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAppearanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxFontStyleSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelStyles)
                    .addComponent(LabelFontSize)
                    .addComponent(SpinnerFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(268, Short.MAX_VALUE))
        );

        TabsConfig.addTab("Appearance", PanelAppearance);

        LabelSearch.setText(bundle.getString("SEARCH : ")); // NOI18N

        TextFieldSearch.getDocument().addDocumentListener(buildDocumentListener());

        javax.swing.GroupLayout PanelPluginsSearchLayout = new javax.swing.GroupLayout(PanelPluginsSearch);
        PanelPluginsSearch.setLayout(PanelPluginsSearchLayout);
        PanelPluginsSearchLayout.setHorizontalGroup(
            PanelPluginsSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPluginsSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelPluginsSearchLayout.setVerticalGroup(
            PanelPluginsSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPluginsSearchLayout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addGroup(PanelPluginsSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelSearch)))
        );

        SplitPanePlugins.setDividerLocation(320);

        this.pluginsModel = new PluginsTableModel(pluginsData);
        this.sorter = new TableRowSorter<PluginsTableModel>(this.pluginsModel);
        TablePlugins.setAutoCreateRowSorter(true);
        TablePlugins.setModel(this.pluginsModel);
        TablePlugins.setRowSorter(this.sorter);
        TablePlugins.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(TablePlugins);
        TablePlugins.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TablePlugins.getSelectionModel().addListSelectionListener(buildRowSelectionEventHandler());

        SplitPanePlugins.setLeftComponent(jScrollPane1);

        LabelPluginDescription.setText(bundle.getString("DESCRIPTION")); // NOI18N
        SplitPanePlugins.setRightComponent(LabelPluginDescription);

        BTNRegistPlugin.setText(bundle.getString("REGIST NEW PLUGIN")); // NOI18N
        BTNRegistPlugin.setFocusable(false);
        BTNRegistPlugin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRegistPluginActionPerformed(evt);
            }
        });

        BTNUpgradePlugin.setText("Upgrade Plugin");
        BTNUpgradePlugin.setFocusable(false);
        BTNUpgradePlugin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNUpgradePluginActionPerformed(evt);
            }
        });

        BTNRemovePlugin.setText("Remove Plugin");
        BTNRemovePlugin.setFocusable(false);
        BTNRemovePlugin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRemovePluginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelPluginRegistLayout = new javax.swing.GroupLayout(PanelPluginRegist);
        PanelPluginRegist.setLayout(PanelPluginRegistLayout);
        PanelPluginRegistLayout.setHorizontalGroup(
            PanelPluginRegistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPluginRegistLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BTNRegistPlugin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTNUpgradePlugin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTNRemovePlugin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelPluginRegistLayout.setVerticalGroup(
            PanelPluginRegistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPluginRegistLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPluginRegistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNRegistPlugin)
                    .addComponent(BTNUpgradePlugin)
                    .addComponent(BTNRemovePlugin))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelPluginsLayout = new javax.swing.GroupLayout(PanelPlugins);
        PanelPlugins.setLayout(PanelPluginsLayout);
        PanelPluginsLayout.setHorizontalGroup(
            PanelPluginsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPluginsSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(SplitPanePlugins, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
            .addComponent(PanelPluginRegist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelPluginsLayout.setVerticalGroup(
            PanelPluginsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPluginsLayout.createSequentialGroup()
                .addComponent(PanelPluginsSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SplitPanePlugins, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelPluginRegist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        TabsConfig.addTab("Plugins", PanelPlugins);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelOperations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TabsConfig)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TabsConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PanelOperations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<Auto-Generate>
    private void CheckBoxEnableCustomFontItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CheckBoxEnableCustomFontItemStateChanged
        impCheckBoxEnableCustomFontItemStateChanged(evt);
//        if (this.CheckBoxEnableCustomFont.isSelected() && !this.CustomFontEnable()) {
//            this.ComboBoxFontSelector.setEnabled(true);
//            this.ComboBoxFontStyleSelector.setEnabled(true);
//            this.SpinnerFontSize.setEnabled(true);
//        } else {
//            if (this.CustomFontEnable()) {
//                this.ComboBoxFontSelector.setEnabled(false);
//                this.ComboBoxFontStyleSelector.setEnabled(false);
//                this.SpinnerFontSize.setEnabled(false);
//            }
//        }
    }//GEN-LAST:event_CheckBoxEnableCustomFontItemStateChanged

    // Custom font changes event
    private void ComboBoxFontSelectorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxFontSelectorItemStateChanged
        impComboBoxFontSelectorItemStateChanged(evt);
        // this.refreshFontPreview();
    }//GEN-LAST:event_ComboBoxFontSelectorItemStateChanged

    private void ComboBoxFontStyleSelectorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxFontStyleSelectorItemStateChanged
        impComboBoxFontStyleSelectorItemStateChanged(evt);
        // this.refreshFontPreview();
    }//GEN-LAST:event_ComboBoxFontStyleSelectorItemStateChanged

    private void SpinnerFontSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerFontSizeStateChanged
        impSpinnerFontSizeStateChanged(evt);
        // this.refreshFontPreview();
    }//GEN-LAST:event_SpinnerFontSizeStateChanged

    private void ComboBoxThemeSelectorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxThemeSelectorItemStateChanged
        impComboBoxThemeSelectorItemStateChanged(evt);
    }//GEN-LAST:event_ComboBoxThemeSelectorItemStateChanged

    private void ComboBoxLanguageItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxLanguageItemStateChanged
        impComboBoxLanguageItemStateChanged(evt);
    }//GEN-LAST:event_ComboBoxLanguageItemStateChanged

    private void BTNRegistPluginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRegistPluginActionPerformed
        impBTNRegistPluginActionPerformed(evt);
    }//GEN-LAST:event_BTNRegistPluginActionPerformed

    private void BTNOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNOKActionPerformed
        impBTNOKActionPerformed(evt);
    }//GEN-LAST:event_BTNOKActionPerformed

    private void BTNCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCancelActionPerformed
        impBTNCancelActionPerformed(evt);
    }//GEN-LAST:event_BTNCancelActionPerformed

    private void BTNApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNApplyActionPerformed
        impBTNApplyActionPerformed(evt);
    }//GEN-LAST:event_BTNApplyActionPerformed

    private void BTNUpgradePluginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNUpgradePluginActionPerformed
        impBTNUpgradePluginActionPerformed(evt);
    }//GEN-LAST:event_BTNUpgradePluginActionPerformed

    private void BTNRemovePluginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRemovePluginActionPerformed
        impBTNRemovePluginActionPerformed(evt);
    }//GEN-LAST:event_BTNRemovePluginActionPerformed
    //</Auto-Generate>

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatSolarizedLightIJTheme.install();

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                impSettings dialog = new impSettings(new javax.swing.JFrame(), true);
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
    private boolean CustomFontEnable() {
        return this.ComboBoxFontSelector.isEnabled();
    }

    private String[] supportedLanguages = new String[]{"English", "Simplified Chinese"};

    private String[] supportedThemes = new String[]{
        "FlatSolarizedLightIJTheme",
        "Metal",
        "Nimbus",
        "FlatDarculaLaf",
        "FlatIntelliJLaf",
        "FlatCyanLightIJTheme",
        "FlatHiberbeeDarkIJTheme"
    };

    // RGB color, for example "43,43,43"
//    private String[] SupportedThemeBackgroundColors = new String[]{
//        "238,232,213",
//        "238,238,238",
//        "214,217,223",
//        "60,63,65",
//        "242,242,242",
//        "228,230,235",
//        "50,50,50"
//    };
    private final String[] SupportedFontStyle = new String[]{"Plain", "Bold", "Italic", "Bold Italic"};

    protected Object[][] pluginsData = {
        {"Plugin 1", new Boolean(true)},
        {"1 Plugin 2", new Boolean(false)}
    };
    
    protected String[] pluginsDescs = {
        "<html>First line<br>Second line</html>",
        "<html>SECOND line<br>Third line</html>"
    };

    /**
     * Update custom font preview according to user's selection.
     */
    protected void refreshFontPreview() {
        String fontChoice = (String) this.ComboBoxFontSelector.getSelectedItem();
        String fontStyleChoice = this.SupportedFontStyle[this.ComboBoxFontStyleSelector.getSelectedIndex()];
        int realStyle;
        switch (fontStyleChoice) {
            case "Plain":
                realStyle = Font.PLAIN;
                break;
            case "Bold":
                realStyle = Font.BOLD;
                break;
            case "Italic":
                realStyle = Font.PLAIN;
                break;
            case "Bold Italic":
                realStyle = Font.BOLD | Font.ITALIC;
                break;
            default:
                realStyle = Font.PLAIN;
        }
        int realFontSize = Integer.parseInt(this.SpinnerFontSize.getModel().getValue().toString());
        this.LabelPreviewCustomFont.setFont(new Font(fontChoice, realStyle, realFontSize));
    }

    protected TableRowSorter<PluginsTableModel> sorter;

    // use readData to get the data
    protected PluginsTableModel pluginsModel;

    protected void newFilter() {
        RowFilter<PluginsTableModel, Object> rf = null;
        try {
            // case insensitive
            rf = RowFilter.regexFilter("(?i)" + TextFieldSearch.getText(), 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    protected ListSelectionListener buildRowSelectionEventHandler() {
        ListSelectionListener handler = (ListSelectionEvent e) -> {
            int index = TablePlugins.getSelectedRow();
            LabelPluginDescription.setText(pluginsDescs[index]);
        };
        return handler;
    }

    protected DocumentListener buildDocumentListener() {
        DocumentListener handler = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter();
            }
        };
        return handler;
    }
    //</My-Custom>

    @Override
    public void pack() {
        super.pack(); //To change body of generated methods, choose Tools | Templates.
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton BTNApply;
    protected javax.swing.JButton BTNCancel;
    protected javax.swing.JButton BTNOK;
    protected javax.swing.JButton BTNRegistPlugin;
    protected javax.swing.JButton BTNRemovePlugin;
    protected javax.swing.JButton BTNUpgradePlugin;
    protected javax.swing.JCheckBox CheckBoxEnableCustomFont;
    protected javax.swing.JComboBox<String> ComboBoxFontSelector;
    protected javax.swing.JComboBox<String> ComboBoxFontStyleSelector;
    protected javax.swing.JComboBox<String> ComboBoxLanguage;
    protected javax.swing.JComboBox<String> ComboBoxThemeSelector;
    private javax.swing.JLabel LabelFontSize;
    private javax.swing.JLabel LabelLanguage;
    protected javax.swing.JLabel LabelPluginDescription;
    private javax.swing.JLabel LabelPreviewCustomFont;
    private javax.swing.JLabel LabelSearch;
    private javax.swing.JLabel LabelStyles;
    private javax.swing.JLabel LabelThemes;
    protected javax.swing.JPanel PanelAppearance;
    protected javax.swing.JPanel PanelGeneral;
    protected javax.swing.JPanel PanelOperations;
    protected javax.swing.JPanel PanelPluginRegist;
    protected javax.swing.JPanel PanelPlugins;
    protected javax.swing.JPanel PanelPluginsSearch;
    protected javax.swing.JSpinner SpinnerFontSize;
    protected javax.swing.JSplitPane SplitPanePlugins;
    protected javax.swing.JTable TablePlugins;
    protected javax.swing.JTabbedPane TabsConfig;
    protected javax.swing.JTextField TextFieldSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    //<Auto-Generate-Result>
    protected void impCheckBoxEnableCustomFontItemStateChanged(java.awt.event.ItemEvent evt){}
    protected void impComboBoxFontSelectorItemStateChanged(java.awt.event.ItemEvent evt){}
    protected void impComboBoxFontStyleSelectorItemStateChanged(java.awt.event.ItemEvent evt){}
    protected void impSpinnerFontSizeStateChanged(javax.swing.event.ChangeEvent evt){}
    protected void impComboBoxThemeSelectorItemStateChanged(java.awt.event.ItemEvent evt){}
    protected void impComboBoxLanguageItemStateChanged(java.awt.event.ItemEvent evt){}
    protected void impBTNRegistPluginActionPerformed(java.awt.event.ActionEvent evt){}
    protected void impBTNOKActionPerformed(java.awt.event.ActionEvent evt){}
    protected void impBTNCancelActionPerformed(java.awt.event.ActionEvent evt){}
    protected void impBTNApplyActionPerformed(java.awt.event.ActionEvent evt){}
    protected void impBTNUpgradePluginActionPerformed(java.awt.event.ActionEvent evt){}
    protected void impBTNRemovePluginActionPerformed(java.awt.event.ActionEvent evt){}
    //</Auto-Generate-Result>
}
