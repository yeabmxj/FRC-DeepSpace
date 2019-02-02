package frc.Base;

public class State {
    public int state = 0;
    public void update() {}
    public void setState(int s) { state = s;}

    private static String currentState = "";
    public static void setFSMState(String state) { currentState = state;}
    public String getFSMState() { return currentState;}
}