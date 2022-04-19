package robots.gui.abstractmenu;

import robots.gui.GameWindow;
import robots.gui.LogWindow;

import javax.swing.*;
import java.awt.*;
import java.util.EventObject;

public class GenerateExitButton {

    public void generateUniversalExitButton(EventObject eventObject) {

        Object[] options = {"Да", "Нет!"};
        int n = JOptionPane
                .showOptionDialog(((Component) eventObject.getSource()), "Закрыть окно?",
                        "Подтверждение", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);

        if (n == 0) {

            if ((eventObject.getSource()).getClass() == LogWindow.class) {
                ((Component) eventObject.getSource()).setVisible(false);
            }

            if (((eventObject.getSource()).getClass() == JMenuItem.class) || ((eventObject.getSource()).getClass() == JButton.class)){
                System.exit(0);
            }


            if ((eventObject.getSource()).getClass() == GameWindow.class) {
                ((Component) eventObject.getSource()).setVisible(false);
            }
        }
    }
}

