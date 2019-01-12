package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI;

import frc.robot.*;

public class DriveTrain {

    public TalonSRX frontleftMotor;
    public TalonSRX frontrightMotor; 
    public TalonSRX backleftMotor; 
    public TalonSRX backrightMotor;

    public AnalogInput distanceToWallSensor;

    public AHRS navx;

    double[] processed;

    double m_rightSideInvertMultiplier = -1;

    public DriveTrain() {
        frontleftMotor = new TalonSRX(Constants.FrontLeftTalonID);
        frontrightMotor = new TalonSRX(Constants.FrontRightTalonID);
        backleftMotor = new TalonSRX(Constants.BackLeftTalonID);
        backrightMotor = new TalonSRX(Constants.BackRightTalonID);

        distanceToWallSensor = new AnalogInput(Constants.DistanceToWallSensor);
        
        if (Robot.dLibrary.getDriveTrainType() == "Mecanum") {
            frontleftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
            frontrightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
            backleftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
            backrightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
        }
        else if (Robot.dLibrary.getDriveTrainType() == "Tank") {
            frontleftMotor.set(ControlMode.Follower, Constants.BackLeftTalonID);
            frontrightMotor.set(ControlMode.Follower, Constants.BackRightTalonID);

            backleftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
            backrightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
        }
        navx = new AHRS(SPI.Port.kMXP);
    }
    public void MecanumDrive(double yaxis, double xaxis, double zaxis, double gyroangle, double throttle) {
        yaxis = Robot.dLibrary.applyDeadband(Robot.dLibrary.limit(yaxis));
        xaxis = Robot.dLibrary.applyDeadband(Robot.dLibrary.limit(xaxis));

        processed = Robot.dLibrary.rotate(xaxis, yaxis, gyroangle);

        double[] wheelSpeeds = new double[5];
        wheelSpeeds[Constants.FrontLeftTalonID] = processed[0] + processed[1] + zaxis;
        wheelSpeeds[Constants.FrontRightTalonID] = processed[0] - processed[1] + zaxis;
        wheelSpeeds[Constants.BackLeftTalonID] = processed[0] + processed[1] + zaxis;
        wheelSpeeds[Constants.BackRightTalonID] = processed[0] + processed[1] - zaxis;

        Robot.dLibrary.normalize(wheelSpeeds);

        frontleftMotor.set(ControlMode.PercentOutput, (wheelSpeeds[Constants.FrontLeftTalonID]) * throttle);
        frontrightMotor.set(ControlMode.PercentOutput, ((wheelSpeeds[Constants.FrontRightTalonID]) * throttle) * m_rightSideInvertMultiplier);
        backleftMotor.set(ControlMode.PercentOutput, (wheelSpeeds[Constants.BackLeftTalonID]) * throttle);
        backrightMotor.set(ControlMode.PercentOutput, ((wheelSpeeds[Constants.BackRightTalonID]) * throttle) * m_rightSideInvertMultiplier);
    }
    public void TankDrive(double yaxis, double xaxis, double throttle) {
        double leftspeed = (yaxis + xaxis) * throttle;
        double rightspeed = (yaxis - xaxis) * throttle;

        backleftMotor.set(ControlMode.PercentOutput, leftspeed);
        backrightMotor.set(ControlMode.PercentOutput, rightspeed);
    }
    public void AutoDrive(double yaxis, double xaxis, double throttle) {
        double leftspeed = (yaxis + xaxis) * throttle;
        double rightspeed = (yaxis - xaxis) * throttle;

        frontleftMotor.set(ControlMode.PercentOutput, leftspeed);
        backleftMotor.set(ControlMode.PercentOutput, leftspeed);
        frontrightMotor.set(ControlMode.PercentOutput, rightspeed);
        backrightMotor.set(ControlMode.PercentOutput, rightspeed);
    }
    public void StopMotors() {
        if (Robot.dLibrary.getDriveTrainType() == "Mecanum") {
            MecanumDrive(0,0,0,0,0);
        }
        if (Robot.dLibrary.getDriveTrainType() == "Tank") {
            TankDrive(0,0,0);
        }
    }
    public double getDistanceToWall() { 
        double distanceToWall = (Constants.HIGHTTOTAPE - Constants.HIGHTTOCAMERA) / Math.tan(Constants.CAMERAANGLE + Robot.limeLightVision.getContourInfo("ts"));
        return distanceToWall;
    }
    public float getYaw() {
        return navx.getYaw();
    }
    public void resetGyro() {
        navx.reset();
    }
    public double getVoltage() {
        return distanceToWallSensor.getVoltage();
    }
    public void resetEncoders() {
        frontleftMotor.setSelectedSensorPosition(0,0,0);
        frontrightMotor.setSelectedSensorPosition(0,0,0);
        backleftMotor.setSelectedSensorPosition(0,0,0);
        backrightMotor.setSelectedSensorPosition(0,0,0);
    }
}