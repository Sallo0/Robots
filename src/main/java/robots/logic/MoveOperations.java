package robots.logic;

public class MoveOperations {

    private final MathOperations mathOperations = new MathOperations();
    private final RobotConstants robotConstants;
    public MoveOperations(RobotConstants robotConstants) {
        this.robotConstants = robotConstants;
    }

    public void moveRobot(double velocity, double angularVelocity, double duration) {
        velocity = MathOperations.applyLimits(velocity, 0, robotConstants.getMaxVelocity());
        angularVelocity = MathOperations.applyLimits(angularVelocity, -robotConstants.getMaxAngularVelocity(), robotConstants.getMaxAngularVelocity());


        double newX = robotConstants.getM_robotPositionX() + velocity / angularVelocity *
                (Math.sin(robotConstants.getM_robotDirection() + angularVelocity * duration) -
                        Math.sin(robotConstants.getM_robotDirection()));


        if (!Double.isFinite(newX)) {
            newX = robotConstants.getM_robotPositionX() + velocity * duration * Math.cos(robotConstants.getM_robotDirection());
        }


        double newY = robotConstants.getM_robotPositionY() - velocity / angularVelocity *
                (Math.cos(robotConstants.getM_robotDirection() + angularVelocity * duration) -
                        Math.cos(robotConstants.getM_robotDirection()));


        if (!Double.isFinite(newY)) {
            newY = robotConstants.getM_robotPositionY() + velocity * duration * Math.sin(robotConstants.getM_robotDirection());
        }

        robotConstants.setM_robotPositionX(newX);
        robotConstants.setM_robotPositionY(newY);
        double newDirection = MathOperations.asNormalizedRadians(robotConstants.getM_robotDirection() + angularVelocity * duration);
        robotConstants.setM_robotDirection(newDirection);
    }
}
