package io.github.hochikong.ktmeta.swingui.controller

import com.formdev.flatlaf.FlatIntelliJLaf
import io.github.hochikong.ktmeta.common.SupportedDBs
import io.github.hochikong.ktmeta.dao.impl.DBResourcePool
import io.github.hochikong.ktmeta.dao.impl.ESResourcePool
import io.github.hochikong.ktmeta.dao.impl.MLResourcePool
import io.github.hochikong.ktmeta.dao.impl.MPResourcePool
import io.github.hochikong.ktmeta.swingui.codegen.impKtmetaMainFrame
import io.github.hochikong.ktmeta.swingui.controller.dialogs.AddDatabaseWizard
import io.github.hochikong.ktmeta.swingui.controller.dialogs.AddIndexWizard
import io.github.hochikong.ktmeta.swingui.controller.dialogs.AddMetaLibWizard
import java.awt.event.ActionEvent
import java.lang.management.ManagementFactory
import java.lang.management.OperatingSystemMXBean
import javax.swing.JOptionPane
import javax.swing.SwingUtilities
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel
import kotlin.system.exitProcess


class MainScene : impKtmetaMainFrame() {
    /**
     * Update MainFrame's metadata libraries tree on the left
     * */
    fun fullUpdateMetaLibsTree(libs: List<String>) {
        val model = this.TreeMetadataLibs.model as DefaultTreeModel
        val myNode = DefaultMutableTreeNode("My MetaLibs")
        for (name in libs) {
            myNode.add(DefaultMutableTreeNode(name))
        }
        SwingUtilities.invokeLater {
            model.setRoot(myNode)
            model.reload(model.root as DefaultMutableTreeNode)
        }
    }

    fun fullUpdateDBTree(libs: List<String>) {
        val model = this.TreeDatabases.model as DefaultTreeModel
        val myNode = DefaultMutableTreeNode("My Databases")
        for (name in libs) {
            myNode.add(DefaultMutableTreeNode(name))
        }
        SwingUtilities.invokeLater {
            model.setRoot(myNode)
            model.reload(model.root as DefaultMutableTreeNode)
        }
    }

    fun fullUpdateIndexTree(indices: List<String>) {
        val model = this.TreeIndices.model as DefaultTreeModel
        val myNode = DefaultMutableTreeNode("My Indices")
        for (name in indices) {
            myNode.add(DefaultMutableTreeNode(name))
        }
        SwingUtilities.invokeLater {
            model.setRoot(myNode)
            model.reload(model.root as DefaultMutableTreeNode)
        }
    }

    private fun updateMetaLibsTreeWithQuery() {
        val mList = MLResourcePool.getAllRecords()
        this.fullUpdateMetaLibsTree(mList.map { it.lib_name }.toList())
    }

    /**
     * When click about, show message.
     * */
    override fun impMenuItemAboutActionPerformed(evt: ActionEvent?) {
        val system: OperatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean()
        val runtime = ManagementFactory.getRuntimeMXBean()
        val info = "OS: ${system.name} \nArch: ${system.arch} " +
                "\nVM: ${runtime.vmName} \nVendor: ${runtime.vmVendor}" +
                "\nSpec: ${runtime.specVersion} \nVersion: ${runtime.vmVersion}"
        JOptionPane.showMessageDialog(
            this,
            "KtMeta, a metadata management platform \nckhoidea@hotmail.com \n\n$info",
            "About",
            JOptionPane.INFORMATION_MESSAGE
        )
    }

    /**
     * Add a new database resource
     * */
    override fun impMenuItemAddDatabaseActionPerformed(evt: ActionEvent?) {
        val dialog = AddDatabaseWizard(this, SupportedDBs.values().map { it.identity }.toTypedArray())
        dialog.setLocationRelativeTo(null)
        dialog.isVisible = true

        // update tree
        val dbList = DBResourcePool.getAllRecords()
        fullUpdateDBTree(dbList.map { it.db_name }.toList())
    }

    override fun impMenuItemAddIndexActionPerformed(evt: ActionEvent?) {
        val dialog = AddIndexWizard(this)
        dialog.setLocationRelativeTo(null)
        dialog.isVisible = true

        // update tree
        val indexList = ESResourcePool.getAllRecords()
        fullUpdateIndexTree(indexList.map { it.index_name }.toList())
    }

    override fun impMenuItemNewMetaLibActionPerformed(evt: ActionEvent?) {
        var allDBs = DBResourcePool.getAllRecords().map { it.db_name }.toTypedArray()
        if (allDBs.isEmpty()) {
            allDBs = arrayOf("None")
        }
        var allIndices = ESResourcePool.getAllRecords().map { it.index_name }.toTypedArray()
        if (allIndices.isEmpty()) {
            allIndices = arrayOf("None")
        }
        var allPlugins = MPResourcePool.getAllRecords().map { it.plugin_name }.toTypedArray()
        if (allPlugins.isEmpty()) {
            allPlugins = arrayOf("None")
        }
        val dialog = AddMetaLibWizard(this, allPlugins, allDBs, allIndices)
        dialog.setLocationRelativeTo(null)
        dialog.isVisible = true

        val mlTree = MLResourcePool.getAllRecords()
        fullUpdateMetaLibsTree(mlTree.map { it.lib_name }.toList())
    }

    override fun impMenuItemExitActionPerformed(evt: ActionEvent?) {
        exitProcess(0)
    }
}

fun main() {
    FlatIntelliJLaf.install()
    val x = MainScene()
    x.setLocationRelativeTo(null)
    x.isVisible = true
}