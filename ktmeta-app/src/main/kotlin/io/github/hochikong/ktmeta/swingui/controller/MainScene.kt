package io.github.hochikong.ktmeta.swingui.controller

import io.github.hochikong.ktmeta.swingui.codegen.impKtmetaMainFrame
import com.formdev.flatlaf.FlatIntelliJLaf
import java.awt.event.ActionEvent
import java.lang.management.ManagementFactory
import java.lang.management.OperatingSystemMXBean
import javax.swing.JOptionPane

class MainScene : impKtmetaMainFrame() {
    override fun impMenuItemAboutActionPerformed(evt: ActionEvent?) {
        val system: OperatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean()
        val runtime = ManagementFactory.getRuntimeMXBean()
        val info = "OS: ${system.name} \nArch: ${system.arch} " +
                "\nVM: ${runtime.vmName} \nVendor: ${runtime.vmVendor}" +
                "\nSpec: ${runtime.specVersion} \nVersion: ${runtime.vmVersion}"
        JOptionPane.showMessageDialog(
            this,
            "KtMeta, a metadata management platform \nckhoidea@hotmail.com \n\n$info",
            "About",
            JOptionPane.INFORMATION_MESSAGE
        )
    }
}

fun main() {
    FlatIntelliJLaf.install()
    val x = MainScene()
    x.setLocationRelativeTo(null)
    x.isVisible = true
}