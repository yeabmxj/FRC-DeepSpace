package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.AnalogInput;

import frc.Base.Constants;
import frc.Base.Controls;
import frc.StateMachines.*;
import frc.robot.*;

public class m_Drivetrain {

    public TalonSRX frontleftMotor;
    public TalonSRX frontrightMotor; 
    public TalonSRX backleftMotor; 
    public TalonSRX backrightMotor;

    public AnalogInput distanceToWallSensor;

    public m_Drivetrain() {
        frontleftMotor = new TalonSRX(Constants.FRONT_LEFT_TALON_ID);
        frontrightMotor = new TalonSRX(Constants.FRONT_RIGHT_TALON_ID);
        backleftMotor = new TalonSRX(Constants.BACK_LEFT_TALON_ID);
        backrightMotor = new TalonSRX(Constants.BACK_RIGHT_TALON_ID);

        distanceToWallSensor = new AnalogInput(Constants.DISTANCE_TO_WALL_SENSOR);
        
        frontleftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
        frontrightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
        backleftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
        backrightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);

    }
    public void MecanumDrive(double yaxis, double xaxis, double zaxis, double gyroangle, double throttle) {
        yaxis = Robot.dLibrary.applyDeadband(Robot.dLibrary.limit(yaxis));
        xaxis = Robot.dLibrary.applyDeadband(Robot.dLibrary.limit(xaxis));

        double[] processed = Robot.dLibrary.rotate(xaxis, yaxis, gyroangle);

        double[] wheelSpeeds = new double[5];
        wheelSpeeds[Constants.FRONT_LEFT_TALON_ID] = processed[0] + processed[1] + zaxis;
        wheelSpeeds[Constants.FRONT_RIGHT_TALON_ID] = processed[0] - processed[1] + zaxis;
        wheelSpeeds[Constants.BACK_LEFT_TALON_ID] = processed[0] + processed[1] + zaxis;
        wheelSpeeds[Constants.BACK_RIGHT_TALON_ID] = processed[0] + processed[1] - zaxis;

        Robot.dLibrary.normalizeMecanum(wheelSpeeds);

        frontleftMotor.set(ControlMode.PercentOutput, (wheelSpeeds[Constants.FRONT_LEFT_TALON_ID]) * throttle);
        frontrightMotor.set(ControlMode.PercentOutput, ((wheelSpeeds[Constants.FRONT_RIGHT_TALON_ID]) * throttle) * -1);
        backleftMotor.set(ControlMode.PercentOutput, (wheelSpeeds[Constants.BACK_LEFT_TALON_ID]) * throttle);
        backrightMotor.set(ControlMode.PercentOutput, ((wheelSpeeds[Constants.BACK_RIGHT_TALON_ID]) * throttle) * -1);
    }
    public void TankDrive(double yaxis, double xaxis, double throttle) {
        double leftspeed = (yaxis + xaxis) * throttle;
        double rightspeed = (yaxis - xaxis) * throttle;

        frontleftMotor.set(ControlMode.PercentOutput, leftspeed);
        backleftMotor.set(ControlMode.PercentOutput, leftspeed);
        frontrightMotor.set(ControlMode.PercentOutput, rightspeed);
        backrightMotor.set(ControlMode.PercentOutput, rightspeed);
    }
    public void TankLeft(double speed) {
        frontleftMotor.set(ControlMode.PercentOutput, speed);
        backleftMotor.set(ControlMode.PercentOutput, speed);
    }
    public void TankRight(double speed) {
        frontrightMotor.set(ControlMode.PercentOutput, speed);
        backrightMotor.set(ControlMode.PercentOutput, speed);
    }
    public double getDistanceToWall() {
        return (Constants.HEIGHT_TO_TAPE - Constants.HEIGHT_TO_CAMERA) / Math.tan(Constants.CAMERA_ANGLE + Robot.eLimeLightVision.getContourInfo("ty"));
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
    public void StopMotors() {
        frontrightMotor.set(ControlMode.PercentOutput, 0);
        frontleftMotor.set(ControlMode.PercentOutput, 0);
        backrightMotor.set(ControlMode.PercentOutput, 0);
        backleftMotor.set(ControlMode.PercentOutput, 0);
    }
    public void update() {
        switch (Robot.i_drivetrain.getFSMState()){
            case "Input":
                Robot.i_drivetrain.setState(i_Drivetrain.Input);
                break;
            case "Mecanum":
                Robot.m_drivetrain.MecanumDrive(Controls.getY(), Controls.getX(), Controls.getZ(), Robot.eNavx.getYaw(), .5);
                break;
            case "Tank":
                Robot.m_drivetrain.TankDrive(Controls.getY(), Controls.getX(), .5);
                break;
            case "Stop":
                StopMotors();
                break;
        }
    }
    public void updateAuto() {
        switch (Robot.i_auto.getFSMState()) {
            case "":
                Robot.i_auto.update();
                break;
            case "Manual":
                Robot.i_drivetrain.update();
                break;
        }
    }
}