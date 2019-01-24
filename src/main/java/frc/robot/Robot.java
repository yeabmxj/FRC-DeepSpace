package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.StateMachines.Arm;
import frc.StateMachines.SandyDrive;
import frc.Subsystems.ArmManipulator;
import frc.Subsystems.LimeLightVision;
import frc.StateMachines.Drive;
import frc.Subsystems.DriveTrain;
import frc.Base.DLibrary;

public class Robot extends TimedRobot {

    public static DriveTrain driveTrain;
    public static LimeLightVision limeLightVision;
    public static DLibrary dLibrary;
    public static ArmManipulator armManipulator;
    public static Arm arm;

    public static Drive drive;
    public static SandyDrive sandyDrive;

    public void robotInit() {

        driveTrain = new DriveTrain();
        drive = new Drive();
        limeLightVision = new LimeLightVision();
        dLibrary = new DLibrary();
        armManipulator = new ArmManipulator();
        arm = new Arm();

        dLibrary.setDriveTrainType("Tank");
        sandyDrive = new SandyDrive();
    }
    public void teleopInit() {
        driveTrain.resetGyro();
        Robot.drive.setState(Robot.dLibrary.getDriveTrainType().equals("Mecanum") ? Drive.Mecanum : Drive.Tank);
    }
    public void teleopPeriodic() {drive.update();}
}
