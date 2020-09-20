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
package io.github.hochikong.ktmeta.swingui;

import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.image.BufferedImage;

/**
 *
 * @author ckhoi
 */
public class KtmetaJFrameDesign extends javax.swing.JFrame {

    /**
     * Creates new form KtmetaJFrame
     */
    public KtmetaJFrameDesign() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CentreTabbedPane = new javax.swing.JTabbedPane();
        MainToolBar = new javax.swing.JToolBar();
        AddDatabaseBTN = new javax.swing.JButton();
        AddIndexBTN = new javax.swing.JButton();
        MainSplitPane = new javax.swing.JSplitPane();
        SubLeftPane = new javax.swing.JPanel();
        SubLeftSplitPane = new javax.swing.JSplitPane();
        SubLeftUpTab = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        DefaultMutableTreeNode dbms = new DefaultMutableTreeNode("DBMS");
        DefaultMutableTreeNode sqlite = new DefaultMutableTreeNode("SQLite");
        DefaultMutableTreeNode postgresql = new DefaultMutableTreeNode("PostgreSQL");
        dbms.add(sqlite);
        dbms.add(postgresql);
        DatabasesTree = new javax.swing.JTree(dbms);
        jScrollPane2 = new javax.swing.JScrollPane();
        DefaultMutableTreeNode esind = new DefaultMutableTreeNode("ElasticSearch Indices");
        IndicesTree = new javax.swing.JTree(esind);
        SubLeftDownTab = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        DefaultMutableTreeNode mtlb = new DefaultMutableTreeNode("Metadata Libs");
        MetadataLibsTree = new javax.swing.JTree(mtlb);
        SubRightSplitPane = new javax.swing.JSplitPane();
        CentreSplit = new javax.swing.JPanel();
        GeneralSplitPane = new javax.swing.JSplitPane();
        GeneralUpPaneTemplate = new javax.swing.JPanel();
        GeneralDownTabbedPaneTemplate = new javax.swing.JTabbedPane();
        GeneralQueryTab = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox3 = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        SQLQueryTab = new javax.swing.JPanel();
        InfoOutputTab = new javax.swing.JPanel();
        RightSplit = new javax.swing.JPanel();
        MainMenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        NewQueryItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        AddDatabaseItem = new javax.swing.JMenuItem();
        AddIndexItem = new javax.swing.JMenuItem();
        EditMenu = new javax.swing.JMenu();
        ToolsMenu = new javax.swing.JMenu();
        ToolsPrefItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        HelpMenu = new javax.swing.JMenu();
        HelpAboutItem = new javax.swing.JMenuItem();

        CentreTabbedPane.setPreferredSize(new java.awt.Dimension(1100, 719));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ktmeta");
        setIconImages(null);
        setMinimumSize(new java.awt.Dimension(1440, 900));
        setName("MainFrame"); // NOI18N
        setSize(new java.awt.Dimension(1920, 1080));

        MainToolBar.setRollover(true);

        AddDatabaseBTN.setText("Add DB");
        AddDatabaseBTN.setFocusable(false);
        AddDatabaseBTN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AddDatabaseBTN.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MainToolBar.add(AddDatabaseBTN);

        AddIndexBTN.setText("Add Index");
        AddIndexBTN.setFocusable(false);
        AddIndexBTN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AddIndexBTN.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AddIndexBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddIndexBTNActionPerformed(evt);
            }
        });
        MainToolBar.add(AddIndexBTN);

        MainSplitPane.setOneTouchExpandable(true);

        SubLeftPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        SubLeftPane.setPreferredSize(new java.awt.Dimension(250, 981));

        SubLeftSplitPane.setDividerLocation(450);
        SubLeftSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        SubLeftSplitPane.setOneTouchExpandable(true);
        SubLeftSplitPane.setPreferredSize(new java.awt.Dimension(100, 206));

        jScrollPane1.setViewportView(DatabasesTree);

        SubLeftUpTab.addTab("Databases", jScrollPane1);

        jScrollPane2.setViewportView(IndicesTree);

        SubLeftUpTab.addTab("ES Indices", jScrollPane2);

        SubLeftSplitPane.setTopComponent(SubLeftUpTab);

        jScrollPane3.setViewportView(MetadataLibsTree);

        SubLeftDownTab.addTab("Metadata Libraries", jScrollPane3);

        SubLeftSplitPane.setRightComponent(SubLeftDownTab);

        javax.swing.GroupLayout SubLeftPaneLayout = new javax.swing.GroupLayout(SubLeftPane);
        SubLeftPane.setLayout(SubLeftPaneLayout);
        SubLeftPaneLayout.setHorizontalGroup(
                SubLeftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(SubLeftSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
        );
        SubLeftPaneLayout.setVerticalGroup(
                SubLeftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(SubLeftSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
        );

        MainSplitPane.setLeftComponent(SubLeftPane);

        SubRightSplitPane.setOneTouchExpandable(true);

        CentreSplit.setMinimumSize(new java.awt.Dimension(900, 100));
        CentreSplit.setPreferredSize(new java.awt.Dimension(1280, 700));

        GeneralSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        GeneralSplitPane.setOneTouchExpandable(true);

        GeneralUpPaneTemplate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        GeneralUpPaneTemplate.setMinimumSize(new java.awt.Dimension(900, 200));
        GeneralUpPaneTemplate.setPreferredSize(new java.awt.Dimension(900, 650));

        javax.swing.GroupLayout GeneralUpPaneTemplateLayout = new javax.swing.GroupLayout(GeneralUpPaneTemplate);
        GeneralUpPaneTemplate.setLayout(GeneralUpPaneTemplateLayout);
        GeneralUpPaneTemplateLayout.setHorizontalGroup(
                GeneralUpPaneTemplateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1276, Short.MAX_VALUE)
        );
        GeneralUpPaneTemplateLayout.setVerticalGroup(
                GeneralUpPaneTemplateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 646, Short.MAX_VALUE)
        );

        GeneralSplitPane.setLeftComponent(GeneralUpPaneTemplate);

        GeneralDownTabbedPaneTemplate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        GeneralDownTabbedPaneTemplate.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        GeneralDownTabbedPaneTemplate.setMinimumSize(new java.awt.Dimension(104, 40));
        GeneralDownTabbedPaneTemplate.setPreferredSize(new java.awt.Dimension(1280, 200));

        jTextField1.setForeground(new java.awt.Color(204, 204, 204));
        jTextField1.setText("Something");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });

        jCheckBox1.setText("Search on databases");

        jCheckBox2.setText("Search on ElasticSearch");

        jLabel1.setText("On properties : ");

        jButton1.setText("Show all properties");

        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ckhoi\\Documents\\NetBeansProjects\\basic\\src\\main\\resources\\icons\\ico\\搜索.png")); // NOI18N
        jButton2.setToolTipText("Start Searching");

        jCheckBox3.setText("Create new result tab");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("On Metadata Libraries : ");

        javax.swing.GroupLayout GeneralQueryTabLayout = new javax.swing.GroupLayout(GeneralQueryTab);
        GeneralQueryTab.setLayout(GeneralQueryTabLayout);
        GeneralQueryTabLayout.setHorizontalGroup(
                GeneralQueryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(GeneralQueryTabLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(GeneralQueryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox2)
                                        .addGroup(GeneralQueryTabLayout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton1))
                                        .addComponent(jCheckBox1)
                                        .addGroup(GeneralQueryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, GeneralQueryTabLayout.createSequentialGroup()
                                                        .addComponent(jLabel2)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jCheckBox3))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, GeneralQueryTabLayout.createSequentialGroup()
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jButton2))))
                                .addContainerGap(726, Short.MAX_VALUE))
        );
        GeneralQueryTabLayout.setVerticalGroup(
                GeneralQueryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(GeneralQueryTabLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(GeneralQueryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(GeneralQueryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jCheckBox3)
                                        .addComponent(jLabel2)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(GeneralQueryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jLabel1))
                                .addContainerGap(108, Short.MAX_VALUE))
        );

        GeneralDownTabbedPaneTemplate.addTab("General Query", GeneralQueryTab);

        javax.swing.GroupLayout SQLQueryTabLayout = new javax.swing.GroupLayout(SQLQueryTab);
        SQLQueryTab.setLayout(SQLQueryTabLayout);
        SQLQueryTabLayout.setHorizontalGroup(
                SQLQueryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1276, Short.MAX_VALUE)
        );
        SQLQueryTabLayout.setVerticalGroup(
                SQLQueryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 280, Short.MAX_VALUE)
        );

        GeneralDownTabbedPaneTemplate.addTab("SQL Query", SQLQueryTab);

        javax.swing.GroupLayout InfoOutputTabLayout = new javax.swing.GroupLayout(InfoOutputTab);
        InfoOutputTab.setLayout(InfoOutputTabLayout);
        InfoOutputTabLayout.setHorizontalGroup(
                InfoOutputTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1276, Short.MAX_VALUE)
        );
        InfoOutputTabLayout.setVerticalGroup(
                InfoOutputTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 280, Short.MAX_VALUE)
        );

        GeneralDownTabbedPaneTemplate.addTab("Info", InfoOutputTab);

        GeneralSplitPane.setRightComponent(GeneralDownTabbedPaneTemplate);

        javax.swing.GroupLayout CentreSplitLayout = new javax.swing.GroupLayout(CentreSplit);
        CentreSplit.setLayout(CentreSplitLayout);
        CentreSplitLayout.setHorizontalGroup(
                CentreSplitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CentreSplitLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(GeneralSplitPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        CentreSplitLayout.setVerticalGroup(
                CentreSplitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CentreSplitLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(GeneralSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE))
        );

        SubRightSplitPane.setLeftComponent(CentreSplit);

        javax.swing.GroupLayout RightSplitLayout = new javax.swing.GroupLayout(RightSplit);
        RightSplit.setLayout(RightSplitLayout);
        RightSplitLayout.setHorizontalGroup(
                RightSplitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        RightSplitLayout.setVerticalGroup(
                RightSplitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 981, Short.MAX_VALUE)
        );

        SubRightSplitPane.setRightComponent(RightSplit);

        MainSplitPane.setRightComponent(SubRightSplitPane);

        FileMenu.setText("File");

        NewQueryItem.setIcon(new ImageIcon(new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB)));
        NewQueryItem.setText("New Query");
        FileMenu.add(NewQueryItem);
        FileMenu.add(jSeparator1);

        AddDatabaseItem.setIcon(new ImageIcon(new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB)));
        AddDatabaseItem.setText("Add database");
        FileMenu.add(AddDatabaseItem);

        AddIndexItem.setIcon(new ImageIcon(new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB)));
        AddIndexItem.setText("Add ES Index");
        FileMenu.add(AddIndexItem);

        MainMenuBar.add(FileMenu);

        EditMenu.setText("Edit");
        MainMenuBar.add(EditMenu);

        ToolsMenu.setText("Tools");

        ToolsPrefItem.setIcon(new ImageIcon(new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB)));
        ToolsPrefItem.setText("Preference");
        ToolsPrefItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToolsPrefItemActionPerformed(evt);
            }
        });
        ToolsMenu.add(ToolsPrefItem);
        ToolsMenu.add(jSeparator2);

        jMenuItem1.setIcon(new ImageIcon(new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB)));
        jMenuItem1.setText("ElasticSearch");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        ToolsMenu.add(jMenuItem1);

        MainMenuBar.add(ToolsMenu);

        HelpMenu.setText("Help");

        HelpAboutItem.setIcon(new ImageIcon(new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB)));
        HelpAboutItem.setText("About");
        HelpAboutItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelpAboutItemActionPerformed(evt);
            }
        });
        HelpMenu.add(HelpAboutItem);

        MainMenuBar.add(HelpMenu);

        setJMenuBar(MainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(MainToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(MainSplitPane)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(MainToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(MainSplitPane)
                                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ToolsPrefItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToolsPrefItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ToolsPrefItemActionPerformed

    private void AddIndexBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddIndexBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddIndexBTNActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void HelpAboutItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelpAboutItemActionPerformed
        KtHelpAboutItemActionPerformed(evt);
    }//GEN-LAST:event_HelpAboutItemActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        this.jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        this.jTextField1.setText("");
    }//GEN-LAST:event_jTextField1MouseClicked

    /**
     * Kotlin override functions
     */
    protected void KtHelpAboutItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Install theme */
        FlatSolarizedLightIJTheme.install();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KtmetaJFrameDesign().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton AddDatabaseBTN;
    protected javax.swing.JMenuItem AddDatabaseItem;
    protected javax.swing.JButton AddIndexBTN;
    protected javax.swing.JMenuItem AddIndexItem;
    protected javax.swing.JPanel CentreSplit;
    protected javax.swing.JTabbedPane CentreTabbedPane;
    protected javax.swing.JTree DatabasesTree;
    protected javax.swing.JMenu EditMenu;
    protected javax.swing.JMenu FileMenu;
    protected javax.swing.JTabbedPane GeneralDownTabbedPaneTemplate;
    protected javax.swing.JPanel GeneralQueryTab;
    protected javax.swing.JSplitPane GeneralSplitPane;
    private javax.swing.JPanel GeneralUpPaneTemplate;
    protected javax.swing.JMenuItem HelpAboutItem;
    protected javax.swing.JMenu HelpMenu;
    protected javax.swing.JTree IndicesTree;
    protected javax.swing.JPanel InfoOutputTab;
    protected javax.swing.JMenuBar MainMenuBar;
    protected javax.swing.JSplitPane MainSplitPane;
    protected javax.swing.JToolBar MainToolBar;
    protected javax.swing.JTree MetadataLibsTree;
    protected javax.swing.JMenuItem NewQueryItem;
    protected javax.swing.JPanel RightSplit;
    protected javax.swing.JPanel SQLQueryTab;
    protected javax.swing.JTabbedPane SubLeftDownTab;
    protected javax.swing.JPanel SubLeftPane;
    protected javax.swing.JSplitPane SubLeftSplitPane;
    protected javax.swing.JTabbedPane SubLeftUpTab;
    private javax.swing.JSplitPane SubRightSplitPane;
    protected javax.swing.JMenu ToolsMenu;
    protected javax.swing.JMenuItem ToolsPrefItem;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
