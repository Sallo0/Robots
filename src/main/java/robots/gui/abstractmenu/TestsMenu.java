package robots.gui.abstractmenu;

import robots.log.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class TestsMenu {
    private JMenu testsMenu;
    private JMenuItem addLogMessageItem;
    public JMenu generateTestsMenu(){
        testsMenu = new JMenu(ResourceBundle.getBundle("locale").getString("title.tests"));
        testsMenu.setMnemonic(KeyEvent.VK_T);
        testsMenu.getAccessibleContext().setAccessibleDescription(
                ResourceBundle.getBundle("locale").getString("text.testCommands"));

        addLogMessageItem = new JMenuItem(ResourceBundle.getBundle("locale").getString("title.log"), KeyEvent.VK_S);
        addLogMessageItem.addActionListener((event) -> {
            Logger.debug(ResourceBundle.getBundle("locale").getString("text.newString"));
        });

        testsMenu.add(addLogMessageItem);
        return testsMenu;
    }

    public void changeLocale(){
        testsMenu.setText(ResourceBundle.getBundle("locale").getString("title.tests"));
        testsMenu.getAccessibleContext().setAccessibleDescription(
                ResourceBundle.getBundle("locale").getString("text.testCommands"));
        addLogMessageItem.setText(ResourceBundle.getBundle("locale").getString("title.log"));
    }
}
