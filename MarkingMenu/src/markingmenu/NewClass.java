/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popbutton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class NewClass {

    public static void main(String[] args) {
        new NewClass();
    }
    private float progress;

    public NewClass() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                final ArcProgressPane p1 = new ArcProgressPane();
                p1.setForeground(Color.RED);
                final ArcProgressPane p2 = new ArcProgressPane();
                p2.setForeground(Color.BLUE);
                p2.setFillProgress(true);

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new FlowLayout());
                frame.add(p1);
                frame.add(p2);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                Timer timer = new Timer(40, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        progress += 0.01f;
                        if (progress >= 1f) {
                            progress = 1f;
                            ((Timer) e.getSource()).stop();
                        }
                        p1.setProgress(progress);
                        p2.setProgress(progress);
                    }
                });
                timer.setRepeats(true);
                timer.setCoalesce(true);
                timer.start();

            }
        });
    }

    
}
