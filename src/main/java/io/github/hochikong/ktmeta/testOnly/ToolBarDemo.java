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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ToolBarDemo extends JPanel
        implements ActionListener {

    protected JTextArea textArea;
    protected String newline = "\n";
    static final private String PREVIOUS = "previous";
    static final private String UP = "up";
    static final private String NEXT = "next";

    public ToolBarDemo() {
        super(new BorderLayout());

        //Create the toolbar.
        JToolBar toolBar = new JToolBar("Still draggable");
        addButtons(toolBar);

        //Create the text area used for output.  Request
        //enough space for 5 rows and 30 columns.
        textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Lay out the main panel.
        setPreferredSize(new Dimension(450, 130));
        add(toolBar, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
    }

    protected void addButtons(JToolBar toolBar) {
        JButton button = null;

        //first button
        button = makeNavigationButton("Back24", PREVIOUS,
                "Back to previous something-or-other",
                "Previous");
        toolBar.add(button);

        //second button
        button = makeNavigationButton("Up24", UP,
                "Up to something-or-other",
                "Up");
        toolBar.add(button);

        //third button
        button = makeNavigationButton("Forward24", NEXT,
                "Forward to something-or-other",
                "Next");
        toolBar.add(button);
    }

    protected JButton makeNavigationButton(String imageName,
                                           String actionCommand,
                                           String toolTipText,
                                           String altText) {
        //Look for the image.
        String imgLocation = "images/"
                + imageName
                + ".gif";
        URL imageURL = ToolBarDemo.class.getResource(imgLocation);

        //Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);

        if (imageURL != null) {                      //image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else {                                     //no image found
            button.setText(altText);
            System.err.println("Resource not found: "
                    + imgLocation);
        }

        return button;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String description = null;

        // Handle each button.
        if (PREVIOUS.equals(cmd)) { //first button clicked
            description = "taken you to the previous <something>.";
        } else if (UP.equals(cmd)) { // second button clicked
            description = "taken you up one level to <something>.";
        } else if (NEXT.equals(cmd)) { // third button clicked
            description = "taken you to the next <something>.";
        }

        displayResult("If this were a real app, it would have "
                + description);
    }

    protected void displayResult(String actionDescription) {
        textArea.append(actionDescription + newline);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ToolBarDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new ToolBarDemo());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}
