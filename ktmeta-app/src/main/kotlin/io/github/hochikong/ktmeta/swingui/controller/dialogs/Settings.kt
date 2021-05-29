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


import io.github.hochikong.ktmeta.swingui.controller.SettingConfigs
import io.github.hochikong.ktmeta.swingui.dialogs.codegen.impSettings
import java.awt.Frame
import java.awt.GraphicsEnvironment
import java.awt.event.ActionEvent
import java.awt.event.ItemEvent
import javax.swing.DefaultComboBoxModel
import javax.swing.event.ChangeEvent

//import javax.swing.JFrame
//import com.formdev.flatlaf.FlatIntelliJLaf

class Settings(
    parent: Frame,
    supportedLanguages: Array<String>,
    supportedThemes: Array<String>,
    pluginsData: Array<Array<Any>>,
    pluginsDesc: Array<String>
) : impSettings(parent, true, supportedLanguages, supportedThemes, pluginsData, pluginsDesc) {
    private val gENV = GraphicsEnvironment.getLocalGraphicsEnvironment()

    private var langSelected = supportedLanguages[0]
    private var themeSelected = supportedThemes[0]
    private var enableCustomFont = false
    private var customFont = ""
    private var customFontStyle = ""
    private var customFontSize = 0

    // each plugin data contains three elements: plugin name, plugin enable or not, plugin unique id
    // private var pluginMapping: Map<String, String> = this.pluginsData.associate { it[2] as String to it[1].toString() }

    private var commitCfg: SettingConfigs? = null

    init {
        this.ComboBoxFontSelector.model = DefaultComboBoxModel(gENV.availableFontFamilyNames)
        this.BTNUpgradePlugin.isEnabled = false
    }

    // change language
    override fun impComboBoxLanguageItemStateChanged(evt: ItemEvent?) {
        if (evt != null) {
            if (evt.stateChange == ItemEvent.SELECTED) {
                this.langSelected = evt.item as String
            }
        }
    }

    // change theme
    override fun impComboBoxThemeSelectorItemStateChanged(evt: ItemEvent?) {
        if (evt != null) {
            if (evt.stateChange == ItemEvent.SELECTED) {
                this.themeSelected = evt.item as String
            }
        }
    }

    // custom font
    override fun impCheckBoxEnableCustomFontItemStateChanged(evt: ItemEvent?) {
        if (this.CheckBoxEnableCustomFont.isSelected) {
            this.ComboBoxFontSelector.isEnabled = true
            this.SpinnerFontSize.isEnabled = true
            this.ComboBoxFontStyleSelector.isEnabled = true
            this.enableCustomFont = true
        } else {
            this.ComboBoxFontSelector.isEnabled = false
            this.SpinnerFontSize.isEnabled = false
            this.ComboBoxFontStyleSelector.isEnabled = false
            this.enableCustomFont = false
        }
    }

    override fun impComboBoxFontSelectorItemStateChanged(evt: ItemEvent?) {
        if (evt != null) {
            if (evt.stateChange == ItemEvent.SELECTED) {
                this.customFont = evt.item as String
                this.refreshFontPreview()
            }
        }
    }

    override fun impComboBoxFontStyleSelectorItemStateChanged(evt: ItemEvent?) {
        if (evt != null) {
            if (evt.stateChange == ItemEvent.SELECTED) {
                this.customFontStyle = evt.item as String
                this.refreshFontPreview()
            }
        }
    }

    override fun impSpinnerFontSizeStateChanged(evt: ChangeEvent?) {
        if (evt != null) {
            this.customFontSize = SpinnerFontSize.value as Int
            this.refreshFontPreview()
        }
    }

    // plugins relative
    override fun impBTNRegistPluginActionPerformed(evt: ActionEvent?) {
        // TODO
    }

    override fun impBTNUpgradePluginActionPerformed(evt: ActionEvent?) {
        throw NotImplementedError("Plugin upgrade is not available")
    }

    override fun impBTNRemovePluginActionPerformed(evt: ActionEvent?) {
        // TODO
    }

    // commit
    override fun impBTNOKActionPerformed(evt: ActionEvent?) {
        this.commitCfg = SettingConfigs(
            langSelected = this.langSelected,
            themeSelected = this.themeSelected,
            enableCustomFont = this.enableCustomFont,
            customFont = this.customFont,
            customFontStyle = this.customFontStyle,
            customFontSize = this.customFontSize,
            pluginMapping = this.pluginsModel.readData().associate { it[2] as String to it[1].toString() }
        )
//        println(commitCfg)
    }

    override fun impBTNCancelActionPerformed(evt: ActionEvent?) {
        this.commitCfg = null
    }

    override fun impBTNApplyActionPerformed(evt: ActionEvent?) {
        this.commitCfg = SettingConfigs(
            langSelected = this.langSelected,
            themeSelected = this.themeSelected,
            enableCustomFont = this.enableCustomFont,
            customFont = this.customFont,
            customFontStyle = this.customFontStyle,
            customFontSize = this.customFontSize,
            pluginMapping = this.pluginsModel.readData().associate { it[2] as String to it[1].toString() }
        )
//        println(commitCfg)
    }
}

//fun main() {
//    FlatIntelliJLaf.install()
//    val dialog = Settings(
//        JFrame(),
//        arrayOf("chinese", "eng"),
//        arrayOf(
//            "FlatDarculaLaf",
//            "FlatIntelliJLaf",
//        ),
//        arrayOf(arrayOf("plugin1", true, "id1111"), arrayOf("xs plugin2", false, "id2222")),
//        arrayOf("<html>First line<br>Second line</html>", "<html>SECOND line<br>Third line</html>")
//    )
//    dialog.setLocationRelativeTo(null)
//    dialog.isVisible = true
//}