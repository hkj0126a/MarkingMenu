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
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import markingmenu.MarkingMenu;

public class Test {

    private MarkingMenu markingMenu;
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
                markingMenu = new MarkingMenu();

                JLabel jLabel = new JLabel("JLABEL");
                jLabel.setLocation(200, 200);
                JPanel panel = new JPanel();
                panel.add(jLabel);
                frame.add(panel);
                frame.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        markingMenu.hide();
                    }
                });
                jLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (e.getButton() == MouseEvent.BUTTON3) {
                            List<String> actions = new ArrayList();
                            actions.add("Color");
                            actions.add("Position");
                            actions.add("Text");
                            //Crée la liste d'actions, MarkingMenu.setActions, execute
//                            markingMenu = new MarkingMenu(actions);
                            markingMenu.setListActions(actions, e.getXOnScreen(), e.getYOnScreen());
                            markingMenu.addMarkingMenuItemClick((int position) -> {
                                System.out.println("ITEM CLICK" + position + " State = " + markingMenu.getState());
//                                jLabel.setText("POSITION " + position);
                                //switch sur la position avec actions dans chaque case
                                Random r = new Random();
                                switch (position) {
                                    case 1:
                                        Color c = new Color(r.nextInt(126) + 50, r.nextInt(126) + 50, r.nextInt(126) + 50);
                                        jLabel.setForeground(c);
                                        break;
                                    case 2:
                                        jLabel.setLocation(r.nextInt(500), r.nextInt(500));
                                        break;
                                    case 3:
                                        jLabel.setText(r.nextInt(500) + "");
                                        break;
                                }
                                //markingMenu.removeMarkingMenuItemClick(this);
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
