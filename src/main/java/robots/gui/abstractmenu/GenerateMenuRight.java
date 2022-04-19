package robots.gui.abstractmenu;

import robots.log.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class GenerateMenuRight{

    public JMenu generateMenuRight(){
        JMenu testMenu = new JMenu("Тесты");
        testMenu.setMnemonic(KeyEvent.VK_T);
        testMenu.getAccessibleContext().setAccessibleDescription(
                "Тестовые команды");

        JMenuItem addLogMessageItem = new JMenuItem("Сообщение в лог", KeyEvent.VK_S);
        addLogMessageItem.addActionListener((event) -> {
            Logger.debug("Новая строка");
        });

        testMenu.add(addLogMessageItem);
        return testMenu;

    }
}
