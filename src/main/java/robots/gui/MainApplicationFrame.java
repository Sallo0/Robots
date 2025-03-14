package robots.gui;

import robots.gui.abstractmenu.*;
import robots.gui.windows.CoordinateWindow;
import robots.gui.windows.GameWindow;
import robots.gui.windows.LogWindow;
import robots.locale.ILocalable;
import robots.locale.LocalableComponents;
import robots.log.Logger;
import robots.storage.FileStorage;
import robots.storage.ISaveable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.EventObject;

public class MainApplicationFrame extends JFrame {
    private final String file = System.getProperty("user.home") + File.separator + "robotState";
    private final JDesktopPane desktopPane = new JDesktopPane();
    private final CoordinateWindow coordinateListenerWindow = new CoordinateWindow();
    private final GameWindow gameWindow = new GameWindow();
    private final LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
    private final LookAndFeelMenu lookAndFeelMenu = new LookAndFeelMenu();
    private final TestsMenu testsMenu = new TestsMenu();
    private final ExitMenu exitMenu = new ExitMenu();
    private final LocaleMenu localeMenu = new LocaleMenu();
    private final DialogGenerator dialogGenerator = new DialogGenerator();
    private FileStorage storage = new FileStorage();


    public MainApplicationFrame() {
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);
        setContentPane(desktopPane);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                quit(e);
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                storage = dialogGenerator.windowsRecoverDialogResult(e) == JOptionPane.NO_OPTION
                        ? new FileStorage()
                        : new FileStorage(file);
                logWindow.setParams(storage.getState(logWindow.getClass().getName()));
                gameWindow.setParams(storage.getState(gameWindow.getClass().getName()));
                coordinateListenerWindow.setParams(storage.getState(coordinateListenerWindow.getClass().getName()));
                addWindow(logWindow);
                addWindow(gameWindow);
                addWindow(coordinateListenerWindow);
            }
        });
        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    private void addWindow(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private ItemListener onChange() {
        return e -> SwingUtilities.invokeLater(() -> {
            for (ILocalable component : LocalableComponents.components) {
                component.changeLocale();
            }
        });
    }

    private void quit(EventObject e) {
        if (dialogGenerator.appExitDialogResult(e) == JOptionPane.YES_OPTION) {
            saveParamsToFile();
            System.exit(0);
        }
    }

    private JMenuBar generateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(lookAndFeelMenu.generateLookAndFeelMenu());
        menuBar.add(testsMenu.generateTestsMenu());
        menuBar.add(localeMenu.generateLocaleMenu(onChange()));
        menuBar.add(exitMenu.generateMenuExit(this::quit));
        return menuBar;
    }

    private void saveParamsToFile() {
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            storage.setState(frame.getClass().getName(), ((ISaveable) frame).windowParams());
        }
        storage.saveToFile(file);
    }
}
