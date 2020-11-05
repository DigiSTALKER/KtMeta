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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * This applet displays a String with the user's selected
 * fontname, style and size attributes.
 */

public class FontSelector extends JApplet
        implements ChangeListener, ItemListener {

    TextTestPanel textTestPanel;
    JComboBox fonts, styles;
    JSpinner sizes;
    String fontChoice = "Dialog";
    int styleChoice = 0;
    int sizeChoice = 12;

    public void init() {

        try {
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn);
        } catch (Exception cnf) {
        }

        JPanel fontSelectorPanel = new JPanel();

        fontSelectorPanel.add(new JLabel("Font family:"));

        GraphicsEnvironment gEnv =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        fonts = new JComboBox(gEnv.getAvailableFontFamilyNames());
        fonts.setSelectedItem(fontChoice);
        fonts.setMaximumRowCount(5);
        fonts.addItemListener(this);
        fontSelectorPanel.add(fonts);

        fontSelectorPanel.add(new JLabel("Style:"));

        String[] styleNames = {"Plain", "Bold", "Italic", "Bold Italic"};
        styles = new JComboBox(styleNames);
        styles.addItemListener(this);
        fontSelectorPanel.add(styles);

        fontSelectorPanel.add(new JLabel("Size:"));

        sizes = new JSpinner(new SpinnerNumberModel(12, 6, 24, 1));
        sizes.addChangeListener(this);
        fontSelectorPanel.add(sizes);

        textTestPanel = new TextTestPanel();
        textTestPanel.setFont(new Font(fontChoice, styleChoice, sizeChoice));
        textTestPanel.setBackground(Color.white);

        add(BorderLayout.NORTH, fontSelectorPanel);
        add(BorderLayout.CENTER, textTestPanel);
    }

    /*
     * Detect a state change in any of the settings and create a new
     * Font with the corresponding settings. Set it on the test component.
     */
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }
        if (e.getSource() == fonts) {
            fontChoice = (String) fonts.getSelectedItem();
        } else {
            styleChoice = styles.getSelectedIndex();
        }
        textTestPanel.setFont(new Font(fontChoice, styleChoice, sizeChoice));
    }

    public void stateChanged(ChangeEvent e) {
        try {
            String size = sizes.getModel().getValue().toString();
            sizeChoice = Integer.parseInt(size);
            textTestPanel.setFont(new Font(fontChoice, styleChoice, sizeChoice));
        } catch (NumberFormatException nfe) {
        }
    }

    public static void main(String[] s) {

        JFrame f = new JFrame("FontSelector");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        JApplet fontSelector = new FontSelector();
        f.add(fontSelector, BorderLayout.CENTER);
        fontSelector.init();
        f.pack();
        f.setVisible(true);
    }

}


class TextTestPanel extends JComponent {

    public Dimension getPreferredSize() {
        return new Dimension(500, 200);
    }

    public void setFont(Font font) {
        super.setFont(font);
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        g.setFont(getFont());
        FontMetrics metrics = g.getFontMetrics();
        String text = "The quick brown fox jumped over the lazy dog";
        int x = getWidth() / 2 - metrics.stringWidth(text) / 2;
        int y = getHeight() - 80;
        g.drawString(text, x, y);
    }
}
