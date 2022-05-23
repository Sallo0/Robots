package robots.gui.abstractmenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class LookAndFeelMenu extends AbstractMenu {
    private final JMenuItem systemLookAndFeel = new JMenuItem(ResourceBundle.getBundle("locale").getString("text.systemScheme"), KeyEvent.VK_S);
    private final JMenuItem crossplatformLookAndFeel = new JMenuItem(ResourceBundle.getBundle("locale").getString("text.universalScheme"), KeyEvent.VK_S);
    public JMenu lookAndFeelMenu = new JMenu(ResourceBundle.getBundle("locale").getString("title.viewMode"));

    public JMenu generateLookAndFeelMenu() {
        systemLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getSystemLookAndFeelClassName(), event);
            ((JMenuItem) event.getSource()).getParent().validate();
        });

        crossplatformLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName(), event);
            ((JMenuItem) event.getSource()).getParent().validate();
        });

        lookAndFeelMenu.add(crossplatformLookAndFeel);
        lookAndFeelMenu.add(systemLookAndFeel);
        return lookAndFeelMenu;
    }

    @Override
    public void changeLocale() {
        lookAndFeelMenu.setText(ResourceBundle.getBundle("locale").getString("title.viewMode"));
        systemLookAndFeel.setText(ResourceBundle.getBundle("locale").getString("text.systemScheme"));
        crossplatformLookAndFeel.setText(ResourceBundle.getBundle("locale").getString("text.universalScheme"));
    }

    private void setLookAndFeel(String className, ActionEvent event) {
        try {
            UIManager.setLookAndFeel(className);
//            SwingUtilities.updateComponentTreeUI((JMenuItem)event.getSource());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // just ignore
        }
    }
}
