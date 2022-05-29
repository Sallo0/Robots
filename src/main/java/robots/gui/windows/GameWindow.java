package robots.gui.windows;

import robots.gui.GameVisualizer;
import robots.gui.abstractmenu.DialogGenerator;
import robots.logic.RobotConstants;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.util.ResourceBundle;

public class GameWindow extends AbstractWindow {

    public GameWindow(RobotConstants robotConstants) {
        super(ResourceBundle.getBundle("locale").getString("title.gameField"),
                true,
                true,
                true,
                true
        );
        GameVisualizer m_visualizer = new GameVisualizer(robotConstants);
        add(m_visualizer);
        pack();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addInternalFrameListener(new InternalFrameAdapter() {
                                     @Override
                                     public void internalFrameClosing(InternalFrameEvent e) {
                                         DialogGenerator.windowExitDialog(e);
                                     }
                                 }
        );
    }

    @Override
    public void changeLocale() {
        this.setTitle(ResourceBundle.getBundle("locale").getString("title.gameField"));
    }
}

