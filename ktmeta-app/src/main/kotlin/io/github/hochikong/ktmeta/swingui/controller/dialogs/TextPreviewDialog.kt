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