package robots.logic;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Observable;

public class RobotConstants extends Observable {
    private final MoveOperations moveOperations = new MoveOperations(this);
    private final double maxVelocity = 0.1;
    private final double maxAngularVelocity = 0.001;
    private final ArrayDeque<Point> targets = new ArrayDeque<>();
    private volatile double robotPositionX = 100;
    private volatile double robotPositionY = 100;
    private volatile double robotDirection = 0;
    private volatile double destinationToTarget = 0;
    private volatile int currentTargetPositionX = 200;
    private volatile int currentTargetPositionY = 200;
    private final Point currentRobotPosition = new Point((int)robotPositionX,(int)robotPositionY);
    private int maximumX;
    private int maximumY;

    public ArrayDeque<Point> getTargets() {
        synchronized (targets) {
            return targets;
        }
    }

    public void removeFirstTarget() {
        synchronized (targets) {
            targets.removeFirst();
            setChanged();
            notifyObservers(this);
        }
    }

    public void removeTarget(Point target) {
        synchronized (targets) {
            targets.remove(target);
            setChanged();
            notifyObservers(this);
        }
    }

    public Point peekFirstTarget() {
        synchronized (targets) {
            return targets.peekFirst();
        }
    }

    public void appendTarget(Point point) {
        synchronized (targets) {
            targets.addLast(point);
            setChanged();
            notifyObservers(this);
        }
    }

    public void changeTarget(Point point, Point newPoint){
        point.x = newPoint.x;
        point.y = newPoint.y;
        setChanged();
        notifyObservers(this);
    }

    public double getRobotPositionX() {
        return robotPositionX;
    }

    public void setRobotPositionX(double robotPositionX) {
        this.robotPositionX = robotPositionX;
        currentRobotPosition.x = MathOperations.round(robotPositionX);
        setChanged();
        notifyObservers(currentRobotPosition);
    }

    public double getRobotPositionY() {
        return robotPositionY;
    }

    public void setRobotPositionY(double robotPositionY) {
        this.robotPositionY = robotPositionY;
        currentRobotPosition.y = MathOperations.round(robotPositionY);
        setChanged();
        notifyObservers(currentRobotPosition);
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

    public double getDestinationToTarget() {
        return destinationToTarget;
    }

    public void setDestinationToTarget(double value) {
        destinationToTarget = value;
        setChanged();
        notifyObservers(destinationToTarget);
    }

    public int getMaximumX(){
        return maximumX;
    }

    public int getMaximumY(){
        return maximumY;
    }

    public void updateRobot(Component component) {
        maximumX = component.getWidth();
        maximumY = component.getHeight();
        if (getTargets().isEmpty())
            return;
        else {
            setTargetPosition(peekFirstTarget());
        }

        setDestinationToTarget(MathOperations.distance(
                getCurrentTargetPositionX(),
                getCurrentTargetPositionY(),
                getRobotPositionX(),
                getRobotPositionY()
        ));

        if (getDestinationToTarget() < 0.5)
            removeFirstTarget();

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
    }
}

