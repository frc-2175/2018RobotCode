package org.usfirst.frc.team2175;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;

public class SolenoidWrapper {
	private DoubleSolenoid doubleSolenoid;
    private Solenoid solenoid;
    private boolean isDouble;

    public SolenoidWrapper(int port) {
    	solenoid = new Solenoid(port);
    	isDouble = false;
    }
    
    public SolenoidWrapper(int port1, int port2) {
    	doubleSolenoid = new DoubleSolenoid(port1, port2);
    	isDouble = true;
    }

    public void set(final boolean on) {
        if (isDouble) {
            if (on) {
                doubleSolenoid.set(Value.kForward);
            } else {
                doubleSolenoid.set(Value.kReverse);
            }
        } else {
            solenoid.set(on);
        }
    }

    public boolean get() {
        if (isDouble) {
            if (doubleSolenoid.get() == Value.kForward) {
                return true;
            } else {
                return false;
            }
        } else {
            return solenoid.get();
        }
    }
}
