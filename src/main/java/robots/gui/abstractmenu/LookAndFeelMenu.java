package robots.gui.abstractmenu;

import robots.gui.ILocalable;
import robots.gui.LocalableComponents;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class LookAndFeelMenu extends JFrame implements ILocalable {
    private final JMenuItem systemLookAndFeel = new JMenuItem(ResourceBundle.getBundle("locale").getString("text.systemScheme"), KeyEvent.VK_S);
    private final JMenuItem crossplatformLookAndFeel = new JMenuItem(ResourceBundle.getBundle("locale").getString("text.universalScheme"), KeyEvent.VK_S);
    public JMenu lookAndFeelMenu = new JMenu(ResourceBundle.getBundle("locale").getString("title.viewMode"));

    public LookAndFeelMenu(){
        LocalableComponents.components.add(this);
    }

    public JMenu generateLookAndFeelMenu() {
        systemLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            this.invalidate();
        });

        crossplatformLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            this.invalidate();
        });

        lookAndFeelMenu.add(crossplatformLookAndFeel);
        lookAndFeelMenu.add(systemLookAndFeel);
        return lookAndFeelMenu;
    }

    public void changeLocale() {
        lookAndFeelMenu.setText(ResourceBundle.getBundle("locale").getString("title.viewMode"));
        systemLookAndFeel.setText(ResourceBundle.getBundle("locale").getString("text.systemScheme"));
        crossplatformLookAndFeel.setText(ResourceBundle.getBundle("locale").getString("text.universalScheme"));
    }

    private void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // just ignore
        }
    }
}
