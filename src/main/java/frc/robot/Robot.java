package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.Base.*;
import frc.External.e_LimeLightVision;
import frc.External.e_Navx;
import frc.StateMachines.*;
import frc.Subsystems.*;
import frc.auto.i_Auto;

public class Robot extends TimedRobot {
    public static e_Navx eNavx;
    public static e_LimeLightVision eLimeLightVision;

    public static DLibrary dLibrary;

    public static m_Drivetrain m_drivetrain;
    public static m_Arm m_arm;
    public static m_Climber m_climber;
    public static m_Vacuum m_vacuum;

    public static i_Drivetrain i_drivetrain;
    public static i_Auto i_auto;
    public static i_Arm i_arm;
    public static i_Climber i_climber;
    public static i_Vacuum i_vacuum;

    public void robotInit() {
        eNavx = new e_Navx();

        dLibrary = new DLibrary();

        m_drivetrain = new m_Drivetrain();
        eLimeLightVision = new e_LimeLightVision();
        m_arm = new m_Arm();
        m_climber = new m_Climber();
        m_vacuum = new m_Vacuum();

        i_drivetrain = new i_Drivetrain();
        i_auto = new i_Auto();
        i_arm = new i_Arm();
        i_climber = new i_Climber();
        i_vacuum = new i_Vacuum();

        dLibrary.setDriveTrainType("Tank");
        m_drivetrain.resetEncoders();
        eNavx.resetGyro();
        Operator.update();
    }
    public void robotPeriodic() {
        Operator.update();
    }
    public void autonomousInit() { }
    public void autonomousPeriodic() {
        i_auto.update();
    }

    public void teleopInit() {
        i_drivetrain.update();
        i_arm.update();
    }
    public void teleopPeriodic() {
        i_drivetrain.update();
        i_arm.update();
    }
}
