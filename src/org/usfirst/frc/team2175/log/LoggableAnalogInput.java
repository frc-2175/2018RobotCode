package org.usfirst.frc.team2175.log;

import java.util.HashMap;

import edu.wpi.first.wpilibj.AnalogInput;

public class LoggableAnalogInput implements Loggable {
	HashMap<String, Object> fields;
	private AnalogInput analogInput;
	private String key;

	public LoggableAnalogInput(String key, AnalogInput analogInput) {
		fields = new HashMap<>();
		this.analogInput = analogInput;
		this.key = key;
	}

	@Override
	public String getLogType() {
		return "analog-input";
	}

	@Override
	public String getId() {
		return key;
	}

	@Override
	public HashMap<String, Object> getValues() {
		fields.put("voltage", analogInput.getVoltage());
		// Equation from http://www.revrobotics.com/content/docs/REV-11-1107-DS.pdf
		fields.put("psi", 250 * analogInput.getVoltage() / 5 - 25);
		return fields;
	}

}
