/*
 * Copyright 2020 Hochikong
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

package io.github.hochikong.ktmeta.ktgui

import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme
import io.github.hochikong.ktmeta.predefined.SupportedDBs
import io.github.hochikong.ktmeta.swingui.AddDatabaseWizard
import javax.swing.SwingWorker

class MainGUI(supportedDBS: Array<String>) : AddDatabaseWizard(javax.swing.JFrame(), true, supportedDBS) {
    val worker = object : SwingWorker<Unit, Unit>() {
        override fun doInBackground() {
            println("Sleep")
            Thread.sleep(2000)
            println("Wake up")
            ProgressBarTestConn.isIndeterminate = false
            for (i in 0..100) {
                ProgressBarTestConn.value = i
                Thread.sleep(100)
            }
        }

        override fun done() {
            println("Done")
        }
    }

    override fun KtBTNTestConnActionPerformed() {
        worker.execute()
    }
}

fun main() {
    FlatSolarizedLightIJTheme.install()
    val l: Array<String> = SupportedDBs.values().map { it.identity }.filter { it.isNotBlank() }.toTypedArray()
    val gui = MainGUI(l)
    gui.isVisible = true
}