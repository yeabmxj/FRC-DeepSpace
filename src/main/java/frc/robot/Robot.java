package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.Subsystems.Mecanum;
import frc.StateMachines.Drive;

public class Robot extends TimedRobot {

  public static Mecanum mecanum;

  public static Drive drive;

  @Override
  public void robotInit() {
    mecanum = new Mecanum(new TalonSRX(Constants.FrontLeftTalonID), new TalonSRX(Constants.FrontRightTalonID), new TalonSRX(Constants.BackLeftTalonID), new TalonSRX(Constants.BackRightTalonID));

    drive = new Drive();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testPeriodic() {
  }
}
