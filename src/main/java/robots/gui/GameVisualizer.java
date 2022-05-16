package robots.gui;

import robots.logic.MathOperations;
import robots.logic.MoveOperations;
import robots.logic.RobotConstants;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GameVisualizer extends JPanel {
    private RobotConstants robotConstants = new RobotConstants();
    private MoveOperations moveOperations = new MoveOperations(robotConstants);
    private MathOperations mathOperations = new MathOperations();
    private final java.util.Timer m_timer = initTimer();

    private static java.util.Timer initTimer() {
        java.util.Timer timer = new Timer("events generator", true);
        return timer;
    }

    public GameVisualizer() {
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onRedrawEvent();
            }
        }, 0, 50);
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onModelUpdateEvent();
            }
        }, 0, 10);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                robotConstants.setTargetPosition(e.getPoint());
                repaint();
            }
        });
        setDoubleBuffered(true);
    }

    protected void onModelUpdateEvent() {
        double distance = mathOperations.distance(robotConstants.getM_targetPositionX(), robotConstants.getM_targetPositionY(),
                robotConstants.getM_robotPositionX(), robotConstants.getM_robotPositionY());
        if (distance < 0.5) {
            return;
        }
        double velocity = robotConstants.getMaxVelocity();
        double angleToTarget = mathOperations.angleTo(robotConstants.getM_robotPositionX(), robotConstants.getM_robotPositionY(), robotConstants.getM_targetPositionX(), robotConstants.getM_targetPositionY());
        double angularVelocity = 0;


        if ((robotConstants.getM_robotPositionX() >= this.getWidth()) || (robotConstants.getM_robotPositionX() < 0)) {
            if (robotConstants.getM_robotPositionX() >= this.getWidth()) {
                robotConstants.setM_robotPositionX(0);
            } else {
                robotConstants.setM_robotPositionX(this.getWidth());
            }
        }

        if ((robotConstants.getM_robotPositionY() >= this.getHeight()) || (robotConstants.getM_robotPositionY() < 0)) {
            if (robotConstants.getM_robotPositionY() >= this.getHeight()) {
                robotConstants.setM_robotPositionY(0);
            } else {
                robotConstants.setM_robotPositionY(this.getHeight());
            }
        }


        if (angleToTarget > robotConstants.getM_robotDirection()) {
            angularVelocity = robotConstants.getMaxAngularVelocity();
        }
        if (angleToTarget < robotConstants.getM_robotDirection()) {
            angularVelocity = -1 * robotConstants.getMaxAngularVelocity();
        }

        moveOperations.moveRobot(velocity, angularVelocity, 10);
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawRobot(g2d, mathOperations.round(robotConstants.getM_robotPositionY()), mathOperations.round(robotConstants.getM_robotPositionY()), robotConstants.getM_robotDirection());
        drawTarget(g2d, robotConstants.getM_targetPositionX(), robotConstants.getM_targetPositionY());
    }

    private void drawRobot(Graphics2D g, int x, int y, double direction) {
        int robotCenterX = mathOperations.round(robotConstants.getM_robotPositionX());
        int robotCenterY = mathOperations.round(robotConstants.getM_robotPositionY());
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

    private static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private void drawTarget(Graphics2D g, int x, int y) {
        g.setColor(Color.GREEN);
        fillOval(g, x, y, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, x, y, 5, 5);
    }
}