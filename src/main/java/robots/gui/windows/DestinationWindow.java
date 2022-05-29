package robots.gui.windows;

import robots.logic.RobotConstants;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class DestinationWindow extends AbstractWindow implements Observer {

    private final JLabel label = new JLabel();

    public DestinationWindow() {
        super(ResourceBundle.getBundle("locale").getString("title.destinationWindow"),
                true,
                true,
                true,
                true
        );
        label.setBounds(50, 50, 100, 100);
        label.setFont(new Font("Arial", Font.ITALIC, 20));
        label.setVisible(true);
        add(label);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass().equals(Double.class))
            label.setText(String.valueOf((int)((double) arg)));
    }

    @Override
    public void changeLocale() {
        setTitle(ResourceBundle.getBundle("locale").getString("title.destinationWindow"));
    }
}
