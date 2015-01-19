/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markingmenu;

import autresProjets.TicTacToeItem;
import autresProjets.ArcProgressPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.Timer;

/**
 *
 * @author hakje
 */
public class MarkingMenuPopupActionListener implements MouseListener {

    private Component component;
    private Popup popup;
    private Timer timer;

    MarkingMenuPopupActionListener(Component component) {
        this.component = component;
        initTimerHider();

    }

    private void initTimerHider() {
        ActionListener hider = (ActionEvent e1) -> {
            popup.hide();
        };
        // Hide popup in 3 seconds
        timer = new Timer(3000, hider);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (popup != null) {
            popup.hide();
        }
        timer.stop();
        if (e.getButton() == MouseEvent.BUTTON3) {
//            ArcProgressPane ap = new ArcProgressPane();
//            ap.setFillProgress(true);
//            ap.setProgress(0.36f);
//            ap.setForeground(Color.blue);
//            ap.setBorder(BorderFactory.createLineBorder(Color.red));
            Pie pie = new Pie();
            PopupFactory factory = PopupFactory.getSharedInstance();

            int x = e.getXOnScreen() - (pie.getPreferredSize().width/2);
            int y = e.getYOnScreen() - (pie.getPreferredSize().height/2);

            popup = factory.getPopup(component, pie, x, y);
            popup.show();

            timer.restart();

            MouseListener mouseHider = new MouseListenerHiderImpl();
            pie.addMouseListener(mouseHider);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private class MouseListenerHiderImpl implements MouseListener {

        public MouseListenerHiderImpl() {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            popup.hide();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
