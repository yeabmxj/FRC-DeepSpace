package frc.Base;

public class FSMBase {

    private static String currentState = "";
    private static String currentSubsystem = "";

    public static void setFSMSubsystem(String subsystem) {
        currentSubsystem = subsystem;
    }
    public static void setFSMState(String state) {
        currentState = state;
    }
    public static String[] getState() {
        return new String[]{currentSubsystem, currentState};
    }
}
