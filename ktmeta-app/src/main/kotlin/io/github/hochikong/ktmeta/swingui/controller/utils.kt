package io.github.hochikong.ktmeta.swingui.controller

import java.awt.Color
import javax.swing.JTextField

fun doWhenClickOnTextField(reg: Map<String, JTextField>, key: String) {
    val comp = reg[key]
    if (comp is JTextField) {
        comp.selectAll()
        if (comp.foreground != Color(0, 0, 0)) {
            comp.foreground = Color(0, 0, 0)
        }
    }
}