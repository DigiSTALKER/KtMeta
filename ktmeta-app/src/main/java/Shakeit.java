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

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Shakeit {
    JDialog dialog;
    Point naturalLocation;
    Timer shakeTimer;

    public Shakeit(JDialog d) {
        dialog = d;
    }

    public void startShake() {
        final long startTime;

        naturalLocation = dialog.getLocation();
        startTime = System.currentTimeMillis();
        shakeTimer = new Timer(5, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double TWO_PI = Math.PI * 2.0;
                double SHAKE_CYCLE = 5;

                long elapsed = System.currentTimeMillis() - startTime;
                double waveOffset = (elapsed % SHAKE_CYCLE) / SHAKE_CYCLE;
                double angle = waveOffset * TWO_PI;

                int SHAKE_DISTANCE = 2;

                int shakenX = (int) ((Math.sin(angle) * SHAKE_DISTANCE) + naturalLocation.x);
                dialog.setLocation(shakenX, naturalLocation.y);
                dialog.repaint();

                int SHAKE_DURATION = 1000;
                if (elapsed >= SHAKE_DURATION)
                    stopShake();
            }
        });
        shakeTimer.start();
    }

    public void stopShake() {
        shakeTimer.stop();
        dialog.setLocation(naturalLocation);
        dialog.repaint();
    }

    public static void main(String[] args) {
        JOptionPane pane = new JOptionPane("your message",JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
        JDialog d = pane.createDialog(null, "title");
        Shakeit dec = new Shakeit(d);
        d.pack();
        d.setModal(false);
        d.setVisible(true);
        dec.startShake();

    }

}
