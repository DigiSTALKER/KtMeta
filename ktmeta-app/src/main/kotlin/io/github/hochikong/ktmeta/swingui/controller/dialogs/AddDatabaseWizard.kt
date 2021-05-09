package io.github.hochikong.ktmeta.swingui.controller.dialogs

import io.github.hochikong.ktmeta.swingui.dialogs.codegen.impAddDatabaseWizard
import java.awt.Color
import java.awt.Frame
import java.awt.event.ActionEvent
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
     * Change color
     * */
    override fun impFieldNameMouseClicked(evt: MouseEvent?) {
        this.doWhenClickOnField("FieldName")
    }

    override fun impFieldDescriptionMouseClicked(evt: MouseEvent?) {
        this.doWhenClickOnField("FieldDescription")
    }

    override fun impFieldJDBCURLMouseClicked(evt: MouseEvent?) {
        this.doWhenClickOnField("FieldJDBCURL")
    }

    override fun impFieldUsernameMouseClicked(evt: MouseEvent?) {
        this.doWhenClickOnField("FieldUsername")
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

    private fun doWhenClickOnField(key: String) {
        val comp = this.componentRegister[key]
        if (comp is JTextField) {
            comp.selectAll()
            if (comp.foreground != Color(0, 0, 0)) {
                comp.foreground = Color(0, 0, 0)
            }
        }
    }
}


//fun main() {
//    FlatIntelliJLaf.install()
//    val dialog = AddDatabaseWizard(JFrame(), arrayOf("SQLite", "PostgreSQL"))
//    dialog.isVisible = true
//}