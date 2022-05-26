package robots.gui;

import robots.logic.MathOperations;
import robots.logic.MoveOperations;
import robots.logic.RobotConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

public class GameVisualizer extends JPanel {
    private final java.util.Timer m_timer = initTimer();
    private final RobotConstants robotConstants;
    private final MoveOperations moveOperations;
    private final MathOperations mathOperations = new MathOperations();

    public GameVisualizer(RobotConstants robotConstants) {
        this.robotConstants = robotConstants;
        moveOperations = new MoveOperations(robotConstants);
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onRedrawEvent();
            }
        }, 0, 50);
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateGame();
            }
        }, 0, 10);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                robotConstants.appendPoint(e.getPoint());
                repaint();
            }
        });
        setDoubleBuffered(true);
    }

    private static java.util.Timer initTimer() {
        java.util.Timer timer = new Timer("events generator", true);
        return timer;
    }

    private static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private void updateGame(){
        robotConstants.updateGame(this);
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Point point : robotConstants.getTargets()) {
            drawTarget(
                    g2d,
                    point.x,
                    point.y
            );
        }

        drawRobot(
                g2d,
                mathOperations.round(robotConstants.getRobotPositionX()),
                mathOperations.round(robotConstants.getRobotPositionY()),
                robotConstants.getRobotDirection()
        );
    }

    private void drawRobot(Graphics2D g, int robotCenterX, int robotCenterY, double direction) {
        AffineTransform old = g.getTransform();
        g.rotate(direction, robotCenterX, robotCenterY);
        g.setColor(Color.GREEN);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX + 10, robotCenterY, 5, 5);
        g.setTransform(old);
    }

    private void drawTarget(Graphics2D g, int x, int y) {
        g.setColor(Color.GREEN);
        fillOval(g, x, y, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, x, y, 5, 5);
    }
}