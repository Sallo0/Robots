package robots.gui.windows;

import robots.gui.abstractmenu.DialogGenerator;
import robots.logic.RobotConstants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class CoordinatesLabel extends AbstractLabel {
    private final RobotConstants robotConstants;
    private final Point point;
    private final JButton changeButton = new JButton();
    private final JButton deleteButton = new JButton();

    CoordinatesLabel(Point point, RobotConstants robotConstants) {
        super();
        this.robotConstants = robotConstants;
        this.point = point;
        setLayout(new GridLayout(1,3));
        setBorder(new LineBorder(Color.BLACK));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("    X = " + point.x + "; Y = " + point.y + ";");
        textArea.setFont(new Font("Arial", Font.ITALIC,10));
        add(textArea);


        deleteButton.setBounds(0, 0, 100, 25);
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
        Point newPoint = DialogGenerator.pointParamsDialogResult(robotConstants.getMaximumX()
                ,robotConstants.getMaximumX());
        if (newPoint != null) {
            robotConstants.changeTarget(point,newPoint);
        }
    }

    @Override
    public void changeLocale() {
        deleteButton.setText(ResourceBundle.getBundle("locale").getString("text.delete"));
        changeButton.setText(ResourceBundle.getBundle("locale").getString("text.change"));
    }
}
