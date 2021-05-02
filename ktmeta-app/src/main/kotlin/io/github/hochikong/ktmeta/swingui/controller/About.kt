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

package io.github.hochikong.ktmeta.swingui.controller

//import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme
//import javax.swing.JFrame
import io.github.hochikong.ktmeta.swingui.dialogs.codegen.impAbout
import java.awt.Frame
import java.awt.event.MouseEvent
import java.lang.management.ManagementFactory
import java.lang.management.OperatingSystemMXBean


class About(parent: Frame) : impAbout(parent, true) {
    private val system: OperatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean()
    private val runtime = ManagementFactory.getRuntimeMXBean()

    init {
        this.updateButtomText(
            """
            OS: ${system.name}   
            Arch: ${system.arch}
            Cores: ${system.availableProcessors}
            
            ${runtime.vmName} By ${runtime.vmVendor}
            Spec: ${runtime.specVersion}   Ver: ${runtime.vmVersion}
        """.trimIndent()
        )
    }

    override fun impLabelPhotoMouseClicked(evt: MouseEvent?) {
        this.dispose()
    }

    override fun impTextPaneInfoMouseClicked(evt: MouseEvent?) {
        this.dispose()
    }
}

//fun main() {
//    FlatCarbonIJTheme.install()
//    val dialog = About(JFrame())
//    dialog.isVisible = true
//}