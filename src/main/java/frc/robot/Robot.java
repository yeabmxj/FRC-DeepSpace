package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.auto.*;
import frc.Base.*;
import frc.External.*;
import frc.StateMachines.*;
import frc.Subsystems.*;

import java.util.function.IntToDoubleFunction;

public class Robot extends TimedRobot {
    public static UDPClient message;

    public static e_Navx e_navx;
    public static e_LimeLightVision e_limeLightVision;

    public static b_Drivetrain b_drivetrain;

    public static TalonSRX oof;

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

    double count = 2;
    String heldItem;
    double distance;

    TalonSRX test;

    public void robotInit() {

        test = new TalonSRX(4);
        test.setSelectedSensorPosition(0,0,0);

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
        e_navx.resetGyro();
    }
    public void robotPeriodic() {
        Operator.update();
    }
    public void autonomousInit() {
        e_limeLightVision.setCameraControls("ledMode", 3);
        e_limeLightVision.setCameraControls("camMode", 0);
        e_navx.resetGyro();
        distance = Math.abs(e_limeLightVision.getDistance());
    }
    public void autonomousPeriodic() {
        count +=
                Controls.getButton(14) && count + 1 != 5 ? 1 :
                count == 4 ? -2: 0;
        heldItem =
                count == 2 ? "None":
                count == 3 ? "Hatch":
                count == 4 ? "Ball": "Calc Bad";
        System.out.println(heldItem + " " + count);
//        i_angleCorrection.update();
        MotionCalculation.setSystem("Auto");
        System.out.println(distance);
        System.out.println(m_drivetrain.getEncodervalues());
        if (Controls.getButton(Controls.autonomousButton)) {
            m_drivetrain.TankDrive(
                MotionCalculation.normalize(0, 0, e_limeLightVision.getX(), 5) / 3,
                MotionCalculation.normalize(4, 0, m_drivetrain.getEncodervalues(), 1),
                .5);
        }
        else {
            System.out.println("fuck");
            m_drivetrain.TankDrive(0,0,0);
        }
    }

    public void teleopInit() {
        e_navx.resetGyro();
        m_drivetrain.resetEncoders();
    }
    public void teleopPeriodic() {
        i_drivetrain.update();
    }

    public void testPeriodic() {
        test.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
        System.out.println((test.getSelectedSensorPosition(0) / 1440 * 6 * Math.PI / 12));
    }
}