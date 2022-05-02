package robots.gui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import robots.gui.abstractmenu.*;

import robots.gui.storemanager.WindowState;
import robots.log.Logger;
import robots.storage.FileStorage;

import java.util.ResourceBundle;

public class MainApplicationFrame extends JFrame
{
    private final String file = new String(System.getProperty("user.home") + File.separator + "robotState");
    private final JDesktopPane desktopPane = new JDesktopPane();
    private FileStorage storage = new FileStorage(file);
    private WindowCoordinate coordinaLisener = new WindowCoordinate();
    private final GameWindow gameWindow = new GameWindow(coordinaLisener);
    private final LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());


    public void quitListener() {
        UIManager.put("OptionPane.yesButtonText", ResourceBundle.getBundle("locale").getString("text.yes"));
        UIManager.put("OptionPane.noButtonText", ResourceBundle.getBundle("locale").getString("text.no"));

        int userAnswer = JOptionPane.showConfirmDialog(
                this,
                ResourceBundle.getBundle("locale").getString("text.exitAsk"),
                ResourceBundle.getBundle("locale").getString("title.confirmExit"),
                JOptionPane.YES_NO_OPTION);


        if (userAnswer == JOptionPane.YES_OPTION) {
            storage.setState("gameWindow", new WindowState(
                    gameWindow.getWidth(),
                    gameWindow.getHeight(),
                    gameWindow.getX(),
                    gameWindow.getY(),
                    gameWindow.isClosed()));
            storage.setState("logWindow", new WindowState(
                    logWindow.getWidth(),
                    logWindow.getHeight(),
                    logWindow.getX(),
                    logWindow.getY(),
                    logWindow.isClosed()));
            storage.saveToFile(file);
            System.exit(0);
        }
    }

    public MainApplicationFrame(FileStorage str) {
        //Make the big window be indented 50 pixels from each edge
        //of the screen.

        int inset = 50;
        //Добавил
        GenerateExitButton exitButton = new GenerateExitButton();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width  - inset*2,
                screenSize.height - inset*2);

        setContentPane(desktopPane);

        coordinaLisener.setSize(300,300);
        addWindow(coordinaLisener);

        LogWindow logWindow = createLogWindow();
        logWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        logWindow.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                exitButton.generateUniversalExitButton(e);
            }

        });
        addWindow(logWindow);

        var gameWindowParams = storage.getState("gameWindow");
        gameWindow.setSize(gameWindowParams.getWidth(), gameWindowParams.getHeight());
        gameWindow.setLocation(gameWindowParams.getLocationX(), gameWindowParams.getLocationY());
        try {
            gameWindow.setClosed(gameWindowParams.isClosed());
        } catch (Exception e) {
        }
        gameWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        gameWindow.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                exitButton.generateUniversalExitButton(e);
            }
        });
        addWindow(gameWindow);


        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    protected LogWindow createLogWindow()
    {
        var logWindowParams = storage.getState("logWindow");
        logWindow.setLocation(logWindowParams.getLocationX(), logWindowParams.getLocationY());
        logWindow.setSize(logWindowParams.getWidth(), logWindowParams.getHeight());
        try {
            logWindow.setClosed(logWindowParams.isClosed());
        } catch (Exception e) {

        }
        setMinimumSize(logWindow.getSize());

        Logger.debug(ResourceBundle.getBundle("locale").getString("text.protocolWorks"));
        return logWindow;
    }

    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }


    private JMenuBar generateMenuBar()
    {
        ItemListener onChange = e -> {
            this.getRootPane().getParent().setVisible(false);
            SwingUtilities.invokeLater(() -> {
                storage.setState("gameWindow", new WindowState(
                        gameWindow.getWidth(),
                        gameWindow.getHeight(),
                        gameWindow.getX(),
                        gameWindow.getY(),
                        gameWindow.isClosed()));
                storage.setState("logWindow", new WindowState(
                        logWindow.getWidth(),
                        logWindow.getHeight(),
                        logWindow.getX(),
                        logWindow.getY(),
                        logWindow.isClosed()));
                storage.saveToFile(file);
                MainApplicationFrame frame = new MainApplicationFrame(new FileStorage(file));
                frame.pack();
                frame.setVisible(true);
                frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            });
        };
        ActionListener quit = (event) -> quitListener();
        JMenuBar menuBar = new JMenuBar();
        GenerateMenuLeft menuLeft = new GenerateMenuLeft();
        GenerateMenuRight menuRight = new GenerateMenuRight();
        ExitMenu exitMenu = new ExitMenu();
        GenerateLocaleButton localeMenu = new GenerateLocaleButton();

        menuBar.add(menuLeft.generateMenuLeft());
        menuBar.add(menuRight.generateMenuRight());
        menuBar.add(localeMenu.generateLocaleButton(onChange));
        menuBar.add(exitMenu.generateMenuExit(quit));
        return menuBar;
    }
}
