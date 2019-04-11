package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.Base.Constants;
import frc.Base.Controls;
import frc.StateMachines.i_Drivetrain;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.AnalogInput;
public class m_Drivetrain {

    public TalonSRX frontleftMotor;
    public TalonSRX frontrightMotor; 
    public TalonSRX backleftMotor; 
    public TalonSRX backrightMotor;

//    public AnalogInput leftIRSensor;

    public m_Drivetrain() {
        frontleftMotor = new TalonSRX(Constants.FRONT_LEFT_TALON_ID);
        frontrightMotor = new TalonSRX(Constants.FRONT_RIGHT_TALON_ID);
        backleftMotor = new TalonSRX(Constants.BACK_LEFT_TALON_ID);
        backrightMotor = new TalonSRX(Constants.BACK_RIGHT_TALON_ID);

        //leftIRSensor = new AnalogInput(Constants.LEFT_IR_SENSOR_ID);
        //rightIRSensor = new AnalogInput(Constants.RIGHT_IR_SENSOR_ID);

        frontleftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 5);
        frontrightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 5);
        backleftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 5);
        backrightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 5);

    }
    public void MecanumDrive(double yaxis, double xaxis, double zaxis, double gyroangle, double throttle) {
        yaxis = Robot.b_drivetrain.applyDeadband(Robot.b_drivetrain.limit(yaxis));
        xaxis = Robot.b_drivetrain.applyDeadband(Robot.b_drivetrain.limit(xaxis));

        double[] processed = Robot.b_drivetrain.rotate(xaxis, yaxis, gyroangle);

        double[] wheelSpeeds = new double[5];
        wheelSpeeds[Constants.FRONT_LEFT_TALON_ID] = processed[0] + processed[1] + zaxis;
        wheelSpeeds[Constants.FRONT_RIGHT_TALON_ID] = processed[0] - processed[1] + zaxis;
        wheelSpeeds[Constants.BACK_LEFT_TALON_ID] = processed[0] + processed[1] + zaxis;
        wheelSpeeds[Constants.BACK_RIGHT_TALON_ID] = processed[0] + processed[1] - zaxis;

        Robot.b_drivetrain.normalizeMecanum(wheelSpeeds);

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
        return (Constants.HEIGHT_TO_TAPE - Constants.HEIGHT_TO_CAMERA) / Math.tan(Constants.CAMERA_ANGLE + Robot.e_limeLightVision.getContourInfo("ty"));
    }
    public double Voltage_to_Feet(double voltage) {
    	return voltage / Constants.VOLTAGE_TO_FEET_FACTOR;
    }
    public double getEncodervalues() {
        return frontrightMotor.getSelectedSensorPosition(0) / 1440 * 6 * Math.PI / 12;
    }
    public void resetEncoders() {
        frontleftMotor.setSelectedSensorPosition(0,0,0);
        frontrightMotor.setSelectedSensorPosition(0,0,0);
        backleftMotor.setSelectedSensorPosition(0,0,0);
        backrightMotor.setSelectedSensorPosition(0,0,0);
    }
    private void StopMotors() {
        frontrightMotor.set(ControlMode.PercentOutput, 0);
        frontleftMotor.set(ControlMode.PercentOutput, 0);
        backrightMotor.set(ControlMode.PercentOutput, 0);
        backleftMotor.set(ControlMode.PercentOutput, 0);
    }
    public void update() {
        switch (Robot.i_drivetrain.getFSMState()){
            case "Mecanum":
                Robot.m_drivetrain.MecanumDrive(Controls.getAxis(Controls.yAxis), Controls.getAxis(Controls.xAxis), Controls.getAxis(Controls.zAxis), Robot.e_navx.getYaw(), .5);
                break;
            case "Tank":
                Robot.m_drivetrain.TankDrive(Controls.getAxis(Controls.yAxis), Controls.getAxis(Controls.xAxis), Controls.getT());
                break;
            case "Stop":
                StopMotors();
                break;
        }
    }
}