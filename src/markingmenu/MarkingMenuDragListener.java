/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markingmenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author nathan
 */
public class MarkingMenuDragListener implements java.awt.event.MouseMotionListener, MouseListener {
    private MarkingMenu markingMenu;
    
    public MarkingMenuDragListener (MarkingMenu mm) {
        markingMenu = mm;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("DRAGGED");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //lancer le timer
        System.out.println("PRESSED");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("RELEASED");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    
}
