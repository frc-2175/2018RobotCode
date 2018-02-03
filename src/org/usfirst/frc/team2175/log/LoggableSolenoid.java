package org.usfirst.frc.team2175.log;

import java.util.HashMap;

import org.usfirst.frc.team2175.SolenoidWrapper;

public class LoggableSolenoid implements Loggable {
	HashMap<String, Object> fields;
	private SolenoidWrapper solenoid;
	private String key;

	public LoggableSolenoid(String key, SolenoidWrapper solenoid) {
		fields = new HashMap<>();
		this.solenoid = solenoid;
		this.key = key;
	}

	@Override
	public String getLogType() {
		return "solenoid";
	}

	@Override
	public String getId() {
		return key;
	}

	@Override
	public HashMap<String, Object> getValues() {
		fields.put("value", solenoid.get());
		return fields;
	}
}
