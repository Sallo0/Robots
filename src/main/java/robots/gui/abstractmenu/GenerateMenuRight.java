package robots.gui.abstractmenu;

import robots.log.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class GenerateMenuRight{

    public JMenu generateMenuRight(){
        JMenu testMenu = new JMenu(ResourceBundle.getBundle("locale").getString("title.tests"));
        testMenu.setMnemonic(KeyEvent.VK_T);
        testMenu.getAccessibleContext().setAccessibleDescription(
                ResourceBundle.getBundle("locale").getString("text.testCommands"));

        JMenuItem addLogMessageItem = new JMenuItem(ResourceBundle.getBundle("locale").getString("title.log"), KeyEvent.VK_S);
        addLogMessageItem.addActionListener((event) -> {
            Logger.debug(ResourceBundle.getBundle("locale").getString("text.newString"));
        });

        testMenu.add(addLogMessageItem);
        return testMenu;

    }
}
