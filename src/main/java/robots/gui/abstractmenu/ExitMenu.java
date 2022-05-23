package robots.gui.abstractmenu;

import robots.gui.ILocalable;
import robots.gui.LocalableComponents;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class ExitMenu implements ILocalable {
    private JMenu exitMenu;
    private JMenuItem exitButton;

    public ExitMenu(){
        LocalableComponents.components.add(this);
    }

    public JMenu generateMenuExit(ActionListener actionListener) {
        exitMenu = new JMenu(ResourceBundle.getBundle("locale").getString("title.actions"));
        exitButton = new JMenuItem(ResourceBundle.getBundle("locale").getString("text.exit"), KeyEvent.VK_S);
        exitButton.addActionListener(actionListener);
        exitMenu.add(exitButton);
        return exitMenu;
    }

    public void changeLocale() {
        exitMenu.setText(ResourceBundle.getBundle("locale").getString("title.actions"));
        exitButton.setText(ResourceBundle.getBundle("locale").getString("text.exit"));
    }
}
