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

    public CoordinateWindow(RobotConstants robotConstants) {
        super(ResourceBundle.getBundle("locale").getString("title.coordinateCheck"),
                true,
                true,
                true,
                true);
        this.robotConstants = robotConstants;
        JScrollPane scrollablePanel = new JScrollPane(panel);
        scrollablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setJMenuBar(generateMenuBar());
        add(scrollablePanel);
    }

    private JMenuBar generateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(coordinateMenu.generateCoordinateMenu(e -> {
            Point newPoint = DialogGenerator.pointParamsDialogResult();
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
        panel.removeAll();
        CoordinatesLabel label;
        JLabel robotPosLabel = new JLabel("x=" +
                (int) (((RobotConstants) arg).getRobotPositionX()) +
                "; y=" +
                (int) (((RobotConstants) arg).getRobotPositionY()));
        robotPosLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        int y = 0;
        robotPosLabel.setBounds(0, y, getWidth(), 50);
        y += robotPosLabel.getHeight();
        panel.add(robotPosLabel);
        for (Point point : ((RobotConstants) arg).getTargets()) {
            label = new CoordinatesLabel(point, (RobotConstants) arg);
            panel.add(label).setBounds(0, y, getWidth(), 50);
            y += label.getHeight();
        }
        panel.repaint();
    }
}