package markingmenu.listener;

import java.awt.event.MouseEvent;

/**
 * @author Nathan
 * @author Jean-Luc
 */
public interface MarkingMenuItemExtendListener {

    public void actionMarkingMenuItemClicked(int position, MouseEvent e);

    public void actionMarkingMenuItemEntered(int position, MouseEvent e);

    public void actionMarkingMenuItemExited(int position, MouseEvent e);
}
