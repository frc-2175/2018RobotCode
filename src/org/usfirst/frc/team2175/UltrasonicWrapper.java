package org.usfirst.frc.team2175;

import edu.wpi.first.wpilibj.AnalogInput;

public class UltrasonicWrapper {
	private AnalogInput ultrasonic;
	private double voltageToInches;

	public UltrasonicWrapper(int channel, boolean isHRLS) {
		ultrasonic = new AnalogInput(channel);
		voltageToInches = (isHRLS) ? (0.009766 / 2) / 5.08 : 0.009766;
	}

	public double getDistance() {
		return ultrasonic.getVoltage() / voltageToInches;
	}
}
