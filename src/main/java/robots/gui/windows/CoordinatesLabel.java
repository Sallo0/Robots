package robots.gui.windows;

import robots.gui.abstractmenu.DialogGenerator;
import robots.logic.RobotConstants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class CoordinatesLabel extends JLabel {
    private final RobotConstants robotConstants;
    private final Point point;

    CoordinatesLabel(Point point, RobotConstants robotConstants) {
        this.robotConstants = robotConstants;
        this.point = point;
        setBorder(new LineBorder(Color.BLACK));
        setText("    X = " + point.x + "; Y = " + point.y + ";");
        setVisible(true);

        JButton deleteButton = new JButton();
        deleteButton.setBounds(350, 12, 100, 25);
        deleteButton.setBackground(Color.RED);
        deleteButton.setVisible(true);
        deleteButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonPressed();
            }
        });
        deleteButton.setText(ResourceBundle.getBundle("locale").getString("text.delete"));
        add(deleteButton);

        JButton changeButton = new JButton();
        changeButton.setBounds(200, 12, 100, 25);
        changeButton.setBackground(Color.BLUE);
        changeButton.setVisible(true);
        changeButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeButtonPressed();
            }
        });
        changeButton.setText(ResourceBundle.getBundle("locale").getString("text.change"));
        add(changeButton);
    }

    private void deleteButtonPressed() {
        robotConstants.removeTarget(point);
    }

    private void changeButtonPressed() {
        Point newPoint = DialogGenerator.pointParamsDialogResult();
        if (newPoint != null) {
            point.x = newPoint.x;
            point.y = newPoint.y;
        }
    }
}
