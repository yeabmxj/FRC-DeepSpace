package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.Base.Constants;
import frc.robot.Robot;

public class m_Wrist {
    private VictorSPX xTranslation;
    private VictorSPX yTranslation;

    private DigitalInput leftXLimit;
    private DigitalInput rightXLimit;

    private static double[] Speeds;
    private static int XDirection;
    private static int YDirection;
    private static int flip;

    public m_Wrist() {
        xTranslation = new VictorSPX(Constants.WRIST_X_MOVER);
        yTranslation = new VictorSPX(Constants.WRIST_Y_MOVER);

        leftXLimit = new DigitalInput(Constants.WRIST_X_LIMIT);
        rightXLimit = new DigitalInput(Constants.WRIST_Y_LIMIT);
    }
    public void SetTranslation(double xSpeed, double ySpeed) {
        xTranslation.set(ControlMode.PercentOutput, xSpeed);
        yTranslation.set(ControlMode.PercentOutput, ySpeed);
    }
    public boolean GetPressed(String side) {
        return side.equals("Left") ? leftXLimit.get() :
                side.equals("Right") ? rightXLimit.get() : false;
    }
    public void WristSpeedParse(String message, int speedINDEX, int charINDEX) {
        Speeds[speedINDEX] = message.charAt(charINDEX);
    }

    public void updateSpeed() {
        if (Robot.i_wrist.getMessage().length() == 5) {
            WristSpeedParse(Robot.i_wrist.getMessage(), 0, 1);
            WristSpeedParse(Robot.i_wrist.getMessage(), 1, 4);
            String xSign = String.valueOf(Robot.i_wrist.getMessage().charAt(0));
            String ySign = String.valueOf(Robot.i_wrist.getMessage().charAt(3));
            XDirection = xSign.equals("+") ? 1 :
                    xSign.equals("-") ? -1 : 0;
            YDirection = ySign.equals("+") ? 1 :
                    ySign.equals("-") ? -1 : 0;
            Speeds[0] = Speeds[0] == 5 ? Speeds[0] / 10 : Speeds[0];
            Speeds[1] = Speeds[1] == 5 ? Speeds[1] / 10 : Speeds[1];
        } else {
            Speeds[0] = 0;
            Speeds[1] = 0;
        }
    }
    public void update() {
        switch (Robot.i_wrist.getFSMState()) {
            case "MOVING":
                SetTranslation(
                    (!GetPressed("Left") || !GetPressed("Right") ? 1 : 0) * XDirection * Speeds[0],
                    YDirection * Speeds[1]
                );
                break;
            case "STOPPED":
                SetTranslation(0,0);
                break;
        }
    }
}
