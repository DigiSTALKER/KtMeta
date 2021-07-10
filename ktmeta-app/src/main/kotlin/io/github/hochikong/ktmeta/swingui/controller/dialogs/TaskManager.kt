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

import io.github.hochikong.ktmeta.swingui.dialogs.codegen.impTaskManager
import java.awt.Frame
import java.awt.event.ActionEvent

import javax.swing.JFrame
import com.formdev.flatlaf.FlatIntelliJLaf

class TaskManager(parent: Frame, tasks: Array<Array<String>>) : impTaskManager(parent, true, tasks){
    init {
        if (tasks.isNotEmpty()){
            this.BTNKillTask.isEnabled = true
        }
    }

    override fun impBTNKillTaskActionPerformed(evt: ActionEvent?) {
        // TODO
    }
}

fun main() {
    FlatIntelliJLaf.install()
    val dialog = TaskManager(JFrame(), arrayOf(arrayOf("task 1", "running", "id1"), arrayOf("task 2", "pending", "id2")))
    dialog.setLocationRelativeTo(null)
    dialog.isVisible = true
}