package robots.logic;

public class MoveOperations {

    private final MathOperations mathOperations = new MathOperations();
    private final RobotConstants robotConstants;

    public MoveOperations(RobotConstants robotConstants) {
        this.robotConstants = robotConstants;
    }

    public void moveRobot(double velocity, double angularVelocity, double duration) {
        velocity = MathOperations.applyLimits(
                velocity,
                0,
                robotConstants.getMaxVelocity()
        );
        angularVelocity = MathOperations.applyLimits(
                angularVelocity,
                -robotConstants.getMaxAngularVelocity(),
                robotConstants.getMaxAngularVelocity()
        );


        double newX = robotConstants.getRobotPositionX() + velocity / angularVelocity *
                (Math.sin(robotConstants.getRobotDirection() + angularVelocity * duration) -
                        Math.sin(robotConstants.getRobotDirection()));


        if (!Double.isFinite(newX)) {
            newX = robotConstants.getRobotPositionX()
                    + velocity
                    * duration
                    * Math.cos(robotConstants.getRobotDirection());
        }


        double newY = robotConstants.getRobotPositionY()
                - velocity / angularVelocity
                * (Math.cos(robotConstants.getRobotDirection() + angularVelocity * duration)
                - Math.cos(robotConstants.getRobotDirection()));


        if (!Double.isFinite(newY)) {
            newY = robotConstants.getRobotPositionY()
                    + velocity
                    * duration
                    * Math.sin(robotConstants.getRobotDirection());
        }

        robotConstants.setRobotPositionX(newX);
        robotConstants.setRobotPositionY(newY);
        double newDirection = MathOperations.asNormalizedRadians(robotConstants.getRobotDirection()
                + angularVelocity
                * duration);
        robotConstants.setRobotDirection(newDirection);
    }
}
