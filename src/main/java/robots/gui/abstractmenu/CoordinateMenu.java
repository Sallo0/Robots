package robots.gui.abstractmenu;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class CoordinateMenu extends AbstractMenu {

    private JMenu coordinateMenu;
    private JMenuItem addPointButton;

    public JMenu generateCoordinateMenu(ActionListener actionListener) {
        coordinateMenu = new JMenu(ResourceBundle.getBundle("locale").getString("title.actions"));
        addPointButton = new JMenuItem(ResourceBundle.getBundle("locale").getString("text.addTarget"), KeyEvent.VK_S);
        addPointButton.addActionListener(actionListener);
        coordinateMenu.add(addPointButton);
        return coordinateMenu;
    }

    @Override
    public void changeLocale() {
        coordinateMenu.setText(ResourceBundle.getBundle("locale").getString("title.actions"));
        addPointButton.setText(ResourceBundle.getBundle("locale").getString("text.addTarget"));
    }
}
