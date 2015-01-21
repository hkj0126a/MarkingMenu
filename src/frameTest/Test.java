/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frameTest;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import markingmenu.MarkingMenu;

public class Test {

    private Popup popup;
    private JFrame frame;

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                initJFrame();
                JLabel jLabel = new JLabel("JLABEL");
                jLabel.setLocation(200, 200);
                JPanel panel = new JPanel();
                panel.add(jLabel);
                frame.add(panel);
                jLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (popup != null) {
                            popup.hide();
                        }
                        if (e.getButton() == MouseEvent.BUTTON3) {                            
                            List<String> actions = new ArrayList();
                            actions.add("Color");
                            actions.add("Position");
                            actions.add("Text");
                            MarkingMenu pie = new MarkingMenu(actions);
                            PopupFactory factory = PopupFactory.getSharedInstance();

                            int x = e.getXOnScreen() - (pie.getPreferredSize().width / 2);
                            int y = e.getYOnScreen() - (pie.getPreferredSize().height / 2);

                            popup = factory.getPopup(frame, pie, x, y);
                            popup.show();

                            pie.addMarkingMenuItemClick((int position) -> {
                                System.out.println("ITEM CLICK" + position);
//                                jLabel.setText("POSITION " + position);
                                //switch sur la position avec actions dans chaque case
                                switch (position) {
                                    case 1:
                                        jLabel.setForeground(Color.orange);
                                        break;
                                    case 2:
                                        jLabel.setLocation(400, 400);
                                        break;
                                    case 3:
                                        jLabel.setText("Bonjour");
                                        break;
                                }
                                popup.hide();
                            });
                        }
                    }
                });

            }
        });
    }

    public void initJFrame() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
