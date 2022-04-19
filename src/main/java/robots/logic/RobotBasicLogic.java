package robots.logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

public class RobotBasicLogic extends JPanel
{
    private RobotConstants myConst = new RobotConstants();
    private MoveOperations moveOperations = new MoveOperations(myConst);
    private MathOperations mathOperations = new MathOperations();
    private final java.util.Timer m_timer = initTimer();

    private static java.util.Timer initTimer()
    {
        java.util.Timer timer = new Timer("events generator", true);
        return timer;
    }

    public RobotBasicLogic()
    {
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                onRedrawEvent();
            }
        }, 0, 50);
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                onModelUpdateEvent();
            }
        }, 0, 10);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                myConst.setTargetPosition(e.getPoint());
                repaint();
            }
        });
        setDoubleBuffered(true);
    }

    protected void onModelUpdateEvent()
    {
        double distance = mathOperations.distance(myConst.getM_targetPositionX(), myConst.getM_targetPositionY(),
                myConst.getM_robotPositionX(), myConst.getM_robotPositionY());
        if (distance < 0.5)
        {
            return;
        }
        double velocity = myConst.getMaxVelocity();
        double angleToTarget = mathOperations.angleTo(myConst.getM_robotPositionX(), myConst.getM_robotPositionY(), myConst.getM_targetPositionX(), myConst.getM_targetPositionY());
        double angularVelocity = 0;


        if ((myConst.getM_robotPositionX() >= getSize().width)||(myConst.getM_robotPositionX() < 0)){
            if(myConst.getM_robotPositionX() >= getSize().width) {myConst.setM_robotPositionX(0);}
            else {myConst.setM_robotPositionX(getWidth());}
        }

        if ((myConst.getM_robotPositionY() >= getSize().height)||( myConst.getM_robotPositionY() < 0)) {
            if(myConst.getM_robotPositionY() >= getSize().height) {myConst.setM_robotPositionY(0);}
            else {myConst.setM_robotPositionY(getHeight());}
        }


        if (angleToTarget > myConst.getM_robotDirection())
        {
            angularVelocity = myConst.getMaxAngularVelocity();
        }
        if (angleToTarget < myConst.getM_robotDirection())
        {
            angularVelocity = -1*myConst.getMaxAngularVelocity();
        }

        moveOperations.moveRobot(velocity, angularVelocity, 10);
    }

    protected void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        drawRobot(g2d, mathOperations.round(myConst.getM_robotPositionY()), mathOperations.round(myConst.getM_robotPositionY()), myConst.getM_robotDirection());
        drawTarget(g2d, myConst.getM_targetPositionX(), myConst.getM_targetPositionY());
    }

    private void drawRobot(Graphics2D g, int x, int y, double direction)
    {
        int robotCenterX = mathOperations.round(myConst.getM_robotPositionX());
        int robotCenterY = mathOperations.round(myConst.getM_robotPositionY());
        AffineTransform t = AffineTransform.getRotateInstance(direction, robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
    }

    private static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2)
    {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2)
    {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private void drawTarget(Graphics2D g, int x, int y)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, x, y, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, x, y, 5, 5);
    }
}
