package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI;

import frc.Base.Constants;
import frc.robot.*;

public class DriveTrain {

    public TalonSRX frontleftMotor;
    public TalonSRX frontrightMotor; 
    public TalonSRX backleftMotor; 
    public TalonSRX backrightMotor;

    public AnalogInput distanceToWallSensor;

    public AHRS navx;

    private double throttle = 0.5;

    public DriveTrain() {
        frontleftMotor = new TalonSRX(Constants.FrontLeftTalonID);
        frontrightMotor = new TalonSRX(Constants.FrontRightTalonID);
        backleftMotor = new TalonSRX(Constants.BackLeftTalonID);
        backrightMotor = new TalonSRX(Constants.BackRightTalonID);

        distanceToWallSensor = new AnalogInput(Constants.DistanceToWallSensor);
        
        frontleftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
        frontrightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
        backleftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
        backrightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
        
        navx = new AHRS(SPI.Port.kMXP);
    }
    public void MecanumDrive(double yaxis, double xaxis, double zaxis, double gyroangle, double throttle) {
        yaxis = Robot.dLibrary.applyDeadband(Robot.dLibrary.limit(yaxis));
        xaxis = Robot.dLibrary.applyDeadband(Robot.dLibrary.limit(xaxis));

        double[] processed = Robot.dLibrary.rotate(xaxis, yaxis, gyroangle);

        double[] wheelSpeeds = new double[5];
        wheelSpeeds[Constants.FrontLeftTalonID] = processed[0] + processed[1] + zaxis;
        wheelSpeeds[Constants.FrontRightTalonID] = processed[0] - processed[1] + zaxis;
        wheelSpeeds[Constants.BackLeftTalonID] = processed[0] + processed[1] + zaxis;
        wheelSpeeds[Constants.BackRightTalonID] = processed[0] + processed[1] - zaxis;

        Robot.dLibrary.normalize(wheelSpeeds);

        frontleftMotor.set(ControlMode.PercentOutput, (wheelSpeeds[Constants.FrontLeftTalonID]) * throttle);
        frontrightMotor.set(ControlMode.PercentOutput, ((wheelSpeeds[Constants.FrontRightTalonID]) * throttle) * -1);
        backleftMotor.set(ControlMode.PercentOutput, (wheelSpeeds[Constants.BackLeftTalonID]) * throttle);
        backrightMotor.set(ControlMode.PercentOutput, ((wheelSpeeds[Constants.BackRightTalonID]) * throttle) * -1);
    }
    public void TankDrive(double yaxis, double xaxis, double throttle) {
        double leftspeed = (yaxis + xaxis) * throttle;
        double rightspeed = (yaxis - xaxis) * throttle;

        frontleftMotor.set(ControlMode.PercentOutput, leftspeed);
        backleftMotor.set(ControlMode.PercentOutput, leftspeed);
        frontrightMotor.set(ControlMode.PercentOutput, rightspeed);
        backrightMotor.set(ControlMode.PercentOutput, rightspeed);
    }
    public void StopMotors() {
        if (Robot.dLibrary.getDriveTrainType().equals("Mecanum")) {
            MecanumDrive(0,0,0,0,0);
        }
        if (Robot.dLibrary.getDriveTrainType().equals("Tank")) {
            TankDrive(0,0,0);
        }
    }
    public double Throttle(double rawAxis3, double rawAxis2) {
        throttle += .005 * (rawAxis3 - rawAxis2);
        if (throttle > 1) {
            throttle = Math.signum(throttle);
        }
        if (throttle < 0) {
            throttle = 0;
        }
        return throttle;
    }
    public double getDistanceToWall() {
        return (Constants.HIGHTTOTAPE - Constants.HIGHTTOCAMERA) / Math.tan(Constants.CAMERAANGLE + Robot.limeLightVision.getContourInfo("ts"));
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
    public double getEncodervalues() {
        double backleftDist = backleftMotor.getSelectedSensorPosition(0);
        double backrightDist = backrightMotor.getSelectedSensorPosition(0);
        double frontleftDist = frontleftMotor.getSelectedSensorPosition(0);
        double frontrightDist = frontrightMotor.getSelectedSensorPosition(0);

        double frontAverage = (frontleftDist + frontrightDist) / 2;
        double backAverage = (backleftDist + backrightDist) / 2;

        return (frontAverage + backAverage) / 1440 * 6 * Math.PI / 12;
    }
    public void resetEncoders() {
        frontleftMotor.setSelectedSensorPosition(0,0,0);
        frontrightMotor.setSelectedSensorPosition(0,0,0);
        backleftMotor.setSelectedSensorPosition(0,0,0);
        backrightMotor.setSelectedSensorPosition(0,0,0);
    }
}