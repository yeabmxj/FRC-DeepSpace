package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.Base.Controls;
import frc.Base.Operator;
import frc.StateMachines.*;
import frc.Subsystems.*;
import frc.Base.DLibrary;

public class Robot extends TimedRobot {

    public static DLibrary dLibrary;

    public static DriveTrain driveTrain;
    public static LimeLightVision limeLightVision;
    public static ArmManipulator armManipulator;
    public static Climber climber;
    public static Vacuum vaccum;

    public static Drive drive;
    public static Auto auto;
    public static Arm arm;
    public static Climb climb;
    public static Suction suction;

    public void robotInit() {

        dLibrary = new DLibrary();

        driveTrain = new DriveTrain();
        limeLightVision = new LimeLightVision();
        armManipulator = new ArmManipulator();
        climber = new Climber();
        vaccum = new Vacuum();

        drive = new Drive();
        arm = new Arm();
        auto = new Auto();
        climb = new Climb();
        suction = new Suction();

        dLibrary.setDriveTrainType("Tank");
        driveTrain.resetEncoders();
        Operator.update();
    }
    public void robotPeriodic() {}
    public void autonomousInit() {
        auto.setState(Auto.ReachLine);
    }
    public void autonomousPeriodic() {
        auto.update();
    }

    public void teleopInit() {
        driveTrain.resetGyro();
        drive.setState(Drive.Input);
    }
    public void teleopPeriodic() {
        if (Controls.Vision()) {
            limeLightVision.setCameraControls("camMode", 0);
            auto.setState(Auto.ReachLine);
        }
        else { drive.update();}
    }
}
