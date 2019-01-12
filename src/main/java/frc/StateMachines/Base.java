package frc.StateMachines;

public class Base {
    public int state = 0;
    public Base() {}
    protected void updateChildren() {}
    public void update() {}
    public void setState(int s) { state = s;}
}