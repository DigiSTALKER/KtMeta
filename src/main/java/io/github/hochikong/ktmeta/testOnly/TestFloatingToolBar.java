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
package io.github.hochikong.ktmeta.testOnly;

/**
 * @author ckhoi
 */

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;

public class TestFloatingToolBar {

    public static void main(String[] args) {
        new TestFloatingToolBar();
    }

    public TestFloatingToolBar() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                final JFrame frame = new JFrame("Test");

                final JToolBar tb = new JToolBar();
                tb.add(new JButton("Pop"));
                tb.setFloatable(true);

                tb.addAncestorListener(new AncestorListener() {
                    @Override
                    public void ancestorAdded(AncestorEvent event) {
                        tell();
                        if (SwingUtilities.getWindowAncestor(tb).equals(frame)) {
                            System.out.println("...In Main Frame");
                        } else {
                            System.out.println("...Maybe floating");
                        }
                    }

                    @Override
                    public void ancestorRemoved(AncestorEvent event) {
                        tell();
                        if (SwingUtilities.getWindowAncestor(tb).equals(frame)) {
                            System.out.println("...In Main Frame");
                        } else {
                            System.out.println("...Maybe floating");
                        }
                    }

                    @Override
                    public void ancestorMoved(AncestorEvent event) {
                    }
                });

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(tb, BorderLayout.NORTH);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        });
    }

    public void tell() {
        Exception exp = new Exception();
        StackTraceElement[] stackTrace = exp.getStackTrace();
        System.out.println(">>>" + stackTrace[1].getMethodName());
    }

}