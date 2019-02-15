package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.Base.Constants;

public class Wrist {
    VictorSPX xTranslation;
    VictorSPX yTranslation;

    DigitalInput leftXLimit;
    DigitalInput rightXLimit;

    public Wrist() {
        xTranslation = new VictorSPX(Constants.WRIST_X_MOVER);
        yTranslation = new VictorSPX(Constants.WRIST_Y_MOVER);

        leftXLimit = new DigitalInput(Constants.WRIST_X_LIMIT);
        rightXLimit = new DigitalInput(Constants.WRIST_Y_LIMIT);
    }
    public boolean getLeft() { return leftXLimit.get();}
    public boolean getRight() { return rightXLimit.get();}
    public void setTranslation() {

    }
}
