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


    private int dialogTemplate(String title, String text, EventObject eventObject) {
        return JOptionPane.showOptionDialog(
                ((Component) eventObject.getSource()),
                text,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
    }

    public void changeLocale() {
        options[0] = ResourceBundle.getBundle("locale").getString("text.yes");
        options[1] = ResourceBundle.getBundle("locale").getString("text.no");
    }

    public int appExitDialogResult(EventObject eventObject) {
        return dialogTemplate(
                ResourceBundle.getBundle("locale").getString("title.confirmExit"),
                ResourceBundle.getBundle("locale").getString("text.exitAsk"),
                eventObject
        );
    }

    public int windowsRecoverDialogResult(EventObject eventObject) {
        return dialogTemplate(
                ResourceBundle.getBundle("locale").getString("title.confirmWinRestore"),
                ResourceBundle.getBundle("locale").getString("text.winRestoreAsk"),
                eventObject
        );
    }

    public void windowExitDialog(EventObject eventObject) {
        int dialogResult = dialogTemplate(
                ResourceBundle.getBundle("locale").getString("text.confirm"),
                ResourceBundle.getBundle("locale").getString("text.closeWindowAsk"),
                eventObject
        );
        if (dialogResult == JOptionPane.YES_OPTION) {
            if ((eventObject.getSource()).getClass() == LogWindow.class) {
                ((Component) eventObject.getSource()).setVisible(false);
            }
            if ((eventObject.getSource()).getClass() == GameWindow.class) {
                ((Component) eventObject.getSource()).setVisible(false);
            }
        }
    }
}

