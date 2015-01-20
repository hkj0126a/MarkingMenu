/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markingmenu;

import java.awt.EventQueue;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestFrame {

    public static void main(String[] args) {
        new TestFrame();
    }

    public TestFrame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setSize(800, 800);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true); 
               
                MouseListener markingPopupActionListener = new MarkingMenuPopupActionListener(frame);
                frame.addMouseListener(markingPopupActionListener);
                
            }
        });
    }

}
