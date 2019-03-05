package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.auto.*;
import frc.Base.*;
import frc.External.*;
import frc.StateMachines.*;
import frc.Subsystems.*;

public class Robot extends TimedRobot {
    public static UDPClient message;

    public static e_Navx e_navx;
    public static e_LimeLightVision e_limeLightVision;

    public static b_Drivetrain b_drivetrain;

    public static m_Drivetrain m_drivetrain;
    public static m_Auto m_auto;
    public static m_AngleCorrection m_angleCorrection;
    public static m_Arm m_arm;
    public static m_Climber m_climber;
    public static m_Vacuum m_vacuum;
    public static m_Wrist m_wrist;

    public static i_Drivetrain i_drivetrain;
    public static i_Auto i_auto;
    public static i_AngleCorrection i_angleCorrection;
    public static i_Arm i_arm;
    public static i_Climber i_climber;
    public static i_Vacuum i_vacuum;
    public static i_Wrist i_wrist;

    public void robotInit() {
        message = new UDPClient("10.51.15.2", 8005);

        e_navx = new e_Navx();
        e_limeLightVision = new e_LimeLightVision();

        b_drivetrain = new b_Drivetrain();

        m_drivetrain = new m_Drivetrain();
        m_auto = new m_Auto();
        m_angleCorrection = new m_AngleCorrection();
        m_arm = new m_Arm();
        m_climber = new m_Climber();
        m_vacuum = new m_Vacuum();
        m_wrist = new m_Wrist();

        i_drivetrain = new i_Drivetrain();
        i_auto = new i_Auto();
        i_angleCorrection = new i_AngleCorrection();
        i_arm = new i_Arm();
        i_climber = new i_Climber();
        i_vacuum = new i_Vacuum();
        i_wrist = new i_Wrist();

        Controls.updatePorts();
        Operator.update();
        b_drivetrain.setDriveTrainType("Tank");
        m_drivetrain.resetEncoders();
        e_navx.resetGyro();
    }
    public void robotPeriodic() {
        Operator.update();
    }
    public void autonomousInit() {
        Robot.m_drivetrain.resetEncoders(); }
    public void autonomousPeriodic() {
        //i_auto.update();
        MotionCalculation.setSystem("Auto");
        if (!MotionCalculation.isFinished()) {
            System.out.println(MotionCalculation.getError() + "  " + Robot.m_drivetrain.getEncodervalues());
            Robot.m_drivetrain.TankLeft((MotionCalculation.normalize(10,1, Robot.m_drivetrain.getEncodervalues(),1)));
            Robot.m_drivetrain.TankRight((MotionCalculation.normalize(10,1, Robot.m_drivetrain.getEncodervalues(),1)));
        }
        else {
            Robot.m_drivetrain.TankLeft((MotionCalculation.normalize(90,5, Robot.e_navx.getYaw(), 1)));
            Robot.m_drivetrain.TankRight((MotionCalculation.normalize(90,5, Robot.e_navx.getYaw(), 1)));
        }
    }
    public void teleopInit() {
        e_navx.resetGyro();
        m_drivetrain.resetEncoders();
    }
    public void teleopPeriodic() {
        //i_drivetrain.update();
    }
    public void testPeriodic() {
        System.out.println(Controls.mainJoystick.getRawButton(3) ? "yes" : "no");
        System.out.println(Controls.secondaryJoystick.getRawButton(4) ? "yes" : "no");
        i_arm.update();
        i_climber.update();
        i_drivetrain.update();
        i_vacuum.update();
        i_wrist.update();
//        i_wrist.update();
//        i_vacuum.update();

//        m_drivetrain.TankDrive(0, e_limeLightVision.getX(), .4);
//        m_drivetrain.TankLeft(m_auto.ellipticalDriveDistance(e_limeLightVision.getX(), m_auto.computeDistance(1.5, 3, e_limeLightVision.getY()), 1));
//        m_drivetrain.TankRight(m_auto.ellipticalDriveDistance(e_limeLightVision.getX(), m_auto.computeDistance(1.5, 3, e_limeLightVision.getY()) + 1.5, 1));
//        m_auto.startLine(4);
//        m_auto.startTurn(90);
//        m_auto.startLine(2);
    }
}