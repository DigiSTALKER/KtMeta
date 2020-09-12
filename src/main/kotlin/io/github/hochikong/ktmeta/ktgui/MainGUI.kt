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
import io.github.hochikong.ktmeta.swingui.About
import io.github.hochikong.ktmeta.swingui.KtmetaJFrame
import java.awt.event.ActionEvent

class MainGUI : KtmetaJFrame() {
    val about = About(this, true).apply { isVisible = false }
    override fun HelpAboutItemActionPerformed(evt: ActionEvent?) {
        this.about.isVisible = true
    }
}

fun main() {
    FlatSolarizedLightIJTheme.install()
    val gui = MainGUI()
    gui.isVisible = true
}