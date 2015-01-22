/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markingmenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.Timer;

/**
 *
 * @author hakje
 */
public class MarkingMenu extends javax.swing.JPanel implements MarkingMenuItemListener {

    private JFrame myFrame;
    private MarkingMenuState state;
    private Popup popup;
    private boolean isInMenu;
    private int nbOptions;
    private Timer timer;
    private List<MarkingMenuItem> options;
    private List<String> labelOptions;
    private List<MarkingMenuItemListener> observers;

    /**
     * Creates new form Pie
     */
    public MarkingMenu() {
        this(Arrays.asList("one", "two", "three", "four", "five", "six", "seven"));
    }

    public MarkingMenu(List<String> label) {
        initComponents();
        state = MarkingMenuState.IDLE;
        isInMenu = false;
        options = new ArrayList();
        initMarkingMenu(label);
        observers = new ArrayList<>();
        addMouseListener(new MouseMarkingMenuListener());
        initFactory(0, 0);
        popup.hide();
        initTimerHider();
    }

    public final void initMarkingMenu(List<String> label) {
        labelOptions = label;
        nbOptions = getLabelOptions().size();
        MarkingMenuItem app;
        
        for (MarkingMenuItem item : options) {
            remove(item);
        }
        options.clear();

        for (int i = 1; i <= nbOptions; i++) {
            Color c = getRandomColor();
            app = new MarkingMenuItem(c, i, nbOptions, labelOptions.get(i - 1), this);
            options.add(app);
            add(app);
            app.repaint();
        }
    }

    public void initFactory(int posX, int posY) {
        PopupFactory factory = PopupFactory.getSharedInstance();

        int x = posX - (getPreferredSize().width / 2);
        int y = posY - (getPreferredSize().height / 2);
//        int x = e.getXOnScreen() - (getPreferredSize().width / 2);
//        int y = e.getYOnScreen() - (getPreferredSize().height / 2);
        if (popup != null) {
            popup.hide();
        }
        popup = factory.getPopup(myFrame, this, x, y);
        popup.show();
    }

    public void show() {
        if (popup != null) {
            popup.show();
        }
    }

    public void hide() {
        if (popup != null) {
            popup.hide();
        }
    }

    public void addMarkingMenuItemClick(MarkingMenuItemListener itemClickListener) {
        observers.add(itemClickListener);
    }

    public void removeMarkingMenuItemClick(MarkingMenuItemListener itemClickListener) {
        observers.remove(itemClickListener);
    }

    public MarkingMenuState getState() {
        return state;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);
        setLayout(new javax.swing.OverlayLayout(this));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private Color getRandomColor() {
        Random r = new Random();
        int rouge = r.nextInt(128);
        int vert = r.nextInt(128);
        int bleu = r.nextInt(128);

        return new Color(rouge + 128, vert + 128, bleu + 128);
    }

    public String getLabel(int numOption) {
        return labelOptions.get(numOption);
    }

    public List<String> getLabelOptions() {
        return labelOptions;
    }

    public void setListActions(List<String> label, int posX, int posY) {
        initMarkingMenu(label);
        initFactory(posX, posY);
    }
    
    public List<MarkingMenuItem> getOptions() {
        return options;
    }

    public void updateState(MouseEvent e) {
        switch (state) {
            case MENU:
                break;
            case IDLE:
                break;
            case MARKING:
                break;
            case VISIBLE:
                break;
            case INVISIBLE:
                break;
        }
    }

    @Override
    public void actionMarkingMenuPerformed(int position) {
        popup.hide();
        for (MarkingMenuItemListener obs : observers) {
            obs.actionMarkingMenuPerformed(position);
        }
    }

    private void initTimerHider() {
        ActionListener timerAction = (ActionEvent e1) -> {
            switch (state) {
                case MENU:
                    state = MarkingMenuState.MENU;
                    //highlight()
                    break;
                case IDLE:
                    //INTERDIT
                    break;
                case MARKING:
                    state = MarkingMenuState.VISIBLE;
                    popup.show();
                    break;
                case VISIBLE:
                    state = MarkingMenuState.VISIBLE;
                    //highlight();
                    //A3();
                    //A4();
                    break;
                case INVISIBLE:
                    state = MarkingMenuState.INVISIBLE;
                    //highlight();
                    //A3();
                    //A4();
                    break;
            }
        };
        // Hide popup in 3 seconds
        // timer = new Timer(3000, timerAction);
    }

    private class MouseMarkingMenuListener implements MouseMotionListener, MouseListener {

        @Override
        public void mouseDragged(MouseEvent e) {
//            switch (state) {
//                case MENU:
//                    break;
//                case IDLE:
//                    break;
//                case MARKING:
//                    break;
//                case VISIBLE:
//                    break;
//                case INVISIBLE:
//                    break;
//            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            switch (state) {
                case MENU:
                    state = MarkingMenuState.MENU;
                    //highlight()
                    break;
                case IDLE:
                    //INTERDIT
                    break;
                case MARKING:
                    state = MarkingMenuState.INVISIBLE;
                    //A3();
                    break;
                case VISIBLE:
                    state = MarkingMenuState.VISIBLE;
                    //highlight();
                    //A3();
                    //A4();
                    break;
                case INVISIBLE:
                    state = MarkingMenuState.INVISIBLE;
                    //highlight();
                    //A3();
                    //A4();
                    break;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                switch (state) {
                    case MENU:
                        if (isInMenu) {
                            state = MarkingMenuState.IDLE;
                            //commande(); appel a actionMarkingMenuPerformed?
                            popup.hide();
                        } else {
                            state = MarkingMenuState.IDLE;
                            popup.hide();
                        }
                        break;
                    case IDLE:
                        //INTERDIT
                        break;
                    case MARKING:
                        //INTERDIT
                        break;
                    case VISIBLE:
                        //INTERDIT
                        break;
                    case INVISIBLE:
                        //INTERDIT
                        break;
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            switch (state) {
                case MENU:
                    //INTERDIT
                    break;
                case IDLE:
                    state = MarkingMenuState.MARKING;
                    //A1();
                    break;
                case MARKING:
                    //INTERDIT
                    break;
                case VISIBLE:
                    //INTERDIT
                    break;
                case INVISIBLE:
                    //INTERDIT
                    break;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            switch (state) {
                case MENU:
                    //INTERDIT
                    break;
                case IDLE:
                    //INTERDIT
                    break;
                case MARKING:
                    state = MarkingMenuState.MENU;
                    popup.show();
                    break;
                case VISIBLE:
                    if (isInMenu) {
                        state = MarkingMenuState.IDLE;
                        //commande();
                        popup.hide();
                    } else {
                        state = MarkingMenuState.IDLE;
                        popup.hide();
                    }
                    break;
                case INVISIBLE:
                    if (isInMenu) {
                        state = MarkingMenuState.IDLE;
                        //commande();
                        //A2();
                    } else {
                        state = MarkingMenuState.IDLE;
                        //A2();
                    }
                    break;
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            isInMenu = true;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            isInMenu = false;
        }

    }

}
