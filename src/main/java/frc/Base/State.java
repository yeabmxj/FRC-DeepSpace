package frc.Base;

public class State {
    public boolean INUSE = false;

    public int state = 0;

    public void update() {}
    public void setState(int s) { state = s;}

    private static String currentState = "";
    public static void setFSMState(String state) { currentState = state;}
    public String getFSMState() { return currentState;}

    private static String messageToBeSent = "";
    public static void setMessage(String message) { messageToBeSent = message;}
    public String getMessage() { return messageToBeSent;}

    private static String[] multiMessage;
    public static void setMultiMessage(String system, String message) {
        multiMessage[0] = system;
        multiMessage[1] = message;
    }
    public static String getMultiMessage(String find) {
        int MessageID = find.equals("SYSTEM") ? 0 : 1;
        return multiMessage[MessageID];
    }
}