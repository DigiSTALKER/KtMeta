/*
 * Copyright 2021 Hochikong
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