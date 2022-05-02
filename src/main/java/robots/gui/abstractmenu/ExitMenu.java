package robots.gui.abstractmenu;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import static javax.swing.UIManager.setLookAndFeel;

public class ExitMenu {

    public JMenu generateMenuExit(ActionListener actionListener){
        JMenu exitMenu = new JMenu(ResourceBundle.getBundle("locale").getString("title.actions"));
        JMenuItem exitButton = new JMenuItem(ResourceBundle.getBundle("locale").getString("text.exit"), KeyEvent.VK_S);
        exitButton.addActionListener(actionListener);
        exitMenu.add(exitButton);
        return exitMenu;
    }
}
