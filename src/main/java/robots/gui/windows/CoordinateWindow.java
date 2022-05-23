package robots.gui.windows;


import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class CoordinateWindow extends AbstractWindow {

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
    }

    public void handleEvent(double changerCoordinate, double coordinate2) {
        m_logContent.setText("x = " + changerCoordinate + " y = " + coordinate2);
        m_logContent.invalidate();
    }

    @Override
    public void changeLocale() {
        this.setTitle(ResourceBundle.getBundle("locale").getString("title.coordinateCheck"));
    }
}