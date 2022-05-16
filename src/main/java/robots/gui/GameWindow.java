package robots.gui;

import robots.gui.abstractmenu.GenerateExitButton;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.util.ResourceBundle;

public class GameWindow extends JInternalFrame
{
    private final GameVisualizer m_visualizer;
    public GameWindow()
    {
        super(ResourceBundle.getBundle("locale").getString("title.gameField"),
                true,
                true,
                true,
                true
        );
        m_visualizer = new GameVisualizer();
        add(m_visualizer);
        pack();
        GenerateExitButton exitButton = new GenerateExitButton();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                exitButton.generateUniversalExitButton(e);
            }
        });
    }
}

