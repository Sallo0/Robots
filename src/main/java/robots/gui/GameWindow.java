package robots.gui;

import robots.gui.abstractmenu.DialogGenerator;
import robots.gui.storemanager.WindowState;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.util.ResourceBundle;

public class GameWindow extends JInternalFrame implements ILocalable,ISaveable {
    private final DialogGenerator exitDialog = new DialogGenerator();

    public GameWindow() {
        super(ResourceBundle.getBundle("locale").getString("title.gameField"),
                true,
                true,
                true,
                true
        );
        GameVisualizer m_visualizer = new GameVisualizer();
        add(m_visualizer);
        pack();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                exitDialog.windowExitDialog(e);
            }
        });
        LocalableComponents.components.add(this);
    }

    public void setParams(WindowState windowState) {
        this.setSize(windowState.getWidth(), windowState.getHeight());
        this.setLocation(windowState.getLocationX(), windowState.getLocationY());
        try {
            this.setIcon(windowState.isIcon());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeLocale() {
        this.setTitle(ResourceBundle.getBundle("locale").getString("title.gameField"));
    }

    @Override
    public WindowState windowParams() {
        return new WindowState(
                getWidth(),
                getHeight(),
                getX(),
                getY(),
                isIcon()
        );
    }

}

