package robots.gui;

import robots.logic.RobotBasicLogic;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JInternalFrame
{
    private final RobotBasicLogic m_visualizer;
    public GameWindow()
    {
        super("Игровое поле", true, true, true, true);
        m_visualizer = new RobotBasicLogic();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}

