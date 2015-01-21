/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markingmenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 METTRE DES LISTENER SUR LE CLIC ET ASSOCIER UNE ACTION A UN ITEM
 implémente mouse hover pour le highlight --> Stock la couleur et la change avec un peu plus pale
 */
public class MarkingMenuItem extends JPanel {

    private boolean fillProgress = false;
    private double progress;
    private int position;
    private int nbOptionsTotal;
    private double angle;
    private Arc2D arc;
    private JLabel label;
    private MarkingMenuItemListener observer;

    /*public MarkingMenuItem() {
        this(Color.orange, 1, 4, "Mon label",);
    }*/

    public MarkingMenuItem(Color c, int pos, int nbOptions, String _label,MarkingMenuItemListener obs) {
        setOpaque(false);
        setForeground(c);
        nbOptionsTotal = nbOptions;
        position = pos;
        arc = new Arc2D.Double();
        label = new JLabel(_label);
        label.setOpaque(false);
        initAngle();
        observer = obs;

        add(label);
        setFillProgress(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               observer.actionMarkingMenuPerformed(position);
            }
        });
    }

    private void initAngle() {
        angle = (double) (2 * Math.PI / nbOptionsTotal);
        setProgress((double) (angle / (2 * Math.PI)));
        angle *= position - 1;
    }

    private void setFillProgress(boolean value) {
        if (fillProgress != value) {
            this.fillProgress = value;
            firePropertyChange("fillProgress", !fillProgress, fillProgress);
        }
    }

    private boolean isFillProgress() {
        return fillProgress;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    private void setProgress(double value) {
        if (progress != value) {
            double old = progress;
            this.progress = value;
            firePropertyChange("progress", old, progress);
            repaint();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        Point rotatedPoint = rotatePoint(new Point(x, y));
        return arc.contains(rotatedPoint.x, rotatedPoint.y);
    }

    /*@Override
     public void setBounds(int x, int y, int width, int height) {
     super.setBounds(x, y, width, height); //To change body of generated methods, choose Tools | Templates.
     boolean add = false;
     label.setLocation(computeLabelLocation());

     for (int i = 0; i < getComponents().length; i++) {
     if (getComponents()[i] == label) {
     add = true;
     }
     }

     if (!add) {
     add(label);
     }
     }*/
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

        arc = null;
        if (isFillProgress()) {
            arc = new Arc2D.Double(x, y, raidus, raidus, 90, -extent, Arc2D.PIE);
        } else {
            extent = 360 - extent;
            arc = new Arc2D.Double(x, y, raidus, raidus, 90, extent, Arc2D.PIE);
        }
        g2d.fill(arc);
        g2d.dispose();
        label.setLocation(computeLabelLocation());
    }

    private Point computeLabelLocation() {
        label.setForeground(Color.black);

        double x = getWidth() / 2;
        double y = getHeight() / 4 - label.getHeight() / 2;

        Point intermediaire = rotatePoint(new Point((int) x, (int) y), -angle);

        double anglePart = (double) (2 * Math.PI / nbOptionsTotal);
        Point presqueFinal = rotatePoint(intermediaire, -anglePart / 2);

        presqueFinal.x -= label.getWidth() / 2;
        presqueFinal.y -= label.getHeight() / 2;
        return presqueFinal;
    }

    private Point rotatePoint(Point p) {
        Point center = new Point(getWidth() / 2, getHeight() / 2);
        int x = (int) (((p.x - center.x) * Math.cos(angle)) + ((p.y - center.y) * Math.sin(angle)));
        int y = (int) (((p.y - center.y) * Math.cos(angle)) - ((p.x - center.x) * Math.sin(angle)));
        Point rotatedPoint = new Point(x + center.x, y + center.y);
        return rotatedPoint;
    }

    private Point rotatePoint(Point p, double rotationAngle) {
        Point center = new Point(getWidth() / 2, getHeight() / 2);
        int x = (int) (((p.x - center.x) * Math.cos(rotationAngle)) + ((p.y - center.y) * Math.sin(rotationAngle)));
        int y = (int) (((p.y - center.y) * Math.cos(rotationAngle)) - ((p.x - center.x) * Math.sin(rotationAngle)));
        Point rotatedPoint = new Point(x + center.x, y + center.y);
        return rotatedPoint;
    }

}