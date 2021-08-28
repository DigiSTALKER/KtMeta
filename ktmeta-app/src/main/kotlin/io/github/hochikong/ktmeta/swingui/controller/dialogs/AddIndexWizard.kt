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


import io.github.hochikong.ktmeta.dao.ESResourceRecord
import io.github.hochikong.ktmeta.dao.impl.ESResourcePool
import io.github.hochikong.ktmeta.swingui.controller.doWhenClickOnTextField
import io.github.hochikong.ktmeta.swingui.dialogs.codegen.impAddIndexWizard
import java.awt.Frame
import java.awt.event.ActionEvent
import java.awt.event.FocusEvent
import javax.swing.JOptionPane
import javax.swing.JTextField

//import com.formdev.flatlaf.FlatIntelliJLaf
//import javax.swing.JFrame

class AddIndexWizard(parent: Frame) : impAddIndexWizard(parent, true) {
    private val componentRegister = mapOf<String, JTextField>(
        "FieldIndexName" to this.FieldIndexName,
        "FieldIndexDesc" to this.FieldIndexDesc,
        "FieldIndexURL" to this.FieldIndexURL,
    )

    override fun impBTNIndexTestConnActionPerformed(evt: ActionEvent?) {
        this.ProgressBarTestConn.isIndeterminate = true
        // TODO
    }

    override fun impBTNCancelAddIndexActionPerformed(evt: ActionEvent?) {
        this.dispose()
    }

    override fun impBTNOKAddIndexActionPerformed(evt: ActionEvent?) {
        try {
            when{
                this.FieldIndexName.text.isEmpty() -> {
                    JOptionPane.showMessageDialog(
                        this,
                        "Index name can't be empty",
                        "Insert error",
                        JOptionPane.ERROR_MESSAGE
                    )
                    return
                }

                this.FieldIndexURL.text.isEmpty() -> {
                    JOptionPane.showMessageDialog(
                        this,
                        "Index URL can't be empty",
                        "Insert error",
                        JOptionPane.ERROR_MESSAGE
                    )
                    return
                }
            }


            val record = ESResourceRecord(
                index_name = this.FieldIndexName.text,
                index_desc = this.FieldIndexDesc.text,
                index_url = this.FieldIndexURL.text
            )
            ESResourcePool.insertRecord(record)

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

    override fun impFieldIndexNameFocusGained(evt: FocusEvent?) {
        doWhenClickOnTextField(this.componentRegister, "FieldIndexName")
    }

    override fun impFieldIndexDescFocusGained(evt: FocusEvent?) {
        doWhenClickOnTextField(this.componentRegister, "FieldIndexDesc")
    }

    override fun impFieldIndexURLFocusGained(evt: FocusEvent?) {
        doWhenClickOnTextField(this.componentRegister, "FieldIndexURL")
    }

    fun initFocus() {
        BTNCancelAddIndex.requestFocusInWindow()
    }
}

//fun main() {
//    FlatIntelliJLaf.install()
//    val dialog = AddIndexWizard(JFrame())
//    dialog.isVisible = true
//}