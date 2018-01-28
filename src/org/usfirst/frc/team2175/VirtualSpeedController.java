package org.usfirst.frc.team2175;

import edu.wpi.first.wpilibj.SpeedController;

public class VirtualSpeedController implements SpeedController {
	private double output;
	@Override
	public void pidWrite(double output) {}

	@Override
	public void set(double speed) {
		output = speed;
	}

	@Override
	public double get() {
		return output;
	}

	@Override
	public void setInverted(boolean isInverted) {}

	@Override
	public boolean getInverted() {
		return false;
	}

	@Override
	public void disable() {}

	@Override
	public void stopMotor() {}
	
}
