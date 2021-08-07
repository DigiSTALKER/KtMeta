package io.github.hochikong.ktmeta.swingui.controller

import com.formdev.flatlaf.FlatIntelliJLaf
import io.github.hochikong.ktmeta.common.SupportedDBs
import io.github.hochikong.ktmeta.dao.impl.MLResourcePool
import io.github.hochikong.ktmeta.swingui.codegen.impKtmetaMainFrame
import java.awt.event.ActionEvent
import java.lang.management.ManagementFactory
import java.lang.management.OperatingSystemMXBean
import javax.swing.JOptionPane
import javax.swing.SwingUtilities
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel
import kotlin.system.exitProcess
import io.github.hochikong.ktmeta.swingui.controller.dialogs.AddDatabaseWizard


class MainScene : impKtmetaMainFrame() {
    /**
     * Update MainFrame's metadata libraries tree on the left
     * */
    fun fullUpdateMetaLibsTree(libs: List<String>){
        val model = this.TreeMetadataLibs.model as DefaultTreeModel
        val myNode = DefaultMutableTreeNode("My MetaLibs")
        for (name in libs){
            myNode.add(DefaultMutableTreeNode(name))
        }
        SwingUtilities.invokeLater {
            model.setRoot(myNode)
            model.reload(model.root as DefaultMutableTreeNode)
        }
    }

    private fun updateMetaLibsTreeWithQuery(){
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
    }

    override fun impMenuItemNewMetaLibActionPerformed(evt: ActionEvent?) {

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