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

import io.github.hochikong.ktmeta.swingui.dialogs.codegen.impAddCustomTags
import io.github.hochikong.ktmeta.swingui.essentials.CustomTagsTableModel
import java.awt.Frame
import java.awt.event.ActionEvent
import java.awt.event.MouseEvent
import javax.swing.JOptionPane
//import javax.swing.JFrame
//import com.formdev.flatlaf.FlatIntelliJLaf

class AddCustomTags(parent: Frame, tags: List<String>) : impAddCustomTags(parent, true, tags) {
    private val backupTags = this.tagsData.toList()

    override fun impTableUserCustomTagsMouseClicked(evt: MouseEvent?) {
        // For now, do nothing
    }

    /**
     * Add new tag
     * */
    override fun impBTNAddTagActionPerformed(evt: ActionEvent?) {
        addNewTag()
    }

    private fun addNewTag() {
        if (this.TextFieldAddTag.text.isNotEmpty()) {
            this.tagsData.apply {
                add(this@AddCustomTags.TextFieldAddTag.text)
            }
            this.myModel = CustomTagsTableModel(this.tagsData)
            this.TableUserCustomTags.model = this.myModel
            this.TextFieldAddTag.text = ""
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Empty input, failed to add.",
                "Add new tag failed",
                JOptionPane.WARNING_MESSAGE
            )
        }
    }

    /**
     * When press OK button, submit the changes
     * */
    override fun impBTNOKAddTagsActionPerformed(evt: ActionEvent?) {
        this.dispose()
    }

    /**
     * Clear input text field
     * */
    override fun impBTNClearActionPerformed(evt: ActionEvent?) {
        if (this.TextFieldAddTag.text.isNotEmpty()) {
            this.TextFieldAddTag.text = ""
        }
    }

    /**
     * Cancel to add, discard the changes
     * */
    override fun impBTNCancelAddTagsActionPerformed(evt: ActionEvent?) {
        this.tagsData = this.backupTags
        this.updateModel()
    }

    /**
     * Remove tags and update table content
     * */
    override fun impBTNRemoveTagActionPerformed(evt: ActionEvent?) {
        val elementShouldBeDeleted = mutableListOf<String>()
        if (this.TableUserCustomTags.selectedRowCount > 0) {
            this.TableUserCustomTags.selectedRows.forEach { elementShouldBeDeleted.add(this.tagsData[it]) }

            elementShouldBeDeleted.forEach { this.tagsData.remove(it) }
        }
        this.updateModel()
    }

    /**
     * Add new tag
     * */
    override fun impTextFieldAddTagActionPerformed(evt: ActionEvent?) {
        addNewTag()
    }

    private fun updateModel() {
        this.myModel = CustomTagsTableModel(this.tagsData)
        this.TableUserCustomTags.model = this.myModel
    }
}

//fun main() {
//    FlatIntelliJLaf.install()
//    val dialog = AddCustomTags(JFrame(), mutableListOf("1", "2"))
//    dialog.isVisible = true
//}