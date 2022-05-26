package robots.gui.windows;

import robots.gui.abstractmenu.DialogGenerator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;

public class CoordinatesLabel extends JLabel {
    private JButton deleteButton = new JButton();
    private JButton changeButton = new JButton();
    private ArrayDeque<Point> targets;
    private Point point;

    CoordinatesLabel(Point point, ArrayDeque<Point> targets){
        this.targets = targets;
        this.point = point;
        setBorder(new LineBorder(Color.BLACK));
        setText("    X = "+point.x+"; Y = "+point.y+";");
        setVisible(true);

        deleteButton.setBounds(350,12,100, 25);
        deleteButton.setBackground(Color.RED);
        deleteButton.setVisible(true);
        deleteButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonPressed();
            }
        });
        deleteButton.setText("Delete");
        add(deleteButton);

        changeButton.setBounds(200,12,100, 25);
        changeButton.setBackground(Color.BLUE);
        changeButton.setVisible(true);
        changeButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeButtonPressed();
            }
        });
        changeButton.setText("Change");
        add(changeButton);


    }

    private void deleteButtonPressed(){
        targets.remove(point);
    }

    private void changeButtonPressed(){
        Point newPoint = CoordinateWindow.pointDialogResult();
        if (newPoint != null) {
            point.x = newPoint.x;
            point.y = newPoint.y;
        }
    }
}
