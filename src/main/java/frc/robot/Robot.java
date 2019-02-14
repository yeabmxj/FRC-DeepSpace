package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.Base.*;
import frc.StateMachines.*;
import frc.Subsystems.*;
import frc.auto.Auto;

public class Robot extends TimedRobot {
    public static ShuffleboardTab tab =  Shuffleboard.getTab("displays");

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

    PID PIDLoop;

    public void robotInit() {
        dLibrary = new DLibrary();

        driveTrain = new DriveTrain();
        limeLightVision = new LimeLightVision();

        drive = new Drive();
        auto = new Auto();

        dLibrary.setDriveTrainType("Tank");
        driveTrain.resetEncoders();
        Operator.update();
    }
    public void robotPeriodic() {
        Operator.update();
    }
    public void autonomousInit() { }
    public void autonomousPeriodic() {
        auto.update();
    }

    public void teleopInit() {
        driveTrain.resetGyro();
        drive.setState(Drive.Input);
        drive.update();
        driveTrain.update();
    }
    public void teleopPeriodic() {
        if (Controls.Vision()) {
            limeLightVision.setCameraControls("camMode", 0);
        }
        else {
            drive.update();
            driveTrain.update();
        }
    }
}
