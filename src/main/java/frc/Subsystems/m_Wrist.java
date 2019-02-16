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
    private static int xdirection;
    private static int ydirection;

    public m_Wrist() {
        xTranslation = new VictorSPX(Constants.WRIST_X_MOVER);
        yTranslation = new VictorSPX(Constants.WRIST_Y_MOVER);

        leftXLimit = new DigitalInput(Constants.WRIST_X_LIMIT);
        rightXLimit = new DigitalInput(Constants.WRIST_Y_LIMIT);
    }
    public boolean getLeft() { return leftXLimit.get();}
    public boolean getRight() { return rightXLimit.get();}
    public void setxTranslation(double speed) { xTranslation.set(ControlMode.PercentOutput, speed);}
    public void setyTranslation(double speed) { yTranslation.set(ControlMode.PercentOutput, speed);}

    private static void speedSet(String message, int speedINDEX, int charINDEX) {
        Speeds[speedINDEX] = message.charAt(charINDEX);
    }

    public void updateSpeed() {
        if (Robot.i_wrist.getMessage().length() == 5) {
            speedSet(Robot.i_wrist.getMessage(), 0, 1);
            speedSet(Robot.i_wrist.getMessage(), 1, 4);
            String xSign = String.valueOf(Robot.i_wrist.getMessage().charAt(0));
            String ySign = String.valueOf(Robot.i_wrist.getMessage().charAt(3));
            xdirection = xSign.equals("+") ? 1 :
                         xSign.equals("-") ? -1 : 0;
            ydirection = ySign.equals("+") ? 1 :
                         ySign.equals("-") ? -1 : 0;
        }
        else {
            Speeds[0] = 0;
            Speeds[1] = 0;
        }
    }
    public void update() {
        switch (Robot.i_wrist.getFSMState()) {
            case "MOVING":
                updateSpeed();
                setxTranslation(xdirection * Speeds[0]);
                setyTranslation(ydirection * Speeds[1]);
                break;
            case "STOPPED":
                setxTranslation(0);
                setyTranslation(0);
                break;
        }
    }
}
