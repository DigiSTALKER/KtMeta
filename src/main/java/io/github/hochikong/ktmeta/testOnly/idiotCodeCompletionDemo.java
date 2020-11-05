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

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class idiotCodeCompletionDemo {

    private static final JFrame jFrame = new JFrame();
    private static JTextArea textArea;
    private static final Vector<String> vector = new Vector<>();
    private static JList jList;
    private static JPanel jpanel = null;

    public static void main(String[] args) {
        FlatIntelliJLaf.install();
        init();
        addListener();
    }

    public static void init() {
        List<String> list = new ArrayList<>();
        list.add("CREATE TABLE");
        list.add("CREATE INDEX");
        list.add("CREATE VIEW");
        vector.addAll(list);
        textArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(textArea);
        jFrame.getContentPane().add(jScrollPane);
        jFrame.setTitle("Testing");
        jFrame.setMinimumSize(new Dimension(500, 500));
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void addListener() {
        textArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (jpanel != null) {
                    jpanel.setVisible(false);
                }
                try {
                    String content = textArea.getText();
                    if (content.endsWith("c") || content.endsWith("C")) {
                        jpanel = new JPanel();
                        jpanel.setLayout(new BoxLayout(jpanel, 1));
                        jList = new JList(vector);
                        JScrollPane jScrollPane = new JScrollPane(jList);
                        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                        jpanel.add(jScrollPane);
                        Rectangle rectangle = textArea.modelToView(textArea.getCaretPosition());
                        rectangle.y = rectangle.y + 15;
                        jpanel.setBounds(rectangle);
                        jpanel.setSize(new Dimension(200, 100));
                        jpanel.setBackground(Color.WHITE);
                        textArea.add(jpanel);
                    } else {
                        if (jpanel != null) {
                            jpanel.setVisible(false);
                        }
                    }
                } catch (BadLocationException e2) {
                    // TODO
                }
            }
        });

        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN: {
                        int index = jList.getSelectedIndex();
                        if (vector.size() > index + 1) {
                            jList.setSelectedIndex(index + 1);
                        }
                        break;
                    }
                    case KeyEvent.VK_UP: {
                        int index = jList.getSelectedIndex();
                        if (vector.size() > index - 1) {
                            jList.setSelectedIndex(index - 1);
                        }
                        break;
                    }
                    case KeyEvent.VK_ENTER:
                        if (textArea.getText() != null) {
                            if (jList.getSelectedValue() != null) {
                                String selectValue = (String) jList.getSelectedValue();
                                String origin = textArea.getText();
                                StringBuilder builder = new StringBuilder();
                                builder.append(origin, 0, origin.length() - 2);
                                builder.append(selectValue);
                                textArea.setText(builder.toString());
                                jList.clearSelection();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
