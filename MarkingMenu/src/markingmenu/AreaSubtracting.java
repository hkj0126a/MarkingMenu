/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popbutton;

import java.awt.BasicStroke; 
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AreaSubtracting extends JPanel {
  DrawingCanvas canvas;

  public AreaSubtracting() {
    canvas = new DrawingCanvas();
    add(canvas);
    
    canvas.area1 = new Area(canvas.gp);
    canvas.area1.subtract(canvas.area2);
    canvas.repaint();
  }
  public static void main(String arg[]) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add("Center", new AreaSubtracting());
    frame.pack();
    frame.setSize(new Dimension(400, 300));
    frame.setVisible(true);
  }
  class DrawingCanvas extends Canvas {
    GeneralPath gp;
    Ellipse2D ellipse;
    Area area1, area2;

    public DrawingCanvas() {
      setBackground(Color.white);
      setSize(350, 250); // width and height of canvas
      int w = getWidth();
      int h = getHeight();

      gp = new GeneralPath();
      gp.moveTo(w / 8, h / 2);
      gp.lineTo(w / 2, h / 4);
      gp.lineTo(7 * w / 8, h / 2);
      gp.lineTo(w / 2, 3 * h / 4);
      gp.closePath();
      area1 = new Area(gp); // General path area object

      ellipse = new Ellipse2D.Double(w / 4, h / 4, w / 2, h / 2);
      area2 = new Area(ellipse); // Ellipse area object
    }

    public void paint(Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
      g2D.setStroke(new BasicStroke(2.0f));

      g2D.draw(area1);
      g2D.draw(area2);
    }

  }
}
