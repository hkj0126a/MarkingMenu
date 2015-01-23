/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markingmenu;

import java.awt.event.MouseEvent;

/**
 *
 * @author nathan
 */
public interface MarkingMenuItemPrivateListener {

    public void actionMarkingMenuClicked(int position, MouseEvent e);

    public void actionMarkingMenuItemEntered(int position, MouseEvent e);

    public void actionMarkingMenuItemExited(int position, MouseEvent e);
}
