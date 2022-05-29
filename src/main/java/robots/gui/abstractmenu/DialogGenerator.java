package robots.gui.abstractmenu;

import robots.gui.windows.GameWindow;
import robots.gui.windows.LogWindow;

import javax.swing.*;
import java.awt.*;
import java.util.EventObject;
import java.util.ResourceBundle;

public class DialogGenerator {

    private static int dialogTemplate(String title, String text, EventObject eventObject) {
        return JOptionPane.showOptionDialog(
                ((Component) eventObject.getSource()),
                text,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{
                        ResourceBundle.getBundle("locale").getString("text.yes"),
                        ResourceBundle.getBundle("locale").getString("text.no")
                },
                ResourceBundle.getBundle("locale").getString("text.yes")
        );
    }

    public static int appExitDialogResult(EventObject eventObject) {
        return dialogTemplate(
                ResourceBundle.getBundle("locale").getString("title.confirmExit"),
                ResourceBundle.getBundle("locale").getString("text.exitAsk"),
                eventObject
        );
    }

    public static int windowsRecoverDialogResult(EventObject eventObject) {
        return dialogTemplate(
                ResourceBundle.getBundle("locale").getString("title.confirmWinRestore"),
                ResourceBundle.getBundle("locale").getString("text.winRestoreAsk"),
                eventObject
        );
    }

    public static void windowExitDialog(EventObject eventObject) {
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

    public static Point pointParamsDialogResult() {
        JFrame frame = new JFrame();
        SpinnerModel spinnerModelX = new SpinnerNumberModel();
        SpinnerModel spinnerModelY = new SpinnerNumberModel();
        JSpinner spinnerX = new JSpinner(spinnerModelX);
        JSpinner spinnerY = new JSpinner(spinnerModelY);

        JComponent[] inputs = new JComponent[]{
                spinnerX,
                spinnerY
        };
        int result = JOptionPane.showConfirmDialog(
                frame,
                inputs,
                ResourceBundle.getBundle("locale").getString("title.enterCordDialog"),
                JOptionPane.DEFAULT_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            return new Point((Integer) spinnerX.getValue(), (Integer) spinnerY.getValue());
        }
        return null;
    }
}

