/*
 * Copyright 2013 David.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package autresProjets;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.event.EventListenerList;

/**
 *
 * @author David
 */
public class TicTacToeItem extends JComponent {

    public static final int NO_PLAYER_PLAYED = 0;
    public static final int PLAYER1_PLAYED = 1;
    public static final int PLAYER2_PLAYED = 2;
    private static final int DRAWING_WIDTH = 30;
    private static final int DRAWING_HEIGHT = 30;
    private Color backColor = Color.GREEN.darker();
    private Color armedBackColor = Color.RED;
    private Color armedForeColor = Color.WHITE;
    private Color passiveBackColor = armedBackColor;
    private Color passiveForeColor = armedForeColor;
    private Color foreColor = Color.WHITE;
    private Color backDisabledColor = Color.DARK_GRAY;
    private Color foreDisabledColor = Color.LIGHT_GRAY;
    private int played;
    private int value;
    private boolean valueDisplayed;
    private double scale = 0;
    private boolean armed;
    private static double DEFAULT_SIZE = 50;

    public boolean isValueDisplayed() {
        return valueDisplayed;
    }

    public void setValueDisplayed(boolean valueDisplayed) {
        this.valueDisplayed = valueDisplayed;
        repaint();
    }

    public int getValue() {
        return value;
    }

    private void setArmed(boolean armed) {
        this.armed = armed;
        repaint();
    }

    public TicTacToeItem(int value) {
        this.value = value;
        this.armed = false;
        this.played = NO_PLAYER_PLAYED;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (isEnabled()) {
                    ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, (Integer.toString(getValue())));
                    fireActionPerformed(event);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setArmed(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setArmed(false);
            }
        });
    }

    private void fireActionPerformed(ActionEvent event) {
        for (ActionListener listener : eventListenerList.getListeners(ActionListener.class)) {
            listener.actionPerformed(event);
        }
    }

    public TicTacToeItem() {
        this(0);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int) DEFAULT_SIZE, (int) DEFAULT_SIZE);
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = (played == PLAYER1_PLAYED) ? PLAYER1_PLAYED : (played == PLAYER2_PLAYED) ? PLAYER2_PLAYED : NO_PLAYER_PLAYED;
        repaint();
    }

    public void setValue(int value) {
        this.value = value;
        repaint();
    }
    private EventListenerList eventListenerList = new EventListenerList();

    public void addActionListener(ActionListener listener) {
        eventListenerList.add(ActionListener.class, listener);
    }

    public void removeActionListener(ActionListener listener) {
        eventListenerList.remove(ActionListener.class, listener);
    }

    public Color getPassiveBackColor() {
        return passiveBackColor;
    }

    public void setPassiveBackColor(Color passiveBackColor) {
        this.passiveBackColor = passiveBackColor;
        repaint();
    }

    public Color getPassiveForeColor() {
        return passiveForeColor;
    }

    public void setPassiveForeColor(Color passiveForeColor) {
        this.passiveForeColor = passiveForeColor;
        repaint();
    }

    public Color getBackColor() {
        return backColor;
    }

    public void setBackColor(Color backColor) {
        this.backColor = backColor;
        repaint();
    }

    public Color getBackDisabledColor() {
        return backDisabledColor;
    }

    private Color getForeDisabledColor() {
        return foreDisabledColor;
    }

    public Color getArmedBackColor() {
        return armedBackColor;
    }

    public void setArmedBackColor(Color armedBackColor) {
        this.armedBackColor = armedBackColor;
    }

    public Color getArmedForeColor() {
        return armedForeColor;
    }

    public void setArmedForeColor(Color armedForeColor) {
        this.armedForeColor = armedForeColor;
    }

    public Color getForeColor() {
        return foreColor;
    }

    public void setForeColor(Color foreColor) {
        this.foreColor = foreColor;
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        scale = Math.min(width, height) / DEFAULT_SIZE;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.scale(scale, scale);
        double width = getWidth() / scale;
        double height = getHeight() / scale;
        /*
         * Drawing Background
         */
        g2d.setColor(getPlayed() > NO_PLAYER_PLAYED ? getBackDisabledColor() : !isEnabled() ? getBackDisabledColor() : armed ? getArmedBackColor() : getBackColor());
        g2d.fillRoundRect(0, 0, (int) width, (int) height, 5, 5);


        /*
         * Drawing cross or circle if necessary
         */
        g2d.setColor(getPlayed() > NO_PLAYER_PLAYED ? getForeDisabledColor() : !isEnabled() ? getForeDisabledColor() : armed ? getArmedForeColor() : getForeColor());
        g2d.setStroke(new BasicStroke(3.0F));
        
        double x = (width - DRAWING_WIDTH) / 2;
        double y = (height - DRAWING_HEIGHT) / 2;

        if (getPlayed() == PLAYER1_PLAYED) {
            //Draw CROSS
            g2d.drawLine((int)x, (int)y, (int)(x+DRAWING_WIDTH), (int)(y+DRAWING_HEIGHT));
            g2d.drawLine((int)(x+DRAWING_WIDTH), (int)y, (int)x, (int)(y+DRAWING_HEIGHT));
            
        } else if (getPlayed() == PLAYER2_PLAYED) {
            //Draw CIRCLE
            g2d.drawOval((int)x, (int)y, DRAWING_WIDTH, DRAWING_HEIGHT);
        }
        /*
         * Drawing border
         */

        g2d.drawRoundRect(0, 0, (int) width, (int) height, 5, 5);

        /*
         * Drawing Value if necessary
         */
        if (isValueDisplayed()) {
            g2d.setColor(getForeColor());
            Font font = g2d.getFont().deriveFont(32.0F);
            g2d.setFont(font);
            FontRenderContext fontRenderContext = new FontRenderContext(g2d.getTransform(), true, false);
            Rectangle2D r2d = font.getStringBounds(Integer.toString(getValue()), fontRenderContext);
            float xText = (float) ((float) getWidth() / scale / 2 - r2d.getWidth() / 2 - r2d.getX());
            float yText = (float) ((float) getHeight() / scale / 2 - r2d.getHeight() / 2 - r2d.getY());
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4F));
            g2d.drawString(Integer.toString(getValue()), xText, yText);
        }


    }
}
