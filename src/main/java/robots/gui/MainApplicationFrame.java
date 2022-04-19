package robots.gui;


import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import robots.storage.FileStorage;
import robots.gui.storemanager.WindowState;
import robots.gui.abstractmenu.ExitMenu;
import robots.gui.abstractmenu.GenerateMenuLeft;
import robots.gui.abstractmenu.GenerateMenuRight;
import robots.gui.abstractmenu.GenerateExitButton;

import robots.log.Logger;

/**
 * Что требуется сделать:
 * 1. Метод создания меню перегружен функционалом и трудно читается.
 * Следует разделить его на серию более простых методов (или вообще выделить отдельный класс).
 */
public class MainApplicationFrame extends JFrame {


    private final String file = new String(System.getProperty("user.home") + File.separator + "robotState");
    private final JDesktopPane desktopPane = new JDesktopPane();
    private final FileStorage storage = new FileStorage(file);
    private final GameWindow gameWindow = new GameWindow();
    private final LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());


    public MainApplicationFrame() {
        //Make the big window be indented 50 pixels from each edge
        //of the screen.

        int inset = 50;
        //Добавил
        GenerateExitButton exitButton = new GenerateExitButton();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2);

        setContentPane(desktopPane);


        LogWindow logWindow = createLogWindow();
        logWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        logWindow.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                exitButton.generateUniversalExitButton(e);
            }

        });
        addWindow(logWindow);

        WindowCoordinate windowCoordinate = new WindowCoordinate();
        windowCoordinate.setSize(400, 400);
        windowCoordinate.setLocation(830, 20);

        addWindow(windowCoordinate);

        var gameWindowParams = storage.getState("gameWindow");
        gameWindow.setSize(gameWindowParams.getWidth(), gameWindowParams.getHeight());
        gameWindow.setLocation(gameWindowParams.getLocationX(), gameWindowParams.getLocationY());
        try {
            gameWindow.setClosed(gameWindowParams.isClosed());
        } catch (Exception ignored) {
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

    public void quitListener() {
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");

        int userAnswer = JOptionPane.showConfirmDialog(
                this,
                "Выйти?",
                "Подтвердите выход",
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


    protected void windowCoordinate() {

    }

    protected LogWindow createLogWindow() {
        var logWindowParams = storage.getState("logWindow");
        logWindow.setLocation(logWindowParams.getLocationX(), logWindowParams.getLocationY());
        logWindow.setSize(logWindowParams.getWidth(), logWindowParams.getHeight());
        try {
            logWindow.setClosed(logWindowParams.isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setMinimumSize(logWindow.getSize());

        Logger.debug("Протокол работает");
        return logWindow;
    }

    protected void addWindow(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setVisible(true);
    }


    private JMenuBar generateMenuBar() {
        ActionListener quit = (event) -> quitListener();
        JMenuBar menuBar = new JMenuBar();
        GenerateMenuLeft menuLeft = new GenerateMenuLeft();
        GenerateMenuRight menuRight = new GenerateMenuRight();
        ExitMenu exitMenu = new ExitMenu();

        menuBar.add(menuLeft.generateMenuLeft());
        menuBar.add(menuRight.generateMenuRight());
        menuBar.add(exitMenu.generateMenuExit(quit));
        return menuBar;
    }
}
