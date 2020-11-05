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

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ckhoi
 */
public class TestCloseBTNHelper {

    private static JButton getCloseBtn(String colorBackground, String colorEnter) {
        JButton closeBTN = new JButton();
        closeBTN.setIcon(new ImageIcon(TestCloseBTNHelper.class.getResource("/icons/ico/16pix/close.png")));
        closeBTN.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        closeBTN.setBackground(TestCloseBTNHelper.convertString2RGBColor(colorBackground));
        closeBTN.setFocusPainted(false);
        closeBTN.setBorderPainted(false);
        return closeBTN;
    }

    public static Color convertString2RGBColor(String color) {
        String[] tmp = color.split(",");
        List<Integer> rgbColor = Arrays.stream(tmp).map(e -> Integer.parseInt(e)).collect(Collectors.toList());
        return new Color(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2));
    }

    public static JPanel buildCustomTabHeadPanel(String title, String mouseInColor, String backgroundColor) {
        JPanel panelTitle = new JPanel();
        panelTitle.setOpaque(false);

        JLabel labelTitle = new JLabel(title);
        labelTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panelTitle.add(labelTitle);

        JButton btnClose = TestCloseBTNHelper.getCloseBtn(backgroundColor, mouseInColor);
        panelTitle.add(btnClose);

        return panelTitle;
    }
}
