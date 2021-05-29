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


import io.github.hochikong.ktmeta.swingui.controller.doWhenClickOnTextField
import io.github.hochikong.ktmeta.swingui.dialogs.codegen.impAddMetaLibWizard
import java.awt.Frame
import java.awt.event.ActionEvent
import java.awt.event.FocusEvent
import java.awt.event.ItemEvent
import javax.swing.JTextField

//import javax.swing.JFrame
//import com.formdev.flatlaf.FlatIntelliJLaf


class AddMetaLibWizard(
    parent: Frame,
    SupportedPluginsList: Array<String>,
    AvailableDBsList: Array<String>,
    AvailableIndicesList: Array<String>
) : impAddMetaLibWizard(
    parent,
    true,
    SupportedPluginsList,
    AvailableDBsList,
    AvailableIndicesList
) {
    private val componentRegister = mapOf<String, JTextField>(
        "FieldAlias" to this.FieldAlias,
        "FieldDescription" to this.FieldDescription
    )

    private var currentPlugin = this.supportedPlugins[0]
    private var currentDBRes = this.availableDatabases[0]
    private var currentIndexRes = this.availableIndices[0]

    override fun impBTNCancelAddMetaLibActionPerformed(evt: ActionEvent?) {
        // TODO
    }

    override fun impBTNOKAddMetaLibActionPerformed(evt: ActionEvent?) {
        // TODO
    }

    override fun impComboBoxAvailablePluginsItemStateChanged(evt: ItemEvent?) {
        if (evt != null) {
            if (evt.stateChange == ItemEvent.SELECTED) {
                if (this.ComboBoxAvailablePlugins.selectedItem is String) {
                    this.currentPlugin = this.ComboBoxAvailablePlugins.selectedItem as String
                    println(this.currentPlugin)
                }
            }
        }
    }

    override fun impComboBoxAvailableDBsItemStateChanged(evt: ItemEvent?) {
        if (evt != null) {
            if (evt.stateChange == ItemEvent.SELECTED) {
                if (this.ComboBoxAvailableDBs.selectedItem is String) {
                    this.currentDBRes = this.ComboBoxAvailableDBs.selectedItem as String
                    println(this.currentDBRes)
                }
            }
        }
    }

    override fun impComboBoxAvailableIndicesItemStateChanged(evt: ItemEvent?) {
        if (evt != null) {
            if (evt.stateChange == ItemEvent.SELECTED) {
                if (this.ComboBoxAvailableIndices.selectedItem is String) {
                    this.currentIndexRes = this.ComboBoxAvailableIndices.selectedItem as String
                    println(this.currentIndexRes)
                }
            }
        }
    }

    override fun impFieldAliasFocusGained(evt: FocusEvent?) {
        doWhenClickOnTextField(this.componentRegister, "FieldAlias")
    }

    override fun impFieldDescriptionFocusGained(evt: FocusEvent?) {
        doWhenClickOnTextField(this.componentRegister, "FieldDescription")
    }

    fun initFocus(){
        BTNCancelAddMetaLib.requestFocusInWindow()
    }
}

//fun main() {
//    FlatIntelliJLaf.install()
//    val dialog =
//        AddMetaLibWizard(JFrame(), arrayOf("p1", "p2", "p3"), arrayOf("d1", "d2", "d3"), arrayOf("i1", "i2", "i3"))
//    dialog.initFocus()
//    dialog.setLocationRelativeTo(null)
//    dialog.isVisible = true
//}