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

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClickAwayDialog extends JDialog {

    public ClickAwayDialog(final Frame owner) {
        super(owner);
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.add(new JLabel("Click outside this dialog in the parent frame to close it"), BorderLayout.NORTH);
        JButton btn = new JButton("Click Me");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ClickAwayDialog.this, "New Child Window");
            }
        });
        pnl.add(btn, BorderLayout.CENTER);
        this.setContentPane(pnl);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(owner);
        this.setAlwaysOnTop(true);
        this.addWindowFocusListener(new WindowFocusListener() {

            public void windowGainedFocus(WindowEvent e) {
                //do nothing
            }

            public void windowLostFocus(WindowEvent e) {
                if (SwingUtilities.isDescendingFrom(e.getOppositeWindow(), ClickAwayDialog.this)) {
                    return;
                }
                ClickAwayDialog.this.setVisible(false);
            }

        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                parent.setSize(300, 300);
                parent.setLocationByPlatform(true);
                parent.setVisible(true);
                ClickAwayDialog dlg = new ClickAwayDialog(parent);
                dlg.setVisible(true);
            }
        });
    }
}