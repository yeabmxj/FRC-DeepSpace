package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.Subsystems.LimeLightVision;
import frc.StateMachines.Drive;
import frc.Subsystems.DriveTrain;
import frc.Subsystems.DLibrary;

public class Robot extends TimedRobot {

  public static DriveTrain driveTrain;
  public static LimeLightVision limeLightVision;
  public static DLibrary dLibrary;

  public static Drive drive;

  public void robotInit() {
    driveTrain = new DriveTrain();
    drive = new Drive();
    limeLightVision = new LimeLightVision();

    dLibrary.setDriveTrainType("Mecanum");
  }
  public void teleopInit() {
    driveTrain.resetGyro();
    drive.setState(Drive.Driving);
  }
  public void teleopPeriodic() {drive.update();}
}
