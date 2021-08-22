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

import io.github.hochikong.ktmeta.common.Encryption
import io.github.hochikong.ktmeta.dao.DBResourceRecord
import io.github.hochikong.ktmeta.dao.impl.DBResourcePool
import io.github.hochikong.ktmeta.swingui.controller.doWhenClickOnTextField
import io.github.hochikong.ktmeta.swingui.dialogs.codegen.impAddDatabaseWizard
import kotlinx.coroutines.*
import java.awt.Frame
import java.awt.event.ActionEvent
import java.awt.event.FocusEvent
import java.awt.event.ItemEvent
import java.awt.event.MouseEvent
import java.sql.DriverManager
import javax.swing.JOptionPane
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
        try {
            var passwd = ""
            var savePasswd = 0
            if (this.FieldPassword.text.isNotEmpty()) {
                passwd = Encryption.encrypt(this.FieldPassword.text)
                savePasswd = 1
            }


            val record = DBResourceRecord(
                db_type = this.currentDBMSSelected,
                db_name = this.FieldName.text,
                db_desc = this.FieldDescription.text,
                db_url = this.FieldJDBCURL.text,
                user = this.FieldUsername.text,
                password = passwd,
                save_passwd = savePasswd
            )
            DBResourcePool.insertRecord(record)

            this.dispose()
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(
                this,
                e,
                "Insert error",
                JOptionPane.ERROR_MESSAGE
            )
        }
    }

    /**
     * Discard
     * */
    override fun impBTNCancelAddDBActionPerformed(evt: ActionEvent?) {
        this.dispose()
    }

    /**
     * Change color and select
     * */
    override fun impFieldNameFocusGained(evt: FocusEvent?) {
        doWhenClickOnTextField(this.componentRegister, "FieldName")
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
        val handleException = CoroutineExceptionHandler { _, throwable ->
            //可以捕获到launch中抛出的异常
            this.PBarTestConnection.isIndeterminate = false
            println("CoroutineExceptionHandler catch $throwable")
            JOptionPane.showMessageDialog(
                this,
                throwable,
                "Test error",
                JOptionPane.ERROR_MESSAGE
            )
        }

        CoroutineScope(Dispatchers.IO).launch(handleException) {
            this@AddDatabaseWizard.PBarTestConnection.isIndeterminate = true
            if (this@AddDatabaseWizard.currentDBMSSelected == "Sqlite") {
                try {
                    testConnection(
                        this@AddDatabaseWizard.currentDBMSSelected,
                        this@AddDatabaseWizard.FieldJDBCURL.text,
                        "",
                        ""
                    )
                    JOptionPane.showMessageDialog(
                        this@AddDatabaseWizard,
                        "Connection established",
                        "Test OK",
                        JOptionPane.INFORMATION_MESSAGE
                    )
                } catch (e: Exception) {
                    JOptionPane.showMessageDialog(
                        this@AddDatabaseWizard,
                        e,
                        "Test error",
                        JOptionPane.ERROR_MESSAGE
                    )
                }
            } else if (this@AddDatabaseWizard.currentDBMSSelected == "Postgresql") {
                try {
                    testConnection(
                        this@AddDatabaseWizard.currentDBMSSelected,
                        this@AddDatabaseWizard.FieldJDBCURL.text,
                        this@AddDatabaseWizard.FieldUsername.text,
                        this@AddDatabaseWizard.FieldPassword.text
                    )
                    JOptionPane.showMessageDialog(
                        this@AddDatabaseWizard,
                        "Connection established",
                        "Test OK",
                        JOptionPane.INFORMATION_MESSAGE
                    )
                } catch (e: Exception) {
                    JOptionPane.showMessageDialog(
                        this@AddDatabaseWizard,
                        e,
                        "Test error",
                        JOptionPane.ERROR_MESSAGE
                    )
                }
            }
            this@AddDatabaseWizard.PBarTestConnection.isIndeterminate = false
        }
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

    fun initFocus() {
        BTNCancelAddDB.requestFocusInWindow()
    }

    private fun testConnection(DBMS: String, url: String, user: String, password: String): Boolean {
        return if (user.isNotEmpty() && password.isNotEmpty()) {
            val con = DriverManager.getConnection(url, user, password)
            true
        } else if (user.isEmpty() && password.isEmpty()) {
            val con = DriverManager.getConnection(url)
            false
        } else {
            throw AssertionError("Username should provided with password, or no username no password.")
        }
    }
}


//fun main() {
//    FlatIntelliJLaf.install()
//    val dialog = AddDatabaseWizard(JFrame(), arrayOf("SQLite", "PostgreSQL"))
//    dialog.isVisible = true
//}