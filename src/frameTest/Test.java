/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frameTest;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import markingmenu.MarkingMenu;
import markingmenu.MarkingMenuDragListener;

public class Test {

    private MarkingMenu markingMenu;
    private MarkingMenuDragListener dragListener;
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
                dragListener = new MarkingMenuDragListener(markingMenu);
                JLabel jLabel = new JLabel("JLABEL");

                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(jLabel);
                frame.add(panel);
                jLabel.setLocation(500, 500);

                /*
                 ----------- LISTENER INIT
                 */
                frame.addMouseListener(dragListener);
                frame.addMouseMotionListener(dragListener);

                panel.addMouseListener(dragListener);
                panel.addMouseMotionListener(dragListener);

                jLabel.addMouseListener(dragListener);
                jLabel.addMouseMotionListener(dragListener);

                /*
                 * UTILISATION MARKING MENU
                 * 
                 * */
                jLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (e.getButton() == MouseEvent.BUTTON3) {
                            List<String> actions = new ArrayList();
                            actions.add("Color");
                            actions.add("Position");
                            actions.add("Text");

                            markingMenu.setListActions(actions, e.getXOnScreen(), e.getYOnScreen());

                            markingMenu.addMarkingMenuItemClick((int position) -> {
                                System.out.println("ITEM CLICK" + position + " State = " + markingMenu.getState());

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
                                //markingMenu.show();
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
