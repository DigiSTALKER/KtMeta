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
import io.github.hochikong.ktmeta.swingui.dialogs.codegen.impAddDatabaseWizard
import java.awt.Frame
import java.awt.event.ActionEvent
import java.awt.event.FocusEvent
import java.awt.event.ItemEvent
import java.awt.event.MouseEvent
import javax.swing.JTextField

//import com.formdev.flatlaf.FlatIntelliJLaf
//import javax.swing.JFrame


class AddDatabaseWizard(parent: Frame, dbList: Array<String>) : impAddDatabaseWizard(parent, true, dbList) {
    private val componentRegister = mapOf<String, JTextField>(
        "FieldName" to this.FieldName,
        "FieldDescription" to this.FieldDescription,
        "FieldJDBCURL" to this.FieldJDBCURL,
        "FieldUsername" to this.FieldUsername,
    )

    private var currentDBMSSelected = this.supportedDatabaseList[0]

    /**
     * Commit
     * */
    override fun impBTNOKAddDBActionPerformed(evt: ActionEvent?) {
        // TODO
    }

    /**
     * Discard
     * */
    override fun impBTNCancelAddDBActionPerformed(evt: ActionEvent?) {
        // TODO
    }

    /**
     * Change color and select
     * */
    override fun impFieldNameFocusGained(evt: FocusEvent?) {
        doWhenClickOnTextField(this.componentRegister,"FieldName")
    }

    override fun impFieldDescriptionFocusGained(evt: FocusEvent?) {
        doWhenClickOnTextField(this.componentRegister, "FieldDescription")
    }

    override fun impFieldJDBCURLFocusGained(evt: FocusEvent?) {
        doWhenClickOnTextField(this.componentRegister, "FieldJDBCURL")
    }

    override fun impFieldUsernameFocusGained(evt: FocusEvent?) {
        doWhenClickOnTextField(this.componentRegister, "FieldUsername")
    }

    override fun impFieldPasswordMouseClicked(evt: MouseEvent?) {
        this.FieldPassword.selectAll()
    }

    override fun impBTNTestConnActionPerformed(evt: ActionEvent?) {
        this.PBarTestConnection.isIndeterminate = true
        // TODO
    }

    override fun impComboBoxAvailableDBMSItemStateChanged(evt: ItemEvent?) {
        if (evt != null) {
            if (evt.stateChange == ItemEvent.SELECTED) {
                if (this.ComboBoxAvailableDBMS.selectedItem is String) {
                    this.currentDBMSSelected = this.ComboBoxAvailableDBMS.selectedItem as String
                }
            }
        }
    }

    fun initFocus(){
        BTNCancelAddDB.requestFocusInWindow()
    }
}


//fun main() {
//    FlatIntelliJLaf.install()
//    val dialog = AddDatabaseWizard(JFrame(), arrayOf("SQLite", "PostgreSQL"))
//    dialog.isVisible = true
//}