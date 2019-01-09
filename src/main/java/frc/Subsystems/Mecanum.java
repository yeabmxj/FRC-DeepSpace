package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.drive.Vector2d;

import java.util.StringJoiner;

import frc.robot.*;

public class Mecanum {
    private static final double kDefaultDeadband = 0.02;
    private static final double kDefaultMaxOutput = 1;
    private static final double KDefaultStartExpiration = 0.1;

    private final Object m_thisMutex = new Object();

    private static int instances;

    private TalonSRX m_frontleft;
    private TalonSRX m_frontright;
    private TalonSRX m_backleft;
    private TalonSRX m_backright;

    private AHRS navx;

    private boolean m_reported;

    private SendableImpl m_sendableImpl;

    private void addChild(Object child) {
        m_sendableImpl.addChild(child);
    }
    private void setName(String moduleType, int channel) {
        m_sendableImpl.setName(moduleType, channel);
    }
    public Mecanum(TalonSRX frontleftMotor, TalonSRX frontrightMotor, TalonSRX backleftMotor, TalonSRX backrightMotor) {
        frontleftMotor = new TalonSRX(Constants.FrontLeftTalonID);
        verify(frontleftMotor, frontrightMotor, backleftMotor, backrightMotor);
        m_frontleft = frontleftMotor;
        m_frontright = frontrightMotor;
        m_backleft = backleftMotor;
        m_backright = backrightMotor;
        addChild(m_frontleft);
        addChild(m_frontright);
        addChild(m_backleft);
        addChild(m_backright);
        instances++;
        setName("MecanumDrive", instances);

        AHRS navx = new AHRS(SPI.Port.kMXP);
    }
    public void Drive(double yaxis, double xaxis, double zaxis, double gyroangle, double throttle) {
        if (!m_reported) {
            HAL.report(tResourceType.kResourceType_RobotDrive, 4, tInstances.kRobotDrive2_MecanumCartesian);
            m_reported = true;
        }
        yaxis = limit(yaxis);
        double m_deadband = kDefaultDeadband;
        yaxis = applyDeadband(yaxis, m_deadband);

        xaxis = limit(xaxis);
        xaxis = applyDeadband(xaxis, m_deadband);

        Vector2d input = new Vector2d(yaxis, xaxis);
        input.rotate(gyroangle);

        double[] wheelSpeeds = new double[4];
        wheelSpeeds[MotorType.kFrontLeft.value] = input.x + input.y + zaxis;
        wheelSpeeds[MotorType.kFrontRight.value] = input.x + input.y - zaxis;
        wheelSpeeds[MotorType.kRearLeft.value] = input.x + input.y + zaxis;
        wheelSpeeds[MotorType.kRearRight.value] = input.x + input.y - zaxis;

        normalize(wheelSpeeds);

        double m_maxOutput = kDefaultMaxOutput;
        m_frontleft.set(ControlMode.PercentOutput, (wheelSpeeds[MotorType.kFrontLeft.value] * m_maxOutput) * throttle);
        double m_rightSideInvertMultiplier = -1;
        m_frontright.set(ControlMode.PercentOutput, ((wheelSpeeds[MotorType.kFrontRight.value] * m_maxOutput) * throttle) * m_rightSideInvertMultiplier);
        m_backleft.set(ControlMode.PercentOutput, (wheelSpeeds[MotorType.kRearLeft.value] * m_maxOutput) * throttle);
        m_backright.set(ControlMode.PercentOutput, ((wheelSpeeds[MotorType.kRearRight.value] * m_maxOutput) * throttle) * m_rightSideInvertMultiplier);

        feed();
    }
    private void verify(TalonSRX frontleft, TalonSRX frontright, TalonSRX backleft, TalonSRX backright) {
        if (frontleft != null && frontright != null && backleft != null && backright != null) {
            return;
        }
        StringJoiner joiner = new StringJoiner(", ");
        if (frontleft == null) {
            joiner.add("frontleftMotor");
        }
        if (frontright == null) {
            joiner.add("frontrightMotor");
        }
        if (backleft == null) {
            joiner.add("backleftMotor");
        }
        if (backright == null) {
            joiner.add("backrightMotor");
        }
        throw new NullPointerException(joiner.toString());
    }
    private double limit(double value) {
        if (value > 1.0) {
            return 1.0;
          }
          if (value < -1.0) {
            return -1.0;
          }
          return value;
    }
    private double applyDeadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
          if (value > 0.0) {
            return (value - deadband) / (1.0 - deadband);
          } else {
            return (value + deadband) / (1.0 - deadband);
          }
        } else {
          return 0.0;
        }
      }
    private void normalize(double[] wheelSpeeds) {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        for (int i = 1; i < wheelSpeeds.length; i++) {
            double temp = Math.abs(wheelSpeeds[i]);
            if (maxMagnitude < temp) {
                maxMagnitude = temp;
            }
        }
        if (maxMagnitude > 1) {
            for (int i = 0; i < wheelSpeeds.length; i++) {
                wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
            }
        }
    }
    private void feed() {
        synchronized (m_thisMutex) {
            double m_expiration = KDefaultStartExpiration;
            double m_stopTime = Timer.getFPGATimestamp() + m_expiration;
        }
    }
    public float getYaw() {
        return navx.getYaw();
    }
}