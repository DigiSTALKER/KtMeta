/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.hochikong.ktmeta.testOnly;

/**
 * @author ckhoi
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JSplitPaneHideTest extends JFrame {
    private final JButton leftBtn;
    private final JButton rightBtn;
    private final JSplitPane jsp;

    public JSplitPaneHideTest() {
        setTitle(" JSplitPaneHide Test");
        leftBtn = new JButton("Left Button");
        rightBtn = new JButton("Right Button");
        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftBtn, rightBtn);
        jsp.setResizeWeight(0.5);
        // Implemention code to hide left pane or right pane
        ActionListener actionListener = new ActionListener() {
            private int loc = 0;

            public void actionPerformed(ActionEvent ae) {
                JButton source = (JButton) ae.getSource();
                if (jsp.getLeftComponent().isVisible() && jsp.getRightComponent().isVisible()) {
                    loc = jsp.getDividerLocation();
                    jsp.setDividerSize(0);
                    jsp.getLeftComponent().setVisible(source == leftBtn);
                    jsp.getRightComponent().setVisible(source == rightBtn);
                } else {
                    jsp.getLeftComponent().setVisible(true);
                    jsp.getRightComponent().setVisible(true);
                    jsp.setDividerLocation(loc);
                    jsp.setDividerSize(10);
                }
            }
        };
        rightBtn.addActionListener(actionListener);
        leftBtn.addActionListener(actionListener);
        add(jsp, BorderLayout.CENTER);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JSplitPaneHideTest();
    }
}
