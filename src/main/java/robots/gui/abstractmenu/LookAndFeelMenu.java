package robots.gui.abstractmenu;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import static javax.swing.UIManager.setLookAndFeel;

public class LookAndFeelMenu extends JFrame{
    public JMenu lookAndFeelMenu;
    private JMenuItem systemLookAndFeel;
    private JMenuItem crossplatformLookAndFeel;
    public JMenu generateLookAndFeelMenu(){
        lookAndFeelMenu = new JMenu(ResourceBundle.getBundle("locale").getString("title.viewMode"));
        systemLookAndFeel = new JMenuItem(ResourceBundle.getBundle("locale").getString("text.systemScheme"), KeyEvent.VK_S);
        systemLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            this.invalidate();
        });
        crossplatformLookAndFeel = new JMenuItem(ResourceBundle.getBundle("locale").getString("text.universalScheme"), KeyEvent.VK_S);
        crossplatformLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            this.invalidate();
        });
        lookAndFeelMenu.add(crossplatformLookAndFeel);
        lookAndFeelMenu.add(systemLookAndFeel);
        return lookAndFeelMenu;
    }

    public void changeLocale(){
        lookAndFeelMenu.setText(ResourceBundle.getBundle("locale").getString("title.viewMode"));
        systemLookAndFeel.setText(ResourceBundle.getBundle("locale").getString("text.systemScheme"));
        crossplatformLookAndFeel.setText(ResourceBundle.getBundle("locale").getString("text.universalScheme"));
    }


    private void setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            // just ignore
        }
    }
}
