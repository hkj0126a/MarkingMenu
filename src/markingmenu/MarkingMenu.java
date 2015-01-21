/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markingmenu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author hakje
 */
public class MarkingMenu extends javax.swing.JPanel implements MarkingMenuItemListener{

    private JFrame myFrame;
    private MarkingMenuState state;
    private boolean isInMenu;
    private int nbOptions;
    private List<MarkingMenuItem> options;
    private List<String> labelOptions;
    private List<MarkingMenuItemListener> observers;
    
    

    /**
     * Creates new form Pie
     */
    public MarkingMenu() {
        this(Arrays.asList("one", "two", "three", "four", "five", "four", "five"));
    }

    public MarkingMenu(List<String> label) {
        initComponents();
        state = MarkingMenuState.IDLE;
        isInMenu = false;
        options = new ArrayList();
        initMarkingMenu(label);
        observers = new ArrayList<>();
    }

    public final void initMarkingMenu(List<String> label) {
        labelOptions = label;
        nbOptions = getLabelOptions().size();
        MarkingMenuItem app;

        for (int i = 1; i <= nbOptions; i++) {
            Color c = getRandomColor();
            app = new MarkingMenuItem(c, i, nbOptions, labelOptions.get(i-1),this);
            options.add(app);
            add(app);
        }
    }
    
    
    public void addMarkingMenuItemClick(MarkingMenuItemListener itemClickListener) {
        observers.add(itemClickListener);
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

    public void setLabel(int numOption, String optionName) {
        labelOptions.set(numOption, optionName);
        initMarkingMenu(labelOptions);
    }

    public List<String> getLabelOptions() {
        return labelOptions;
    }

    public void setMarkingMenu(List<String> label) {
        initMarkingMenu(label);
    }

    public void updateState() {
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
        for (MarkingMenuItemListener obs : observers) {
            obs.actionMarkingMenuPerformed(position);
        }
    }

}
