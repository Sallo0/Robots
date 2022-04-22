package robots.gui;

import robots.logic.Cordinate;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class WindowCoordinate extends JInternalFrame {

    private String  CountOfRobot;
    private final Cordinate cordinate;


    public WindowCoordinate() {
        super(ResourceBundle.getBundle("locale").getString("title.coordinateCheck"), true, true, true, true);
        cordinate = new Cordinate();
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(cordinate,BorderLayout.CENTER);
        getContentPane().add(jPanel);
        pack();
    }

}
