package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.Base.Controls;
import frc.StateMachines.*;
import frc.Subsystems.*;
import frc.Base.DLibrary;

public class Robot extends TimedRobot {

    public static ShuffleboardTab tab = Shuffleboard.getTab("drive");

    public static DriveTrain driveTrain;
    public static LimeLightVision limeLightVision;
    public static ArmManipulator armManipulator;
    public static Climber climber;
    public static Vacuum vaccum;

    public static DLibrary dLibrary;

    public static Drive drive;
    public static Auto auto;
    public static Arm arm;
    public static Climb climb;
    public static Succ succ;

    public void robotInit() {

        driveTrain = new DriveTrain();
        drive = new Drive();
        limeLightVision = new LimeLightVision();
        dLibrary = new DLibrary();
        armManipulator = new ArmManipulator();
        arm = new Arm();

        dLibrary.setDriveTrainType("Tank");
        auto = new Auto();

        climber = new Climber();
        vaccum = new Vacuum();

        climb = new Climb();
        succ = new Succ();

        driveTrain.resetEncoders();

        Controls.setTState(Controls.Input);
    }
    public void robotPeriodic() {
        Controls.updateDriver();
    }
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
