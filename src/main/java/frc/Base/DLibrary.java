package frc.Base;

import java.util.StringJoiner;

import frc.robot.Robot;

public class DLibrary{

    private String DriveTrainType = "";
    
    public void setDriveTrainType(String DriveType) {
        DriveTrainType = DriveType;
    }
    public String getDriveTrainType() {
        return DriveTrainType;
    }

    public double[] rotate(double x, double y, double gyroangle) {
        double cosA = Math.cos(gyroangle * (Math.PI / 180));
        double sinA = Math.sin(gyroangle * (Math.PI / 180));
        double[] out = new double[2];
        out[0] = x * cosA - y * sinA;
        out[1] = x * sinA + y * cosA;
        return out;
    }
    public void verify() {
        if (Robot.m_drivetrain.frontleftMotor != null && Robot.m_drivetrain.frontrightMotor != null && Robot.m_drivetrain.backleftMotor != null && Robot.m_drivetrain.backrightMotor != null) {
            return;
        }
        StringJoiner joiner = new StringJoiner(", ");
        if (Robot.m_drivetrain.frontleftMotor == null) {
            joiner.add("frontleftMotor");
        }
        if (Robot.m_drivetrain.frontrightMotor == null) {
            joiner.add("frontrightMotor");
        }
        if (Robot.m_drivetrain.backleftMotor == null) {
            joiner.add("backleftMotor");
        }
        if (Robot.m_drivetrain.backrightMotor == null) {
            joiner.add("backrightMotor");
        }
        throw new NullPointerException(joiner.toString());
    }
    public double limit(double value) {
        if (value > 1.0) {
            return 1.0;
          }
          if (value < -1.0) {
            return -1.0;
          }
          return value;
    }
    public double applyDeadband(double value) {
        if (Math.abs(value) > Constants.DEAD_BAND) {
          if (value > 0.0) {
            return (value - Constants.DEAD_BAND) / (1.0 - Constants.DEAD_BAND);
          } else {
            return (value + Constants.DEAD_BAND) / (1.0 - Constants.DEAD_BAND);
          }
        } else {
          return 0.0;
        }
      }
    public void normalizeMecanum(double[] wheelSpeeds) {
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
    // private double normalize(double value, double max, double min) {
    //     return ((value - min) / (max - min));
    // }

}