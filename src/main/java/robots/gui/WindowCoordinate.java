package robots.gui;


import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class WindowCoordinate extends JInternalFrame
{

    private TextArea m_logContent;

    public WindowCoordinate()
    {
        super(ResourceBundle.getBundle("locale").getString("title.coordinateCheck"), true, true, true, true);
        m_logContent = new TextArea("");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_logContent, BorderLayout.CENTER);
        getContentPane().add(panel);
    }

    public void handleEvent(double changerCoordinate,double coordinate2){
        m_logContent.setText("x = " + changerCoordinate + " y = "+ coordinate2);
        m_logContent.invalidate();
    }
}