package io.github.hochikong.ktmeta.swingui.controller.dialogs


import io.github.hochikong.ktmeta.swingui.controller.doWhenClickOnTextField
import io.github.hochikong.ktmeta.swingui.dialogs.codegen.impAddIndexWizard
import java.awt.Frame
import java.awt.event.ActionEvent
import java.awt.event.FocusEvent
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
        // TODO
    }

    override fun impBTNOKAddIndexActionPerformed(evt: ActionEvent?) {
        // TODO
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

    fun initFocus(){
        BTNCancelAddIndex.requestFocusInWindow()
    }
}

//fun main() {
//    FlatIntelliJLaf.install()
//    val dialog = AddIndexWizard(JFrame())
//    dialog.isVisible = true
//}