package robots.logic;

public class MoveOperations {

    public MoveOperations(RobotConstants robotConstants) {
        this.robotConstants = robotConstants;
    }

    private MathOperations mathOperations = new MathOperations();
    private RobotConstants robotConstants;


    public void moveRobot(double velocity, double angularVelocity, double duration)
    {
        velocity = mathOperations.applyLimits(velocity, 0, robotConstants.getMaxVelocity());
        angularVelocity = mathOperations.applyLimits(angularVelocity, -robotConstants.getMaxAngularVelocity(), robotConstants.getMaxAngularVelocity());



        double newX = robotConstants.getM_robotPositionX() + velocity / angularVelocity *
                (Math.sin(robotConstants.getM_robotDirection()  + angularVelocity * duration) -
                        Math.sin(robotConstants.getM_robotDirection()));


        if (!Double.isFinite(newX))
        {
            newX = robotConstants.getM_robotPositionX() + velocity * duration * Math.cos(robotConstants.getM_robotDirection());
        }


        double newY = robotConstants.getM_robotPositionY() - velocity / angularVelocity *
                (Math.cos(robotConstants.getM_robotDirection() + angularVelocity * duration) -
                        Math.cos(robotConstants.getM_robotDirection()));


        if (!Double.isFinite(newY))
        {
            newY = robotConstants.getM_robotPositionY() + velocity * duration * Math.sin(robotConstants.getM_robotDirection());
        }

        robotConstants.setM_robotPositionX(newX);
        robotConstants.setM_robotPositionY(newY);
        double newDirection = MathOperations.asNormalizedRadians(robotConstants.getM_robotDirection() + angularVelocity * duration);
        robotConstants.setM_robotDirection(newDirection);
    }
}
