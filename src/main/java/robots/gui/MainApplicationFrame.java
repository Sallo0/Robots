package robots.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import robots.gui.abstractmenu.*;

import robots.gui.storemanager.WindowState;
import robots.log.Logger;
import robots.storage.FileStorage;

import java.util.ResourceBundle;

public class MainApplicationFrame extends JFrame {
    private final String file = new String(System.getProperty("user.home") + File.separator + "robotState");
    private final JDesktopPane desktopPane = new JDesktopPane();
    private final FileStorage storage = new FileStorage(file);
    private final WindowCoordinate coordinateListenerWindow = new WindowCoordinate();
    private final GameWindow gameWindow = new GameWindow();
    private final LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
    private final LookAndFeelMenu lookAndFeelMenu = new LookAndFeelMenu();
    private final TestsMenu testsMenu = new TestsMenu();
    private final ExitMenu exitMenu = new ExitMenu();
    private final LocaleMenu localeMenu = new LocaleMenu();


    public MainApplicationFrame() {
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);
        setContentPane(desktopPane);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                quitListener();
            }
        });
        coordinateListenerWindow.setSize(300, 300);
        addWindow(coordinateListenerWindow);
        createLogWindow();
        addWindow(logWindow);
        createGameWindow();
        addWindow(gameWindow);
        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    public void quitListener() {
        UIManager.put("OptionPane.yesButtonText",
                ResourceBundle.getBundle("locale").getString("text.yes"));
        UIManager.put("OptionPane.noButtonText",
                ResourceBundle.getBundle("locale").getString("text.no"));

        int userAnswer = JOptionPane.showConfirmDialog(this,
                ResourceBundle.getBundle("locale").getString("text.exitAsk"),
                ResourceBundle.getBundle("locale").getString("title.confirmExit"),
                JOptionPane.YES_NO_OPTION);

        if (userAnswer == JOptionPane.YES_OPTION) {
            saveParamsToFile();
            System.exit(0);
        }
    }

    protected void createGameWindow() {
        var gameWindowParams = storage.getState("gameWindow");
        gameWindow.setSize(gameWindowParams.getWidth(), gameWindowParams.getHeight());
        gameWindow.setLocation(gameWindowParams.getLocationX(), gameWindowParams.getLocationY());
        try {
            gameWindow.setIcon(gameWindowParams.isIcon());
        } catch (Exception e) {
        }
    }

    protected void createLogWindow() {
        var logWindowParams = storage.getState("logWindow");
        logWindow.setLocation(logWindowParams.getLocationX(), logWindowParams.getLocationY());
        logWindow.setSize(logWindowParams.getWidth(), logWindowParams.getHeight());
        try {
            logWindow.setIcon(logWindowParams.isIcon());
        } catch (Exception e) {

        }
        Logger.debug(ResourceBundle.getBundle("locale").getString("text.protocolWorks"));
    }

    protected void addWindow(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private ItemListener onChange() {
        return e -> SwingUtilities.invokeLater(() -> {
            logWindow.setTitle(ResourceBundle.getBundle("locale").getString("text.protocolOfWork"));
            gameWindow.setTitle(ResourceBundle.getBundle("locale").getString("title.gameField"));
            coordinateListenerWindow.setTitle(ResourceBundle.getBundle("locale").getString("title.coordinateCheck"));
            lookAndFeelMenu.changeLocale();
            testsMenu.changeLocale();
            localeMenu.changeLocale();
            exitMenu.changeLocale();
        });
    }

    private JMenuBar generateMenuBar() {
        ActionListener quit = (event) -> quitListener();
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(lookAndFeelMenu.generateLookAndFeelMenu());
        menuBar.add(testsMenu.generateTestsMenu());
        menuBar.add(localeMenu.generateLocaleMenu(onChange()));
        menuBar.add(exitMenu.generateMenuExit(quit));
        return menuBar;
    }

    private void saveParamsToFile() {
        storage.setState("gameWindow", new WindowState(
                gameWindow.getWidth(),
                gameWindow.getHeight(),
                gameWindow.getX(),
                gameWindow.getY(),
                gameWindow.isIcon()));
        storage.setState("logWindow", new WindowState(
                logWindow.getWidth(),
                logWindow.getHeight(),
                logWindow.getX(),
                logWindow.getY(),
                logWindow.isIcon()));
        storage.saveToFile(file);
    }
}
