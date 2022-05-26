package robots.gui.windows;


import robots.gui.abstractmenu.CoordinateMenu;
import robots.logic.RobotConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class CoordinateWindow extends AbstractWindow implements Observer{

    private final JPanel panel = new JPanel();
    private final CoordinateMenu coordinateMenu = new CoordinateMenu();
    private RobotConstants robotConstants;

    public CoordinateWindow(RobotConstants robotConstants) {
        super(ResourceBundle.getBundle("locale").getString("title.coordinateCheck"),
                true,
                true,
                true,
                true);
        this.robotConstants = robotConstants;
        panel.setVisible(true);
        setJMenuBar(generateMenuBar());
        add(panel);
    }


    public static Point pointDialogResult(){
        JFrame frame = new JFrame();
        SpinnerModel spinnerModelX = new SpinnerNumberModel();
        SpinnerModel spinnerModelY = new SpinnerNumberModel();
        JSpinner spinnerX = new JSpinner(spinnerModelX);
        JSpinner spinnerY = new JSpinner(spinnerModelY);

        JComponent[] inputs = new JComponent[] {
                spinnerX,
                spinnerY
        };
        int result = JOptionPane.showConfirmDialog(
                frame,
                inputs,
                "My custom dialog",
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION){
            return new Point((Integer) spinnerX.getValue(), (Integer) spinnerY.getValue());
        }
        return null;
    }

    private JMenuBar generateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(coordinateMenu.generateCoordinateMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point newPoint = pointDialogResult();
                if (newPoint != null){
                    robotConstants.appendPoint(newPoint);
                }
            }
        }));

        return menuBar;
    }

    @Override
    public void changeLocale() {
        this.setTitle(ResourceBundle.getBundle("locale").getString("title.coordinateCheck"));
    }

    @Override
    public void update(Observable o, Object arg) {
        panel.removeAll();
        CoordinatesLabel label;
        JLabel robotPosLabel = new JLabel("x="+
                robotConstants.getRobotPositionX()+
                "; y="+
                robotConstants.getRobotPositionY());
        int y = 0;
        robotPosLabel.setBounds(0,y,getWidth(),50);
        y+= robotPosLabel.getHeight();
        panel.add(robotPosLabel);
        for (Point point: (ArrayDeque<Point>)arg){
            label = new CoordinatesLabel(point, (ArrayDeque<Point>)arg);
            panel.add(label).setBounds(0,y,getWidth(),50);
            y+=label.getHeight();
        }
        panel.repaint();
    }
}