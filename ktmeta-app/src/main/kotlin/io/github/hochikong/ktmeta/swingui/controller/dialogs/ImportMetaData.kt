package io.github.hochikong.ktmeta.swingui.controller.dialogs

import io.github.hochikong.ktmeta.swingui.dialogs.codegen.impImportMetaData
import java.awt.Frame
import java.awt.event.ActionEvent
import java.awt.event.ItemEvent

//import javax.swing.JFrame
//import com.formdev.flatlaf.FlatIntelliJLaf

class ImportMetaData(parent: Frame, metalibs: Array<String>) : impImportMetaData(parent, true, metalibs) {
    private var selectedMetaDataLib = metaLibs[0]

    override fun impComboBoxTargetMetaLibItemStateChanged(evt: ItemEvent?) {
        if (evt != null) {
            if (evt.stateChange == ItemEvent.SELECTED) {
                this.selectedMetaDataLib = metaLibs[ComboBoxTargetMetaLib.selectedIndex]
                println(selectedMetaDataLib)
            }
        }
    }


    override fun impBTNImportFromDiskActionPerformed(evt: ActionEvent?) {
        // TODO
    }
}

//fun main() {
//    FlatIntelliJLaf.install()
//    val dialog = ImportMetaData(JFrame(), arrayOf("meta1", "meta2", "meta3"))
//    dialog.setLocationRelativeTo(null)
//    dialog.isVisible = true
//}