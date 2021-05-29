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

import io.github.hochikong.ktmeta.swingui.dialogs.codegen.impSetESMapping
import io.github.hochikong.ktmeta.swingui.controller.doWhenClickOnTextField
import java.awt.Frame
import java.awt.event.ActionEvent
import javax.swing.JTextField
import java.awt.event.FocusEvent

//import javax.swing.JFrame
//import com.formdev.flatlaf.FlatIntelliJLaf


class SetESMapping(parent: Frame) : impSetESMapping(parent, true) {
    private val componentRegister = mapOf<String, JTextField>(
        "TextFieldESHost" to this.TextFieldESHost,
        "TextFieldESIndex" to this.TextFieldESIndex
    )


    override fun impBTNCreateMappingActionPerformed(evt: ActionEvent?) {
        // TODO
    }

    override fun impTextFieldESHostFocusGained(evt: FocusEvent?) {
        doWhenClickOnTextField(componentRegister, "TextFieldESHost")
    }

    override fun impTextFieldESIndexFocusGained(evt: FocusEvent?) {
        doWhenClickOnTextField(componentRegister, "TextFieldESIndex")
    }

    override fun impBTNResetMappingActionPerformed(evt: ActionEvent?) {
        // TODO
    }

    override fun impBTNClearTextAreaActionPerformed(evt: ActionEvent?) {
        this.TextAreaJSONSample.text = ""
    }
}

//fun main() {
//    FlatIntelliJLaf.install()
//    val dialog = SetESMapping(JFrame())
//    dialog.setLocationRelativeTo(null)
//    dialog.isVisible = true
//}