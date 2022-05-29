package robots.gui.windows;


import robots.gui.abstractmenu.CoordinateMenu;
import robots.gui.abstractmenu.DialogGenerator;
import robots.logic.RobotConstants;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class CoordinateWindow extends AbstractWindow implements Observer {

    private final JPanel panel = new JPanel();
    private final CoordinateMenu coordinateMenu = new CoordinateMenu();
    private final RobotConstants robotConstants;
    private final JLabel robotPosLabel = new JLabel();

    public CoordinateWindow(RobotConstants robotConstants) {
        super(ResourceBundle.getBundle("locale").getString("title.coordinateCheck"),
                true,
                true,
                true,
                true);
        this.robotConstants = robotConstants;
        GridLayout gridLayout = new GridLayout(2,1);
        setLayout(gridLayout);
        robotPosLabel.setBounds(0, 0, getWidth(), 50);
        robotPosLabel.setText("x=" + (int) (robotConstants.getRobotPositionX()) +
                "; y=" + (int) (robotConstants.getRobotPositionY()));
        robotPosLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        robotPosLabel.setVisible(true);
        setJMenuBar(generateMenuBar());
        panel.setVisible(true);
        add(robotPosLabel);
        add(panel);
    }

    private JMenuBar generateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(coordinateMenu.generateCoordinateMenu(e -> {
            Point newPoint = DialogGenerator.pointParamsDialogResult(robotConstants.getMaximumX()
                    ,robotConstants.getMaximumY());
            if (newPoint != null) {
                robotConstants.appendTarget(newPoint);
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
        if (arg.getClass().equals(Point.class)){
            robotPosLabel.setText("x=" + ((Point) arg).getX() +
                    "; y=" + ((Point) arg).getY());
            return;
        }
        if(arg.getClass().equals(RobotConstants.class)) {
            panel.removeAll();
            panel.setLayout(new GridLayout(((RobotConstants) arg).getTargets().size(),1));
            CoordinatesLabel label;
            for (Point point : ((RobotConstants) arg).getTargets()) {
                label = new CoordinatesLabel(point, (RobotConstants) arg);
                label.setBounds(0,0,getWidth(),50);
                panel.add(label);
            }
            panel.validate();
            panel.repaint();
        }
    }
}