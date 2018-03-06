package org.usfirst.frc.team2175;

import edu.wpi.first.wpilibj.AnalogInput;

public class UltrasonicWrapper {
	private AnalogInput ultrasonic;
	private double voltageToInches;

	// Voltage to inches is determined by color of sensor,
	// because we can't remember which color is a certain part number
	public UltrasonicWrapper(int channel, boolean isBlack) {
		ultrasonic = new AnalogInput(channel);
		voltageToInches = (isBlack) ? (0.009766 / 2) / 5.08 : 0.009766;
	}

	public double getDistance() {
		return ultrasonic.getVoltage() / voltageToInches;
	}
}
