package robots.gui;


import robots.gui.storemanager.WindowState;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class CoordinateWindow extends JInternalFrame implements ILocalable,ISaveable {

    private final TextArea m_logContent;

    public CoordinateWindow() {
        super(ResourceBundle.getBundle("locale").getString("title.coordinateCheck"),
                true,
                true,
                true,
                true);
        m_logContent = new TextArea("");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_logContent, BorderLayout.CENTER);
        getContentPane().add(panel);
        LocalableComponents.components.add(this);
    }

    public void handleEvent(double changerCoordinate, double coordinate2) {
        m_logContent.setText("x = " + changerCoordinate + " y = " + coordinate2);
        m_logContent.invalidate();
    }

    @Override
    public void changeLocale() {
        this.setTitle(ResourceBundle.getBundle("locale").getString("title.coordinateCheck"));
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

    public void setParams(WindowState windowState) {
        this.setSize(windowState.getWidth(), windowState.getHeight());
        this.setLocation(windowState.getLocationX(), windowState.getLocationY());
        try {
            this.setIcon(windowState.isIcon());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}