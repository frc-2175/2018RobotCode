package org.usfirst.frc.team2175.log;

import java.util.HashMap;

import org.usfirst.frc.team2175.UltrasonicWrapper;

public class LoggableUltrasonic implements Loggable {

	HashMap<String, Object> fields;
	private UltrasonicWrapper ultrasonic;
	private String key;

	public LoggableUltrasonic(String key, UltrasonicWrapper ultrasonic) {
		fields = new HashMap<>();
		this.ultrasonic = ultrasonic;
		this.key = key;
	}

	@Override
	public String getLogType() {
		return "ultrasonic";
	}

	@Override
	public String getId() {
		return key;
	}

	@Override
	public HashMap<String, Object> getValues() {
		fields.put("inches", ultrasonic.getDistance());
		return fields;
	}

}
