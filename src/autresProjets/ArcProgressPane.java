/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autresProjets;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import javax.swing.JPanel;

public class ArcProgressPane extends JPanel {

    private boolean fillProgress = false;
    private float progress;
    private int position;
    private int nbOptionsTotal;
    private float angle;

    public ArcProgressPane() {
        this(Color.orange, 1,4);
    }
    
    public ArcProgressPane(Color c, int pos, int nbOptions) {
        this.setOpaque(false);
        setBackground(new Color(213, 134, 145, 120));
        setForeground(c);
        nbOptionsTotal = nbOptions;
        
        position = pos;
        initAngle();
        setFillProgress(true);       
    }
    
    public void initAngle() {
        angle = (float) (2 * Math.PI / nbOptionsTotal);
        setProgress((float) (angle/(2*Math.PI)));
        angle *= position;
    }

    public void setFillProgress(boolean value) {
        if (fillProgress != value) {
            this.fillProgress = value;
            firePropertyChange("fillProgress", !fillProgress, fillProgress);
        }
    }

    public boolean isFillProgress() {
        return fillProgress;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }

    public void setProgress(float value) {
        if (progress != value) {
            float old = progress;
            this.progress = value;
            firePropertyChange("progress", old, progress);
            repaint();
        }
    }

    public float getProgress() {
        return progress;
    }

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//
//        Graphics2D g2d = (Graphics2D) g.create();
//        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
//        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
//        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
//        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//        g2d.rotate(90);
//        Insets insets = getInsets();
//        int width = getWidth() - (insets.left + insets.right);
//        int height = getHeight() - (insets.bottom + insets.top);
//        int raidus = Math.min(width, height);
//        int x = insets.left + ((width - raidus) / 2);
//        int y = insets.right + ((height - raidus) / 2);
//
//        double extent = 360d * progress;
//
//        g2d.setColor(getForeground());
//
//        Arc2D arc = null;
//        if (isFillProgress()) {
//            arc = new Arc2D.Double(x, y, raidus, raidus, 90, -extent, Arc2D.PIE);
//        } else {
//            extent = 360 - extent;
//            arc = new Arc2D.Double(x, y, raidus, raidus, 90, extent, Arc2D.PIE);
//        }
//        g2d.fill(arc);
//        g2d.dispose();
//
//    }
    
    //Faire un contains pour le picking
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        int w2 = getWidth() / 2;
        int h2 = getHeight() / 2;
        g2d.rotate(angle, w2, h2);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        Insets insets = getInsets();
        int width = getWidth() - (insets.left + insets.right);
        int height = getHeight() - (insets.bottom + insets.top);
        int raidus = Math.min(width, height);
        int x = insets.left + ((width - raidus) / 2);
        int y = insets.right + ((height - raidus) / 2);

        double extent = 360d * progress;
        g2d.setColor(getForeground());

        Arc2D arc = null;
        if (isFillProgress()) {
            arc = new Arc2D.Double(x, y, raidus, raidus, 90, -extent, Arc2D.PIE);
        } else {
            extent = 360 - extent;
            arc = new Arc2D.Double(x, y, raidus, raidus, 90, extent, Arc2D.PIE);
        }
        g2d.fill(arc);

        g2d.dispose();
    }
}
