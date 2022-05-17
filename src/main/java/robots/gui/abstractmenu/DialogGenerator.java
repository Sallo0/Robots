package robots.gui.abstractmenu;

import robots.gui.GameWindow;
import robots.gui.LogWindow;

import javax.swing.*;
import java.awt.*;
import java.util.EventObject;
import java.util.ResourceBundle;

public class DialogGenerator {
    private static final Object[] options = {
            ResourceBundle.getBundle("locale").getString("text.yes"),
            ResourceBundle.getBundle("locale").getString("text.no")
    };

    public void changeLocale() {
        options[0] = ResourceBundle.getBundle("locale").getString("text.yes");
        options[1] = ResourceBundle.getBundle("locale").getString("text.no");
    }

    public int appExitDialogResult(EventObject eventObject) {
        return JOptionPane.showOptionDialog(
                ((Component) eventObject.getSource()),
                ResourceBundle.getBundle("locale").getString("text.exitAsk"),
                ResourceBundle.getBundle("locale").getString("title.confirmExit"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
    }

    public int windowsRecoverDialogResult(EventObject eventObject) {
        return JOptionPane.showOptionDialog(
                ((Component) eventObject.getSource()),
                ResourceBundle.getBundle("locale").getString("text.winRestoreAsk"),
                ResourceBundle.getBundle("locale").getString("title.confirmWinRestore"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
    }

    public void windowExitDialog(EventObject eventObject) {
        int n = JOptionPane.showOptionDialog(
                ((Component) eventObject.getSource()),
                ResourceBundle.getBundle("locale").getString("text.closeWindowAsk"),
                ResourceBundle.getBundle("locale").getString("text.confirm"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (n == 0) {

            if ((eventObject.getSource()).getClass() == LogWindow.class) {
                ((Component) eventObject.getSource()).setVisible(false);
            }

            if ((eventObject.getSource()).getClass() == GameWindow.class) {
                ((Component) eventObject.getSource()).setVisible(false);
            }
        }
    }
}

