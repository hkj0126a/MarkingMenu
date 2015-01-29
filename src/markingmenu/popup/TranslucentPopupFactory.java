package markingmenu.popup;

import java.awt.Component;
import javax.swing.Popup;
import javax.swing.PopupFactory;

/**
 * @author Nathan
 * @author Jean-Luc
 */
public class TranslucentPopupFactory extends PopupFactory {

    @Override
    public Popup getPopup(Component owner, Component contents, int x, int y)
            throws IllegalArgumentException {
        return new TranslucentPopup(owner, contents, x, y);
    }

}
