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
import io.github.hochikong.ktmeta.swingui.essentials.VerticalTabComp;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author ckhoi
 */
public class KtmetaMainFrame extends javax.swing.JFrame {

    /**
     * Creates new form KtmetaJFrame
     */
    public KtmetaMainFrame() {
        // UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("GenYoMin TW B", Font.PLAIN, 14));
        initComponents();
        HideHead();
        this.setLocationRelativeTo(null);
    }

    public KtmetaMainFrame(Image icon, Font uiFont) {
        this.OverrideUIFont = uiFont;
        initComponents();
        HideHead();
        this.setIconImage(icon);
    }

    protected String[] MetaLibsCardOptions = new String[] { "MetaLibs" };

    //<Auto-Generate>
    private void MenuItemNewMetaLibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemNewMetaLibActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuItemNewMetaLibActionPerformed

    private void MenuItemAddDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemAddDatabaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuItemAddDatabaseActionPerformed

    private void MenuItemAddIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemAddIndexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuItemAddIndexActionPerformed

    private void MenuItemSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemSettingsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuItemSettingsActionPerformed

    private void MenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuItemExitActionPerformed

    private void MenuItemExportToCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemExportToCSVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuItemExportToCSVActionPerformed

    private void MenuItemPluginsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemPluginsMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuItemPluginsMenuActionPerformed

    private void MenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemAboutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuItemAboutActionPerformed

    private void BTNAddDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddDatabaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNAddDatabaseActionPerformed

    private void BTNAddESIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddESIndexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNAddESIndexActionPerformed

    private void BTNAddMetaLibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddMetaLibActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNAddMetaLibActionPerformed

    private void BTNAggrSearcgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAggrSearcgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNAggrSearcgActionPerformed

    private void BTNAdvanceSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAdvanceSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNAdvanceSearchActionPerformed

    private void TreeMetadataLibsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TreeMetadataLibsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TreeMetadataLibsMouseClicked

    private void TreeMetadataLibsValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_TreeMetadataLibsValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TreeMetadataLibsValueChanged

    private void TreeDatabasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TreeDatabasesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TreeDatabasesMouseClicked

    private void TreeDatabasesValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_TreeDatabasesValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TreeDatabasesValueChanged

    private void TreeIndicesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TreeIndicesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TreeIndicesMouseClicked

    private void TreeIndicesValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_TreeIndicesValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TreeIndicesValueChanged

    private void ComboBoxFTAvailableMetaLibsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxFTAvailableMetaLibsItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxFTAvailableMetaLibsItemStateChanged

    private void BTNFTSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNFTSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNFTSearchActionPerformed

    private void RightTextPaneAbstractMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RightTextPaneAbstractMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_RightTextPaneAbstractMouseClicked

    private void RightTextPaneTagsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RightTextPaneTagsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_RightTextPaneTagsMouseClicked

    private void BTNRightPreviewManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRightPreviewManagerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNRightPreviewManagerActionPerformed

    private void BTNRunningTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRunningTasksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNRunningTasksActionPerformed

    private void PMAbstractCopyAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMAbstractCopyAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMAbstractCopyAllActionPerformed

    private void PMAbstractCopySelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMAbstractCopySelectedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMAbstractCopySelectedActionPerformed

    private void PMTagsAddCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMTagsAddCustomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMTagsAddCustomActionPerformed

    private void PMTagsCopyAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMTagsCopyAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMTagsCopyAllActionPerformed

    private void PMTagsCopySelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMTagsCopySelectedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMTagsCopySelectedActionPerformed

    private void PMViewEnlargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMViewEnlargeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMViewEnlargeActionPerformed

    private void RightPreviewLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RightPreviewLabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_RightPreviewLabelMouseClicked

    private void PMRenameDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMRenameDatabaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMRenameDatabaseActionPerformed

    private void PMDBPropertiesEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMDBPropertiesEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMDBPropertiesEditActionPerformed

    private void PMRemoveDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMRemoveDatabaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMRemoveDatabaseActionPerformed

    private void PMRenameIndicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMRenameIndicesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMRenameIndicesActionPerformed

    private void PMIndexPropertiesEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMIndexPropertiesEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMIndexPropertiesEditActionPerformed

    private void PMRemoveIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMRemoveIndexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMRemoveIndexActionPerformed

    private void PMRenameMetaLibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMRenameMetaLibActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMRenameMetaLibActionPerformed

    private void PMMetaLibPropertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMMetaLibPropertiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMMetaLibPropertiesActionPerformed

    private void PMImportDataToDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMImportDataToDBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMImportDataToDBActionPerformed

    private void PMSyncToESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMSyncToESActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMSyncToESActionPerformed

    private void PMRemoveMetaLibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PMRemoveMetaLibActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PMRemoveMetaLibActionPerformed

    private void MenuItemImportMetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemImportMetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuItemImportMetaActionPerformed

    private void MenuItemCheckESConnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemCheckESConnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuItemCheckESConnActionPerformed

    private void MenuItemCreateMappingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemCreateMappingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuItemCreateMappingActionPerformed
    protected String[] ResourceCardOptions = new String[] { "Databases", "Indices" };
    protected DefaultMutableTreeNode metalibsRootNode = new DefaultMutableTreeNode("MetaLibs Collections");
    protected DefaultMutableTreeNode databaseRootNode = new DefaultMutableTreeNode("Database Collections");
    protected DefaultMutableTreeNode indicesRootNode = new DefaultMutableTreeNode("ES Index Collections");
    protected javax.swing.JButton BTNCollapseTree;
    protected javax.swing.JButton BTNCollapseTree1;
    //</Auto-Generate>

    @Override
    public void layout() {
        super.layout(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Install theme */
        //FlatArcDarkIJTheme.install();
        //FlatCobalt2IJTheme.install();
        //FlatGradiantoDeepOceanIJTheme.install();
        //FlatGruvboxDarkSoftIJTheme.install();
        //FlatNordIJTheme.install();
        FlatSolarizedLightIJTheme.install();
//        FlatDarculaLaf.install();
//        FlatIntelliJLaf.install();
        //FlatCyanLightIJTheme.install();
        //FlatHiberbeeDarkIJTheme.install();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                KtmetaMainFrame f = new KtmetaMainFrame();
                f.setVisible(true);
                f.setLocationRelativeTo(null);
            }
        });
    }

    //<My-Custom>
    protected String[] default_metalibs = {"Lib 1", "Lib 2"};

    protected void HideHead() {
        // Like IDEA style
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
    }

    // Used by vertical tabs in the right side.
    protected JLabel registerVerticalTab(String tabTitle, boolean clockwise) {
        JLabel result = new JLabel(tabTitle);
        result.setPreferredSize(new Dimension(12, 80));
        result.setFont(OverrideUIFont);
        result.setVerticalAlignment(SwingConstants.CENTER);
        result.setHorizontalAlignment(SwingConstants.CENTER);
        result.setUI(new VerticalTabComp(clockwise));
        return result;
    }

    private Font OverrideUIFont = UIManager.getFont("defaultFont").deriveFont(14.0F);
    protected javax.swing.JButton BTNExpandTree;
    protected javax.swing.JButton BTNExpandTree1;
    protected javax.swing.JComboBox<String> ComboBoxMLOptions;
    protected javax.swing.JComboBox<String> ComboBoxRESOptions;
    protected javax.swing.JPanel PanelMetaLibsTreeContainer;
    //</My-Custom>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton BTNAddDatabase;
    protected javax.swing.JButton BTNAddESIndex;
    protected javax.swing.JButton BTNAddMetaLib;
    protected javax.swing.JButton BTNAdvanceSearch;
    protected javax.swing.JButton BTNAggrSearcg;
    protected javax.swing.JPanel PanelResourcesTreeContainer;
    protected javax.swing.JToolBar ToolBarMetaLibs;
    protected javax.swing.JToolBar ToolBarResources;
    private javax.swing.Box.Filler filler2;
    protected javax.swing.JButton BTNFTSearch;
    protected javax.swing.JButton BTNRightPreviewManager;
    protected javax.swing.JButton BTNRunningTasks;
    protected javax.swing.JPanel BottomPanel;
    private javax.swing.ButtonGroup ButtonGroupOnWhere;
    protected javax.swing.JCheckBox CheckBoxFTCreateNewTab;
    protected javax.swing.JCheckBox CheckBoxFTOnDB;
    protected javax.swing.JCheckBox CheckBoxFTOnES;
    protected javax.swing.JComboBox<String> ComboBoxFTAvailableMetaLibs;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PopupMenuAbstract = new javax.swing.JPopupMenu();
        PMEditAbstract = new javax.swing.JMenuItem();
        PMAbstractCopyAll = new javax.swing.JMenuItem();
        PMAbstractCopySelected = new javax.swing.JMenuItem();
        PopupMenuTags = new javax.swing.JPopupMenu();
        PMTagsAddCustom = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        PMTagsCopyAll = new javax.swing.JMenuItem();
        PMTagsCopySelected = new javax.swing.JMenuItem();
        PopupMenuPreview = new javax.swing.JPopupMenu();
        PMViewEnlarge = new javax.swing.JMenuItem();
        PopupMenuDatabasesTree = new javax.swing.JPopupMenu();
        PMRenameDatabase = new javax.swing.JMenuItem();
        PMDBPropertiesEdit = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        PMRemoveDatabase = new javax.swing.JMenuItem();
        PopupMenuIndicesTree = new javax.swing.JPopupMenu();
        PMRenameIndices = new javax.swing.JMenuItem();
        PMIndexPropertiesEdit = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        PMRemoveIndex = new javax.swing.JMenuItem();
        PopupMenuMetaLibsTree = new javax.swing.JPopupMenu();
        PMRenameMetaLib = new javax.swing.JMenuItem();
        PMMetaLibProperties = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        PMImportDataToDB = new javax.swing.JMenuItem();
        PMSyncToES = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        PMRemoveMetaLib = new javax.swing.JMenuItem();
        ButtonGroupOnWhere = new javax.swing.ButtonGroup();
        PopupMenuMetaDoc = new javax.swing.JPopupMenu();
        PMExportMetaDocToFile = new javax.swing.JMenuItem();
        ToolBarPanel = new javax.swing.JPanel();
        MainToolBar = new javax.swing.JToolBar();
        BTNAddDatabase = new javax.swing.JButton();
        BTNAddESIndex = new javax.swing.JButton();
        BTNAddMetaLib = new javax.swing.JButton();
        jSeparator14 = new javax.swing.JToolBar.Separator();
        BTNAggrSearcg = new javax.swing.JButton();
        BTNAdvanceSearch = new javax.swing.JButton();
        MainSplitPane = new javax.swing.JSplitPane();
        SplitPaneRightTabbedPane = new javax.swing.JTabbedPane();
        PanelMetaLibs = new javax.swing.JPanel();
        ToolBarMetaLibs = new javax.swing.JToolBar();
        ComboBoxMLOptions = new javax.swing.JComboBox<>();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        BTNExpandTree = new javax.swing.JButton();
        BTNCollapseTree = new javax.swing.JButton();
        PanelMetaLibsTreeContainer = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TreeMetadataLibs = new javax.swing.JTree(metalibsRootNode);
        PanelResources = new javax.swing.JPanel();
        ToolBarResources = new javax.swing.JToolBar();
        ComboBoxRESOptions = new javax.swing.JComboBox<>();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        BTNExpandTree1 = new javax.swing.JButton();
        BTNCollapseTree1 = new javax.swing.JButton();
        PanelResourcesTreeContainer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TreeDatabases = new javax.swing.JTree(databaseRootNode);
        jScrollPane2 = new javax.swing.JScrollPane();
        TreeIndices = new javax.swing.JTree(indicesRootNode);
        SplitPaneSubRight = new javax.swing.JSplitPane();
        PanelSplitCenter = new javax.swing.JPanel();
        SplitPaneVTGeneral = new javax.swing.JSplitPane();
        TabbedPaneQueryResult = new javax.swing.JTabbedPane();
        PanelQuery = new javax.swing.JTabbedPane();
        PanelQuickQuery = new javax.swing.JPanel();
        LabelQQueryOnMetaLib = new javax.swing.JLabel();
        ComboBoxFTAvailableMetaLibs = new javax.swing.JComboBox<>();
        CheckBoxFTCreateNewTab = new javax.swing.JCheckBox();
        LabelFTSearch = new javax.swing.JLabel();
        TextFieldFTSearch = new javax.swing.JTextField();
        BTNFTSearch = new javax.swing.JButton();
        CheckBoxFTOnDB = new javax.swing.JCheckBox();
        CheckBoxFTOnES = new javax.swing.JCheckBox();
        PanelInfoOutput = new javax.swing.JPanel();
        TabbedPaneSplitRight = new javax.swing.JTabbedPane();
        PanelRightPreview = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        PanelInfoWrapper = new javax.swing.JPanel();
        LabelRightAbstract = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        RightTextPaneAbstract = new javax.swing.JTextPane();
        LabelRightTags = new javax.swing.JLabel();
        LabelRightPreview = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        RightTextPaneTags = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        RightPreviewLabel = new javax.swing.JLabel();
        BTNRightPreviewManager = new javax.swing.JButton();
        RightDetailsPane = new javax.swing.JPanel();
        RightLabelMetadataDoc = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        RightTextPaneMetaDoc = new javax.swing.JTextPane();
        BottomPanel = new javax.swing.JPanel();
        BTNRunningTasks = new javax.swing.JButton();
        MainMenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        MenuItemNewMetaLib = new javax.swing.JMenuItem();
        MenuItemImportMeta = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MenuItemAddDatabase = new javax.swing.JMenuItem();
        MenuItemAddIndex = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        MenuItemSettings = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        MenuItemExit = new javax.swing.JMenuItem();
        EditMenu = new javax.swing.JMenu();
        MenuExportTable = new javax.swing.JMenu();
        MenuItemExportToCSV = new javax.swing.JMenuItem();
        ToolsMenu = new javax.swing.JMenu();
        MenuItemPluginsMenu = new javax.swing.JMenuItem();
        MenuES = new javax.swing.JMenu();
        MenuItemCheckESConn = new javax.swing.JMenuItem();
        MenuItemCreateMapping = new javax.swing.JMenuItem();
        HelpMenu = new javax.swing.JMenu();
        MenuItemAbout = new javax.swing.JMenuItem();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/Frames/KtmetaMainFrame_trans"); // NOI18N
        PMEditAbstract.setText(bundle.getString("EDIT ABSTRACT")); // NOI18N
        PopupMenuAbstract.add(PMEditAbstract);

        PMAbstractCopyAll.setText(bundle.getString("COPY ALL")); // NOI18N
        PMAbstractCopyAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMAbstractCopyAllActionPerformed(evt);
            }
        });
        PopupMenuAbstract.add(PMAbstractCopyAll);

        PMAbstractCopySelected.setText(bundle.getString("COPY SELECTED")); // NOI18N
        PMAbstractCopySelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMAbstractCopySelectedActionPerformed(evt);
            }
        });
        PopupMenuAbstract.add(PMAbstractCopySelected);

        PMTagsAddCustom.setText(bundle.getString("ADD CUSTOM TAGS")); // NOI18N
        PMTagsAddCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMTagsAddCustomActionPerformed(evt);
            }
        });
        PopupMenuTags.add(PMTagsAddCustom);
        PopupMenuTags.add(jSeparator8);

        PMTagsCopyAll.setText(bundle.getString("COPY ALL")); // NOI18N
        PMTagsCopyAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMTagsCopyAllActionPerformed(evt);
            }
        });
        PopupMenuTags.add(PMTagsCopyAll);

        PMTagsCopySelected.setText(bundle.getString("COPY SELECTED")); // NOI18N
        PMTagsCopySelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMTagsCopySelectedActionPerformed(evt);
            }
        });
        PopupMenuTags.add(PMTagsCopySelected);

        PMViewEnlarge.setText(bundle.getString("ENLARGE PREVIEW")); // NOI18N
        PMViewEnlarge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMViewEnlargeActionPerformed(evt);
            }
        });
        PopupMenuPreview.add(PMViewEnlarge);

        PMRenameDatabase.setText(bundle.getString("RENAME")); // NOI18N
        PMRenameDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMRenameDatabaseActionPerformed(evt);
            }
        });
        PopupMenuDatabasesTree.add(PMRenameDatabase);

        PMDBPropertiesEdit.setText(bundle.getString("PROPERTIES")); // NOI18N
        PMDBPropertiesEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMDBPropertiesEditActionPerformed(evt);
            }
        });
        PopupMenuDatabasesTree.add(PMDBPropertiesEdit);
        PopupMenuDatabasesTree.add(jSeparator6);

        PMRemoveDatabase.setText(bundle.getString("REMOVE")); // NOI18N
        PMRemoveDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMRemoveDatabaseActionPerformed(evt);
            }
        });
        PopupMenuDatabasesTree.add(PMRemoveDatabase);

        PMRenameIndices.setText(bundle.getString("RENAME")); // NOI18N
        PMRenameIndices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMRenameIndicesActionPerformed(evt);
            }
        });
        PopupMenuIndicesTree.add(PMRenameIndices);

        PMIndexPropertiesEdit.setText(bundle.getString("PROPERTIES")); // NOI18N
        PMIndexPropertiesEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMIndexPropertiesEditActionPerformed(evt);
            }
        });
        PopupMenuIndicesTree.add(PMIndexPropertiesEdit);
        PopupMenuIndicesTree.add(jSeparator10);

        PMRemoveIndex.setText(bundle.getString("REMOVE")); // NOI18N
        PMRemoveIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMRemoveIndexActionPerformed(evt);
            }
        });
        PopupMenuIndicesTree.add(PMRemoveIndex);

        PMRenameMetaLib.setText(bundle.getString("RENAME")); // NOI18N
        PMRenameMetaLib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMRenameMetaLibActionPerformed(evt);
            }
        });
        PopupMenuMetaLibsTree.add(PMRenameMetaLib);

        PMMetaLibProperties.setText(bundle.getString("PROPERTIES")); // NOI18N
        PMMetaLibProperties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMMetaLibPropertiesActionPerformed(evt);
            }
        });
        PopupMenuMetaLibsTree.add(PMMetaLibProperties);
        PopupMenuMetaLibsTree.add(jSeparator12);

        PMImportDataToDB.setText(bundle.getString("IMPORT METADATA TO DATABASE")); // NOI18N
        PMImportDataToDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMImportDataToDBActionPerformed(evt);
            }
        });
        PopupMenuMetaLibsTree.add(PMImportDataToDB);

        PMSyncToES.setText(bundle.getString("SYNC DATA TO ELASTICSEARCH")); // NOI18N
        PMSyncToES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMSyncToESActionPerformed(evt);
            }
        });
        PopupMenuMetaLibsTree.add(PMSyncToES);
        PopupMenuMetaLibsTree.add(jSeparator13);

        PMRemoveMetaLib.setText(bundle.getString("REMOVE")); // NOI18N
        PMRemoveMetaLib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PMRemoveMetaLibActionPerformed(evt);
            }
        });
        PopupMenuMetaLibsTree.add(PMRemoveMetaLib);

        PMExportMetaDocToFile.setText(bundle.getString("EXPORT METADATA DOCUMENT TO FILE")); // NOI18N
        PopupMenuMetaDoc.add(PMExportMetaDocToFile);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(bundle.getString("KTMETA")); // NOI18N
        setIconImages(null);
        setName("MainFrame"); // NOI18N
        setUndecorated(true);
        setSize(new java.awt.Dimension(1920, 1080));

        ToolBarPanel.setMaximumSize(new java.awt.Dimension(159, 35));
        ToolBarPanel.setMinimumSize(new java.awt.Dimension(159, 35));
        ToolBarPanel.setPreferredSize(new java.awt.Dimension(159, 35));
        ToolBarPanel.setLayout(new java.awt.BorderLayout());

        MainToolBar.setRollover(true);

        BTNAddDatabase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico/20pix/database.png"))); // NOI18N
        BTNAddDatabase.setToolTipText(bundle.getString("ADD DATABASE")); // NOI18N
        BTNAddDatabase.setFocusable(false);
        BTNAddDatabase.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTNAddDatabase.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTNAddDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAddDatabaseActionPerformed(evt);
            }
        });
        MainToolBar.add(BTNAddDatabase);

        BTNAddESIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico/20pix/es.png"))); // NOI18N
        BTNAddESIndex.setToolTipText(bundle.getString("ADD METADATA LIBRARY")); // NOI18N
        BTNAddESIndex.setFocusable(false);
        BTNAddESIndex.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTNAddESIndex.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTNAddESIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAddESIndexActionPerformed(evt);
            }
        });
        MainToolBar.add(BTNAddESIndex);

        BTNAddMetaLib.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico/20pix/metalib.png"))); // NOI18N
        BTNAddMetaLib.setToolTipText(bundle.getString("ADD METADATA LIBRARY")); // NOI18N
        BTNAddMetaLib.setFocusable(false);
        BTNAddMetaLib.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTNAddMetaLib.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTNAddMetaLib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAddMetaLibActionPerformed(evt);
            }
        });
        MainToolBar.add(BTNAddMetaLib);

        jSeparator14.setForeground(new java.awt.Color(204, 204, 204));
        MainToolBar.add(jSeparator14);

        BTNAggrSearcg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico/20pix/advance search.png"))); // NOI18N
        BTNAggrSearcg.setToolTipText(bundle.getString("ADVANCE SEARCH")); // NOI18N
        BTNAggrSearcg.setFocusable(false);
        BTNAggrSearcg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTNAggrSearcg.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTNAggrSearcg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAggrSearcgActionPerformed(evt);
            }
        });
        MainToolBar.add(BTNAggrSearcg);

        BTNAdvanceSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico/20pix/aggr search.png"))); // NOI18N
        BTNAdvanceSearch.setToolTipText(bundle.getString("AGGREGATE SEARCH")); // NOI18N
        BTNAdvanceSearch.setFocusable(false);
        BTNAdvanceSearch.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTNAdvanceSearch.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTNAdvanceSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAdvanceSearchActionPerformed(evt);
            }
        });
        MainToolBar.add(BTNAdvanceSearch);

        ToolBarPanel.add(MainToolBar, java.awt.BorderLayout.CENTER);

        MainSplitPane.setDividerSize(2);

        SplitPaneRightTabbedPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        SplitPaneRightTabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        PanelMetaLibs.setPreferredSize(new java.awt.Dimension(220, 811));
        PanelMetaLibs.setLayout(new java.awt.BorderLayout());

        ToolBarMetaLibs.setRollover(true);

        ComboBoxMLOptions.setModel(new javax.swing.DefaultComboBoxModel<>(MetaLibsCardOptions));
        ComboBoxMLOptions.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxMLOptionsItemStateChanged(evt);
            }
        });
        ToolBarMetaLibs.add(ComboBoxMLOptions);
        ToolBarMetaLibs.add(filler1);

        BTNExpandTree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico/16pix/expandall.png"))); // NOI18N
        BTNExpandTree.setToolTipText("Expand All");
        BTNExpandTree.setFocusable(false);
        BTNExpandTree.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTNExpandTree.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTNExpandTree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNExpandTreeActionPerformed(evt);
            }
        });
        ToolBarMetaLibs.add(BTNExpandTree);

        BTNCollapseTree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico/16pix/collapseall.png"))); // NOI18N
        BTNCollapseTree.setToolTipText("Collapse All");
        BTNCollapseTree.setFocusable(false);
        BTNCollapseTree.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTNCollapseTree.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTNCollapseTree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCollapseTreeActionPerformed(evt);
            }
        });
        ToolBarMetaLibs.add(BTNCollapseTree);

        PanelMetaLibs.add(ToolBarMetaLibs, java.awt.BorderLayout.NORTH);

        PanelMetaLibsTreeContainer.setLayout(new java.awt.CardLayout());

        jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jScrollPane3.setMinimumSize(new java.awt.Dimension(2, 19));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(220, 442));

        TreeMetadataLibs.setPreferredSize(new java.awt.Dimension(200, 90));
        TreeMetadataLibs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TreeMetadataLibsMouseClicked(evt);
            }
        });
        TreeMetadataLibs.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                TreeMetadataLibsValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(TreeMetadataLibs);

        PanelMetaLibsTreeContainer.add(jScrollPane3, "MetaLibs");

        PanelMetaLibs.add(PanelMetaLibsTreeContainer, java.awt.BorderLayout.CENTER);

        SplitPaneRightTabbedPane.addTab("MetaLibs", PanelMetaLibs);
        SplitPaneRightTabbedPane.setTabComponentAt(0, registerVerticalTab(bundle.getString("VERTICAL METALIBS"), false));

        PanelResources.setPreferredSize(new java.awt.Dimension(230, 811));
        PanelResources.setLayout(new java.awt.BorderLayout());

        ToolBarResources.setRollover(true);

        ComboBoxRESOptions.setModel(new javax.swing.DefaultComboBoxModel<>(ResourceCardOptions));
        ComboBoxRESOptions.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxRESOptionsItemStateChanged(evt);
            }
        });
        ToolBarResources.add(ComboBoxRESOptions);
        ToolBarResources.add(filler2);

        BTNExpandTree1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico/16pix/expandall.png"))); // NOI18N
        BTNExpandTree1.setToolTipText("Expand All");
        BTNExpandTree1.setFocusable(false);
        BTNExpandTree1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTNExpandTree1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTNExpandTree1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNExpandTree1ActionPerformed(evt);
            }
        });
        ToolBarResources.add(BTNExpandTree1);

        BTNCollapseTree1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico/16pix/collapseall.png"))); // NOI18N
        BTNCollapseTree1.setToolTipText("Collapse All");
        BTNCollapseTree1.setFocusable(false);
        BTNCollapseTree1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTNCollapseTree1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTNCollapseTree1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCollapseTree1ActionPerformed(evt);
            }
        });
        ToolBarResources.add(BTNCollapseTree1);

        PanelResources.add(ToolBarResources, java.awt.BorderLayout.NORTH);

        PanelResourcesTreeContainer.setLayout(new java.awt.CardLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        TreeDatabases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TreeDatabasesMouseClicked(evt);
            }
        });
        TreeDatabases.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                TreeDatabasesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(TreeDatabases);

        PanelResourcesTreeContainer.add(jScrollPane1, "Databases");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        TreeIndices.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TreeIndicesMouseClicked(evt);
            }
        });
        TreeIndices.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                TreeIndicesValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(TreeIndices);

        PanelResourcesTreeContainer.add(jScrollPane2, "Indices");

        PanelResources.add(PanelResourcesTreeContainer, java.awt.BorderLayout.CENTER);

        SplitPaneRightTabbedPane.addTab("Resources", PanelResources);
        SplitPaneRightTabbedPane.setTabComponentAt(1, registerVerticalTab(bundle.getString("VERTICAL RESOURCES"), false));

        MainSplitPane.setLeftComponent(SplitPaneRightTabbedPane);

        SplitPaneSubRight.setDividerLocation(860);
        SplitPaneSubRight.setDividerSize(2);

        PanelSplitCenter.setMinimumSize(new java.awt.Dimension(100, 100));
        PanelSplitCenter.setPreferredSize(new java.awt.Dimension(900, 700));

        SplitPaneVTGeneral.setDividerLocation(580);
        SplitPaneVTGeneral.setDividerSize(2);
        SplitPaneVTGeneral.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        SplitPaneVTGeneral.setPreferredSize(new java.awt.Dimension(900, 856));

        TabbedPaneQueryResult.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        TabbedPaneQueryResult.setPreferredSize(new java.awt.Dimension(900, 650));
        SplitPaneVTGeneral.setLeftComponent(TabbedPaneQueryResult);

        PanelQuery.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        PanelQuery.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        PanelQuery.setMinimumSize(new java.awt.Dimension(104, 40));
        PanelQuery.setPreferredSize(new java.awt.Dimension(1280, 200));

        LabelQQueryOnMetaLib.setText(bundle.getString("ON METADATA LIBRARY : ")); // NOI18N

        ComboBoxFTAvailableMetaLibs.setModel(new javax.swing.DefaultComboBoxModel<>(this.default_metalibs));
        ComboBoxFTAvailableMetaLibs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxFTAvailableMetaLibsItemStateChanged(evt);
            }
        });

        CheckBoxFTCreateNewTab.setText(bundle.getString("CREATE NEW TAB")); // NOI18N

        LabelFTSearch.setText(bundle.getString("SEARCH : ")); // NOI18N

        TextFieldFTSearch.setForeground(new java.awt.Color(204, 204, 204));

        BTNFTSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico/20pix/search.png"))); // NOI18N
        BTNFTSearch.setToolTipText(bundle.getString("FULL-TEXT SEARCH")); // NOI18N
        BTNFTSearch.setFocusable(false);
        BTNFTSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNFTSearchActionPerformed(evt);
            }
        });

        ButtonGroupOnWhere.add(CheckBoxFTOnDB);
        CheckBoxFTOnDB.setText(bundle.getString("ON DATABASE")); // NOI18N

        ButtonGroupOnWhere.add(CheckBoxFTOnES);
        CheckBoxFTOnES.setText(bundle.getString("ON ELASTICSEARCH")); // NOI18N

        javax.swing.GroupLayout PanelQuickQueryLayout = new javax.swing.GroupLayout(PanelQuickQuery);
        PanelQuickQuery.setLayout(PanelQuickQueryLayout);
        PanelQuickQueryLayout.setHorizontalGroup(
            PanelQuickQueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelQuickQueryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelQuickQueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelQuickQueryLayout.createSequentialGroup()
                        .addComponent(LabelQQueryOnMetaLib)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxFTAvailableMetaLibs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CheckBoxFTCreateNewTab))
                    .addGroup(PanelQuickQueryLayout.createSequentialGroup()
                        .addComponent(LabelFTSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldFTSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTNFTSearch))
                    .addComponent(CheckBoxFTOnES)
                    .addComponent(CheckBoxFTOnDB))
                .addContainerGap(408, Short.MAX_VALUE))
        );
        PanelQuickQueryLayout.setVerticalGroup(
            PanelQuickQueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelQuickQueryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelQuickQueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxFTAvailableMetaLibs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelQQueryOnMetaLib)
                    .addComponent(CheckBoxFTCreateNewTab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelQuickQueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelFTSearch)
                    .addComponent(TextFieldFTSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNFTSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CheckBoxFTOnDB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CheckBoxFTOnES)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        PanelQuery.addTab("Full-Text Search", PanelQuickQuery);

        PanelInfoOutput.setPreferredSize(new java.awt.Dimension(896, 686));

        javax.swing.GroupLayout PanelInfoOutputLayout = new javax.swing.GroupLayout(PanelInfoOutput);
        PanelInfoOutput.setLayout(PanelInfoOutputLayout);
        PanelInfoOutputLayout.setHorizontalGroup(
            PanelInfoOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelInfoOutputLayout.setVerticalGroup(
            PanelInfoOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelQuery.addTab(bundle.getString("INFO"), PanelInfoOutput); // NOI18N

        SplitPaneVTGeneral.setRightComponent(PanelQuery);

        javax.swing.GroupLayout PanelSplitCenterLayout = new javax.swing.GroupLayout(PanelSplitCenter);
        PanelSplitCenter.setLayout(PanelSplitCenterLayout);
        PanelSplitCenterLayout.setHorizontalGroup(
            PanelSplitCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SplitPaneVTGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
        );
        PanelSplitCenterLayout.setVerticalGroup(
            PanelSplitCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSplitCenterLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(SplitPaneVTGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE))
        );

        SplitPaneSubRight.setLeftComponent(PanelSplitCenter);

        TabbedPaneSplitRight.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        TabbedPaneSplitRight.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        TabbedPaneSplitRight.setPreferredSize(new java.awt.Dimension(250, 811));

        PanelRightPreview.setPreferredSize(new java.awt.Dimension(250, 807));

        jScrollPane8.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        LabelRightAbstract.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        LabelRightAbstract.setText(bundle.getString("ABSTRACT : ")); // NOI18N

        RightTextPaneAbstract.setText(bundle.getString("NO ABSTRACT")); // NOI18N
        RightTextPaneAbstract.setToolTipText(bundle.getString("YOU CAN COPY THEM")); // NOI18N
        RightTextPaneAbstract.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RightTextPaneAbstractMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(RightTextPaneAbstract);

        LabelRightTags.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        LabelRightTags.setText(bundle.getString("TAGS : ")); // NOI18N

        LabelRightPreview.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        LabelRightPreview.setText(bundle.getString("1ST PREVIEW : ")); // NOI18N

        RightTextPaneTags.setText(bundle.getString("NO TAGS")); // NOI18N
        RightTextPaneTags.setToolTipText(bundle.getString("RIGHT CLICK TO ADD CUSTOM TAGS")); // NOI18N
        RightTextPaneTags.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RightTextPaneTagsMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(RightTextPaneTags);

        RightPreviewLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RightPreviewLabel.setText("<html><h1>Preview</h1><br>Empty</html>");
        RightPreviewLabel.setToolTipText(bundle.getString("RIGHT CLICK TO ENLARGE PREVIEW")); // NOI18N
        RightPreviewLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RightPreviewLabelMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(RightPreviewLabel);

        BTNRightPreviewManager.setText(bundle.getString("ZOOM IN / MORE")); // NOI18N
        BTNRightPreviewManager.setFocusable(false);
        BTNRightPreviewManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRightPreviewManagerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelInfoWrapperLayout = new javax.swing.GroupLayout(PanelInfoWrapper);
        PanelInfoWrapper.setLayout(PanelInfoWrapperLayout);
        PanelInfoWrapperLayout.setHorizontalGroup(
            PanelInfoWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInfoWrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInfoWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane5)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelInfoWrapperLayout.createSequentialGroup()
                        .addGroup(PanelInfoWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LabelRightAbstract, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelRightTags, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelInfoWrapperLayout.createSequentialGroup()
                        .addComponent(LabelRightPreview)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTNRightPreviewManager)))
                .addContainerGap())
        );
        PanelInfoWrapperLayout.setVerticalGroup(
            PanelInfoWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInfoWrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelRightAbstract)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelRightTags)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelInfoWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelRightPreview)
                    .addComponent(BTNRightPreviewManager))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane8.setViewportView(PanelInfoWrapper);

        javax.swing.GroupLayout PanelRightPreviewLayout = new javax.swing.GroupLayout(PanelRightPreview);
        PanelRightPreview.setLayout(PanelRightPreviewLayout);
        PanelRightPreviewLayout.setHorizontalGroup(
            PanelRightPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
        );
        PanelRightPreviewLayout.setVerticalGroup(
            PanelRightPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8)
        );

        jScrollPane8.getVerticalScrollBar().setUnitIncrement(16);

        TabbedPaneSplitRight.addTab("Preview", PanelRightPreview);
        TabbedPaneSplitRight.setTabComponentAt(0, registerVerticalTab(bundle.getString("VERTICAL PREVIEW"), true));

        RightLabelMetadataDoc.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        RightLabelMetadataDoc.setText(bundle.getString("METADATA DOCUMENT : ")); // NOI18N

        RightTextPaneMetaDoc.setEditable(false);
        RightTextPaneMetaDoc.setText(bundle.getString("NO METADATA")); // NOI18N
        jScrollPane7.setViewportView(RightTextPaneMetaDoc);

        javax.swing.GroupLayout RightDetailsPaneLayout = new javax.swing.GroupLayout(RightDetailsPane);
        RightDetailsPane.setLayout(RightDetailsPaneLayout);
        RightDetailsPaneLayout.setHorizontalGroup(
            RightDetailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightDetailsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RightDetailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightDetailsPaneLayout.createSequentialGroup()
                        .addComponent(RightLabelMetadataDoc)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );
        RightDetailsPaneLayout.setVerticalGroup(
            RightDetailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightDetailsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RightLabelMetadataDoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );

        TabbedPaneSplitRight.addTab("Details", RightDetailsPane);
        TabbedPaneSplitRight.setTabComponentAt(1, registerVerticalTab(bundle.getString("VERTICAL DETAILS"), true));

        SplitPaneSubRight.setRightComponent(TabbedPaneSplitRight);

        MainSplitPane.setRightComponent(SplitPaneSubRight);

        BottomPanel.setPreferredSize(new java.awt.Dimension(100, 35));
        BottomPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        BTNRunningTasks.setText(bundle.getString("RUNNING TASKS")); // NOI18N
        BTNRunningTasks.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BTNRunningTasks.setContentAreaFilled(false);
        BTNRunningTasks.setFocusable(false);
        BTNRunningTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRunningTasksActionPerformed(evt);
            }
        });
        BottomPanel.add(BTNRunningTasks);

        FileMenu.setText(bundle.getString("FILE")); // NOI18N

        MenuItemNewMetaLib.setText(bundle.getString("ADD NEW METADATA LIB")); // NOI18N
        MenuItemNewMetaLib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemNewMetaLibActionPerformed(evt);
            }
        });
        FileMenu.add(MenuItemNewMetaLib);

        MenuItemImportMeta.setText(bundle.getString("IMPORT METADATA")); // NOI18N
        MenuItemImportMeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemImportMetaActionPerformed(evt);
            }
        });
        FileMenu.add(MenuItemImportMeta);
        FileMenu.add(jSeparator1);

        MenuItemAddDatabase.setText(bundle.getString("ADD NEW DATABASE")); // NOI18N
        MenuItemAddDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemAddDatabaseActionPerformed(evt);
            }
        });
        FileMenu.add(MenuItemAddDatabase);

        MenuItemAddIndex.setText(bundle.getString("ADD NEW ES INDEX")); // NOI18N
        MenuItemAddIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemAddIndexActionPerformed(evt);
            }
        });
        FileMenu.add(MenuItemAddIndex);
        FileMenu.add(jSeparator4);

        MenuItemSettings.setText(bundle.getString("SETTINGS")); // NOI18N
        MenuItemSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemSettingsActionPerformed(evt);
            }
        });
        FileMenu.add(MenuItemSettings);
        FileMenu.add(jSeparator5);

        MenuItemExit.setText(bundle.getString("EXIT")); // NOI18N
        MenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemExitActionPerformed(evt);
            }
        });
        FileMenu.add(MenuItemExit);

        MainMenuBar.add(FileMenu);

        EditMenu.setText(bundle.getString("EDIT")); // NOI18N

        MenuExportTable.setText(bundle.getString("EXPORT TABLE")); // NOI18N

        MenuItemExportToCSV.setText(bundle.getString("TO CSV")); // NOI18N
        MenuItemExportToCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemExportToCSVActionPerformed(evt);
            }
        });
        MenuExportTable.add(MenuItemExportToCSV);

        EditMenu.add(MenuExportTable);

        MainMenuBar.add(EditMenu);

        ToolsMenu.setText(bundle.getString("TOOLS")); // NOI18N

        MenuItemPluginsMenu.setText(bundle.getString("PLUGINS MENU")); // NOI18N
        MenuItemPluginsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemPluginsMenuActionPerformed(evt);
            }
        });
        ToolsMenu.add(MenuItemPluginsMenu);

        MenuES.setText(bundle.getString("ELASTICSEARCH")); // NOI18N

        MenuItemCheckESConn.setText(bundle.getString("CHECK CONNECTION")); // NOI18N
        MenuItemCheckESConn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemCheckESConnActionPerformed(evt);
            }
        });
        MenuES.add(MenuItemCheckESConn);

        MenuItemCreateMapping.setText(bundle.getString("CREATE MAPPING")); // NOI18N
        MenuItemCreateMapping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemCreateMappingActionPerformed(evt);
            }
        });
        MenuES.add(MenuItemCreateMapping);

        ToolsMenu.add(MenuES);

        MainMenuBar.add(ToolsMenu);

        HelpMenu.setText(bundle.getString("HELP")); // NOI18N

        MenuItemAbout.setIcon(new ImageIcon(new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB)));
        MenuItemAbout.setText(bundle.getString("ABOUT")); // NOI18N
        MenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemAboutActionPerformed(evt);
            }
        });
        HelpMenu.add(MenuItemAbout);

        MainMenuBar.add(HelpMenu);

        setJMenuBar(MainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1520, Short.MAX_VALUE)
            .addComponent(ToolBarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1520, Short.MAX_VALUE)
            .addComponent(BottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ToolBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(MainSplitPane)
                .addGap(2, 2, 2)
                .addComponent(BottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxMLOptionsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxMLOptionsItemStateChanged
        CardLayout cl = (CardLayout) (PanelMetaLibsTreeContainer.getLayout());
        cl.show(PanelMetaLibsTreeContainer, (String) evt.getItem());
    }//GEN-LAST:event_ComboBoxMLOptionsItemStateChanged
    protected javax.swing.JMenu EditMenu;
    protected javax.swing.JMenu FileMenu;
    protected javax.swing.JMenu HelpMenu;
    private javax.swing.JLabel LabelFTSearch;
    private javax.swing.JLabel LabelQQueryOnMetaLib;
    private javax.swing.JLabel LabelRightAbstract;
    private javax.swing.JLabel LabelRightPreview;
    private javax.swing.JLabel LabelRightTags;
    protected javax.swing.JMenuBar MainMenuBar;
    protected javax.swing.JSplitPane MainSplitPane;
    protected javax.swing.JToolBar MainToolBar;
    protected javax.swing.JMenu MenuES;
    protected javax.swing.JMenu MenuExportTable;
    protected javax.swing.JMenuItem MenuItemAbout;
    protected javax.swing.JMenuItem MenuItemAddDatabase;
    protected javax.swing.JMenuItem MenuItemAddIndex;
    protected javax.swing.JMenuItem MenuItemCheckESConn;
    protected javax.swing.JMenuItem MenuItemCreateMapping;
    protected javax.swing.JMenuItem MenuItemExit;
    protected javax.swing.JMenuItem MenuItemExportToCSV;
    protected javax.swing.JMenuItem MenuItemImportMeta;
    protected javax.swing.JMenuItem MenuItemNewMetaLib;
    protected javax.swing.JMenuItem MenuItemPluginsMenu;
    protected javax.swing.JMenuItem MenuItemSettings;
    protected javax.swing.JMenuItem PMAbstractCopyAll;
    protected javax.swing.JMenuItem PMAbstractCopySelected;
    protected javax.swing.JMenuItem PMDBPropertiesEdit;
    protected javax.swing.JMenuItem PMEditAbstract;
    protected javax.swing.JMenuItem PMExportMetaDocToFile;
    protected javax.swing.JMenuItem PMImportDataToDB;
    protected javax.swing.JMenuItem PMIndexPropertiesEdit;
    protected javax.swing.JMenuItem PMMetaLibProperties;
    protected javax.swing.JMenuItem PMRemoveDatabase;
    protected javax.swing.JMenuItem PMRemoveIndex;
    protected javax.swing.JMenuItem PMRemoveMetaLib;
    protected javax.swing.JMenuItem PMRenameDatabase;
    protected javax.swing.JMenuItem PMRenameIndices;
    protected javax.swing.JMenuItem PMRenameMetaLib;
    protected javax.swing.JMenuItem PMSyncToES;
    protected javax.swing.JMenuItem PMTagsAddCustom;
    protected javax.swing.JMenuItem PMTagsCopyAll;
    protected javax.swing.JMenuItem PMTagsCopySelected;
    protected javax.swing.JMenuItem PMViewEnlarge;
    protected javax.swing.JPanel PanelInfoOutput;
    protected javax.swing.JPanel PanelInfoWrapper;
    protected javax.swing.JPanel PanelMetaLibs;

    private void BTNExpandTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNExpandTreeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNExpandTreeActionPerformed
    protected javax.swing.JTabbedPane PanelQuery;
    protected javax.swing.JPanel PanelQuickQuery;
    protected javax.swing.JPanel PanelResources;

    private void BTNCollapseTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCollapseTreeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNCollapseTreeActionPerformed
    protected javax.swing.JPanel PanelRightPreview;
    protected javax.swing.JPanel PanelSplitCenter;
    protected javax.swing.JPopupMenu PopupMenuAbstract;
    protected javax.swing.JPopupMenu PopupMenuDatabasesTree;
    protected javax.swing.JPopupMenu PopupMenuIndicesTree;
    protected javax.swing.JPopupMenu PopupMenuMetaDoc;
    protected javax.swing.JPopupMenu PopupMenuMetaLibsTree;
    protected javax.swing.JPopupMenu PopupMenuPreview;
    protected javax.swing.JPopupMenu PopupMenuTags;
    protected javax.swing.JPanel RightDetailsPane;
    private javax.swing.JLabel RightLabelMetadataDoc;
    protected javax.swing.JLabel RightPreviewLabel;
    protected javax.swing.JTextPane RightTextPaneAbstract;
    protected javax.swing.JTextPane RightTextPaneMetaDoc;
    protected javax.swing.JTextPane RightTextPaneTags;
    protected javax.swing.JTabbedPane SplitPaneRightTabbedPane;
    protected javax.swing.JSplitPane SplitPaneSubRight;
    protected javax.swing.JSplitPane SplitPaneVTGeneral;
    protected javax.swing.JTabbedPane TabbedPaneQueryResult;
    protected javax.swing.JTabbedPane TabbedPaneSplitRight;
    protected javax.swing.JTextField TextFieldFTSearch;

    private void ComboBoxRESOptionsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxRESOptionsItemStateChanged
        CardLayout cl = (CardLayout) (PanelResourcesTreeContainer.getLayout());
        cl.show(PanelResourcesTreeContainer, (String) evt.getItem());
    }//GEN-LAST:event_ComboBoxRESOptionsItemStateChanged
    protected javax.swing.JPanel ToolBarPanel;

    private void BTNExpandTree1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNExpandTree1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNExpandTree1ActionPerformed
    protected javax.swing.JMenu ToolsMenu;
    protected javax.swing.JTree TreeDatabases;
    protected javax.swing.JTree TreeIndices;
    protected javax.swing.JTree TreeMetadataLibs;
    private javax.swing.Box.Filler filler1;

    private void BTNCollapseTree1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCollapseTree1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNCollapseTree1ActionPerformed
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JToolBar.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    // End of variables declaration//GEN-END:variables
}
