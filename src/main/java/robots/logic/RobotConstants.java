package robots.logic;

import java.awt.*;
import java.util.*;

public class RobotConstants extends Observable{
    private MoveOperations moveOperations = new MoveOperations(this);
    private final double maxVelocity = 0.1;
    private final double maxAngularVelocity = 0.001;
    private volatile double robotPositionX = 100;
    private volatile double robotPositionY = 100;
    private volatile double robotDirection = 0;
    private volatile int currentTargetPositionX = 200;
    private volatile int currentTargetPositionY = 200;
    private final ArrayDeque<Point> targets = new ArrayDeque<>();

    public ArrayDeque<Point> getTargets(){
        return targets;
    }

    public void appendPoint(int x, int y){
        targets.addLast(new Point(x,y));
    }

    public void appendPoint(Point point){
        targets.addLast(point);
        notifyObservers();
    }

    public double getRobotPositionX() {
        return robotPositionX;
    }

    public void setRobotPositionX(double robotPositionX) {
        this.robotPositionX = robotPositionX;
    }

    public double getRobotPositionY() {
        return robotPositionY;
    }

    public void setRobotPositionY(double robotPositionY) {
        this.robotPositionY = robotPositionY;
    }

    public double getRobotDirection() {
        return robotDirection;
    }

    public void setRobotDirection(double robotDirection) {
        this.robotDirection = robotDirection;
    }

    public int getCurrentTargetPositionX() {
        return currentTargetPositionX;
    }

    public int getCurrentTargetPositionY() {
        return currentTargetPositionY;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public double getMaxAngularVelocity() {
        return maxAngularVelocity;
    }

    public void setTargetPosition(Point p) {
        currentTargetPositionX = p.x;
        currentTargetPositionY = p.y;
    }

    public void updateGame(Component component) {
        if (getTargets().isEmpty())
            return;
        else {
            setTargetPosition(getTargets().peekFirst());
        }

        double distance = MathOperations.distance(
                getCurrentTargetPositionX(),
                getCurrentTargetPositionY(),
                getRobotPositionX(),
                getRobotPositionY()
        );

        if (distance < 0.5)
            getTargets().removeFirst();

        double velocity = getMaxVelocity();
        double angleToTarget = MathOperations.angleTo(
                getRobotPositionX(),
                getRobotPositionY(),
                getCurrentTargetPositionX(),
                getCurrentTargetPositionY()
        );
        double angularVelocity = 0;


        if ((getRobotPositionX() >= component.getWidth())
                || (getRobotPositionX() < 0)) {
            if (getRobotPositionX() >= component.getWidth()) {
                setRobotPositionX(0);
            } else {
                setRobotPositionX(component.getWidth());
            }
        }

        if ((getRobotPositionY() >= component.getHeight())
                || (getRobotPositionY() < 0)) {
            if (getRobotPositionY() >= component.getHeight()) {
                setRobotPositionY(0);
            } else {
                setRobotPositionY(component.getHeight());
            }
        }


        if (angleToTarget > getRobotDirection()) {
            angularVelocity = getMaxAngularVelocity();
        }
        if (angleToTarget < getRobotDirection()) {
            angularVelocity = -1 * getMaxAngularVelocity();
        }
        moveOperations.moveRobot(velocity, angularVelocity, 10);
        setChanged();
        notifyObservers(targets);
    }
}

