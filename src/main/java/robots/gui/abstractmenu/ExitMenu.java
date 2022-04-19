package robots.gui.abstractmenu;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ExitMenu {

    public JMenu generateMenuExit(ActionListener actionListener){
        JMenu exitMenu = new JMenu("Действия");
        JMenuItem exitButton = new JMenuItem("Выход из приложения", KeyEvent.VK_S);
        exitButton.addActionListener(actionListener);
        exitMenu.add(exitButton);
        return exitMenu;
    }
}
