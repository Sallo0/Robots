package robots.logic;

public class MoveOperations {

    public MoveOperations(RobotConstants myConst) {
        this.myConst = myConst;
    }

    private MathOperations mathOperations = new MathOperations();
    private RobotConstants myConst;


    public void moveRobot(double velocity, double angularVelocity, double duration)
    {
        velocity = mathOperations.applyLimits(velocity, 0, myConst.getMaxVelocity());
        angularVelocity = mathOperations.applyLimits(angularVelocity, -myConst.getMaxAngularVelocity(), myConst.getMaxAngularVelocity());



        double newX = myConst.getM_robotPositionX() + velocity / angularVelocity *
                (Math.sin(myConst.getM_robotDirection()  + angularVelocity * duration) -
                        Math.sin(myConst.getM_robotDirection()));


        if (!Double.isFinite(newX))
        {
            newX = myConst.getM_robotPositionX() + velocity * duration * Math.cos(myConst.getM_robotDirection());
        }


        double newY = myConst.getM_robotPositionY() - velocity / angularVelocity *
                (Math.cos(myConst.getM_robotDirection() + angularVelocity * duration) -
                        Math.cos(myConst.getM_robotDirection()));


        if (!Double.isFinite(newY))
        {
            newY = myConst.getM_robotPositionY() + velocity * duration * Math.sin(myConst.getM_robotDirection());
        }

        myConst.setM_robotPositionX(newX);
        myConst.setM_robotPositionY(newY);
        double newDirection = MathOperations.asNormalizedRadians(myConst.getM_robotDirection() + angularVelocity * duration);
        myConst.setM_robotDirection(newDirection);
    }
}
