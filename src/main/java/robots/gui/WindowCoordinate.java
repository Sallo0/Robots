package robots.gui;

import robots.logic.Cordinate;

import javax.swing.*;
import java.awt.*;

public class WindowCoordinate extends JInternalFrame {

    private String  CountOfRobot;
    private final Cordinate cordinate;


    public WindowCoordinate() {
        super("Отслеживание координат", true, true, true, true);
        cordinate = new Cordinate();
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(cordinate,BorderLayout.CENTER);
        getContentPane().add(jPanel);
        pack();

    }

}
