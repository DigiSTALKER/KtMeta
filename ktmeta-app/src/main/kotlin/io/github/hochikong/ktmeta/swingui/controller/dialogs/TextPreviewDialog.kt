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

import io.github.hochikong.ktmeta.swingui.dialogs.codegen.impTextPreviewDialog
import java.awt.Frame
import java.awt.event.ActionEvent
import javax.swing.event.CaretEvent

import javax.swing.JFrame
import com.formdev.flatlaf.FlatIntelliJLaf

class TextPreviewDialog(parent: Frame) : impTextPreviewDialog(parent, true) {
    override fun impBTNClearEditorActionPerformed(evt: ActionEvent?) {
        TextAreaEditor.text = ""
        LabelEditorPreview.text = ""
    }

    override fun impBTNOKAddTextPreviewActionPerformed(evt: ActionEvent?) {
        // TODO
    }

    override fun impBTNCancelAddTextPreviewActionPerformed(evt: ActionEvent?) {
        // TODO
    }

    override fun impTextAreaEditorCaretUpdate(evt: CaretEvent?) {
        LabelEditorPreview.text = simpleTextToHTML(TextAreaEditor.text)
    }

    private fun simpleTextToHTML(inputText: String): String {
        val segs = inputText.split("\n").toMutableList()
        val head = "<html>"
        val tail = "</html>"
        if (segs.size > 1){
            segs[0] = "<h3>${segs[0]}</h3>"
        }
        val body = segs.reduce { acc, s -> "$acc<h3>$s</h3>" }
        return head + body + tail
    }

    fun cleanDefaultContent(){
        TextAreaEditor.text = ""
        LabelEditorPreview.text = ""
    }
}

fun main() {
    FlatIntelliJLaf.install()
    val dialog = TextPreviewDialog(JFrame())
    dialog.cleanDefaultContent()
    dialog.setLocationRelativeTo(null)
    dialog.isVisible = true
}