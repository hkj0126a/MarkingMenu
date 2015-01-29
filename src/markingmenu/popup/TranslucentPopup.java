
package markingmenu.popup;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JWindow;
import javax.swing.Popup;

/**
 * @author Nathan
 * @author Jean-Luc
 */
public class TranslucentPopup extends Popup {

    JWindow popupWindow;

    TranslucentPopup(Component owner, Component contents, int ownerX, int ownerY) {
        this.popupWindow = new JWindow();
        com.sun.awt.AWTUtilities.setWindowOpacity(popupWindow, 1f);
        popupWindow.setLocation(ownerX, ownerY);
        popupWindow.getContentPane().add(contents, BorderLayout.CENTER);
        contents.invalidate();
        JComponent parent = (JComponent) contents.getParent();
    }

    @Override
    public void show() {
        this.popupWindow.setVisible(true);
        this.popupWindow.pack();
        com.sun.awt.AWTUtilities.setWindowOpaque(this.popupWindow, false);
    }

    @Override
    public void hide() {
        this.popupWindow.setVisible(false);
        this.popupWindow.removeAll();
        this.popupWindow.dispose();
    }

}
