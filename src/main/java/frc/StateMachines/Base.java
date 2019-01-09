package frc.StateMachines;

public class Base {

    public static final int Stop = 0;
    public static final int Input = 1;
    public static final int Sandstorm = 2;
    public static final int Driving = 3;
    
    public int state = 0;
    public Base() {}
    protected void updateChildren() {}
    public void update() {}
    public void setState(int s) {
        state = s;
    }
}